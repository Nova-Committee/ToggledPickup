package committee.nova.toggledpickup.mixin;

import com.mojang.authlib.GameProfile;
import committee.nova.toggledpickup.api.ExtendedEntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
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
    public NetHandlerPlayServer playerNetServerHandler;

    @Shadow
    public abstract void addChatComponentMessage(IChatComponent p_146105_1_);

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
        this.playerNetServerHandler.sendPacket(new S29PacketSoundEffect("random.click", posX, posY, posZ, 1.0F, 1.0F));
        this.addChatComponentMessage(new ChatComponentTranslation(String.format(
                "msg.toggledpickup.auto.%s",
                autoPickup ? "on" : "off"
        )).setChatStyle(new ChatStyle().setColor(autoPickup ? EnumChatFormatting.GREEN : EnumChatFormatting.RED)));
    }

    @Override
    public boolean toggledpickup$isManuallyPickingUp() {
        return this.toggledPickup$manuallyPickingUp;
    }

    @Override
    public void toggledpickup$setManuallyPickingUp(boolean manually) {
        final boolean wasManually = this.toggledPickup$manuallyPickingUp;
        this.toggledPickup$manuallyPickingUp = manually;
        if (manually != wasManually) addChatComponentMessage(new ChatComponentTranslation(
                String.format("msg.toggledpickup.manually.%s", manually ? "on" : "off")
        ).setChatStyle(new ChatStyle().setColor(manually ? EnumChatFormatting.AQUA : EnumChatFormatting.YELLOW)));
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void inject$readAdditionalSaveData(NBTTagCompound tag, CallbackInfo ci) {
        this.toggledPickup$autoPickup = tag.getBoolean("autoPickup");
    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    private void inject$addAdditionalSaveData(NBTTagCompound tag, CallbackInfo ci) {
        tag.setBoolean("autoPickup", this.toggledPickup$autoPickup);
    }

    @Inject(method = "clonePlayer", at = @At("TAIL"))
    private void inject$restoreFrom(EntityPlayer player, boolean bl, CallbackInfo ci) {
        if (!(player instanceof EntityPlayerMP)) return;
        this.toggledPickup$setAutoPickup(((ExtendedEntityPlayerMP) player).toggledPickup$isAutoPickup(), false);
    }
}
