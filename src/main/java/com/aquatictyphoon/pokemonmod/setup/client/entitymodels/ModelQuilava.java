package com.aquatictyphoon.pokemonmod.setup.client.entitymodels;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ModelQuilava<T extends EntityModel> extends EntityModel<PokemonEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart Body;
    private final ModelPart MainHead;
    private final ModelPart leftleg;
    private final ModelPart leftarm;
    private final ModelPart rightleg;
    private final ModelPart rightarm;

    public ModelQuilava(ModelPart root) {
        this.Body = root.getChild("Body");
        this.MainHead = root.getChild("MainHead");
        this.leftleg = root.getChild("leftleg");
        this.leftarm = root.getChild("LeftMainArm2");
        this.rightleg = root.getChild("rightleg");
        this.rightarm = root.getChild("rightarm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 43).addBox(-1.2F, -0.06F, -7.085F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, 15.16F, 0.0F));

        PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.4F, 12.05F, -7.15F, 8.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7154F, -2.5178F, -14.1655F, 1.4399F, 0.0F, 0.0F));

        PartDefinition Butt_r1 = Body.addOrReplaceChild("Butt_r1", CubeListBuilder.create().texOffs(0, 116).mirror().addBox(-8.5F, -11.135F, 1.25F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.8F, -0.06F, 5.45F, -0.8599F, -0.2332F, 0.2622F));

        PartDefinition Butt_r2 = Body.addOrReplaceChild("Butt_r2", CubeListBuilder.create().texOffs(0, 116).addBox(-7.5F, -11.135F, 1.25F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8F, -0.06F, 5.45F, -0.8599F, 0.2332F, -0.2622F));

        PartDefinition Butt_r3 = Body.addOrReplaceChild("Butt_r3", CubeListBuilder.create().texOffs(0, 43).addBox(-3.7F, 3.08F, 27.17F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -4.875F, -20.67F, -0.1309F, 0.0F, 0.0F));

        PartDefinition MainHead = partdefinition.addOrReplaceChild("MainHead", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 15.95F, -8.2F, -0.1309F, 0.0F, 0.0F));

        PartDefinition fire_r1 = MainHead.addOrReplaceChild("fire_r1", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -7.85F, -8.4F, 0.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.9F, 2.25F, 0.2094F, 0.0F, 0.0F));

        PartDefinition ear_r1 = MainHead.addOrReplaceChild("ear_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -0.75F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, 3.0F, -1.1556F, -0.3215F, -0.1384F));

        PartDefinition ear_r2 = MainHead.addOrReplaceChild("ear_r2", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -0.75F, -1.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 3.0F, -1.1556F, 0.3215F, 0.1384F));

        PartDefinition Head_r1 = MainHead.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(16, 19).addBox(-3.7F, -2.26F, -8.495F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2F, -0.725F, 1.83F, 0.1309F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create(), PartPose.offset(3.6F, 20.58F, 7.6F));

        PartDefinition leftleg_r1 = leftleg.addOrReplaceChild("leftleg_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-1.7F, 1.5162F, -7.0142F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4154F, -0.74F, 0.07F, 0.1396F, 0.0F, 0.0F));

        PartDefinition LeftMainArm2 = partdefinition.addOrReplaceChild("LeftMainArm2", CubeListBuilder.create(), PartPose.offset(-1.3F, 17.5F, -4.6F));

        PartDefinition LeftArm_r1 = LeftMainArm2.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(16, 32).addBox(-0.61F, -1.4495F, -6.1688F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8346F, 1.45F, -0.015F, 1.1007F, -0.4995F, 0.3349F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create(), PartPose.offset(-3.6F, 20.58F, 7.6F));

        PartDefinition rightleg_r1 = rightleg.addOrReplaceChild("rightleg_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-1.45F, 1.5162F, -7.0142F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1846F, -0.74F, 0.07F, 0.1396F, 0.0F, 0.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(-1.3F, 17.5F, -4.6F));

        PartDefinition rightarm_r1 = rightarm.addOrReplaceChild("rightarm_r1", CubeListBuilder.create().texOffs(16, 32).mirror().addBox(-0.39F, -1.4495F, -6.1688F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.4346F, 1.45F, -0.015F, 1.1007F, 0.4995F, -0.3349F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        MainHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(PokemonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.MainHead.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.MainHead.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.rightarm.xRot  = Mth.cos(pLimbSwing * 0.6662F) * 1.3F * pLimbSwingAmount;
        this.leftarm.xRot   = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.3F * pLimbSwingAmount;
        this.rightleg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * pLimbSwingAmount;
        this.leftleg.xRot = Mth.cos(pLimbSwing * 0.6662F) * pLimbSwingAmount;
    }
}
