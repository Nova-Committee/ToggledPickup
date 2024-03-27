package committee.nova.toggledpickup.proxy;

import committee.nova.toggledpickup.client.keybinding.KeyBindings;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ClientRegistry.registerKeyBinding(KeyBindings.toggleAutoPickup);
        ClientRegistry.registerKeyBinding(KeyBindings.manuallyPickup);
    }
}
