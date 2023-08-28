package me.carboxy.forgemod.item.custom;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DiceItem extends Item {
    public DiceItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        /** Multiplayer server support */
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            int roll = RandomSource.createNewThreadLocalInstance().nextInt(6) + 1;
            outputServerMessage(player, "You rolled a " + roll + "!");
            player.getCooldowns().addCooldown(this, 20*5);
        }
        return super.use(level, player, hand);
    }

    private void outputServerMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }
}
