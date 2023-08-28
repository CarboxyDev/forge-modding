package me.carboxy.forgemod.item;

import me.carboxy.forgemod.CarboxyForgeMod;
import me.carboxy.forgemod.item.custom.DiceItem;
import me.carboxy.forgemod.item.custom.RunicSword;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CarboxyForgeMod.MODID);
    
    public static final RegistryObject<Item> RUNE_SHARD = ITEMS.register("rune_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DICE = ITEMS.register("dice", () -> new DiceItem(new Item.Properties().stacksTo(1)));
    
    public static final RegistryObject<SwordItem> RUNIC_SWORD = ITEMS.register("runic_sword", () -> new RunicSword());
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
