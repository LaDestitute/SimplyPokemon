package com.aquatictyphoon.pokemonmod.setup.client;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.client.entitymodels.*;
import com.aquatictyphoon.pokemonmod.setup.client.render.PokemonRenderer;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.EntityTypeInit;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.aquatictyphoon.pokemonmod.setup.client.render.PokemonRenderer.*;


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
        event.registerLayerDefinition(CHIKORITA_LAYER_LOCATION, ModelChikorita::createBodyLayer);
        event.registerLayerDefinition(BAYLEEF_LAYER_LOCATION, ModelBayleef::createBodyLayer);
        event.registerLayerDefinition(CYNDAQUIL_LAYER_LOCATION, ModelCyndaquil::createBodyLayer);
        event.registerLayerDefinition(QUILAVA_LAYER_LOCATION, ModelQuilava::createBodyLayer);
        event.registerLayerDefinition(TOTODILE_LAYER_LOCATION, ModelTotodile::createBodyLayer);
        event.registerLayerDefinition(EGG_LAYER_LOCATION, ModelEgg::createBodyLayer);
    }

    //Register our renderers
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeInit.POKEMON.get(), PokemonRenderer::new );
    }

}
