package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemAlchemicalFuel extends ItemEE {

    public ItemAlchemicalFuel() {
        super(Names.Items.ALCHEMICAL_FUEL);
        setMaxStackSize(64);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.ALCHEMICAL_FUEL);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s.%s", Textures.RESOURCE_PREFIX, Names.Items.ALCHEMICAL_FUEL, Names.Items.ALCHEMICAL_FUEL_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.ALCHEMICAL_FUEL_SUBTYPES.length - 1)]);
    }
}
