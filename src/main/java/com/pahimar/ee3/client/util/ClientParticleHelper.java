package com.pahimar.ee3.client.util;

import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ClientParticleHelper
{
    public static void spawnParticleAtLocation(EnumParticleTypes particle, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity)
    {
        FMLClientHandler.instance().getWorldClient().spawnParticle(particle, xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity);
    }
}
