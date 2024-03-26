package committee.nova.toggledpickup.common;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class ToggledPickup implements ModInitializer {
    public static final String MODID = "toggledpickup";
    public static final ResourceLocation TOGGLE = new ResourceLocation(MODID, "toggle");
    public static final ResourceLocation MANUALLY = new ResourceLocation(MODID, "manually");

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(TOGGLE, (server, player, handler, buf, responseSender) -> {
            final ExtendedServerPlayer extended = (ExtendedServerPlayer) player;
            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
        });
        ServerPlayNetworking.registerGlobalReceiver(MANUALLY, (server, player, handler, buf, responseSender) -> {
            ((ExtendedServerPlayer) player).toggledpickup$setManuallyPickingUp(buf.readBoolean());
        });
    }
}
