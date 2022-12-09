package com.aquatictyphoon.pokemonmod.setup.client;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.client.models.ModelEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.aquatictyphoon.pokemonmod.PokemonMod.POKEMON;
import static com.aquatictyphoon.pokemonmod.PokemonMod.POKE_BALL;
import static com.aquatictyphoon.pokemonmod.setup.client.PokemonRenderer.EGG_LAYER_LOCATION;

@Mod.EventBusSubscriber(modid = PokemonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    public static void initRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(POKE_BALL.get(),
                ThrownItemRenderer::new);
    }

    //This is now a requirement in 1.18, we register our model's layer, which holds the model's body
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EGG_LAYER_LOCATION, ModelEgg::createBodyLayer);
    }
    //Register our renderers
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(POKEMON.get(), PokemonRenderer::new );
    }
}
