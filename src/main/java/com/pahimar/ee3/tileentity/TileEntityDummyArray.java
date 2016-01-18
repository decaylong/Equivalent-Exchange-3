package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageTileEntityDummy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;

// TODO Replace literals with String constants for NBT tag names
public class TileEntityDummyArray extends TileEntityEE {

    private BlockPos trueBlockPos;
    private int ticksSinceSync;

    public TileEntityDummyArray()
    {
        super();
    }

    @Deprecated
    public int getTrueXCoord() {
        return getTrueBlockPos().getX();
    }

    @Deprecated
    public int getTrueYCoord() {
        return getTrueBlockPos().getY();
    }

    @Deprecated
    public int getTrueZCoord() {
        return getTrueBlockPos().getZ();
    }

    public BlockPos getTrueBlockPos() {
        return trueBlockPos;
    }

    public void setTrueBlockPos(BlockPos trueBlockPos) {
        this.trueBlockPos = trueBlockPos;
    }

    @Deprecated
    public void setTrueCoords(int trueXCoord, int trueYCoord, int trueZCoord) {
        this.setTrueBlockPos(new BlockPos(trueXCoord, trueYCoord, trueZCoord));
    }

    public TileEntityAlchemyArray getAssociatedTileEntity() {

        if (this.worldObj.getTileEntity(trueBlockPos) instanceof TileEntityAlchemyArray) {
            return (TileEntityAlchemyArray) this.worldObj.getTileEntity(trueBlockPos);
        }

        return null;
    }

    public int getLightLevel() {

        TileEntityAlchemyArray tileEntityAlchemyArray = getAssociatedTileEntity();

        if (tileEntityAlchemyArray != null) {
            return tileEntityAlchemyArray.getLightLevel();
        }

        return 0;
    }

    @Override
    public void update() {

        super.update();

        if (++ticksSinceSync % 10 == 0) {
            if (!getWorld().isRemote && !(getWorld().getTileEntity(getTrueBlockPos()) instanceof TileEntityAlchemyArray)) {
                this.invalidate();
                getWorld().setBlockToAir(getTrueBlockPos());
                getWorld().markBlockForUpdate(getTrueBlockPos());
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return Network.INSTANCE.getPacketFrom(new MessageTileEntityDummy(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        int trueXCoord = nbtTagCompound.getInteger("trueXCoord");
        int trueYCoord = nbtTagCompound.getInteger("trueYCoord");
        int trueZCoord = nbtTagCompound.getInteger("trueZCoord");
        setTrueBlockPos(new BlockPos(trueXCoord, trueYCoord, trueZCoord));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("trueXCoord", getTrueBlockPos().getX());
        nbtTagCompound.setInteger("trueYCoord", getTrueBlockPos().getY());
        nbtTagCompound.setInteger("trueZCoord", getTrueBlockPos().getZ());
    }
}
