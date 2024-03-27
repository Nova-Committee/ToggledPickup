package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity {
    @Inject(method = "playerTouch", at = @At("HEAD"), cancellable = true)
    private void inject$playerTouch(PlayerEntity player, CallbackInfo ci) {
        if (!(player instanceof ServerPlayerEntity)) return;
        final ExtendedServerPlayer extended = (ExtendedServerPlayer) player;
        if (!extended.toggledPickup$isAutoPickup() && !extended.toggledpickup$isManuallyPickingUp()) ci.cancel();
    }
}
