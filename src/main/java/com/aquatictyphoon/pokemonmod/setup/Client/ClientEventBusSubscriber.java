package com.aquatictyphoon.pokemonmod.setup.Client;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.Client.entitymodels.PokemonModel;
import com.aquatictyphoon.pokemonmod.setup.Client.render.PokemonRenderer;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.EntityTypeInit;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = PokemonMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onStaticClientSetup(FMLClientSetupEvent event) {

    }

    public static void initRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeInit.POKE_BALL.get(),
                ThrownItemRenderer::new);
    }

    //This is now a requirement in 1.18, we register our model's layer, which holds the model's body
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PokemonModel.LAYER_LOCATION, PokemonModel::createBodyLayer);
    }

    //Register our renderers
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeInit.POKEMON.get(), PokemonRenderer::new);
    }

}
