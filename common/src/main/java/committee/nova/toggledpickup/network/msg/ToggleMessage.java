package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.Constants;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ToggleMessage() implements CustomPacketPayload {
    public static final ToggleMessage INSTANCE = new ToggleMessage();

    public static final Identifier ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "toggle");

    public static final Type<ToggleMessage> TYPE = new Type<>(ID);

    public static final StreamCodec<ByteBuf, ToggleMessage> STREAM_CODEC =
            StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
