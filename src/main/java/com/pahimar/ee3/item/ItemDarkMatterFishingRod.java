package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.reference.Names;
import net.minecraft.item.ItemFishingRod;

public class ItemDarkMatterFishingRod extends ItemFishingRod {

    public ItemDarkMatterFishingRod() {
        super();
        setCreativeTab(CreativeTab.EE3_TAB);
        setNoRepair();
        setUnlocalizedName(Names.Tools.DARK_MATTER_FISHING_ROD);
    }
}
