package com.pahimar.ee3.handler;

import com.pahimar.ee3.knowledge.AbilityRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AbilityRegistrySerializationHandler
{
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            if (FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getWorldTime() % 600 == 0)
            {
                AbilityRegistry.getInstance().save();
            }
        }
    }
}
