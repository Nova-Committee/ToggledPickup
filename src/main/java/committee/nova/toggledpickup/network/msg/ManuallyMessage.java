package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ManuallyMessage {
    private final boolean pressed;

    public ManuallyMessage(PacketBuffer buf) {
        this.pressed = buf.readBoolean();
    }

    public ManuallyMessage(boolean pressed) {
        this.pressed = pressed;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBoolean(this.pressed);
    }

    public void handler(Supplier<NetworkEvent.Context> sup) {
        final NetworkEvent.Context ctx = sup.get();
        ctx.enqueueWork(() -> {
            final ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;
            ((ExtendedServerPlayer) player).toggledpickup$setManuallyPickingUp(pressed);
        });
    }
}
