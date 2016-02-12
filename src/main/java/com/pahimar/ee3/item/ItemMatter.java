package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemMatter extends ItemEE {

    public ItemMatter() {
        super(Names.Items.MATTER);
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.MATTER);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s%s", Textures.RESOURCE_PREFIX, Names.Items.MATTER, Names.Items.MATTER_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.MATTER_SUBTYPES.length - 1)]);
    }
}
