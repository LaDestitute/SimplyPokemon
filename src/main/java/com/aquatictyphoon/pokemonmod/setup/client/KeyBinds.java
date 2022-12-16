package com.aquatictyphoon.pokemonmod.setup.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {

    public static final String KEY_CATEGORY_POKEBALL = "key.category.pokemonmod.pokemonmod";
    public static final String KEY_POKEBALL = "key.pokemonmod.pokeball";

    public static KeyMapping pokeball_keymapping;

    public static void init(RegisterKeyMappingsEvent event) {
        pokeball_keymapping = new KeyMapping(KEY_POKEBALL, KeyConflictContext.IN_GAME, InputConstants.getKey("key.keyboard.period"), KEY_CATEGORY_POKEBALL);
        event.register(pokeball_keymapping);
    }
}
