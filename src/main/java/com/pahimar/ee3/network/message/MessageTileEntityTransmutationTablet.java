package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// TODO This likely is a crap implementation for synching Tablet data, revisit this
public class MessageTileEntityTransmutationTablet implements IMessage {

    public NBTTagCompound tileEntityTransmutationTabletNBT;

    public MessageTileEntityTransmutationTablet() {
    }

    public MessageTileEntityTransmutationTablet(TileEntityTransmutationTablet tileEntityTransmutationTablet) {

        tileEntityTransmutationTabletNBT = new NBTTagCompound();
        tileEntityTransmutationTablet.writeToNBT(tileEntityTransmutationTabletNBT);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        this.tileEntityTransmutationTabletNBT = new NBTTagCompound();
        byte[] compressedNBT = null;
        int readableBytes = byteBuf.readInt();

        if (readableBytes > 0) {
            compressedNBT = byteBuf.readBytes(readableBytes).array();
        }

        if (compressedNBT != null && compressedNBT.length > 0) {
            try {
                this.tileEntityTransmutationTabletNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            if (tileEntityTransmutationTabletNBT != null) {
                CompressedStreamTools.writeCompressed(tileEntityTransmutationTabletNBT, byteArrayOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byteBuf.writeInt(byteArrayOutputStream.size());
        if (byteArrayOutputStream.size() > 0) {
            byteBuf.writeBytes(byteArrayOutputStream.toByteArray());
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageTileEntityTransmutationTablet, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityTransmutationTablet message, MessageContext ctx) {

            if (message.tileEntityTransmutationTabletNBT != null) {

                TileEntityAlchemyArray tileEntityAlchemyArray = new TileEntityAlchemyArray();
                tileEntityAlchemyArray.readFromNBT(message.tileEntityTransmutationTabletNBT);

                TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityAlchemyArray.getPos());

                if (tileEntity instanceof TileEntityTransmutationTablet) {

                    tileEntity.readFromNBT(message.tileEntityTransmutationTabletNBT);
                    FMLClientHandler.instance().getClient().theWorld.checkLight(tileEntityAlchemyArray.getPos());
                }
            }

            return null;
        }
    }
}
