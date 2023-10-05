package me.carboxy.forgemod.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;

@Mixin(Block.class)
public abstract class SmeltTouchMixin {
    @Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;", 
    at = @At("RETURN"), 
    cancellable = true)
    private static void onGetDrops(BlockState state, ServerLevel level, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        CarboxyForgeMod.LOGGER.info("[SmeltTouchMixin] onGetDrops");
        List<ItemStack> drops = new ArrayList<>();
        List<ItemStack> returnValue = cir.getReturnValue();

        for (ItemStack itemStack: returnValue) {
            Optional<SmeltingRecipe> recipe = level.getRecipeManager().getAllRecipesFor(RecipeType.SMELTING).stream().filter(r -> r.getIngredients().get(0).test(itemStack)).findFirst();

            if (recipe.isPresent()) {
                ItemStack smeltedItem = recipe.get().getResultItem(null);
                smeltedItem.setCount(itemStack.getCount());
                drops.add(smeltedItem);
            } else {
                drops.add(itemStack);
            }
        }

        cir.setReturnValue(drops);
    }
}
