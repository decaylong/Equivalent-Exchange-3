package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityAludel;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityAludel extends MessageTileEntityEE {

    public ItemStack outputItemStack;

    public MessageTileEntityAludel() {
    }

    public MessageTileEntityAludel(TileEntityAludel tileEntityAludel) {

        super(tileEntityAludel);
        this.outputItemStack = tileEntityAludel.getStackInSlot(TileEntityAludel.OUTPUT_INVENTORY_INDEX);
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

    public static class MessageHandler implements IMessageHandler<MessageTileEntityAludel, IMessage> {

        @Override
        public IMessage onMessage(MessageTileEntityAludel message, MessageContext ctx) {

            MessageTileEntityEE.MessageHandler.updateTileEntity(message, ctx);
            TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);

            if (tileEntity instanceof TileEntityAludel) {
                ((TileEntityAludel) tileEntity).outputItemStack = message.outputItemStack;
                FMLClientHandler.instance().getClient().theWorld.checkLight(message.blockPos);
            }

            return null;
        }
    }
}
