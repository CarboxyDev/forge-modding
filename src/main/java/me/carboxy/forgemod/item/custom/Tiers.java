package me.carboxy.forgemod.item.custom;

import me.carboxy.forgemod.item.ModItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class Tiers {
    public static final Tier RUNIC = new ForgeTier(4, 2800, 0f, 5, 200, null, () -> Ingredient.of(ModItems.RUNE_SHARD.get()));

}
