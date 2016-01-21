package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
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

// TODO This likely is a crap implementation for synching Array data, revisit this
public class MessageTileEntityAlchemyArray implements IMessage {

    public NBTTagCompound tileEntityAlchemyArrayNBT;

    public MessageTileEntityAlchemyArray() {
    }

    public MessageTileEntityAlchemyArray(TileEntityAlchemyArray tileEntityAlchemyArray) {

        tileEntityAlchemyArrayNBT = new NBTTagCompound();
        tileEntityAlchemyArray.writeToNBT(tileEntityAlchemyArrayNBT);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        this.tileEntityAlchemyArrayNBT = new NBTTagCompound();
        byte[] compressedNBT = null;
        int readableBytes = byteBuf.readInt();

        if (readableBytes > 0) {
            compressedNBT = byteBuf.readBytes(readableBytes).array();
        }

        if (compressedNBT != null && compressedNBT.length > 0) {
            try {
                this.tileEntityAlchemyArrayNBT = CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressedNBT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void toBytes(ByteBuf byteBuf) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            if (tileEntityAlchemyArrayNBT != null) {
                CompressedStreamTools.writeCompressed(tileEntityAlchemyArrayNBT, byteArrayOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byteBuf.writeInt(byteArrayOutputStream.size());
        if (byteArrayOutputStream.size() > 0) {
            byteBuf.writeBytes(byteArrayOutputStream.toByteArray());
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageTileEntityAlchemyArray, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityAlchemyArray message, MessageContext ctx) {

            if (message.tileEntityAlchemyArrayNBT != null) {

                TileEntityAlchemyArray tileEntityAlchemyArray = new TileEntityAlchemyArray();
                tileEntityAlchemyArray.readFromNBT(message.tileEntityAlchemyArrayNBT);

                TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntityAlchemyArray.getPos());

                if (tileEntity instanceof TileEntityAlchemyArray) {

                    tileEntity.readFromNBT(message.tileEntityAlchemyArrayNBT);
                    FMLClientHandler.instance().getClient().theWorld.checkLight(tileEntityAlchemyArray.getPos());
                }
            }

            return null;
        }
    }
}