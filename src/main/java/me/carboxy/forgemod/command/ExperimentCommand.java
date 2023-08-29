package me.carboxy.forgemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;

public class ExperimentCommand {
        public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("experiment").executes((command) -> {
            return execute(command);
        }));
    }

    
    private static int execute(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player){
            Player player = (Player) command.getSource().getEntity();
            player.displayClientMessage(Component.literal("Run Experiment"), false);
            
            Block blockUnderPlayer = player.getBlockStateOn().getBlock();

            
        }
        return Command.SINGLE_SUCCESS;
    }
}
