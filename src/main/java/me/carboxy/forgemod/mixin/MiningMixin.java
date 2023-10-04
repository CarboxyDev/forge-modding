package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Learning notes: LootTable.Builder transletes to Lnet/minecraft/world/level/storage/loot/LootTable$Builder;
 * Note how the $ is used to specify an inner class
 */

@Mixin(BlockLootSubProvider.class)
public abstract class MiningMixin {

    @Inject(method = "createOreDrop(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/level/storage/loot/LootTable$Builder;", at = @At("RETURN"))
    protected void onCreateOreDrop(Block block, Item item, CallbackInfoReturnable<LootTable.Builder> ci) {
        CarboxyForgeMod.LOGGER.info("[MiningMixin] onCreateOreDrop");

    }

}
