package committee.nova.toggledpickup.network.handler;

import committee.nova.toggledpickup.ToggledPickup;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel TOGGLE;
    public static SimpleChannel MANUALLY;

    public static void registerMessage() {
        TOGGLE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ToggledPickup.MODID, "toggle"))
                .clientAcceptedVersions(NetworkRegistry.ACCEPTVANILLA::equals)
                .serverAcceptedVersions(NetworkRegistry.ACCEPTVANILLA::equals)
                .simpleChannel();
        TOGGLE.messageBuilder(ToggleMessage.class, 0)
                .encoder(ToggleMessage::toBytes)
                .decoder(ToggleMessage::new)
                .consumer(ToggleMessage::handler)
                .add();
        MANUALLY = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ToggledPickup.MODID, "manually"))
                .clientAcceptedVersions(NetworkRegistry.ACCEPTVANILLA::equals)
                .serverAcceptedVersions(NetworkRegistry.ACCEPTVANILLA::equals)
                .simpleChannel();
        MANUALLY.messageBuilder(ManuallyMessage.class, 0)
                .encoder(ManuallyMessage::toBytes)
                .decoder(ManuallyMessage::new)
                .consumer(ManuallyMessage::handler)
                .add();
    }
}
