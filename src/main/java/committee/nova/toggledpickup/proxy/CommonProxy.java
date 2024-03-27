package committee.nova.toggledpickup.proxy;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.init();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
