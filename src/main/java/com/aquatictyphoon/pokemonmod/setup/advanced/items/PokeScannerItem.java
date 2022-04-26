package com.aquatictyphoon.pokemonmod.setup.advanced.items;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.network.chat.Component;
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
        tooltip.add(new TranslatableComponent("item.pokemonmod.pokescanner.description"));

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }




        public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {


        if(pInteractionTarget instanceof PokemonEntity) {
            String nickname = String.valueOf(((PokemonEntity) pInteractionTarget).getPokeName());
            String level = String.valueOf(((PokemonEntity) pInteractionTarget).getPokeLevel());

            String type1 = String.valueOf(((PokemonEntity) pInteractionTarget).getType1Name());
            String type2 = String.valueOf(((PokemonEntity) pInteractionTarget).getType2Name());
            String size = String.valueOf(((PokemonEntity) pInteractionTarget).getSizeName());
            String nature = String.valueOf(((PokemonEntity) pInteractionTarget).getNatureName());

            if (!pPlayer.level.isClientSide && pInteractionTarget instanceof PokemonEntity) {
                pPlayer.sendMessage(new TranslatableComponent("" + (nickname) + ", Level: " + (level)), pPlayer.getUUID());

                pPlayer.sendMessage(new TranslatableComponent("Type " + (type1) + ", " + (type2)), pPlayer.getUUID());
                pPlayer.sendMessage(new TranslatableComponent("Size: " + (size)), pPlayer.getUUID());
                pPlayer.sendMessage(new TranslatableComponent("Nature: " + (nature)), pPlayer.getUUID());
            }
        }
        return InteractionResult.PASS;
    }
}
