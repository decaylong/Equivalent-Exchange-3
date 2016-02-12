package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemGem extends ItemEE {

    public ItemGem() {
        super(Names.Items.GEM);
        setMaxStackSize(64);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.GEM);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s%s", Textures.RESOURCE_PREFIX, Names.Items.GEM, Names.Items.GEM_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.GEM_SUBTYPES.length - 1)]);
    }
}
