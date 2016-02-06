package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.util.IChargeable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class ItemToolEE extends ItemTool {

    public ItemToolEE(float damageVsEntity, Item.ToolMaterial toolMaterial, Set<Block> blocksEffectiveAgainst) {

        super(damageVsEntity, toolMaterial, blocksEffectiveAgainst);
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setNoRepair();
        this.maxStackSize = 1;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack) {

        if (itemStack.getItem() instanceof IChargeable) {
            return ((IChargeable) itemStack.getItem()).getChargeLevel(itemStack) > 0;
        }

        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack itemStack) {

        if (itemStack.getItem() instanceof IChargeable) {
            return (double) (((IChargeable) itemStack.getItem()).getMaxChargeLevel() - ((IChargeable) itemStack.getItem()).getChargeLevel(itemStack)) / (double) ((IChargeable) itemStack.getItem()).getMaxChargeLevel();
        }

        return 1d;
    }
}
