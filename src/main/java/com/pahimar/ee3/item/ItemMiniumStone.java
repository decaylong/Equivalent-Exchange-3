package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.IKeyBound;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMiniumStone extends ItemEE implements IKeyBound {
    public ItemMiniumStone() {
        super(Names.Items.MINIUM_STONE);
        this.setMaxDamage(1000); // TODO Get this from configs
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
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack) {
        return NBTHelper.hasTag(itemStack, Names.NBT.CRAFTING_GUI_OPEN) || NBTHelper.hasTag(itemStack, Names.NBT.TRANSMUTATION_GUI_OPEN);
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key) {
        LogHelper.info("{} {} {}", entityPlayer.toString(), itemStack.toString(), key.toString());
    }
}
