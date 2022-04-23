package com.aquatictyphoon.pokemonmod.setup.Client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;


public class ModelEgg<T extends PokemonEntity> extends EntityModel<PokemonEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart bb_main;

    public ModelEgg(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.75F, -2.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(12, 9).addBox(-2.5F, -4.75F, -1.45F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-2.0F, -5.75F, -0.9F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-2.5F, -1.0F, -1.55F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(PokemonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}