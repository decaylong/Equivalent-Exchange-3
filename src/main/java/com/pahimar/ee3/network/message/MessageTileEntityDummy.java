package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityDummyArray;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityDummy extends MessageTileEntityEE {

    public BlockPos trueBlockPos;

    public MessageTileEntityDummy() {
    }

    public MessageTileEntityDummy(TileEntityDummyArray tileEntityDummyArray) {

        super(tileEntityDummyArray);
        this.trueBlockPos = tileEntityDummyArray.getTrueBlockPos();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        super.fromBytes(byteBuf);
        this.trueBlockPos = new BlockPos(byteBuf.readInt(), byteBuf.readInt(), byteBuf.readInt());
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        super.toBytes(byteBuf);
        byteBuf.writeInt(trueBlockPos.getX());
        byteBuf.writeInt(trueBlockPos.getY());
        byteBuf.writeInt(trueBlockPos.getZ());
    }

    public static class MessageHandler implements IMessageHandler<MessageTileEntityDummy, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityDummy message, MessageContext ctx) {

            MessageTileEntityEE.MessageHandler.updateTileEntity(message, ctx);
            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);

            if (tileEntity instanceof TileEntityDummyArray) {
                ((TileEntityDummyArray) tileEntity).setTrueBlockPos(message.trueBlockPos);
            }

            return null;
        }
    }
}
