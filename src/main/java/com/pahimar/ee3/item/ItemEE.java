package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import net.minecraft.item.Item;

public class ItemEE extends Item {

    public ItemEE() {
        super();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setNoRepair();
    }
}
