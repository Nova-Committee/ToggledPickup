package committee.nova.toggledpickup.client;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientForgeEventHandler {
    @SubscribeEvent
    public static void onInput(InputEvent.Key event) {
        if (event.getKey() == KeyMappings.toggleAutoPickup.getKey().getValue() && KeyMappings.toggleAutoPickup.isDown()) {
            NetworkHandler.TOGGLE.send(PacketDistributor.SERVER.noArg(), new ToggleMessage());
        }
    }
}
