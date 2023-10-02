package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.carboxy.forgemod.CarboxyForgeMod;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;

@Mixin(SelectWorldScreen.class)
public abstract class MixinTest {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    public void onSelectWorldScreenInit(CallbackInfo ci) {
        CarboxyForgeMod.LOGGER.info("MixinTest init");
    }

}
