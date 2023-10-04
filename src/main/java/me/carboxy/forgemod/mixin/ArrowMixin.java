package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.carboxy.forgemod.CarboxyForgeMod;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/** 
 * Learning notes:
 * CallbackInfo is used when the method being injected into has return type void
 * Otherwise CallbackInfoReturnable<T> is used
 */

@Mixin(AbstractArrow.class)
public abstract class ArrowMixin {
    @Inject(method = "onHitBlock(Lnet/minecraft/world/phys/BlockHitResult;)V", at = @At("TAIL"), cancellable = true)
    protected void onArrowHitBlock(BlockHitResult blockHitResult, CallbackInfo ci) {
        CarboxyForgeMod.LOGGER.info("[ArrowMixin] onArrowHitBlock");
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        Level level = arrow.getOwner().getLevel();
        BlockPos hitPos = blockHitResult.getBlockPos();

        BlockState hitBlock = level.getBlockState(hitPos).getBlock().defaultBlockState();
        BlockState magmaBlockState = Blocks.MAGMA_BLOCK.defaultBlockState();
        BlockState lavaBlockState = Blocks.LAVA.defaultBlockState();


        if (hitBlock == magmaBlockState) {
            level.setBlock(hitPos, lavaBlockState, 0, 0);
        }
        else if (hitBlock == lavaBlockState) {
        }
        else {
            level.setBlock(hitPos, magmaBlockState, 0);
        }

        level.addParticle(ParticleTypes.EXPLOSION, hitPos.getX(), hitPos.getY(), hitPos.getZ(), 0.0D, 0.0D, 0.0D);
    }


}
