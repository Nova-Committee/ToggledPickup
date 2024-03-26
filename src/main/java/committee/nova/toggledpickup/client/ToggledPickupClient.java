package committee.nova.toggledpickup.client;

import com.mojang.blaze3d.platform.InputConstants;
import committee.nova.toggledpickup.common.ToggledPickup;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.KeyMapping;

public class ToggledPickupClient implements ClientModInitializer {
    public static final KeyMapping toggleAutoPickup = new KeyMapping(
            "key.toggledpickup.toggle",
            InputConstants.Type.KEYSYM, InputConstants.KEY_GRAVE,
            "category.toggledpickup"
    );

    public static final KeyMapping manuallyPickup = new KeyMapping(
            "key.toggledpickup.manually",
            InputConstants.Type.KEYSYM, -1,
            "category.toggledpickup"
    );

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(toggleAutoPickup);
        KeyBindingHelper.registerKeyBinding(manuallyPickup);
        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            boolean pressed = false;
            while (toggleAutoPickup.consumeClick()) pressed = true;
            if (!pressed) return;
            ClientPlayNetworking.send(ToggledPickup.TOGGLE, PacketByteBufs.create());
        });
    }
}
