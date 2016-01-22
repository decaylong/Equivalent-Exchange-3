package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.knowledge.TransmutationKnowledge;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.CompressionHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Collection;

// TODO This is a suboptimal way to send transmutation knowledge, revisit this in the future
public class MessageTransmutationKnowledgeUpdate implements IMessage {

    public int xCoord, yCoord, zCoord;
    public BlockPos blockPos;
    public TransmutationKnowledge transmutationKnowledge;

    public MessageTransmutationKnowledgeUpdate() {
    }

    public MessageTransmutationKnowledgeUpdate(TileEntityTransmutationTablet tileEntityTransmutationTablet, Collection<ItemStack> knownTransmutationsCollection) {

        if (tileEntityTransmutationTablet != null) {
            this.blockPos = tileEntityTransmutationTablet.getPos();
        }

        if (knownTransmutationsCollection != null) {
            this.transmutationKnowledge = new TransmutationKnowledge(knownTransmutationsCollection);
        } else {
            this.transmutationKnowledge = new TransmutationKnowledge();
        }
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        this.transmutationKnowledge = new TransmutationKnowledge();
        this.blockPos = new BlockPos(byteBuf.readInt(), byteBuf.readInt(), byteBuf.readInt());

        if (byteBuf.readBoolean()) {
            byte[] compressedString = null;
            int readableBytes = byteBuf.readInt();

            if (readableBytes > 0) {
                compressedString = byteBuf.readBytes(readableBytes).array();
            }

            if (compressedString != null) {
                String uncompressedString = CompressionHelper.decompressStringFromByteArray(compressedString);
                this.transmutationKnowledge = TransmutationKnowledge.createFromJson(uncompressedString);
            }
        }
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        byteBuf.writeInt(xCoord);
        byteBuf.writeInt(yCoord);
        byteBuf.writeInt(zCoord);

        byte[] compressedString = null;

        if (transmutationKnowledge != null) {
            compressedString = CompressionHelper.compressStringToByteArray(transmutationKnowledge.toJson());
        }

        if (compressedString != null) {
            byteBuf.writeBoolean(true);
            byteBuf.writeInt(compressedString.length);
            byteBuf.writeBytes(compressedString);
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageTransmutationKnowledgeUpdate, IMessage> {

        @Override
        public IMessage onMessage(MessageTransmutationKnowledgeUpdate message, MessageContext ctx) {

            if (message.yCoord != Integer.MIN_VALUE) {
                if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiContainer) {

                    GuiContainer guiContainer = (GuiContainer) FMLClientHandler.instance().getClient().currentScreen;

                    if (guiContainer.inventorySlots instanceof ContainerTransmutationTablet) {
                        if (FMLClientHandler.instance().getWorldClient().getTileEntity(message.blockPos) instanceof TileEntityTransmutationTablet) {
                            ((ContainerTransmutationTablet) guiContainer.inventorySlots).handleTransmutationKnowledgeUpdate(message.transmutationKnowledge);
                        }
                    }
                }
            }

            return null;
        }
    }
}
