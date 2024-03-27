package committee.nova.toggledpickup;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(ToggledPickup.MODID)
public class ToggledPickup {
    public static final String MODID = "toggledpickup";

    public ToggledPickup() {
        NetworkHandler.registerMessage();
    }
}
