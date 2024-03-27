package committee.nova.toggledpickup.proxy;

import committee.nova.toggledpickup.client.event.FMLClientEventHandler;
import committee.nova.toggledpickup.client.keybinding.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        FMLClientEventHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ClientRegistry.registerKeyBinding(KeyBindings.toggleAutoPickup);
        ClientRegistry.registerKeyBinding(KeyBindings.manuallyPickup);
    }
}
