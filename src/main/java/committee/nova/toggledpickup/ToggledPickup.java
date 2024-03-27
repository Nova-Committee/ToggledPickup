package committee.nova.toggledpickup;

import committee.nova.toggledpickup.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
