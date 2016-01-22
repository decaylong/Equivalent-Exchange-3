package com.pahimar.ee3.command;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageSetEnergyValue;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.BlockPos;

import java.util.List;
import java.util.Map;

public class CommandSetEnergyValue extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.SET_ENERGY_VALUE;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.SET_ENERGY_VALUE_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) throws CommandException
    {
        if (args.length < 4)
        {
            throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
        }
        else
        {
            Item item = getItemByText(commandSender, args[2]);
            float energyValue = 0;
            int metaData = 0;

            if (args.length >= 4)
            {
                energyValue = (float) parseDouble(args[3], 0);
            }
            else if (args.length >= 5)
            {
                metaData = parseInt(args[4]);
            }

            ItemStack itemStack = new ItemStack(item, 1, metaData);

            if (args.length >= 6)
            {
                String stringNBTData = getChatComponentFromNthArg(commandSender, args, 5).getUnformattedText();

                try {
                    itemStack.setTagCompound(JsonToNBT.getTagFromJson(stringNBTData));
                } catch (Exception exception) {
                    throw new CommandException(Messages.Commands.INVALID_NBT_TAG_ERROR);
                }
            }

            WrappedStack wrappedStack = WrappedStack.wrap(itemStack);
            EnergyValue newEnergyValue = new EnergyValue(energyValue);

            if (wrappedStack != null && newEnergyValue != null && Float.compare(newEnergyValue.getValue(), 0) > 0)
            {
                if (args[1].equalsIgnoreCase("pre"))
                {
                    EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                    Map<WrappedStack, EnergyValue> preAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.PRE_CALCULATION_ENERGY_VALUES);
                    preAssignedValues.put(wrappedStack, newEnergyValue);
                    SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.PRE_CALCULATION_ENERGY_VALUES, preAssignedValues);
                    EnergyValueRegistry.getInstance().setShouldRegenNextRestart(true);
                }
                else if (args[1].equalsIgnoreCase("global-pre"))
                {
                    EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                    Map<WrappedStack, EnergyValue> preAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.Global.preCalcluationEnergyValueFile);
                    preAssignedValues.put(wrappedStack, newEnergyValue);
                    SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.Global.preCalcluationEnergyValueFile, preAssignedValues);
                    EnergyValueRegistry.getInstance().setShouldRegenNextRestart(true);
                }
                else if (args[1].equalsIgnoreCase("post"))
                {
                    EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                    Map<WrappedStack, EnergyValue> postAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.POST_CALCULATION_ENERGY_VALUES);
                    postAssignedValues.put(wrappedStack, newEnergyValue);
                    SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.POST_CALCULATION_ENERGY_VALUES, postAssignedValues);

                    Network.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                }
                else if (args[1].equalsIgnoreCase("global-post"))
                {
                    EnergyValueRegistry.getInstance().setEnergyValue(wrappedStack, newEnergyValue);

                    Map<WrappedStack, EnergyValue> postAssignedValues = SerializationHelper.readEnergyValueStackMapFromJsonFile(Files.Global.postCalcluationEnergyValueFile);
                    postAssignedValues.put(wrappedStack, newEnergyValue);
                    SerializationHelper.writeEnergyValueStackMapToJsonFile(Files.Global.postCalcluationEnergyValueFile, postAssignedValues);

                    Network.INSTANCE.sendToAll(new MessageSetEnergyValue(wrappedStack, newEnergyValue));
                }
                else
                {
                    throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
                }

                // Notify admins and log the value change
                notifyOperators(commandSender, this, Messages.Commands.SET_ENERGY_VALUE_SUCCESS, new Object[]{commandSender.getName(), args[1], itemStack.getChatComponent(), newEnergyValue.getChatComponent()});
            }
            else
            {
                throw new WrongUsageException(Messages.Commands.SET_ENERGY_VALUE_USAGE);
            }
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender commandSender, String[] args, BlockPos pos)
    {
        if (args.length == 2)
        {
            return getListOfStringsMatchingLastWord(args, "pre", "global-pre", "post", "global-post");
        }
        else if (args.length == 3)
        {
            return getListOfStringsMatchingLastWord(args, Item.itemRegistry.getKeys());
        }

        return null;
    }
}
