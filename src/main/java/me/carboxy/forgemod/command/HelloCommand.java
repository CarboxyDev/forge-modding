package me.carboxy.forgemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class HelloCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("hello").executes((command) -> {
            return execute(command);
        }));
    }

    
    private static int execute(CommandContext<CommandSourceStack> command){
        CarboxyForgeMod.LOGGER.info("Execute hello command");
        if(command.getSource().getEntity() instanceof Player){
            Player player = (Player) command.getSource().getEntity();
            player.displayClientMessage(Component.literal("hello world!"), false);
        }
        return Command.SINGLE_SUCCESS;
    }
}
