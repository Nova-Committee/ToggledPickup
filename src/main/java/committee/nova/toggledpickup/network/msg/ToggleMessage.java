package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class ToggleMessage implements CustomPacketPayload {
    public ToggleMessage(FriendlyByteBuf buf) {
    }

    public ToggleMessage() {
    }

    @Override
    public void write(FriendlyByteBuf buf) {

    }

    @Override
    public ResourceLocation id() {
        return NetworkHandler.TOGGLE;
    }
}
