package com.pahimar.ee3.api.exchange;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

public class EnergyValueMappingsTester {

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    public static void runTest(File file) {
        runTest(file, false);
    }

    public static void runTest(File file, boolean strict) {

        init();

        if (ee3Mod != null) {
            EE3Wrapper.ee3mod.runEnergyValueMappingTest(file, strict);
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
