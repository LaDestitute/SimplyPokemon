package com.aquatictyphoon.pokemonmod.setup;


import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.aquatictyphoon.pokemonmod.setup.Registration.POKE_BALL;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {

        //Blocks
    }

    public static void initRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(POKE_BALL.get(),
                ThrownItemRenderer::new);
    }
}