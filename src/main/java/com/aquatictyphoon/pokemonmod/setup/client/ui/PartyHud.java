package com.aquatictyphoon.pokemonmod.setup.client.ui;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PartyHud {
    private static final ResourceLocation PARTY_UI = new ResourceLocation(PokemonMod.MODID, "textures/gui/party_ui.png");
    public static final IGuiOverlay HUD_PARTY = ((gui, poseStack, partialTick, width, height) -> {
        int xPos = width / 2;
        int yPos = height;
        int xSize = 32 ;
        int ySize = 193;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, PARTY_UI);
        GuiComponent.blit(poseStack, xPos, yPos, 0, 0, xSize, ySize, xSize, ySize);

    });
}
