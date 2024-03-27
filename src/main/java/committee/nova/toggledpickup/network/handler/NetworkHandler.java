package committee.nova.toggledpickup.network.handler;

import committee.nova.toggledpickup.ToggledPickup;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel TOGGLE;
    public static SimpleChannel MANUALLY;

    public static void registerMessage() {
        TOGGLE = ChannelBuilder.named(new ResourceLocation(ToggledPickup.MODID, "toggle"))
                .optional()
                .simpleChannel();
        TOGGLE.messageBuilder(ToggleMessage.class, 0)
                .encoder(ToggleMessage::toBytes)
                .decoder(ToggleMessage::new)
                .consumerMainThread(ToggleMessage::handler)
                .add();
        MANUALLY = ChannelBuilder.named(new ResourceLocation(ToggledPickup.MODID, "manually"))
                .optional()
                .simpleChannel();
        MANUALLY.messageBuilder(ManuallyMessage.class, 0)
                .encoder(ManuallyMessage::toBytes)
                .decoder(ManuallyMessage::new)
                .consumerMainThread(ManuallyMessage::handler)
                .add();
    }
}
