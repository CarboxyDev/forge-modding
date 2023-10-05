package me.carboxy.forgemod.mixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;

/**
 * Some helpful notes
 * 
 * Z -> Boolean
 * L -> For specifing path for a Class (has to end with ;)
 * methodName(x) -> x must contain ALL the parameters of the method
 * Write the return datatype of method at the end after the )
 */


@Mixin(LocalPlayer.class)
public abstract class MixinTest {
    @Inject(method = "startRiding(Lnet/minecraft/world/entity/Entity;Z)Z", at = @At("HEAD"), cancellable = true)
    protected void onLocalPlayerRiding(Entity riddenEntity, boolean b, CallbackInfoReturnable<Boolean> cir) {
        CarboxyForgeMod.LOGGER.info("[MixinTest] onLocalPlayerRiding");
        LocalPlayer player = (LocalPlayer) (Object) this;
        player.experienceLevel += 1;

        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "/saves/Dev 2/level.dat";

        File levelDataFile = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(levelDataFile);
            CompoundTag levelData = NbtIo.readCompressed(fis);
            CarboxyForgeMod.LOGGER.info("[MixinTest] levelData -> " + levelData.toString());
            fis.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        
    }
}
