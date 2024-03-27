package committee.nova.toggledpickup.network.msg;

import committee.nova.toggledpickup.api.ExtendedEntityPlayerMP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ManuallyMessage implements IMessage {
    private boolean manually = false;

    public ManuallyMessage() {
    }

    public void writeData(boolean manually) {
        this.manually = manually;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.manually = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.manually);
    }

    public static class Handler implements IMessageHandler<ManuallyMessage, IMessage> {
        @Override
        public IMessage onMessage(ManuallyMessage message, MessageContext ctx) {
            if (ctx.side != Side.SERVER) return null;
            final EntityPlayerMP mp = ctx.getServerHandler().player;
            if (mp == null) return null;
            ((ExtendedEntityPlayerMP) mp).toggledpickup$setManuallyPickingUp(message.manually);
            return null;
        }
    }
}
