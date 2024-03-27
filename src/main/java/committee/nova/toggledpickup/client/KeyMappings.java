package committee.nova.toggledpickup.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
    public static void onRegisterKey(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(toggleAutoPickup);
        ClientRegistry.registerKeyBinding(manuallyPickup);
    }
}
