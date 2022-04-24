package com.aquatictyphoon.pokemonmod.setup.client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ModelCyndaquil<T extends EntityModel> extends EntityModel<PokemonEntity> {

    private final ModelPart Body;
    private final ModelPart Fire;
    private final ModelPart MainHead;
    private final ModelPart LeftMainArm;
    private final ModelPart LeftMainLeg;
    private final ModelPart RightMainArm;
    private final ModelPart RightMainLeg;

    public ModelCyndaquil(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Fire = root.getChild("Fire");
        this.MainHead = root.getChild("MainHead");
        this.LeftMainArm = root.getChild("LeftMainArm");
        this.LeftMainLeg = root.getChild("LeftMainLeg");
        this.RightMainArm = root.getChild("RightMainArm");
        this.RightMainLeg = root.getChild("RightMainLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body",
                CubeListBuilder.create().texOffs(0, 22)
                        .addBox(-2.0F, -1.4F, -3.7F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 22)
                        .addBox(-2.0F, -0.9F, 2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.0F, 17.2F, 0.0F));

        PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1",
                CubeListBuilder.create().texOffs(12, 12).addBox(-4.0F, 12.5F, -5.5F, 6.0F, 6.0F, 6.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, -1.6868F, -13.8965F, 1.5708F, 0.0F, 0.0F));

        PartDefinition Fire = partdefinition.addOrReplaceChild("Fire", CubeListBuilder.create(),
                PartPose.offsetAndRotation(1.0F, 13.4F, -1.6F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r1 = Fire.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(48, 50).mirror()
                        .addBox(-4.2F, -3.6F, -0.2F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(-5.6F, 0.0F, 1.6F, 1.5708F, 1.4835F, 1.5708F));

        PartDefinition cube_r2 = Fire.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(48, 50).mirror()
                        .addBox(2.1298F, -4.0144F, -1.4951F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(-5.6F, 0.0F, 1.6F, -0.0426F, 0.0094F, 0.218F));

        PartDefinition cube_r3 = Fire.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(48, 50).addBox(1.5619F, -4.5484F, -0.2F, 0.0F, 6.0F, 8.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.4F, -1.5708F, -1.3526F, 1.5708F));

        PartDefinition cube_r4 = Fire.addOrReplaceChild("cube_r4",
                CubeListBuilder.create().texOffs(48, 50).addBox(-2.1042F, -4.0092F, -0.4951F, 0.0F, 6.0F, 8.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, 0.0F, 0.4F, -0.043F, -0.0076F, -0.1744F));

        PartDefinition MainHead = partdefinition.addOrReplaceChild("MainHead",
                CubeListBuilder.create().texOffs(0, 22)
                        .addBox(-3.0F, -1.0F, -7.2F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(8, 13)
                        .addBox(-2.0F, 0.3F, -9.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 15.5F, -4.0F));

        PartDefinition Head_r1 = MainHead.addOrReplaceChild("Head_r1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.5F, -2.5F, 6.0F, 6.0F, 6.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.8132F, 1.1035F, 1.5708F, 0.0F, 0.0F));

        PartDefinition LeftMainArm = partdefinition.addOrReplaceChild("LeftMainArm", CubeListBuilder.create(),
                PartPose.offset(1.0F, 19.0F, -2.0F));

        PartDefinition LeftArm_r1 = LeftMainArm.addOrReplaceChild("LeftArm_r1",
                CubeListBuilder.create().texOffs(0, 16).addBox(-0.3F, -0.8073F, -3.0722F, 2.0F, 2.0F, 4.0F,
                        new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, -0.8F, 0.8544F, -0.4379F, 0.7554F));

        PartDefinition LeftMainLeg = partdefinition.addOrReplaceChild("LeftMainLeg", CubeListBuilder.create(),
                PartPose.offset(2.0F, 19.6F, 2.0F));

        PartDefinition LgLeft_r1 = LeftMainLeg
                .addOrReplaceChild("LgLeft_r1",
                        CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, 1.474F, -3.3955F, 2.0F, 2.0F, 4.0F,
                                new CubeDeformation(0.0F)),
                        PartPose.offsetAndRotation(0.0F, 1.2F, 0.9F, 0.0087F, 0.0F, 0.0F));

        PartDefinition LgLeft_r2 = LeftMainLeg
                .addOrReplaceChild("LgLeft_r2",
                        CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.626F, -1.5955F, 2.0F, 4.0F, 4.0F,
                                new CubeDeformation(0.0F)),
                        PartPose.offsetAndRotation(0.0F, 1.2F, 0.9F, 0.829F, 0.0F, 0.0F));

        PartDefinition RightMainArm = partdefinition.addOrReplaceChild("RightMainArm", CubeListBuilder.create(),
                PartPose.offset(-2.9F, 19.0F, -2.0F));

        PartDefinition LeftArm_r2 = RightMainArm.addOrReplaceChild("LeftArm_r2",
                CubeListBuilder.create().texOffs(0, 16).mirror()
                        .addBox(-1.7F, -0.8073F, -3.0722F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(0.0F, 0.0F, -0.8F, 0.8544F, 0.4379F, -0.7554F));

        PartDefinition RightMainLeg = partdefinition.addOrReplaceChild("RightMainLeg", CubeListBuilder.create(),
                PartPose.offset(-4.3F, 19.6F, 2.0F));

        PartDefinition LgLeft_r3 = RightMainLeg.addOrReplaceChild("LgLeft_r3",
                CubeListBuilder.create().texOffs(20, 0).mirror()
                        .addBox(-1.0F, 1.474F, -3.3955F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(0.0F, 1.2F, 0.9F, 0.0087F, 0.0F, 0.0F));

        PartDefinition LgLeft_r4 = RightMainLeg.addOrReplaceChild("LgLeft_r4",
                CubeListBuilder.create().texOffs(0, 14).mirror()
                        .addBox(-1.0F, -2.626F, -1.5955F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(0.0F, 1.2F, 0.9F, 0.829F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        Body.render(poseStack, buffer, packedLight, packedOverlay);
        Fire.render(poseStack, buffer, packedLight, packedOverlay);
        MainHead.render(poseStack, buffer, packedLight, packedOverlay);
        LeftMainArm.render(poseStack, buffer, packedLight, packedOverlay);
        LeftMainLeg.render(poseStack, buffer, packedLight, packedOverlay);
        RightMainArm.render(poseStack, buffer, packedLight, packedOverlay);
        RightMainLeg.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(PokemonEntity pEntity, float limbSwing, float limbSwingAmount, float pAgeInTicks, float netHeadYaw, float headPitch) {
        this.RightMainArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.LeftMainLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.LeftMainArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.MainHead.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.MainHead.xRot = headPitch / (180F / (float) Math.PI);
        this.RightMainLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
    }
}
