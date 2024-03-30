package committee.nova.toggledpickup.mixin;

import com.mojang.authlib.GameProfile;
import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayer extends PlayerEntity implements ExtendedServerPlayer {

    public MixinServerPlayer(World w, BlockPos p, float f, GameProfile g) {
        super(w, p, f, g);
    }
    @Unique
    private boolean toggledPickup$autoPickup = true;

    @Unique
    private boolean toggledPickup$manuallyPickingUp = false;

    @Override
    public boolean toggledpickup$isManuallyPickingUp() {
        return toggledPickup$manuallyPickingUp;
    }

    @Override
    public void toggledpickup$setManuallyPickingUp(boolean manually) {
        this.toggledPickup$manuallyPickingUp = manually;
    }

    @Override
    public boolean toggledPickup$isAutoPickup() {
        return this.toggledPickup$autoPickup;
    }

    @Override
    public void toggledPickup$setAutoPickup(boolean autoPickup, boolean notify) {
        this.toggledPickup$autoPickup = autoPickup;
        if (!notify) return;
        displayClientMessage(
                new TranslationTextComponent(
                        String.format(
                                "msg.toggledpickup.status.%s",
                                autoPickup ? "on" : "off"
                        )
                ).withStyle(autoPickup ? TextFormatting.GREEN : TextFormatting.RED),
                true
        );
        playNotifySound(
                autoPickup ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                SoundCategory.MASTER,
                1.0F, 1.0F
        );
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void inject$readAdditionalSaveData(CompoundNBT tag, CallbackInfo ci) {
        this.toggledPickup$autoPickup = tag.getBoolean("autoPickup");
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void inject$addAdditionalSaveData(CompoundNBT tag, CallbackInfo ci) {
        tag.putBoolean("autoPickup", this.toggledPickup$autoPickup);
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void inject$restoreFrom(ServerPlayerEntity serverPlayer, boolean bl, CallbackInfo ci) {
        this.toggledPickup$setAutoPickup(((ExtendedServerPlayer) serverPlayer).toggledPickup$isAutoPickup(), false);
    }
}
