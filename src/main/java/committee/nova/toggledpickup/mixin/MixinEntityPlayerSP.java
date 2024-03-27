package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.client.keybinding.KeyBindings;
import committee.nova.toggledpickup.network.handler.NetworkHandler;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {
    @Inject(method = "onLivingUpdate", at = @At("TAIL"))
    private void inject$tick(CallbackInfo ci) {
        final boolean manually = KeyBindings.manuallyPickup.isKeyDown();
        final ManuallyMessage msg = new ManuallyMessage();
        msg.writeData(manually);
        NetworkHandler.instance.sendToServer(msg);
    }
}
