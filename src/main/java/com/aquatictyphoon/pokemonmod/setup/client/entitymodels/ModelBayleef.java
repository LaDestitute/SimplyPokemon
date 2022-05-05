package com.aquatictyphoon.pokemonmod.setup.client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ModelBayleef<T extends EntityModel> extends EntityModel<PokemonEntity>{
        // This layer location should be baked with EntityRendererProvider.Context in
    // the entity renderer and passed into this model's constructor
    public final ModelPart Body;
    public final ModelPart Head;
    public final ModelPart Neck;
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;
    public final ModelPart FrontRightLeg2;
    public final ModelPart FrontRightLeg3;

    public ModelBayleef(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = root.getChild("Head");
        this.Neck = root.getChild("Neck");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.FrontRightLeg2 = root.getChild("FrontRightLeg2");
        this.FrontRightLeg3 = root.getChild("FrontRightLeg3");
    }

    public static LayerDefinition createBodyLayer() {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(11, 0).addBox(-1.594F, -1.298F, 4.514F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.4F, -1.48F, 0.1745F, 0.0F, 0.0F));

        PartDefinition Neck_r1 = Body.addOrReplaceChild("Neck_r1", CubeListBuilder.create().texOffs(11, 0).addBox(-1.948F, -5.964F, -4.476F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.4F, -1.48F, 0.1309F, 0.0F, 0.0F));

        PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.81F, -1.412F, -4.588F, 8.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.4F, -1.48F, -0.0873F, 0.0F, 0.0F));

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 9.2F, -5.48F, -0.1309F, 0.0F, 0.0F));

        PartDefinition Neck_r2 = Head.addOrReplaceChild("Neck_r2", CubeListBuilder.create().texOffs(0, 15).addBox(-2.448F, -3.6988F, -3.0524F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.4F, 0.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition Leaf_r1 = Head.addOrReplaceChild("Leaf_r1", CubeListBuilder.create().texOffs(12, 24).addBox(-1.668F, 3.2051F, 0.6323F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.74F, 0.02F, 12.4F, -2.138F, 0.0F, 0.0F));

        PartDefinition Leaf_r2 = Head.addOrReplaceChild("Leaf_r2", CubeListBuilder.create().texOffs(12, 27).addBox(-2.168F, 11.363F, -2.7054F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.74F, 0.02F, 12.4F, -1.7453F, 0.0F, 0.0F));

        PartDefinition Leaf_r3 = Head.addOrReplaceChild("Leaf_r3", CubeListBuilder.create().texOffs(20, 16).addBox(-2.668F, 8.695F, -1.6755F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.74F, 0.02F, 12.4F, -1.8326F, 0.0F, 0.0F));

        PartDefinition Leaf_r4 = Head.addOrReplaceChild("Leaf_r4", CubeListBuilder.create().texOffs(20, 24).addBox(-2.168F, 5.171F, 0.455F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.74F, 0.02F, 12.4F, -2.0944F, 0.0F, 0.0F));

        PartDefinition Leaf_r5 = Head.addOrReplaceChild("Leaf_r5", CubeListBuilder.create().texOffs(12, 27).addBox(-1.668F, 7.0623F, -0.4677F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.74F, 0.02F, 12.4F, -1.9635F, 0.0F, 0.0F));

        PartDefinition Leaf_r6 = Head.addOrReplaceChild("Leaf_r6", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -8.7674F, -3.1672F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, 4.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition Leaf_r7 = Head.addOrReplaceChild("Leaf_r7", CubeListBuilder.create().texOffs(0, 15).addBox(-0.26F, 4.311F, -6.0069F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.24F, -0.48F, 5.48F, -1.2654F, 0.0F, 0.0F));

        PartDefinition bone3 = Head.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition Neck = partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Neckleaf_r1 = Neck.addOrReplaceChild("Neckleaf_r1", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(0.0F, -0.5153F, 1.65F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 0.3617F, 0.4024F, 0.1501F));

        PartDefinition Neckleaf_r2 = Neck.addOrReplaceChild("Neckleaf_r2", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(0.4F, -0.5153F, 1.85F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 0.5096F, 0.8402F, 0.3975F));

        PartDefinition Neckleaf_r3 = Neck.addOrReplaceChild("Neckleaf_r3", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(-0.6F, -0.5153F, 1.65F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 2.3008F, 0.5292F, 2.6382F));

        PartDefinition Neckleaf_r4 = Neck.addOrReplaceChild("Neckleaf_r4", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(-0.6F, -0.5153F, 1.65F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 1.799F, 0.8495F, 1.8809F));

        PartDefinition Neckleaf_r5 = Neck.addOrReplaceChild("Neckleaf_r5", CubeListBuilder.create().texOffs(0, 33).addBox(-0.4F, -0.5153F, 1.65F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 2.3008F, -0.5292F, -2.6382F));

        PartDefinition Neckleaf_r6 = Neck.addOrReplaceChild("Neckleaf_r6", CubeListBuilder.create().texOffs(0, 33).addBox(-0.4F, -0.5153F, 1.65F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 1.799F, -0.8495F, -1.8809F));

        PartDefinition Neckleaf_r7 = Neck.addOrReplaceChild("Neckleaf_r7", CubeListBuilder.create().texOffs(0, 33).addBox(-1.4F, -0.5153F, 1.85F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 0.5096F, -0.8402F, -0.3975F));

        PartDefinition Neckleaf_r8 = Neck.addOrReplaceChild("Neckleaf_r8", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, -0.5153F, 1.65F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.38F, -5.0F, 0.3617F, -0.4024F, -0.1501F));

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 24).addBox(0.266F, -1.66F, -1.11F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.44F, 19.56F, 1.48F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 24).addBox(-2.806F, -1.66F, -1.11F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.44F, 19.56F, 1.48F));

        PartDefinition FrontRightLeg2 = partdefinition.addOrReplaceChild("FrontRightLeg2", CubeListBuilder.create().texOffs(0, 24).addBox(0.812F, -1.66F, -1.11F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.44F, 19.56F, -4.44F));

        PartDefinition FrontRightLeg3 = partdefinition.addOrReplaceChild("FrontRightLeg3", CubeListBuilder.create().texOffs(0, 24).addBox(5.278F, -1.66F, -1.11F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.44F, 19.56F, -4.44F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
                               float blue, float alpha) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        FrontRightLeg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        FrontRightLeg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
