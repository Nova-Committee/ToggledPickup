package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.Constants;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ManuallyMessage(boolean pressed) implements CustomPacketPayload {

    public static final Identifier ID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "manually");

    public static final Type<ManuallyMessage> TYPE = new Type<>(ID);

    public static final StreamCodec<ByteBuf, ManuallyMessage> STREAM_CODEC =
            ByteBufCodecs.BOOL.map(ManuallyMessage::new, ManuallyMessage::pressed);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
