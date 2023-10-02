package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;

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
        CarboxyForgeMod.LOGGER.info("[MixinTest] riddenEntity -> " + riddenEntity.getStringUUID() );
    }
}
