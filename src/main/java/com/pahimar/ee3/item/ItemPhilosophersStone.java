package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.IKeyBound;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemPhilosophersStone extends ItemEE implements IKeyBound {
    private int maxChargeLevel;

    public ItemPhilosophersStone() {
        super(Names.Items.PHILOSOPHERS_STONE);
        this.setMaxDamage(1000);
        this.maxChargeLevel = 3;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack copiedStack = itemStack.copy();

        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);
        copiedStack.stackSize = 1;

        return copiedStack;
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key) {
        LogHelper.info("{} {} {}", entityPlayer.toString(), itemStack.toString(), key.toString());
    }
}
