package com.aquatictyphoon.pokemonmod.setup.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public static final String KEY_CATEGORY_POKEBALL = "key.category.pokemonmod.pokemonmod";
    public static final String KEY_POKEBALL = "key.pokemonmod.pokeball";

    public static final KeyMapping POKEBALL_KEY = new KeyMapping(KEY_POKEBALL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_POKEBALL);
}
