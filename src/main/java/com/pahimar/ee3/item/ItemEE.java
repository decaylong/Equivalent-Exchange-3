package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import net.minecraft.item.Item;

public class ItemEE extends Item {

    public ItemEE() {
        super();
        setMaxStackSize(1);
        setCreativeTab(CreativeTab.EE3_TAB);
        setNoRepair();
    }

    public ItemEE(String itemName) {
        this();
        setRegistryName(itemName);
        setUnlocalizedName(itemName);
    }

    public void setCanRepair(boolean canRepair) {
        this.canRepair = canRepair;
    }
}
