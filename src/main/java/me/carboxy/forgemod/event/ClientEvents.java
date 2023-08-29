package me.carboxy.forgemod.event;

import me.carboxy.forgemod.keybinding.keybinding;
import me.carboxy.forgemod.sound.ModSounds;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
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
                Player player = Minecraft.getInstance().player;
                ((LocalPlayer) player).playSound(ModSounds.SHOUT_WULDNAHKEST.get(), 0, 0);
                player.displayClientMessage(Component.literal("Wuld Nah Kest!"), false);
                
                /** TODO: Normalize the values for +ve rotations only from 0 to 360 deg
                 *                    90: -x axis
                 *                   180: -z axis
                 *                   -90: +x axis
                 *                     0: +z axis
                 */                   

                float playerRotationView = Math.abs(player.getYHeadRot() % 360);
                player.displayClientMessage(Component.literal(String.format("%.2f", playerRotationView)), false);

                if (playerRotationView > 0 && playerRotationView < 90) {
                    player.setPos(player.getX() - 10, player.getY(), player.getZ() - 10);
                } else if (playerRotationView > 90 && playerRotationView < 180) {
                    player.setPos(player.getX() - 10, player.getY(), player.getZ() + 10);
                } else if (playerRotationView > -180 && playerRotationView < -90) {
                    player.setPos(player.getX() + 10, player.getY(), player.getZ() + 10);
                } else if (playerRotationView > -90 && playerRotationView < 0) {
                    player.setPos(player.getX() + 10, player.getY(), player.getZ() - 10);
                }

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
