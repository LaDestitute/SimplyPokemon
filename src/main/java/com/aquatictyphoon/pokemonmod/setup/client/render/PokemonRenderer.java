package com.aquatictyphoon.pokemonmod.setup.client.render;

import com.aquatictyphoon.pokemonmod.setup.client.entitymodels.ModelCyndaquil;
import com.aquatictyphoon.pokemonmod.setup.client.entitymodels.ModelEgg;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import static com.aquatictyphoon.pokemonmod.PokemonMod.MOD_ID;


public class PokemonRenderer extends MobRenderer<PokemonEntity, EntityModel<PokemonEntity>>{

    private ResourceLocation ModelCyndaquil;


    public static final ModelLayerLocation EGG_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "egg"), "main");
    public static final ModelLayerLocation CYNDAQUIL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "cyndaquil"), "main");

    private static final ResourceLocation EGG_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/egg.png");
    private static final ResourceLocation CYNDAQUIL_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/cyndaquil1.png");


    //In 1.18, we now pass a LAYER_LOCATION (see the explanation in PokemonModel) and bake it in
    //using the renderer's EntityRendererProvider.Context in the entity's renderer and pass it through to the constructor

    public PokemonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelEgg<>(context.getModelSet().bakeLayer(EGG_LAYER_LOCATION)), 0.5f);
        this.addLayer(new EyesLayer(this) {
            public RenderType renderType() {
                return RenderType.eyes(new ResourceLocation(MOD_ID,"textures/entity/normal/cyndaquil2.png"));
            }
        });
    }


    @Nullable
    @Override
    public ResourceLocation getTextureLocation(PokemonEntity pEntity) {
        int species = pEntity.getPokeSpecies();
        if(species == 1){
            return CYNDAQUIL_TEXTURE;
        }else{
            return EGG_TEXTURE;
        }
    }


    public void render(PokemonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        int species = pEntity.getPokeSpecies();
        if(species == 1){
            this.model = new ModelCyndaquil(Minecraft.getInstance().getEntityModels().bakeLayer(CYNDAQUIL_LAYER_LOCATION));
        }else{
            this.model = new ModelEgg(Minecraft.getInstance().getEntityModels().bakeLayer(EGG_LAYER_LOCATION));
        }


        this.getTextureLocation(pEntity);



        this.shadowRadius = 0.05F * (float)pEntity.getSize();
        net.minecraftforge.client.event.RenderNameplateEvent renderNameplateEvent = new net.minecraftforge.client.event.RenderNameplateEvent(pEntity, pEntity.getDisplayName(), this, pMatrixStack, pBuffer, pPackedLight, pPartialTicks);
        this.renderLevel(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);
        this.renderSpecies(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);

    }

    protected void scale(PokemonEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 0.999F;
        pMatrixStack.scale(0.4F, 0.4F, 0.4F);
        pMatrixStack.translate(0.0D, (double)0.001F, 0.0D);
        float f1 = (float)pLivingEntity.getSize();
        float f2 = (float)pLivingEntity.getPokeLevel();
        pMatrixStack.scale(f1+(f2/50), f1+(f2/50), f1+(f2/50));
    }

    protected void renderLevel(PokemonEntity pEntity,Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            float displayheight = pEntity.getBbHeight() + 0.7F;
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0D, displayheight, 0.0D);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.scale(-0.0125F, -0.0125F, 0.0125F);
            Matrix4f matrix4f = pMatrixStack.last().pose();
            float BackgroundOpacitySettings = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int BackgroundOpacity = (int)(BackgroundOpacitySettings * 255.0F) << 24;
            Font font = this.getFont();
            float displaywidth = (float)(-font.width("Lv" + (pEntity.getPokeLevel())) / 2);
            if (flag) {
                font.drawInBatch("Lv:" + (pEntity.getPokeLevel()), displaywidth, displayheight, -1, false, matrix4f, pBuffer, false, BackgroundOpacity, pPackedLight);
            }
            pMatrixStack.popPose();
        }
    }


    protected void renderSpecies(PokemonEntity pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            float displayheight = pEntity.getBbHeight() + 0.5F;
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0D, displayheight, 0.0D);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = pMatrixStack.last().pose();
            float BackgroundOpacitySettings = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int BackgroundOpacity = (int)(BackgroundOpacitySettings * 255.0F) << 24;
            Font font = this.getFont();
            float displaywidth = (float)(-font.width(pEntity.getPokeName()) / 2);

            if (flag) {
                font.drawInBatch((pEntity.getPokeName()), displaywidth, displayheight, -1, false, matrix4f, pBuffer, false, BackgroundOpacity, pPackedLight);
            }
            pMatrixStack.popPose();
        }
    }




}

