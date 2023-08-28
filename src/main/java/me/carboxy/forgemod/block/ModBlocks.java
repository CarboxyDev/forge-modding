package me.carboxy.forgemod.block;

import java.util.function.Supplier;

import me.carboxy.forgemod.CarboxyForgeMod;
import me.carboxy.forgemod.block.custom.JumppadBlock;
import me.carboxy.forgemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CarboxyForgeMod.MODID);


    public static final RegistryObject<Block> RUNE_ORE = registerBlock("rune_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops().lightLevel(value -> 10), UniformInt.of(16, 20)));
    public static final RegistryObject<Block> RUNE_BLOCK = registerBlock("rune_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops().lightLevel(value -> 12)));
    public static final RegistryObject<Block> JUMPPAD = registerBlock("jumppad", () -> new JumppadBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(2f)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {        
        RegistryObject<T> regObject = BLOCKS.register(name, block);
        registerBlockItem(name, regObject);
        return regObject; 
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {        
        RegistryObject<Item> regItem = ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return regItem; 
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
