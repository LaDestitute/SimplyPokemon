package com.aquatictyphoon.pokemonmod.setup.client.render;


import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class QuilavaLayer<ModelQuilava extends PokemonEntity> extends EyesLayer<ModelQuilava, EntityModel<ModelQuilava>> {

    private static final RenderType QUILAVA_FIRE = RenderType.eyes(new ResourceLocation("textures/entity/normal/quilava2.png"));

    public QuilavaLayer(RenderLayerParent<ModelQuilava, EntityModel<ModelQuilava>> p_116981_) {
        super(p_116981_);
    }

    @Override
    public RenderType renderType() {
            return QUILAVA_FIRE;
    }
}
