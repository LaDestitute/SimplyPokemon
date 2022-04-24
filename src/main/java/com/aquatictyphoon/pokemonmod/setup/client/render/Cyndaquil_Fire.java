package com.aquatictyphoon.pokemonmod.setup.client.render;


import com.aquatictyphoon.pokemonmod.setup.client.entitymodels.ModelCyndaquil;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Cyndaquil_Fire<ModelCyndaquil extends LivingEntity> extends EyesLayer<ModelCyndaquil, EntityModel<ModelCyndaquil>> {
    private static final RenderType CYNDAQUIL_FIRE = RenderType.eyes(new ResourceLocation("textures/entity/normal/cyndaquil2.png"));

    public Cyndaquil_Fire(RenderLayerParent<ModelCyndaquil, EntityModel<ModelCyndaquil>> p_116981_) {
        super(p_116981_);
    }


    @Override
    public RenderType renderType() {
        return CYNDAQUIL_FIRE;
    }
}
