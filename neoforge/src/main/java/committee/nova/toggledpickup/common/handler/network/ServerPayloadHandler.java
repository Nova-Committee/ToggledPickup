package committee.nova.toggledpickup.common.handler.network;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {
    public static void handleManually(ManuallyMessage msg, IPayloadContext ctx) {
        final ExtendedServerPlayer sp = (ExtendedServerPlayer) ctx.player();
        sp.toggledpickup$setManuallyPickingUp(msg.pressed());
    }

    public static void handleToggle(ToggleMessage msg, IPayloadContext ctx) {
        final ExtendedServerPlayer sp = (ExtendedServerPlayer) ctx.player();
        sp.toggledPickup$setAutoPickup(!sp.toggledPickup$isAutoPickup());
    }
}
