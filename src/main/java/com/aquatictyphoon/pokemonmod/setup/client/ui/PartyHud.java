package com.aquatictyphoon.pokemonmod.setup.client.ui;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PartyHud {
    private static final ResourceLocation PARTY_UI = new ResourceLocation(PokemonMod.MODID,
            "textures/gui/party_ui.png");

    public static final IGuiOverlay HUD_PARTY = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, PARTY_UI);
        //You may need to fiddle with the x/y values to get it where ya want
        GuiComponent.blit(poseStack,x - 134, y - 204,0,0,32,193,
                32,193);

    });
}
