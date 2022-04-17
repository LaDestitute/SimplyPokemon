package com.aquatictyphoon.pokemonmod.util;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.EntityTypeInit;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonEventBusSubscriber {
    @SubscribeEvent
    public static void onStaticCommonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void registerCaps(RegisterCapabilitiesEvent event) {
    }

    //This method is required to give your entity its attributes, not doing so will cause MC to fail to load
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypeInit.POKEMON.get(), PokemonEntity.customAttributes().build());
    }

}

