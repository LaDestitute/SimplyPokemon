package com.aquatictyphoon.pokemonmod.setup.server;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.aquatictyphoon.pokemonmod.PokemonMod.POKEMON;

@Mod.EventBusSubscriber(modid = PokemonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
        event.put(POKEMON.get(), PokemonEntity.createAttributes().build());
    }

}

