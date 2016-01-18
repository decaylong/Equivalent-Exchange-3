package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityResearchStation;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityResearchStation extends MessageTileEntityEE {

    public ItemStack alchenomiconItemStack;

    public MessageTileEntityResearchStation() {
    }

    public MessageTileEntityResearchStation(TileEntityResearchStation tileEntityResearchStation)
    {
        super(tileEntityResearchStation);
        this.alchenomiconItemStack = tileEntityResearchStation.getStackInSlot(TileEntityResearchStation.ALCHENOMICON_SLOT_INVENTORY_INDEX);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        super.fromBytes(byteBuf);
        if (byteBuf.readBoolean()) {
            this.alchenomiconItemStack = ByteBufUtils.readItemStack(byteBuf);
        } else {
            alchenomiconItemStack = null;
        }
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        super.toBytes(byteBuf);
        if (alchenomiconItemStack != null) {
            byteBuf.writeBoolean(true);
            ByteBufUtils.writeItemStack(byteBuf, alchenomiconItemStack);
        } else {
            byteBuf.writeBoolean(false);
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageTileEntityResearchStation, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityResearchStation message, MessageContext ctx) {

            MessageTileEntityEE.MessageHandler.updateTileEntity(message, ctx);
            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);

            if (tileEntity instanceof TileEntityResearchStation) {
                ((TileEntityResearchStation) tileEntity).setInventorySlotContents(TileEntityResearchStation.ALCHENOMICON_SLOT_INVENTORY_INDEX, message.alchenomiconItemStack);
            }

            return null;
        }
    }
}
