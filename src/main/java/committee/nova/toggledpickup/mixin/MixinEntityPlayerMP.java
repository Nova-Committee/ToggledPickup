package committee.nova.toggledpickup.mixin;

import com.mojang.authlib.GameProfile;
import committee.nova.toggledpickup.api.ExtendedEntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP extends EntityPlayer implements ExtendedEntityPlayerMP {
    @Shadow
    public NetHandlerPlayServer connection;

    @Shadow
    public abstract void sendStatusMessage(ITextComponent chatComponent, boolean actionBar);

    @Unique
    private boolean toggledPickup$autoPickup = true;

    @Unique
    private boolean toggledPickup$manuallyPickingUp = false;

    public MixinEntityPlayerMP(World w, GameProfile g) {
        super(w, g);
    }


    @Override
    public boolean toggledPickup$isAutoPickup() {
        return this.toggledPickup$autoPickup;
    }

    @Override
    public void toggledPickup$setAutoPickup(boolean autoPickup, boolean notify) {
        this.toggledPickup$autoPickup = autoPickup;
        if (!notify) return;
        this.connection.sendPacket(
                new SPacketSoundEffect(
                        autoPickup ? SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF,
                        SoundCategory.MASTER, posX, posY, posZ, 1.0F, 1.0F
                ));
        this.sendStatusMessage(new TextComponentTranslation(String.format(
                "msg.toggledpickup.auto.%s",
                autoPickup ? "on" : "off"
        )).setStyle(new Style().setColor(autoPickup ? TextFormatting.GREEN : TextFormatting.RED)), true);
    }

    @Override
    public boolean toggledpickup$isManuallyPickingUp() {
        return this.toggledPickup$manuallyPickingUp;
    }

    @Override
    public void toggledpickup$setManuallyPickingUp(boolean manually) {
        final boolean wasManually = this.toggledPickup$manuallyPickingUp;
        this.toggledPickup$manuallyPickingUp = manually;
        if (manually != wasManually) sendStatusMessage(new TextComponentTranslation(
                String.format("msg.toggledpickup.manually.%s", manually ? "on" : "off")
        ).setStyle(new Style().setColor(manually ? TextFormatting.AQUA : TextFormatting.YELLOW)), true);
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void inject$readAdditionalSaveData(NBTTagCompound tag, CallbackInfo ci) {
        this.toggledPickup$autoPickup = tag.getBoolean("autoPickup");
    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    private void inject$addAdditionalSaveData(NBTTagCompound tag, CallbackInfo ci) {
        tag.setBoolean("autoPickup", this.toggledPickup$autoPickup);
    }

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void inject$restoreFrom(EntityPlayerMP that, boolean keepEverything, CallbackInfo ci) {
        if (!(that instanceof EntityPlayerMP)) return;
        this.toggledPickup$setAutoPickup(((ExtendedEntityPlayerMP) that).toggledPickup$isAutoPickup());
    }
}
