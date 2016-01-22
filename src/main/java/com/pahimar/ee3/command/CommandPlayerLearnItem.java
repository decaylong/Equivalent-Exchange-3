package com.pahimar.ee3.command;

import com.pahimar.ee3.api.knowledge.TransmutationKnowledgeRegistryProxy;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class CommandPlayerLearnItem extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.PLAYER_LEARN_ITEM;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.PLAYER_LEARN_ITEM_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) throws CommandException
    {
        if (args.length < 3)
        {
            throw new WrongUsageException(Messages.Commands.PLAYER_LEARN_ITEM_USAGE);
        }
        else
        {
            EntityPlayer entityPlayer = getPlayer(commandSender, args[1]);

            if (entityPlayer != null)
            {
                Item item = getItemByText(commandSender, args[2]);
                int metaData = 0;

                if (args.length >= 4)
                {
                    metaData = parseInt(args[3]);
                }

                ItemStack itemStack = new ItemStack(item, 1, metaData);

                if (args.length >= 5)
                {
                    String stringNBTData = getChatComponentFromNthArg(commandSender, args, 4).getUnformattedText();

                    try {
                        itemStack.setTagCompound(JsonToNBT.getTagFromJson(stringNBTData));
                    } catch (Exception exception) {
                        throw new CommandException(Messages.Commands.INVALID_NBT_TAG_ERROR);
                    }
                }

                if (AbilityRegistry.getInstance().isLearnable(itemStack))
                {
                    TransmutationKnowledgeRegistryProxy.teachPlayer(entityPlayer, itemStack);
                    notifyOperators(commandSender, this, Messages.Commands.PLAYER_LEARN_ITEM_SUCCESS, new Object[]{commandSender.getName(), entityPlayer.getName(), itemStack.getChatComponent()});
                }
            }
            else
            {
                throw new WrongUsageException(Messages.Commands.PLAYER_NOT_FOUND_ERROR);
            }
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender commandSender, String[] args, BlockPos pos)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames());
        }
        else if (args.length == 3)
        {
            return getListOfStringsMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}
