package me.carboxy.forgemod.event;

import me.carboxy.forgemod.keybinding.keybinding;
import me.carboxy.forgemod.sound.ModSounds;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.behavior.CountDownCooldownTicks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
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
                final float TRAVEL_DISTANCE = 15.0f;
                Player player = Minecraft.getInstance().player;
                if (player == null) {
                    return;
                }

                float playerRotation = Math.abs(player.getYRot() % 360);
                double playerRotationRadians = Math.toRadians(playerRotation);

                double xOffset = -Math.sin(playerRotationRadians) * TRAVEL_DISTANCE;
                double zOffset = Math.cos(playerRotationRadians) * TRAVEL_DISTANCE;


                ((LocalPlayer) player).playSound(ModSounds.SHOUT_WULDNAHKEST.get(), 1f, 1f);
                player.displayClientMessage(Component.literal("Wuld Nah Kest!"), false);
                player.move(MoverType.SELF, new Vec3(xOffset,0, zOffset));
                /** TODO: Normalize the values for +ve rotations only from 0 to 360 deg
                 *                    90: -x axis
                 *                   180: -z axis
                 *                   -90: +x axis
                 *                     0: +z axis
                 */                   

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
