package committee.nova.toggledpickup.client.event;

import committee.nova.toggledpickup.client.keybinding.KeyBindings;
import committee.nova.toggledpickup.network.handler.NetworkHandler;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class FMLClientEventHandler {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.toggleAutoPickup.isKeyDown()) NetworkHandler.instance.sendToServer(new ToggleMessage());
    }
}
