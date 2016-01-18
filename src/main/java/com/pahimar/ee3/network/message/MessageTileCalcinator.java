package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityCalcinator;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileCalcinator extends MessageTileEntityEE {

    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;

    public MessageTileCalcinator() {
    }

    public MessageTileCalcinator(TileEntityCalcinator tileEntityCalcinator) {

        super(tileEntityCalcinator);
        this.leftStackSize = tileEntityCalcinator.leftStackSize;
        this.leftStackMeta = tileEntityCalcinator.leftStackMeta;
        this.rightStackSize = tileEntityCalcinator.rightStackSize;
        this.rightStackMeta = tileEntityCalcinator.rightStackMeta;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        super.fromBytes(byteBuf);
        this.leftStackSize = byteBuf.readByte();
        this.leftStackMeta = byteBuf.readByte();
        this.rightStackSize = byteBuf.readByte();
        this.rightStackMeta = byteBuf.readByte();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        super.toBytes(byteBuf);
        byteBuf.writeByte(leftStackSize);
        byteBuf.writeByte(leftStackMeta);
        byteBuf.writeByte(rightStackSize);
        byteBuf.writeByte(rightStackMeta);
    }

    public static class MessageHandler implements IMessageHandler<MessageTileCalcinator, IMessage> {

        @Override
        public IMessage onMessage(MessageTileCalcinator message, MessageContext ctx) {

            MessageTileEntityEE.MessageHandler.updateTileEntity(message, ctx);

            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);
            if (tileEntity instanceof TileEntityCalcinator) {
                ((TileEntityCalcinator) tileEntity).leftStackSize = message.leftStackSize;
                ((TileEntityCalcinator) tileEntity).leftStackMeta = message.leftStackMeta;
                ((TileEntityCalcinator) tileEntity).rightStackSize = message.rightStackSize;
                ((TileEntityCalcinator) tileEntity).rightStackMeta = message.rightStackMeta;
            }
            return null;
        }
    }
}
