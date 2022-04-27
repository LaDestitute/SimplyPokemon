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

            String ivs_hp = String.valueOf(((PokemonEntity) pInteractionTarget).getIvsHP());
            String ivs_attack = String.valueOf(((PokemonEntity) pInteractionTarget).getIvsAttack());
            String ivs_sp_attack = String.valueOf(((PokemonEntity) pInteractionTarget).getIvsSPAttack());
            String ivs_defence = String.valueOf(((PokemonEntity) pInteractionTarget).getIvsDefence());
            String ivs_sp_defence = String.valueOf(((PokemonEntity) pInteractionTarget).getIvsSPDefence());
            String ivs_speed = String.valueOf(((PokemonEntity) pInteractionTarget).getIvsSpeed());

            String base_hp = String.valueOf(((PokemonEntity) pInteractionTarget).getBaseHP());
            String base_attack = String.valueOf(((PokemonEntity) pInteractionTarget).getBaseAttack());
            String base_sp_attack = String.valueOf(((PokemonEntity) pInteractionTarget).getBaseSPAttack());
            String base_defence = String.valueOf(((PokemonEntity) pInteractionTarget).getBaseDefence());
            String base_sp_defence = String.valueOf(((PokemonEntity) pInteractionTarget).getBaseSPDefence());
            String base_speed = String.valueOf(((PokemonEntity) pInteractionTarget).getBaseSpeed());



            if (!pPlayer.level.isClientSide && pInteractionTarget instanceof PokemonEntity) {
                pPlayer.sendMessage(new TranslatableComponent("" + (nickname) + ": Level: " + (level)), pPlayer.getUUID());

                pPlayer.sendMessage(new TranslatableComponent("Type: " + (type1) + " / " + (type2)), pPlayer.getUUID());
                pPlayer.sendMessage(new TranslatableComponent("Size: " + (size) + " Nature: " + (nature)), pPlayer.getUUID());

                pPlayer.sendMessage(new TranslatableComponent("Hp Ivs: " + (ivs_hp) + " ,Attack Ivs: " + (ivs_attack) + " ,Defence Ivs: " + (ivs_defence) + " ,Special Attack Ivs: " + (ivs_sp_attack) + " ,Special Defence Ivs: " + (ivs_sp_defence)  + " ,Speed Ivs: " + (ivs_speed)), pPlayer.getUUID());
                pPlayer.sendMessage(new TranslatableComponent("Hp Ivs: " + (base_hp) + " ,Attack Ivs: " + (base_attack) + " ,Defence Ivs: " + (base_defence) + " ,Special Attack Ivs: " + (base_sp_attack) + " ,Special Defence Ivs: " + (base_sp_defence)  + " ,Speed Ivs: " + (base_speed)), pPlayer.getUUID());

            }
        }
        return InteractionResult.PASS;
    }
}
