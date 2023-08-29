package me.carboxy.forgemod;

import com.mojang.logging.LogUtils;

import me.carboxy.forgemod.block.ModBlocks;
import me.carboxy.forgemod.command.ExperimentCommand;
import me.carboxy.forgemod.command.GibberishCommand;
import me.carboxy.forgemod.command.HelloCommand;
import me.carboxy.forgemod.item.ModItems;
import me.carboxy.forgemod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CarboxyForgeMod.MODID)
public class CarboxyForgeMod
{
    public static final String MODID = "carboxyforgemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CarboxyForgeMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModSounds.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RUNE_SHARD);
        }   
        if (event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.RUNE_ORE);
            event.accept(ModBlocks.SHRINE_BLOCK);
        }
        if (event.getTab() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(ModBlocks.RUNE_BLOCK);
            event.accept(ModBlocks.JUMPPAD);
        }
        if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.DICE);
        }
        if (event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.RUNIC_SWORD);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // This runs when the server starts 
        LOGGER.info("Modded Development server is starting...");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("Your username is {}", Minecraft.getInstance().getUser().getName());
        }
    }

    @Mod.EventBusSubscriber(modid = CarboxyForgeMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ModEventListener {
        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event){
            HelloCommand.register(event.getDispatcher());
            GibberishCommand.register(event.getDispatcher());
            ExperimentCommand.register(event.getDispatcher());
        }

    }
}
