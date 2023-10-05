package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Map;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;


@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("TAIL"), cancellable = true)
    protected void onDrop(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (!(stack.getItem() instanceof Item)) {
            return;
        }

        Map<Enchantment, Integer> itemEnchants = stack.getAllEnchantments();
        
        boolean hasEfficiencyEnchant = false;
        for (Map.Entry<Enchantment, Integer> entry : itemEnchants.entrySet()) {
            Enchantment enchant = entry.getKey();
            String enchantName = EnchantmentHelper.getEnchantmentId(enchant).toString();
            if (enchantName.equals("minecraft:efficiency")) {
                hasEfficiencyEnchant = true;
                break;
            }
        }

        if (!hasEfficiencyEnchant) {
            return;
        }
        /* Player has an item with efficiency enchantment */
        CarboxyForgeMod.LOGGER.info("[PlayerMixin] onDrop: Efficiency on dropped item");
        Player player = (Player) (Object) this;
        
    
    }


}
