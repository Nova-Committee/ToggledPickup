package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ManuallyMessage {
    private final boolean pressed;

    public ManuallyMessage(FriendlyByteBuf buf) {
        this.pressed = buf.readBoolean();
    }

    public ManuallyMessage(boolean pressed) {
        this.pressed = pressed;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.pressed);
    }

    public void handler(CustomPayloadEvent.Context ctx) {
        final ServerPlayer player = ctx.getSender();
        if (player == null) return;
        ((ExtendedServerPlayer) player).toggledpickup$setManuallyPickingUp(pressed);
    }
}
