package committee.nova.toggledpickup.client.handler.event;

import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import static committee.nova.toggledpickup.client.KeyMappings.*;

@EventBusSubscriber(Dist.CLIENT)
public class ClientForgeEventHandler {
    @SubscribeEvent
    public static void onInput(InputEvent.Key event) {
        final ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) return;
        if (event.getKey() == toggleAutoPickup.get().getKey().getValue() && toggleAutoPickup.get().isDown()) {
            connection.send(new ToggleMessage());
        }
    }

    @SubscribeEvent
    public static void onRegisterKey(RegisterKeyMappingsEvent event) {
        event.registerCategory(TOGGLED_PICKUP_CATEGORY);
        event.register(toggleAutoPickup.get());
        event.register(manuallyPickup.get());
    }
}
