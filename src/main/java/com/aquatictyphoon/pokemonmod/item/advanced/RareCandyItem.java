package com.aquatictyphoon.pokemonmod.item.advanced;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;




public class RareCandyItem extends Item {

    public RareCandyItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if(pLivingEntity instanceof Player){
            ((Player)pLivingEntity).giveExperienceLevels(1);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);




    }
}
