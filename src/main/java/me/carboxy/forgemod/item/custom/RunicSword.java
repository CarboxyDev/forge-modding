package me.carboxy.forgemod.item.custom;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RunicSword extends SwordItem {
    private static final Tier TIER = Tiers.RUNIC;
    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = -2.4f;
    private static final Properties PROPS = new Properties().fireResistant().rarity(Rarity.EPIC);

    public RunicSword() {
        super(TIER, ATTACK_DAMAGE, ATTACK_SPEED, PROPS);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("Blessed by the Gods").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(itemStack, level, list, flag);
    }
    
}
