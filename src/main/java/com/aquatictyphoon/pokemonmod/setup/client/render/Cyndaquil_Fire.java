package com.aquatictyphoon.pokemonmod.setup.client.render;


import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Cyndaquil_Fire<T extends LivingEntity> extends EyesLayer<T, EntityModel<T>> {
    private static final RenderType CYNDAQUIL_FIRE = RenderType.eyes(new ResourceLocation("textures/entity/normal/cyndaquil2.png"));

    public Cyndaquil_Fire(RenderLayerParent<T, EntityModel<T>> p_116981_) {
        super(p_116981_);
    }

    public RenderType renderType() {
        return CYNDAQUIL_FIRE;
    }
}
