package com.aquatictyphoon.pokemonmod.setup.client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;


public class ModelTotodile<T extends EntityModel> extends EntityModel<PokemonEntity> {
    public final ModelPart RightArm;
    public final ModelPart LeftArm;
    public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart RightMainLeg;
    public final ModelPart LeftMainLeg;

    public ModelTotodile(ModelPart root) {
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.RightMainLeg = root.getChild("RightMainLeg");
        this.LeftMainLeg = root.getChild("LeftMainLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
                CubeListBuilder.create().texOffs(24, 0).addBox(-0.1F, 0.0F, -5.75F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.5F, 14.6F, 0.0F, 0.4369F, 0.4407F, 0.1966F));
        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm",
                CubeListBuilder.create().texOffs(19, 28).addBox(-1.1F, 0.0F, -5.75F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.5F, 14.6F, 0.0F, 0.4369F, -0.4407F, -0.1966F));
        PartDefinition Head = partdefinition.addOrReplaceChild("Head",
                CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -9.1F, -3.6F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(39, 19)
                        .addBox(-3.0F, -10.1F, -3.6F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(39, 19)
                        .addBox(3.0F, -10.1F, -3.6F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 19.0F, -1.0F));
        PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(19, 44).addBox(-2.0F, -1.0F, -3.8F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -7.2F, -3.5F, -0.0524F, 0.0F, 0.0F));
        PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(5, 35).addBox(-1.1F, 1.2F, -3.2F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(20, 21)
                        .addBox(-1.6F, 0.8F, -3.6F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -7.2F, -3.5F, 0.2182F, 0.0F, 0.0F));
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, -1.0F));
        PartDefinition cube_r3 = Body.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -6.6F, 2.8F, 0.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(-3.0F, -4.6F, -2.3F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));
        PartDefinition cube_r4 = Body.addOrReplaceChild("cube_r4",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -5.05F, 3.4F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
        PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5",
                CubeListBuilder.create().texOffs(18, 7).addBox(-2.0F, -2.25F, -0.6F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, -0.0873F, 0.0F, 0.0F));
        PartDefinition RightMainLeg = partdefinition.addOrReplaceChild("RightMainLeg", CubeListBuilder.create(),
                PartPose.offset(-4.3F, 19.6F, -1.0F));
        PartDefinition LgLeft_r1 = RightMainLeg.addOrReplaceChild("LgLeft_r1",
                CubeListBuilder.create().texOffs(32, 3).addBox(-1.0F, 1.474F, -3.3955F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.4F, 1.2F, 0.9F, 0.0087F, 0.0F, 0.0F));
        PartDefinition LgLeft_r2 = RightMainLeg.addOrReplaceChild("LgLeft_r2",
                CubeListBuilder.create().texOffs(12, 25).addBox(-1.0F, -2.626F, -1.5955F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.4F, 1.9F, 0.9F, -0.0087F, 0.0F, 0.0F));
        PartDefinition LeftMainLeg = partdefinition.addOrReplaceChild("LeftMainLeg", CubeListBuilder.create(), PartPose.offset(4.3F, 19.6F, -1.0F));
        PartDefinition LgLeft_r3 = LeftMainLeg.addOrReplaceChild("LgLeft_r3",
                CubeListBuilder.create().texOffs(27, 31).addBox(-1.0F, 1.474F, -3.3955F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.4F, 1.2F, 0.9F, 0.0087F, 0.0F, 0.0F));
        PartDefinition LgLeft_r4 = LeftMainLeg.addOrReplaceChild("LgLeft_r4",
                CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -2.626F, -1.5955F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.4F, 1.9F, 0.9F, -0.0087F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        RightArm.render(poseStack, buffer, packedLight, packedOverlay);
        LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
        Head.render(poseStack, buffer, packedLight, packedOverlay);
        Body.render(poseStack, buffer, packedLight, packedOverlay);
        RightMainLeg.render(poseStack, buffer, packedLight, packedOverlay);
        LeftMainLeg.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(PokemonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.Head.xRot = headPitch / (180F / (float) Math.PI);
        this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.LeftMainLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.RightMainLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
    }
}
