package com.pahimar.ee3.api.array;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomAlchemyArrayRender
{
    @SideOnly(Side.CLIENT)
    public abstract void doCustomRendering(TileEntity tileEntity, double x, double y, double z, int arraySize, EnumFacing facing, EnumFacing rotation, float tick);
}
