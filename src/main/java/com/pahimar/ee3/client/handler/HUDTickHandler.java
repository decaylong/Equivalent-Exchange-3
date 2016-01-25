package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.util.IOverlayItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HUDTickHandler
{
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            EntityPlayer entityPlayer = minecraft.thePlayer;

            if (entityPlayer != null)
            {
                ItemStack currentItemStack = entityPlayer.inventory.getCurrentItem();
                if (Minecraft.isGuiEnabled() && minecraft.inGameHasFocus)
                {
                    if (currentItemStack != null && currentItemStack.getItem() instanceof IOverlayItem)
                    {
                        renderHUDOverlayItem(minecraft, entityPlayer, currentItemStack);
                    }
                }
            }
        }
    }

    private static void renderHUDOverlayItem(Minecraft minecraft, EntityPlayer entityPlayer, ItemStack itemStack)
    {
        float overlayScale = 2f;
        ScaledResolution sr = new ScaledResolution(minecraft);
        int hudOverlayX = (int) (sr.getScaledWidth() - 16 * overlayScale);
        int hudOverlayY = (int) (sr.getScaledHeight() - 16 * overlayScale);
        FMLClientHandler.instance().getClient().getRenderItem().renderItemIntoGUI(itemStack, hudOverlayX, hudOverlayY);
    }
}
