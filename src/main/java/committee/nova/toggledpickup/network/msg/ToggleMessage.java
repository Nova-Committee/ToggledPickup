package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedEntityPlayerMP;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

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
            final EntityPlayerMP mp = ctx.getServerHandler().playerEntity;
            if (mp == null) return null;
            final ExtendedEntityPlayerMP extended = (ExtendedEntityPlayerMP) mp;
            extended.toggledPickup$setAutoPickup(!extended.toggledPickup$isAutoPickup());
            return null;
        }
    }
}
