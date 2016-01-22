package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageKeyPressed;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.util.IKeyBound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
    private static Key getPressedKeybinding()
    {
        if (Keybindings.charge.isKeyDown())
        {
            return Key.CHARGE;
        } else if (Keybindings.extra.isKeyDown())
        {
            return Key.EXTRA;
        } else if (Keybindings.release.isKeyDown())
        {
            return Key.RELEASE;
        } else if (Keybindings.toggle.isKeyDown())
        {
            return Key.TOGGLE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if (getPressedKeybinding() == Key.UNKNOWN)
        {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus)
        {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if (entityPlayer.getCurrentEquippedItem() != null)
                {
                    ItemStack currentlyEquippedItemStack = entityPlayer.getCurrentEquippedItem();

                    if (currentlyEquippedItemStack.getItem() instanceof IKeyBound)
                    {
                        if (entityPlayer.worldObj.isRemote)
                        {
                            Network.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeybinding()));
                        }
                        else
                        {
                            ((IKeyBound) currentlyEquippedItemStack.getItem()).doKeyBindingAction(entityPlayer, currentlyEquippedItemStack, getPressedKeybinding());
                        }
                    }
                }
            }
        }
    }
}
