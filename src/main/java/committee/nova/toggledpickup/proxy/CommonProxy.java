package committee.nova.toggledpickup.proxy;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.init();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
