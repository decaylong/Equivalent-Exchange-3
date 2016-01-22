package com.pahimar.ee3.command;

import com.pahimar.ee3.api.knowledge.TransmutationKnowledgeRegistryProxy;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class CommandPlayerForgetEverything extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.PLAYER_FORGET_EVERYTHING;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.PLAYER_FORGET_EVERYTHING_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException(Messages.Commands.PLAYER_FORGET_EVERYTHING_USAGE);
        }
        else
        {
            EntityPlayer entityPlayer = getPlayer(commandSender, args[1]);

            if (entityPlayer != null)
            {
                TransmutationKnowledgeRegistryProxy.makePlayerForgetEverything(entityPlayer);
                notifyOperators(commandSender, this, Messages.Commands.PLAYER_FORGET_EVERYTHING_SUCCESS, new Object[]{commandSender.getName(), entityPlayer.getName()});
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

        return null;
    }
}
