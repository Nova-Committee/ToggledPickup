package committee.nova.toggledpickup.network.handler;

import committee.nova.toggledpickup.ToggledPickup;
import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkHandler {
    public static final ResourceLocation TOGGLE = new ResourceLocation(ToggledPickup.MODID, "toggle");

    public static final ResourceLocation MANUALLY = new ResourceLocation(ToggledPickup.MODID, "manually");

    @SubscribeEvent
    public static void registerPayload(RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(ToggledPickup.MODID);
        registrar
                .play(TOGGLE, ToggleMessage::new, h -> h
                        .server((d, ctx) -> ctx.workHandler().submitAsync(() -> ctx.player().ifPresent(p -> {
                            final ExtendedServerPlayer extended = (ExtendedServerPlayer) p;
                            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
                        })).exceptionally(e -> {
                            ToggledPickup.LOGGER.error("Error occurred while handling 'toggle' message for toggledpickup", e);
                            return null;
                        }))
                )
                .play(MANUALLY, ManuallyMessage::new, h -> h
                        .server((d, ctx) -> ctx.workHandler().submitAsync(() -> ctx.player().ifPresent(p -> {
                            final ExtendedServerPlayer extended = (ExtendedServerPlayer) p;
                            extended.toggledpickup$setManuallyPickingUp(d.isPressed());
                        })).exceptionally(e -> {
                            ToggledPickup.LOGGER.error("Error occurred while handling 'manually' message for toggledpickup", e);
                            return null;
                        }))
                );
    }
}
