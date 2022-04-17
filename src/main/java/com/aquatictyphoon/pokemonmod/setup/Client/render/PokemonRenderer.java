package com.aquatictyphoon.pokemonmod.setup.Client.render;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.Client.entitymodels.PokemonModel;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;


public class PokemonRenderer extends MobRenderer<PokemonEntity, PokemonModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PokemonMod.MOD_ID, "textures/entity/EGG.png");

    //In 1.18, we now pass a LAYER_LOCATION (see the explanation in PokemonModel) and bake it in
    //using the renderer's EntityRendererProvider.Context in the entity's renderer and pass it through to the constructor
    public PokemonRenderer(EntityRendererProvider.Context context) {
        super(context, new PokemonModel(context.getModelSet().bakeLayer(PokemonModel.LAYER_LOCATION)), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(PokemonEntity entity) {
        return TEXTURE;
    }

}

