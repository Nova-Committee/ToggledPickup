package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedEntityPlayerMP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ToggleMessage implements IMessage {
    public ToggleMessage() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<ToggleMessage, IMessage> {
        @Override
        public IMessage onMessage(ToggleMessage message, MessageContext ctx) {
            if (ctx.side != Side.SERVER) return null;
            final EntityPlayerMP mp = ctx.getServerHandler().player;
            if (mp == null) return null;
            final ExtendedEntityPlayerMP extended = (ExtendedEntityPlayerMP) mp;
            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
            return null;
        }
    }
}
