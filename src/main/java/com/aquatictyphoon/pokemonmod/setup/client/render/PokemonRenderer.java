package com.aquatictyphoon.pokemonmod.setup.client.render;

import com.aquatictyphoon.pokemonmod.setup.client.entitymodels.*;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

import static com.aquatictyphoon.pokemonmod.PokemonMod.MOD_ID;


public class PokemonRenderer extends MobRenderer<PokemonEntity, EntityModel<PokemonEntity>>{





    public static final ModelLayerLocation EGG_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "egg"), "main");

    public static final ModelLayerLocation CHIKORITA_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "chikorita"), "main");
    public static final ModelLayerLocation BAYLEEF_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "bayleef"), "main");
    public static final ModelLayerLocation CYNDAQUIL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "cyndaquil"), "main");
    public static final ModelLayerLocation QUILAVA_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "quilava"), "main");
    public static final ModelLayerLocation TOTODILE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "totodile"), "main");



    private static final ResourceLocation EGG_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/egg.png");

    private static final ResourceLocation CHIKORITA_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/chikorita.png");
    private static final ResourceLocation BAYLEEF_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/bayleef.png");
    private static final ResourceLocation CYNDAQUIL_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/cyndaquil.png");
    private static final ResourceLocation QUILAVA_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/quilava.png");
    private static final ResourceLocation TOTODILE_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/normal/totodile.png");

    private static final ResourceLocation CHIKORITA_TEXTURE_S = new ResourceLocation(MOD_ID, "textures/entity/shiny/shiny_chikorita.png");
    private static final ResourceLocation BAYLEEF_TEXTURE_S = new ResourceLocation(MOD_ID, "textures/entity/shiny/bayleef.png");
    private static final ResourceLocation CYNDAQUIL_TEXTURE_S = new ResourceLocation(MOD_ID, "textures/entity/shiny/shiny_cyndaquil.png");
    private static final ResourceLocation QUILAVA_TEXTURE_S = new ResourceLocation(MOD_ID, "textures/entity/shiny/quilava.png");
    private static final ResourceLocation TOTODILE_TEXTURE_S = new ResourceLocation(MOD_ID, "textures/entity/shiny/shiny_totodile.png");

    //In 1.18, we now pass a LAYER_LOCATION (see the explanation in PokemonModel) and bake it in
    //using the renderer's EntityRendererProvider.Context in the entity's renderer and pass it through to the constructor


    public PokemonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelEgg<>(context.getModelSet().bakeLayer(EGG_LAYER_LOCATION)), 0.5f);
        this.addLayer(new CyndaquilLayer(this) {
            public RenderType renderType() {
                return RenderType.eyes(new ResourceLocation(MOD_ID,"textures/entity/normal/cyndaquil2.png"));
            }
        });

        this.addLayer(new QuilavaLayer<>(this) {
            public RenderType renderType() {
                return RenderType.eyes(new ResourceLocation(MOD_ID,"textures/entity/normal/quilava2.png"));
            }
        });
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(PokemonEntity pEntity) {
        int species = pEntity.getPokeSpecies();
        int shiny = pEntity.getShinyness();
        if((shiny == 0)) {
            if (species == 152) {
                return CHIKORITA_TEXTURE;
            } else if (species == 153) {
                return BAYLEEF_TEXTURE;
            } else if (species == 155) {
                return CYNDAQUIL_TEXTURE;
            } else if (species == 156) {
                return QUILAVA_TEXTURE;
            } else if (species == 158) {
                return TOTODILE_TEXTURE;
            }
        }else{
            if (species == 152) {
                return CHIKORITA_TEXTURE_S;
            } else if (species == 153) {
                return BAYLEEF_TEXTURE_S;
            } else if (species == 155) {
                return CYNDAQUIL_TEXTURE_S;
            } else if (species == 156) {
                return QUILAVA_TEXTURE_S;
            } else if (species == 158) {
                return TOTODILE_TEXTURE_S;
            }
        }
        return EGG_TEXTURE;
    }





    public void render(PokemonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        int species = pEntity.getPokeSpecies();
        if(species == 152){
            this.model = new ModelChikorita(Minecraft.getInstance().getEntityModels().bakeLayer(CHIKORITA_LAYER_LOCATION));
        }else if(species == 153){
            this.model = new ModelBayleef<>(Minecraft.getInstance().getEntityModels().bakeLayer(BAYLEEF_LAYER_LOCATION));
        }else if(species == 155){
            this.model = new ModelCyndaquil<>(Minecraft.getInstance().getEntityModels().bakeLayer(CYNDAQUIL_LAYER_LOCATION));
        }else if(species == 156){
            this.model = new ModelQuilava<>(Minecraft.getInstance().getEntityModels().bakeLayer(QUILAVA_LAYER_LOCATION));
        }else if(species == 158){
            this.model = new ModelTotodile<>(Minecraft.getInstance().getEntityModels().bakeLayer(TOTODILE_LAYER_LOCATION));
        }else{
            this.model = new ModelEgg(Minecraft.getInstance().getEntityModels().bakeLayer(EGG_LAYER_LOCATION));
        }
        this.getTextureLocation(pEntity);
        this.shadowRadius = 0.05F * (float)pEntity.getSize();
        net.minecraftforge.client.event.RenderNameTagEvent renderNameplateEvent = new net.minecraftforge.client.event.RenderNameTagEvent(pEntity, pEntity.getDisplayName(), this, pMatrixStack, pBuffer, pPackedLight, pPartialTicks);
        this.renderLevel(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);
        this.renderName(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);
        this.renderHP(pEntity, renderNameplateEvent.getContent(), pMatrixStack, pBuffer, pPackedLight);


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);

    }

    protected float getWhiteOverlayProgress(PokemonEntity pLivingEntity, float pPartialTicks) {
        return pLivingEntity.getTimer() == 0 ? 0.0F : Mth.clamp(pLivingEntity.getTimer(), 0.0F, 1.0F);
    }

    protected void scale(PokemonEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(0.5F, 0.5F, 0.5F);
        pMatrixStack.translate(0.0D, 0.0F, 0.0D);
        float timer = (pLivingEntity.getTimer());
        float f1 = (float)pLivingEntity.getSize();
        float f2 = (float)pLivingEntity.getPokeLevel();
        pMatrixStack.scale((f1+(f2/85)+((timer)/40)), (f1+(f2/85)+((timer)/40)), (f1+(f2/85)+((timer)/40)));
    }

    protected void renderLevel(PokemonEntity pEntity,Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            float displayheight = pEntity.getBbHeight() + 0.9F;
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



    protected void renderHP(PokemonEntity pEntity,Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight){
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            float displayheight = pEntity.getBbHeight() + 0.5F;
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.0D, displayheight, 0.0D);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.scale(-0.0125F, -0.0125F, 0.0125F);
            Font font = this.getFont();
            float displaywidth = (-font.width("HP:" + (pEntity.getHealth())) / 2);
            String s = "HP: " + ((pEntity.getHealth()));
            if (flag) {
                this.getFont().draw(pMatrixStack, s, displaywidth, displayheight, 8453920);

            }
            pMatrixStack.popPose();


        }
    }



    protected void renderName(PokemonEntity pEntity, Component pDisplayName, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(pEntity);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(pEntity, d0)) {
            boolean flag = !pEntity.isDiscrete();
            float displayheight = pEntity.getBbHeight() + 0.8F;
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

