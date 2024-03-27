package committee.nova.toggledpickup;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(ToggledPickup.MODID)
public class ToggledPickup {
    public static final String MODID = "toggledpickup";
    public static final ResourceLocation TOGGLE = new ResourceLocation(MODID, "toggle");
    public static final ResourceLocation MANUALLY = new ResourceLocation(MODID, "manually");

    public ToggledPickup() {
        NetworkHandler.registerMessage();
    }
}
