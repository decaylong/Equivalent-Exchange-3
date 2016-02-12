package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;

public class ItemChalk extends ItemEE {

    public ItemChalk() {
        super(Names.Items.CHALK);
        setCanRepair(true);
        setMaxDamage(80);
    }
}
