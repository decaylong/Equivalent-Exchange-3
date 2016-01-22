package com.pahimar.ee3.command;

import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.BlockPos;

import java.util.List;

public class CommandTemplateForgetItem extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.TEMPLATE_FORGET_ITEM;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.TEMPLATE_FORGET_ITEM_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException(Messages.Commands.TEMPLATE_FORGET_ITEM_USAGE);
        }
        else
        {
            Item item = getItemByText(commandSender, args[1]);
            int metaData = 0;

            if (args.length >= 3)
            {
                metaData = parseInt(args[2]);
            }

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 4)
            {
                String stringNBTData = getChatComponentFromNthArg(commandSender, args, 3).getUnformattedText();

                try {
                    itemStack.setTagCompound(JsonToNBT.getTagFromJson(stringNBTData));
                } catch (Exception exception) {
                    throw new CommandException(Messages.Commands.INVALID_NBT_TAG_ERROR);
                }
            }

            TransmutationKnowledgeRegistry.getInstance().makeTemplateForget(itemStack);
            notifyOperators(commandSender, this, Messages.Commands.TEMPLATE_FORGET_ITEM_SUCCESS, new Object[]{commandSender.getName(), itemStack.getChatComponent()});
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender commandSender, String[] args, BlockPos pos)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}
