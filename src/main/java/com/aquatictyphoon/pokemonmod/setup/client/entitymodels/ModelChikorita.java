package com.aquatictyphoon.pokemonmod.setup.client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ModelChikorita<T extends EntityModel> extends EntityModel<PokemonEntity>{
        // This layer location should be baked with EntityRendererProvider.Context in
    // the entity renderer and passed into this model's constructor
    public final ModelPart Body;
    public final ModelPart Head;
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;
    public final ModelPart FrontRightLeg2;
    public final ModelPart FrontRightLeg3;

    public ModelChikorita(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = root.getChild("Head");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.FrontRightLeg2 = root.getChild("FrontRightLeg2");
        this.FrontRightLeg3 = root.getChild("FrontRightLeg3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.9F, -4.1F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(24, 21).addBox(-1.05F, -5.85F, 2.05F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 11).addBox(-2.6F, -10.3F, -4.7F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone = Head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.0F, -1.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition Leaf_r1 = bone.addOrReplaceChild("Leaf_r1", CubeListBuilder.create().texOffs(18, 11).addBox(-2.1F, -4.2604F, -0.8158F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 1.0F, -1.4835F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Leaf_r2 = bone2.addOrReplaceChild("Leaf_r2", CubeListBuilder.create().texOffs(16, 23).addBox(-2.1F, -4.527F, -1.0541F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, 5.0F, -1.9635F, 0.0F, 0.0F));

        PartDefinition Leaf_r3 = bone2.addOrReplaceChild("Leaf_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.8F, -1.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0908F, 0.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(8, 21).addBox(-0.3F, -1.0F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 21.0F, 1.0F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 21).addBox(-1.7F, -1.0F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 21.0F, 1.0F));

        PartDefinition FrontRightLeg2 = partdefinition.addOrReplaceChild("FrontRightLeg2", CubeListBuilder.create().texOffs(19, 0).addBox(-0.1F, 1.0F, -0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 21.0F, -3.0F));

        PartDefinition FrontRightLeg3 = partdefinition.addOrReplaceChild("FrontRightLeg3", CubeListBuilder.create().texOffs(18, 17).addBox(4.1F, 1.0F, -0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 21.0F, -3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        Body.render(poseStack, buffer, packedLight, packedOverlay);
        Head.render(poseStack, buffer, packedLight, packedOverlay);
        RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
        FrontRightLeg2.render(poseStack, buffer, packedLight, packedOverlay);
        FrontRightLeg3.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(PokemonEntity pEntity, float limbSwing, float limbSwingAmount, float pAgeInTicks, float netHeadYaw, float headPitch) {
        this.Head.xRot = headPitch * ((float)Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.FrontRightLeg2.xRot  = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.FrontRightLeg3.xRot   = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
