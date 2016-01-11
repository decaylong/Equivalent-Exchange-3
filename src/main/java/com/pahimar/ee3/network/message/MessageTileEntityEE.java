package com.pahimar.ee3.network.message;

import com.pahimar.ee3.tileentity.TileEntityEE;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MessageTileEntityEE implements IMessage, IMessageHandler<MessageTileEntityEE, IMessage>
{
    public BlockPos blockPos;
    public EnumFacing facing;
    public byte state;
    public String customName;
    public UUID ownerUUID;

    public MessageTileEntityEE() {
    }

    public MessageTileEntityEE(TileEntityEE tileEntityEE) {

        this.blockPos = tileEntityEE.getPos();
        this.facing = tileEntityEE.getFacing();
        this.state = (byte) tileEntityEE.getState();
        this.customName = tileEntityEE.getCustomName();
        this.ownerUUID = tileEntityEE.getOwnerUUID();
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.facing = EnumFacing.getFront((int) buf.readByte());
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        if (buf.readBoolean()) {
            this.ownerUUID = new UUID(buf.readLong(), buf.readLong());
        } else {
            this.ownerUUID = null;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeByte((byte) facing.ordinal());
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        if (ownerUUID != null) {
            buf.writeBoolean(true);
            buf.writeLong(ownerUUID.getMostSignificantBits());
            buf.writeLong(ownerUUID.getLeastSignificantBits());
        } else {
            buf.writeBoolean(false);
        }
    }

    @Override
    public IMessage onMessage(MessageTileEntityEE message, MessageContext ctx) {

        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.blockPos);

        if (tileEntity instanceof TileEntityEE) {
            ((TileEntityEE) tileEntity).setFacing(message.facing);
            ((TileEntityEE) tileEntity).setState(message.state);
            ((TileEntityEE) tileEntity).setCustomName(message.customName);
            ((TileEntityEE) tileEntity).setOwnerUUID(message.ownerUUID);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("MessageTileEntityEE - x:%s, y:%s, z:%s, facing:%s, state:%s, customName:%s, ownerUUID:%s", blockPos.getX(), blockPos.getY(), blockPos.getZ(), facing, state, customName, ownerUUID);
    }
}
