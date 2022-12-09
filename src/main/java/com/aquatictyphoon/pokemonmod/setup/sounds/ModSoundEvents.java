package com.aquatictyphoon.pokemonmod.setup.sounds;

import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.sound.SoundEvent;

public class ModSoundEvents extends SoundEvent {

    public static final net.minecraft.sounds.SoundEvent POKE_BALL_THROWN = register("pokeball_thrown");

    private static net.minecraft.sounds.SoundEvent register(String pKey) {
        return register(String.valueOf(new ResourceLocation("pokeball_thrown")));
    }


    protected ModSoundEvents(SoundEngine engine) {
        super(engine);
    }
}
