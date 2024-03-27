package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleMessage {
    public ToggleMessage(PacketBuffer buf) {
    }

    public ToggleMessage() {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public void handler(Supplier<NetworkEvent.Context> sup) {
        final NetworkEvent.Context ctx = sup.get();
        ctx.enqueueWork(() -> {
            final ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;
            final ExtendedServerPlayer extended = (ExtendedServerPlayer) player;
            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
        });
    }
}
