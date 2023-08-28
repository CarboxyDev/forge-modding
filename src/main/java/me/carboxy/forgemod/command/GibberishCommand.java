package me.carboxy.forgemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class GibberishCommand {
        public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("gibberish").then(Commands.argument("message", StringArgumentType.string())).executes((command) -> {
            CarboxyForgeMod.LOGGER.info("EEEEEE ->", StringArgumentType.getString(command, "message"));
            return execute(command, StringArgumentType.getString(command, "message"));
        }));
    }

    
    private static int execute(CommandContext<CommandSourceStack> command, String message){
        CarboxyForgeMod.LOGGER.info("Execute gibberist command");
        if(command.getSource().getEntity() instanceof Player){
            Player player = (Player) command.getSource().getEntity();
            player.displayClientMessage(Component.literal("gibberish").withStyle(ChatFormatting.OBFUSCATED), false);
            player.displayClientMessage(Component.literal(message), false);

        }
        return Command.SINGLE_SUCCESS;
    }
}
