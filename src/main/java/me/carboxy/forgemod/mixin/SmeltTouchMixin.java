package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(BlockBehaviour.class)
public abstract class SmeltTouchMixin {
    @Inject(method = "getLootTable()Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    protected void onGetLootTable(CallbackInfoReturnable<ResourceLocation> cir) {
        CarboxyForgeMod.LOGGER.info("[SmeltTouchMixin] onGetLootTable");
        BlockBehaviour context = (BlockBehaviour) (Object) this;
        Item blockItem = context.asItem();
        if (blockItem.equals(Items.IRON_ORE)) {
            ResourceLocation lootTable = new ResourceLocation("item/iron_ingot");
            cir.setReturnValue(lootTable);
        } else if (blockItem.equals(Items.GOLD_ORE)) {
            ResourceLocation lootTable = new ResourceLocation("item", "gold_ingot");
            cir.setReturnValue(lootTable);
        
        }

    }
}
