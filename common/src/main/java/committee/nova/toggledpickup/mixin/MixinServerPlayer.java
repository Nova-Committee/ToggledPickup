package committee.nova.toggledpickup.mixin;

import com.mojang.authlib.GameProfile;
import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player implements ExtendedServerPlayer {

    public MixinServerPlayer(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
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
        sendOverlayMessage(
                Component.translatable(
                        String.format(
                                "msg.toggledpickup.status.%s",
                                autoPickup ? "on" : "off"
                        )
                ).withStyle(autoPickup ? ChatFormatting.GREEN : ChatFormatting.RED)
        );
        playSound(
                autoPickup ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                1.0F, 1.0F
        ); // TODO: Check
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void inject$readAdditionalSaveData(ValueInput input, CallbackInfo ci) {
        this.toggledPickup$autoPickup = input.getBooleanOr("autoPickup", true);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void inject$addAdditionalSaveData(ValueOutput output, CallbackInfo ci) {
        output.putBoolean("autoPickup", this.toggledPickup$autoPickup);
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void inject$restoreFrom(ServerPlayer oldPlayer, boolean restoreAll, CallbackInfo ci) {
        this.toggledPickup$setAutoPickup(((ExtendedServerPlayer) oldPlayer).toggledPickup$isAutoPickup(), false);
    }
}
