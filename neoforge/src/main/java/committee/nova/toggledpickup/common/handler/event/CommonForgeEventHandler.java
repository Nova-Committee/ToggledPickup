package committee.nova.toggledpickup.common.handler.event;

import committee.nova.toggledpickup.Constants;
import committee.nova.toggledpickup.common.handler.network.ServerPayloadHandler;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber
public class CommonForgeEventHandler {
    @SubscribeEvent
    public static void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
        final var registrar = event.registrar(Constants.MOD_ID).versioned("1.0.0");
        registrar.playToServer(ManuallyMessage.TYPE, ManuallyMessage.STREAM_CODEC, ServerPayloadHandler::handleManually);
        registrar.playToServer(ToggleMessage.TYPE, ToggleMessage.STREAM_CODEC, ServerPayloadHandler::handleToggle);
    }
}
