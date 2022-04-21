package com.aquatictyphoon.pokemonmod.setup.Client.render;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.Client.entitymodels.PokemonModel;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;

import javax.annotation.Nullable;


public class PokemonRenderer extends MobRenderer<PokemonEntity, PokemonModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PokemonMod.MOD_ID, "textures/entity/egg.png");

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

    public void render(PokemonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.4F * (float)pEntity.getSize();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    protected void scale(PokemonEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 0.999F;
        pMatrixStack.scale(0.4F, 0.4F, 0.4F);
        pMatrixStack.translate(0.0D, (double)0.001F, 0.0D);
        float f1 = (float)pLivingEntity.getSize();
        pMatrixStack.scale(f1, f1, f1);
    }

}

