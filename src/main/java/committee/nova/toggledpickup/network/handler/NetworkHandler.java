package committee.nova.toggledpickup.network.handler;

import committee.nova.toggledpickup.ToggledPickup;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import committee.nova.toggledpickup.network.msg.ToggleMessage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(ToggledPickup.MODID);

    private static int nextId = 0;

    public static void init() {
        registerMessage(ToggleMessage.Handler.class, ToggleMessage.class, Side.SERVER);
        registerMessage(ManuallyMessage.Handler.class, ManuallyMessage.class, Side.SERVER);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        instance.registerMessage(messageHandler, requestMessageType, nextId++, side);
    }
}
