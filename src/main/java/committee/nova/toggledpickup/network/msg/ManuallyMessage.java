package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.network.handler.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class ManuallyMessage implements CustomPacketPayload {
    private final boolean pressed;

    public ManuallyMessage(FriendlyByteBuf buf) {
        this.pressed = buf.readBoolean();
    }

    public ManuallyMessage(boolean pressed) {
        this.pressed = pressed;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(this.pressed);
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public ResourceLocation id() {
        return NetworkHandler.MANUALLY;
    }
}
