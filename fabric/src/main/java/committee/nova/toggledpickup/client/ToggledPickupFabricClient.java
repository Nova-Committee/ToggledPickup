package committee.nova.toggledpickup.client;

import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static committee.nova.toggledpickup.client.KeyMappings.*;

public class ToggledPickupFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyMappingHelper.registerKeyMapping(toggleAutoPickup.get());
        KeyMappingHelper.registerKeyMapping(manuallyPickup.get());
        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            boolean pressed = false;
            while (toggleAutoPickup.get().consumeClick()) pressed = true;
            if (!pressed) return;
            ClientPlayNetworking.send(ToggleMessage.INSTANCE);
        });
    }
}
