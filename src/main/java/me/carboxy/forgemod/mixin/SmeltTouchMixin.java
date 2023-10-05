package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.world.level.block.state.BlockBehaviour;


@Mixin(BlockBehaviour.class)
public abstract class SmeltTouchMixin {
    //@Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockBehaviour;Lnet/minecraft/world/level/storage/loot/con)", at = @At("TAIL"), cancellable = true)
    protected void onGetDrops() {

    }
}
