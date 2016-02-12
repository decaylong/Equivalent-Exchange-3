package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.ColorHelper;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

public class ItemAlchemicalBag extends ItemEE implements IOwnable
{
    private static final String[] ALCHEMICAL_BAG_ICONS = {"open", "closed", "symbolTier1", "symbolTier2", "symbolTier3"};

    public ItemAlchemicalBag() {
        super(Names.Items.ALCHEMICAL_BAG);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < 3; ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
            if (!ItemHelper.hasOwnerUUID(itemStack))
            {
                ItemHelper.setOwner(itemStack, entityPlayer);
                entityPlayer.addChatComponentMessage(new ChatComponentTranslation(Messages.OWNER_SET_TO_SELF, new Object[]{itemStack.getChatComponent()}));
            }

            // Set a UUID on the Alchemical Bag, if one doesn't exist already
            NBTHelper.setUUID(itemStack);
            // TODO Do a scan of inventory and if we find a bag with the same UUID, change it's UUID
            NBTHelper.setBoolean(itemStack, Names.NBT.ALCHEMICAL_BAG_GUI_OPEN, true);
            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ALCHEMICAL_BAG.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        int bagColor = this.getColor(itemStack);

        if (bagColor < 0)
        {
            bagColor = Integer.parseInt(Colors.PURE_WHITE, 16);
        }

        return bagColor;
    }

    public boolean hasColor(ItemStack itemStack)
    {
        return ColorHelper.hasColor(itemStack);
    }

    public int getColor(ItemStack itemStack)
    {
        return ColorHelper.getColor(itemStack);
    }

    public void setColor(ItemStack itemStack, int color)
    {
        if (itemStack != null)
        {
            if (itemStack.getItem() instanceof ItemAlchemicalBag)
            {
                ColorHelper.setColor(itemStack, color);
            }
        }
    }

    public void removeColor(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound != null)
            {
                NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Names.NBT.DISPLAY);

                if (displayTagCompound.hasKey(Names.NBT.COLOR))
                {
                    displayTagCompound.removeTag(Names.NBT.COLOR);
                }
            }
        }
    }

    @Override
    public UUID getOwner() {
        return null;
    }

    @Override
    public void setOwner(UUID ownerUUID) {

    }

    @Override
    public void setOwner(EntityPlayer entityPlayer) {

    }
}
