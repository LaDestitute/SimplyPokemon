package com.aquatictyphoon.pokemonmod.setup.advanced.items;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class PokeScannerItem extends Item {


    public PokeScannerItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(@Nonnull() ItemStack stack, Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("pokescanner.description"));

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {

        if(!pPlayer.level.isClientSide && pInteractionTarget instanceof PokemonEntity){
            pPlayer.sendMessage(new TextComponent("" + pInteractionTarget.getPersistentData().getString("Species")+ "Level" + pInteractionTarget.getPersistentData().getString("Level")), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("Type" + pInteractionTarget.getPersistentData().getString("Type1") + " " + pInteractionTarget.getPersistentData().getString("Type2")), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("Size" + pInteractionTarget.getPersistentData().getString("Size")), pPlayer.getUUID());
        }

        return InteractionResult.PASS;
    }
}
