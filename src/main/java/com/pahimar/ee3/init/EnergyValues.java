package com.pahimar.ee3.init;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class EnergyValues
{
    public static void addDefaultEnergyValues()
    {
        // OreDictionary assignment
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("cobblestone"), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("dustRedstone"), 32);
        String[] dyes = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
        for (int i = 0; i < dyes.length; i++)
        {
            EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("dye" + dyes[i]), 16);
        }
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("gemDiamond"), 8192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("gemEmerald"), 8192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("gemLapis"), 864);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("gemQuartz"), 256);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("ingotGold"), 2048);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("ingotIron"), 256);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("logWood"), 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreCoal"), 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreDiamond"), 8192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreEmerald"), 8192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreGold"), 2048);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreIron"), 256);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreLapis"), 864);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreQuartz"), 256);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("oreRedstone"), 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("plankWood"), 8);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("record"), 2048);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("sand"), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("sandstone"), 4);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("slabWood"), 4);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("stairWood"), 12);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("stickWood"), 4);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("stone"), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("treeLeaves"), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new OreStack("treeSapling"), 32);

        // Fluids
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(FluidRegistry.WATER, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(FluidRegistry.LAVA, 64);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(FluidRegistry.getFluid("milk"), 64);

        /* Building Blocks */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.stone, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.grass, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.cobblestone, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.gravel, 4);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.glass, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE), 4);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.mossy_cobblestone, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.obsidian, 64);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.ice, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.pumpkin, 144);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.netherrack, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.soul_sand, 49);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.stonebrick, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.mycelium, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.end_stone, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.hardened_clay, 256);

        /* Decoration Blocks */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.web, 12);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.tallgrass, 1, OreDictionary.WILDCARD_VALUE), 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.deadbush, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.yellow_flower, 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE), 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.brown_mushroom, 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.red_mushroom, 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.red_mushroom, 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.snow_layer, 0.125f);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.cactus, 8);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.vine, 8);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Blocks.waterlily, 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.anvil, 1, 1), 5290.667f);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.anvil, 1, 2), 2645.333f);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE), 32);

        /* Redstone */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.redstone, 32);

        /* Transportation */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.saddle, 192);

        /* Miscellaneous */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.snowball, 0.25f);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.slime_ball, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.bone, 48);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.ender_pearl, 1024);

        /* Foodstuffs */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.apple, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.porkchop, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.cooked_porkchop, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE), 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.cooked_fish, 1, OreDictionary.WILDCARD_VALUE), 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.melon, 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.beef, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.cooked_beef, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.chicken, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.cooked_chicken, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.rotten_flesh, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.spider_eye, 128);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.carrot, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.potato, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.baked_potato, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.poisonous_potato, 24);

        /* Brewing */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.ghast_tear, 4096);

        /* Materials */
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.coal, 1, 0), 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.coal, 1, 1), 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.diamond, 8192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.iron_ingot, 256);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.gold_ingot, 2048);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.string, 12);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.feather, 48);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.gunpowder, 192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.wheat_seeds, 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.wheat, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.flint, 4);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.leather, 64);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.brick, 64);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.clay_ball, 64);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.reeds, 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.egg, 32);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.glowstone_dust, 384);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.dye, 1, 0), 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.dye, 1, 2), 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.dye, 1, 3), 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.dye, 1, 4), 864);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.dye, 1, 5), 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(Items.dye, 1, 6), 16);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.blaze_rod, 1536);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.nether_wart, 24);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.emerald, 8192);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.nether_star, 24576);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.netherbrick, 1);
        EnergyValueRegistryProxy.addPreCalculationEnergyValue(Items.quartz, 256);

        /* Equivalent Exchange 3 */
        /**
         *  Alchemical Dusts
         */
//        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 0), 1);
//        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 1), 64);
//        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 2), 2048);
//        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(ModItems.alchemicalDust, 1, 3), 8192);

        /**
         *  Minium Shard
         */
//        EnergyValueRegistryProxy.addPreCalculationEnergyValue(new ItemStack(ModItems.shardMinium), 8192);
    }
}
