package committee.nova.toggledpickup;

import committee.nova.toggledpickup.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ToggledPickup.MODID, useMetadata = true)
public class ToggledPickup {
    public static final String MODID = "toggledpickup";

    @SidedProxy(
            clientSide = "committee.nova.toggledpickup.proxy.ClientProxy",
            serverSide = "committee.nova.toggledpickup.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
