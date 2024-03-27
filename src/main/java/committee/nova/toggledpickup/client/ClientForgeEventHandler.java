package committee.nova.toggledpickup.client;

import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientForgeEventHandler {
    @SubscribeEvent
    public static void onInput(InputEvent.Key event) {
        final ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) return;
        if (event.getKey() == KeyMappings.toggleAutoPickup.getKey().getValue() && KeyMappings.toggleAutoPickup.isDown()) {
            connection.send(new ToggleMessage());
        }
    }
}
