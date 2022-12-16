package com.aquatictyphoon.pokemonmod.setup.pokeballs;

import com.aquatictyphoon.pokemonmod.setup.entities.PokeballEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PokeBallItem extends Item {

    public PokeBallItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        pPlayer.getCooldowns().addCooldown(this, 5);
            ItemStack PokeballItem = pPlayer.getItemInHand(pHand);
            if (PokeballItem != null){
                PokeballEntity projectile = new PokeballEntity(pPlayer, pLevel, PokeballItem, false);
                projectile.setItem(PokeballItem);
                projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.3F, 1.0F);
                pLevel.addFreshEntity(projectile);
                PokeballItem.shrink(1);
            }return InteractionResultHolder.sidedSuccess(PokeballItem, pLevel.isClientSide());
    }
}
