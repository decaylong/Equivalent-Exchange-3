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

    public int trueXCoord, trueYCoord, trueZCoord;
    public BlockPos trueBlockPos;

    public MessageTileEntityDummy() {
    }

    public MessageTileEntityDummy(TileEntityDummyArray tileEntityDummyArray) {

        super(tileEntityDummyArray);
        this.trueXCoord = tileEntityDummyArray.getTrueXCoord();
        this.trueYCoord = tileEntityDummyArray.getTrueYCoord();
        this.trueZCoord = tileEntityDummyArray.getTrueZCoord();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        super.fromBytes(byteBuf);
        this.trueXCoord = byteBuf.readInt();
        this.trueYCoord = byteBuf.readInt();
        this.trueZCoord = byteBuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        super.toBytes(byteBuf);
        byteBuf.writeInt(trueXCoord);
        byteBuf.writeInt(trueYCoord);
        byteBuf.writeInt(trueZCoord);
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
