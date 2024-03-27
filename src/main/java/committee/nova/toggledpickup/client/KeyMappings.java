package committee.nova.toggledpickup.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyMappings {
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

    @SubscribeEvent
    public static void onRegisterKey(RegisterKeyMappingsEvent event) {
        event.register(toggleAutoPickup);
        event.register(manuallyPickup);
    }
}
