package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedEntityPlayerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityItem.class)
public abstract class MixinEntityItem {
    @Inject(method = "onCollideWithPlayer", at = @At("HEAD"), cancellable = true)
    private void inject$onCollideWithPlayer(EntityPlayer player, CallbackInfo ci) {
        if (!(player instanceof EntityPlayerMP)) return;
        final ExtendedEntityPlayerMP extended = (ExtendedEntityPlayerMP) player;
        if (!extended.toggledPickup$isAutoPickup() && !extended.toggledpickup$isManuallyPickingUp()) ci.cancel();
    }
}
