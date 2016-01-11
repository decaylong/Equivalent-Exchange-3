package com.pahimar.ee3.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TabletTransmutationEvent extends Event {
    public final EntityPlayer entityPlayer;
    public final BlockPos blockPos;
    public final ItemStack itemStack;

    public TabletTransmutationEvent(EntityPlayer entityPlayer, BlockPos blockPos, ItemStack itemStack) {
        this.entityPlayer = entityPlayer;
        this.blockPos = blockPos;
        this.itemStack = itemStack;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
