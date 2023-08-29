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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
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
        if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            player.displayClientMessage(Component.literal("You prayed at the shrine").withStyle(ChatFormatting.GOLD), true);
            level.addParticle(ParticleTypes.FLASH, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0, 0);

            int rand = RandomSource.createNewThreadLocalInstance().nextIntBetweenInclusive(0, 10);

            if (rand <= 2) {
                player.displayClientMessage(Component.literal("The gods cursed you!"), false);
                ((LivingEntity)player).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20 * 5, 5 - 1));
                level.playSound(null, blockPos, SoundEvents.GHAST_HURT, SoundSource.HOSTILE, 1f, 1f);
            }
            else {
                player.displayClientMessage(Component.literal("The gods blessed you with nothing."), false);
                level.playSound(null, blockPos, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 1f, 1f);
            }

        }

        return super.use(blockState, level, blockPos, player, interactionHand, bhResult);
    }


}
