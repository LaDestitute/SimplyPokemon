package com.aquatictyphoon.pokemonmod.setup.advanced.items;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.network.chat.Component;
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
        tooltip.add(Component.translatable("item.pokemonmod.pokescanner.description"));

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


            String true_hp = String.valueOf(((PokemonEntity) pInteractionTarget).getTrueHP());
            String true_attack = String.valueOf(((PokemonEntity) pInteractionTarget).getTrueAttack());
            String true_sp_attack = String.valueOf(((PokemonEntity) pInteractionTarget).getTrueSPAttack());
            String true_defence = String.valueOf(((PokemonEntity) pInteractionTarget).getTrueDefence());
            String true_sp_defence = String.valueOf(((PokemonEntity) pInteractionTarget).getTrueSPDefence());
            String true_speed = String.valueOf(((PokemonEntity) pInteractionTarget).getTrueSpeed());
            String happiness = String.valueOf(((PokemonEntity) pInteractionTarget).getHappiness());


            if (!pPlayer.level.isClientSide && pInteractionTarget instanceof PokemonEntity) {
                pPlayer.displayClientMessage(Component.translatable("" + (nickname) + ": Level: " + (level)), false);

                pPlayer.displayClientMessage(Component.translatable("Type: " + (type1) + " / " + (type2)),false);
                pPlayer.displayClientMessage(Component.translatable("Size: " + (size) + " Nature: " + (nature)),false);
                pPlayer.displayClientMessage(Component.translatable("Hp Ivs: " + (ivs_hp) + " ,Attack Ivs: " + (ivs_attack) + " ,Defence Ivs: " + (ivs_defence) + " ,Special Attack Ivs: " + (ivs_sp_attack) + " ,Special Defence Ivs: " + (ivs_sp_defence)  + " ,Speed Ivs: " + (ivs_speed)), false);
                pPlayer.displayClientMessage(Component.translatable("Base Hp : " + (base_hp) + " ,Base Attack: " + (base_attack) + " ,Base Defence: " + (base_defence) + " ,Base Special Attack: " + (base_sp_attack) + " ,Base Special Defence: " + (base_sp_defence)  + " ,Base Speed: " + (base_speed)), false);
                pPlayer.displayClientMessage(Component.translatable("Hp : " + (true_hp) + " ,Attack: " + (true_attack) + " ,Defence: " + (true_defence) + " ,Special Attack: " + (true_sp_attack) + " ,Special Defence: " + (true_sp_defence)  + " ,Speed: " + (true_speed)),false);
                pPlayer.displayClientMessage(Component.translatable("Hapiness : " + (happiness)), false);
            }
        }
        return InteractionResult.PASS;
    }
}
