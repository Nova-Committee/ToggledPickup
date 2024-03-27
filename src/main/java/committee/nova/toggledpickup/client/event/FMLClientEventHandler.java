package committee.nova.toggledpickup.client.event;

import committee.nova.toggledpickup.client.keybinding.KeyBindings;
import committee.nova.toggledpickup.network.handler.NetworkHandler;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class FMLClientEventHandler {
    public static void init() {
        FMLCommonHandler.instance().bus().register(new FMLClientEventHandler());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.toggleAutoPickup.getIsKeyPressed()) NetworkHandler.instance.sendToServer(new ToggleMessage());
    }
}
