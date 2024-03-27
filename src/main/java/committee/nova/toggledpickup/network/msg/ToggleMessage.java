package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ToggleMessage {
    public ToggleMessage(FriendlyByteBuf buf) {
    }

    public ToggleMessage() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handler(CustomPayloadEvent.Context ctx) {
        final ServerPlayer player = ctx.getSender();
        if (player == null) return;
        final ExtendedServerPlayer extended = (ExtendedServerPlayer) player;
        extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
    }
}
