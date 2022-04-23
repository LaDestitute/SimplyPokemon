package com.aquatictyphoon.pokemonmod.setup.Client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ModelCyndaquil<T extends EntityModel> extends EntityModel<PokemonEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart MainBody;
    private final ModelPart MainHead;
    private final ModelPart LeftMainArm;
    private final ModelPart RightMainArm;
    private final ModelPart RightMainLeg;
    private final ModelPart LeftMainLeg;

    public ModelCyndaquil(ModelPart root) {
        this.MainBody = root.getChild("MainBody");
        this.MainHead = root.getChild("MainHead");
        this.LeftMainArm = root.getChild("LeftMainArm");
        this.RightMainArm = root.getChild("RightMainArm");
        this.RightMainLeg = root.getChild("RightMainLeg");
        this.LeftMainLeg = root.getChild("LeftMainLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition MainBody = partdefinition.addOrReplaceChild("MainBody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 19.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r1 = MainBody.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(2, 15).mirror().addBox(-0.6F, -1.8F, -0.1F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.8F, 0.0F, 0.8F, 1.5708F, 1.4835F, 1.5708F));

        PartDefinition cube_r2 = MainBody.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(2, 15).mirror().addBox(1.1073F, -1.816F, -2.2347F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.8F, 0.0F, 0.8F, -0.0426F, 0.0094F, 0.218F));

        PartDefinition cube_r3 = MainBody.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(2, 15).addBox(-0.6496F, -1.8231F, -0.1F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.2F, -1.5708F, -1.3526F, 1.5708F));

        PartDefinition cube_r4 = MainBody.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(2, 15).addBox(-1.0861F, -1.8118F, -1.7347F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.2F, -0.043F, -0.0076F, -0.1744F));

        PartDefinition Neck_r1 = MainBody.addOrReplaceChild("Neck_r1", CubeListBuilder.create().texOffs(9, 3).addBox(-2.0F, -0.5576F, -0.5632F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-1.5F, -0.5076F, -1.6632F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.3963F, -0.4361F, -0.0873F, 0.0F, 0.0F));

        PartDefinition MainHead = partdefinition.addOrReplaceChild("MainHead", CubeListBuilder.create().texOffs(14, 12).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.75F, -2.0F));

        PartDefinition Head_r1 = MainHead.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.25F, -1.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4066F, 0.5017F, 1.5708F, 0.0F, 0.0F));

        PartDefinition LeftMainArm = partdefinition.addOrReplaceChild("LeftMainArm", CubeListBuilder.create(), PartPose.offset(0.5F, 20.0F, -2.0F));

        PartDefinition LeftArm_r1 = LeftMainArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(10, 14).addBox(-1.0F, 0.0F, -1.8861F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0963F, 0.1F, 0.7728F, 0.2549F, 0.2411F));

        PartDefinition RightMainArm = partdefinition.addOrReplaceChild("RightMainArm", CubeListBuilder.create(), PartPose.offset(-1.5F, 20.0F, -2.0F));

        PartDefinition RightArm_r1 = RightMainArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(6, 12).addBox(0.0F, 0.0F, -1.8861F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0963F, 0.1F, 0.7654F, -0.2236F, -0.2099F));

        PartDefinition RightMainLeg = partdefinition.addOrReplaceChild("RightMainLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 21.6463F, 0.8139F));

        PartDefinition FoorRight_r1 = RightMainLeg.addOrReplaceChild("FoorRight_r1", CubeListBuilder.create().texOffs(14, 8).addBox(-0.5F, 0.0F, -1.3139F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.3F, -0.7F, 0.0F, 0.0F, 0.0F));

        PartDefinition LegRight_r1 = RightMainLeg.addOrReplaceChild("LegRight_r1", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -1.2298F, -1.1928F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftMainLeg = partdefinition.addOrReplaceChild("LeftMainLeg", CubeListBuilder.create(), PartPose.offset(1.0F, 21.6463F, 0.8139F));

        PartDefinition FootLeft_r1 = LeftMainLeg.addOrReplaceChild("FootLeft_r1", CubeListBuilder.create().texOffs(9, 0).addBox(-0.5F, 0.0F, -1.3139F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.3F, -0.7F, 0.0F, 0.0F, 0.0F));

        PartDefinition LgLeft_r1 = LeftMainLeg.addOrReplaceChild("LgLeft_r1", CubeListBuilder.create().texOffs(10, 10).addBox(-0.5F, -1.2298F, -1.1928F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.8727F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        MainBody.render(poseStack, buffer, packedLight, packedOverlay);
        MainHead.render(poseStack, buffer, packedLight, packedOverlay);
        LeftMainArm.render(poseStack, buffer, packedLight, packedOverlay);
        RightMainArm.render(poseStack, buffer, packedLight, packedOverlay);
        RightMainLeg.render(poseStack, buffer, packedLight, packedOverlay);
        LeftMainLeg.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(PokemonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                          float headPitch) {
        this.RightMainArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.LeftMainLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.LeftMainArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.MainHead.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.MainHead.xRot = headPitch / (180F / (float) Math.PI);
        this.RightMainLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
    }

}
