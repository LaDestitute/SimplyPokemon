package com.aquatictyphoon.pokemonmod.setup.advanced.items.projectiles;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class UltraBallItem extends PokeBallItem{
    public UltraBallItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        CompoundTag nbt = pStack.getOrCreateTag();
        nbt.putFloat("ballBonus", 2f);

        pStack.setTag(nbt);
    }


    @Override
    @Nonnull
    public Component getName(@Nonnull ItemStack stack) {
        if (!containsEntity(stack))
            return Component.translatable(I18n.get("item.pokemonmod.ultraball"));
        else{

            String s0 = species(stack);
            String s1 = s0.replace(':','.');
            return Component.translatable(name(stack) +": "+ I18n.get(s1));}
    }
}
