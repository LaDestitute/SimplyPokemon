package com.aquatictyphoon.pokemonmod.setup.client;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.client.models.ModelEgg;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

import static net.minecraftforge.versions.forge.ForgeVersion.MOD_ID;

public class PokemonRenderer extends MobRenderer<PokemonEntity, EntityModel<PokemonEntity>>{

    public static final ModelLayerLocation EGG_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "egg"), "main");
    public PokemonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelEgg<>(context.getModelSet().bakeLayer(EGG_LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PokemonEntity pEntity) {
        return new ResourceLocation(PokemonMod.MODID, "textures/entity/egg.png");
    }


    public void render(PokemonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = new ModelEgg(Minecraft.getInstance().getEntityModels().bakeLayer(EGG_LAYER_LOCATION));
        this.getTextureLocation(pEntity);
        this.shadowRadius = 0.15F * (float)pEntity.getSize();
        net.minecraftforge.client.event.RenderNameTagEvent renderNameplateEvent = new net.minecraftforge.client.event.RenderNameTagEvent(pEntity, pEntity.getDisplayName(), this, pMatrixStack, pBuffer, pPackedLight, pPartialTicks);
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        if(!pEntity.isInvisible() && !pEntity.isNoAi()) {
            this.renderLevel(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);
            this.renderName(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);
            this.renderHP(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);
        }
    }

    protected void renderLevel(PokemonEntity pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            float displayHeight = pEntity.getBbHeight() + 0.95F;
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0D, displayHeight, 0.0D);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.scale(-0.0125F, -0.0125F, 0.0125F);
            Matrix4f matrix4f = pMatrixStack.last().pose();
            float BackgroundOpacitySettings = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int BackgroundOpacity = (int)(BackgroundOpacitySettings * 255.0F) << 24;
            Font font = this.getFont();
            float displayWidth = (float)(-font.width("Lv" + (pEntity.getPokeLevel())) / 2);
            if (flag) {
                font.drawInBatch("Lv:" + (pEntity.getPokeLevel()), displayWidth, displayHeight, -1, false, matrix4f, pBuffer, false, BackgroundOpacity, pPackedLight);
            }
            pMatrixStack.popPose();
        }
    }



    protected void renderHP(PokemonEntity pEntity,Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight){
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {

            boolean flag = !pEntity.isDiscrete();
            float displayHeight = pEntity.getBbHeight() + 0.5F;
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0D, displayHeight, 0.0D);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.scale(-0.0125F, -0.0125F, 0.0125F);
            Font font = this.getFont();
            float displayWidth = (-font.width("HP:" + (pEntity.getHealth())) / 2);
            String s = "HP: " + ((pEntity.getHealth()));
            if (flag) {
                this.getFont().draw(pMatrixStack, s, displayWidth, displayHeight, 8453920);

            }
            pMatrixStack.popPose();


        }
    }

    @Override
    protected boolean shouldShowName(PokemonEntity pEntity) {
        return false;
    }

    protected void renderName(PokemonEntity pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {

            boolean flag = !pEntity.isDiscrete();
            float displayHeight = pEntity.getBbHeight() + 0.8F;
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0D, displayHeight, 0.0D);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = pMatrixStack.last().pose();
            float BackgroundOpacitySettings = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int BackgroundOpacity = (int)(BackgroundOpacitySettings * 255.0F) << 24;
            Font font = this.getFont();
            float displayWidth = (float)(-font.width(pEntity.getPokeName()) / 2);

            if (flag) {
                font.drawInBatch((pEntity.getPokeName()), displayWidth, displayHeight, -1, false, matrix4f, pBuffer, false, BackgroundOpacity, pPackedLight);
            }
            pMatrixStack.popPose();
        }
    }



    protected float getWhiteOverlayProgress(PokemonEntity pLivingEntity, float pPartialTicks) {
        return pLivingEntity.getEvoTimer() == 0 ? 0.0F : Mth.clamp(pLivingEntity.getEvoTimer(), 0.0F, 1.0F);
    }

    protected void scale(PokemonEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(0.5F, 0.5F, 0.5F);
        pMatrixStack.translate(0.0D, 0.0F, 0.0D);
        float timer = (pLivingEntity.getEvoTimer());
        float f1 = (float)pLivingEntity.getSize();
        float f2 = (float)pLivingEntity.getPokeLevel();
        pMatrixStack.scale((f1+(f2/85)+((timer)/40)), (f1+(f2/85)+((timer)/40)), (f1+(f2/85)+((timer)/40)));
    }
}

