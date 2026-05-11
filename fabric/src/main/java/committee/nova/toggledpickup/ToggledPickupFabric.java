package committee.nova.toggledpickup;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ToggledPickupFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonClass.init();
        PayloadTypeRegistry.serverboundPlay().register(ManuallyMessage.TYPE, ManuallyMessage.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ToggleMessage.TYPE, ToggleMessage.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ToggleMessage.TYPE, (payload, ctx) -> {
            final ExtendedServerPlayer extended = (ExtendedServerPlayer) ctx.player();
            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
        });
        ServerPlayNetworking.registerGlobalReceiver(ManuallyMessage.TYPE, (payload, ctx) -> {
            ((ExtendedServerPlayer) ctx.player()).toggledpickup$setManuallyPickingUp(payload.pressed());
        });
    }
}
