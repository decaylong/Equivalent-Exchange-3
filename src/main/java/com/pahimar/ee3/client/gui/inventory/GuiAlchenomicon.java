package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.client.gui.GuiBase;
import com.pahimar.ee3.inventory.ContainerAlchenomicon;
import com.pahimar.ee3.reference.Textures;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAlchenomicon extends GuiBase {

    public GuiAlchenomicon(InventoryPlayer inventoryPlayer, ItemStack itemStack) {

        super(new ContainerAlchenomicon(inventoryPlayer.player, itemStack), Textures.Gui.ALCHENOMICON);
        xSize = 256;
        ySize = 226;
    }
}
