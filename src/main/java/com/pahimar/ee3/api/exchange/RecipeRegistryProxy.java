package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

public final class RecipeRegistryProxy {

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void addRecipe(Object recipeOutput, List<?> recipeInputList) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getRecipeRegistry().addRecipe(recipeOutput, recipeInputList);
        }
    }

    public static void dumpRecipeRegistryToLog() {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.getRecipeRegistry().dumpRecipeRegistryToLog();
        }
    }

    private static void init() {

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }
}
