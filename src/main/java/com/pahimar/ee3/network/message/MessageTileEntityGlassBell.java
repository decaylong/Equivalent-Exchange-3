package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityGlassBell extends MessageTileEntityEE {

    public ItemStack outputItemStack;

    public MessageTileEntityGlassBell() {
    }

    public MessageTileEntityGlassBell(TileEntityGlassBell tileEntityGlassBell) {

        super(tileEntityGlassBell);
        this.outputItemStack = tileEntityGlassBell.getStackInSlot(TileEntityGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        super.fromBytes(byteBuf);
        if (byteBuf.readBoolean()) {
            this.outputItemStack = ByteBufUtils.readItemStack(byteBuf);
        } else {
            outputItemStack = null;
        }
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        super.toBytes(byteBuf);

        if (outputItemStack != null) {
            byteBuf.writeBoolean(true);
            ByteBufUtils.writeItemStack(byteBuf, outputItemStack);
        } else {
            byteBuf.writeBoolean(false);
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageTileEntityGlassBell, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityGlassBell message, MessageContext ctx) {
            MessageTileEntityEE.MessageHandler.updateTileEntity(message, ctx);
            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);

            if (tileEntity instanceof TileEntityGlassBell) {
                ((TileEntityGlassBell) tileEntity).outputItemStack = message.outputItemStack;
                FMLClientHandler.instance().getClient().theWorld.checkLight(message.blockPos);
            }

            return null;
        }
    }
}
