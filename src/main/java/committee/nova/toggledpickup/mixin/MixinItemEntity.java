package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ICanToggleAutoPickup;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity {
    @Inject(method = "playerTouch", at = @At("HEAD"), cancellable = true)
    private void inject$playerTouch(Player player, CallbackInfo ci) {
        if (!(player instanceof ServerPlayer sp)) return;
        if (!((ICanToggleAutoPickup) sp).toggledPickup$isAutoPickup()) ci.cancel();
    }
}
