package me.carboxy.forgemod.block.custom;

import me.carboxy.forgemod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class ShrineBlock extends Block {
    private static BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.STONE).strength(4f);

    public ShrineBlock() {
        super(props);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult bhResult) {
        if (interactionHand == InteractionHand.MAIN_HAND) {
            player.displayClientMessage(Component.literal("You prayed at the shrine").withStyle(ChatFormatting.GOLD), true);
            ((LocalPlayer)player).playSound(SoundEvents.LIGHTNING_BOLT_THUNDER, 1f, 1f);
            level.addParticle(ParticleTypes.FLASH, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0, 0);       
        }

        return super.use(blockState, level, blockPos, player, interactionHand, bhResult);
    }


}
