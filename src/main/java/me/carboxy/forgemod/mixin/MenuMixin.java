package me.carboxy.forgemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.carboxy.forgemod.CarboxyForgeMod;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

@Mixin(TitleScreen.class)
public abstract class MenuMixin extends Screen {
    protected MenuMixin(Component title) {
        super(title);
    }

    @Inject(method = "createNormalMenuOptions(II)V", at = @At("TAIL"), cancellable = true)
    protected void onMenuInit(int y, int spacing, CallbackInfo ci) {
        System.out.println("[MenuMixin] createNormalMenuOptions");
     
        this.addRenderableWidget(Button.builder(Component.translatable(this.minecraft.getUser().getName()), (p_232779_) -> {
            CarboxyForgeMod.LOGGER.info("[MenuMixin] Attempt world load");
            // load the last played world
            //this.minecraft.doWorldLoad("", null, null, null, false);

      }).bounds(this.width / 2 - 100 + 205, y, 50, 20).build());

    }    
}
