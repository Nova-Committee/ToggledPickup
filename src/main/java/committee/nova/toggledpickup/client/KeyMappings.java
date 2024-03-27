package committee.nova.toggledpickup.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyMappings {
    public static final KeyBinding toggleAutoPickup = new KeyBinding(
            "key.toggledpickup.toggle",
            InputMappings.Type.KEYSYM, 41,
            "category.toggledpickup"
    );

    public static final KeyBinding manuallyPickup = new KeyBinding(
            "key.toggledpickup.manually",
            InputMappings.Type.KEYSYM, -1,
            "category.toggledpickup"
    );

    @SubscribeEvent
    public static void onRegisterKey(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(toggleAutoPickup);
        ClientRegistry.registerKeyBinding(manuallyPickup);
    }
}
