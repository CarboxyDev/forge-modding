package me.carboxy.forgemod.keybinding;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class keybinding {
    public static final String KEYBINDING_CATEGORY = "key.category.carboxyforgemod";
    public static final String KEYBINDING_SHOUT = "key.carboxyforgemod.shout";

    public static final KeyMapping KEYBINDING_SHOUT_KEY = new KeyMapping(KEYBINDING_SHOUT, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEYBINDING_CATEGORY);


}
