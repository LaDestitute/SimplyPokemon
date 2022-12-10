package com.aquatictyphoon.pokemonmod.setup.sounds;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import net.minecraft.resources.ResourceLocation;


import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;


public class ModSoundEvents {

    public static final SoundEvent POKE_BALL_THROWN = register("pokeball_thrown");
    private static SoundEvent register(String pKey) {
        return SoundEvent.createVariableRangeEvent(new ResourceLocation(PokemonMod.MODID, "pokeball_thrown"));
    }

}
