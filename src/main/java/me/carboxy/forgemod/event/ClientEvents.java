package me.carboxy.forgemod.event;

import me.carboxy.forgemod.keybinding.keybinding;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = CarboxyForgeMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (keybinding.KEYBINDING_SHOUT_KEY.consumeClick()) {
                Minecraft.getInstance().player.displayClientMessage(Component.literal("Fus Ro Dah!"), false);
            }
        }
    }   

    @Mod.EventBusSubscriber(modid = CarboxyForgeMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(keybinding.KEYBINDING_SHOUT_KEY);
        }
    }
}
