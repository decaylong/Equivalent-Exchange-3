package com.pahimar.ee3.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

// TODO Review
public class WorldTransmutationEvent extends BlockEvent {
    public final EnumFacing faceHit;
    public final EntityLiving entityLiving;
    public final ItemStack heldItemStack;

    public WorldTransmutationEvent(World world, BlockPos blockPos, IBlockState blockState, EnumFacing faceHit, EntityLiving entityLiving) {
        super(world, blockPos, blockState);
        this.faceHit = faceHit;
        this.entityLiving = entityLiving;
        this.heldItemStack = entityLiving.getHeldItem();
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
