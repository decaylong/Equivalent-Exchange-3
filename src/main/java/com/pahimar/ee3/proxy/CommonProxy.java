package com.pahimar.ee3.proxy;

import com.pahimar.ee3.handler.*;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new AbilityRegistrySerializationHandler());
        MinecraftForge.EVENT_BUS.register(new ItemEventHandler());
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        MinecraftForge.EVENT_BUS.register(new CraftingHandler());
    }
}
