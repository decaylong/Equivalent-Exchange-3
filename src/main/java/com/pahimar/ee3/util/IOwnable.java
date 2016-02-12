package com.pahimar.ee3.util;

import net.minecraft.entity.player.EntityPlayer;

import java.util.UUID;

public interface IOwnable {

    UUID getOwner();

    void setOwner(UUID ownerUUID);

    void setOwner(EntityPlayer entityPlayer);
}
