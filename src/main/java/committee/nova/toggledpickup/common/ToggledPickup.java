package committee.nova.toggledpickup.common;

import committee.nova.toggledpickup.api.ICanToggleAutoPickup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class ToggledPickup implements ModInitializer {
    public static final ResourceLocation TOGGLE = new ResourceLocation("toggledpickup", "toggle");

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(TOGGLE, (server, player, handler, buf, responseSender) -> {
            final ICanToggleAutoPickup extended = (ICanToggleAutoPickup) player;
            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
        });
    }
}
