package com.pahimar.ee3.handler;

import com.pahimar.ee3.exchange.DynamicEnergyValueInitThread;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class WorldEventHandler
{
    public static boolean hasInitilialized = false;

    @SubscribeEvent
    public void onWorldLoadEvent(WorldEvent.Load event)
    {
        if (!hasInitilialized && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        {
            DynamicEnergyValueInitThread.initEnergyValueRegistry();
            hasInitilialized = true;
        }
    }
}
