package committee.nova.toggledpickup.mixin;

import com.mojang.authlib.GameProfile;
import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player implements ExtendedServerPlayer {
    @Shadow
    public abstract void displayClientMessage(Component component, boolean bl);

    @Shadow
    public abstract void playNotifySound(SoundEvent soundEvent, SoundSource soundSource, float f, float g);

    @Unique
    private boolean toggledPickup$autoPickup = true;

    @Unique
    private boolean toggledPickup$manuallyPickingUp = false;

    public MixinServerPlayer(Level level, BlockPos blockPos, float f, GameProfile gameProfile, @Nullable ProfilePublicKey profilePublicKey) {
        super(level, blockPos, f, gameProfile, profilePublicKey);
    }

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
                Component.translatable(
                        String.format(
                                "msg.toggledpickup.status.%s",
                                autoPickup ? "on" : "off"
                        )
                ).withStyle(autoPickup ? ChatFormatting.GREEN : ChatFormatting.RED),
                true
        );
        playNotifySound(
                autoPickup ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                SoundSource.MASTER,
                1.0F, 1.0F
        );
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void inject$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        this.toggledPickup$autoPickup = tag.getBoolean("autoPickup");
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void inject$addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean("autoPickup", this.toggledPickup$autoPickup);
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void inject$restoreFrom(ServerPlayer serverPlayer, boolean bl, CallbackInfo ci) {
        this.toggledPickup$setAutoPickup(((ExtendedServerPlayer) serverPlayer).toggledPickup$isAutoPickup());
    }
}
