package com.aquatictyphoon.pokemonmod.setup.advanced.items.projectiles;

import com.aquatictyphoon.pokemonmod.setup.entities.misc.Pokeball_Entity;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

public class PokeBallItem extends Item {
    public PokeBallItem(Properties pProperties) {

        super(pProperties);
    }


    @Override
    public void appendHoverText(@Nonnull() ItemStack stack, Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        if (containsEntity(stack)) {
            if (getID(stack).isEmpty()) {
                tooltip.add(Component.translatable("tooltip.pokemonmod.item.pokeball"));
            }
        }

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    public static String name(ItemStack stack)
    {
        return stack.getOrCreateTag().getString("Nickname");
    }

    public static String species(ItemStack stack)
    {
        return stack.getOrCreateTag().getString("Species");
    }

    @Override
    @Nonnull
    public Component getName(@Nonnull ItemStack stack) {
        if (!containsEntity(stack))
            return Component.translatable(I18n.get("item.pokemonmod.pokeball"));
        else{

        String s0 = species(stack);
        String s1 = s0.replace(':','.');
        return Component.translatable(name(stack) +": "+ I18n.get(s1));}
    }

    //Helper
    public static boolean containsEntity(@Nonnull ItemStack stack) {
        if (!stack.hasTag()) return false;
        assert stack.getTag() != null;
        return stack.getTag().contains("entity");
    }

    public static String getID(ItemStack stack)
    {
        return stack.getOrCreateTag().getString("entity");
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        pPlayer.getCooldowns().addCooldown(this, 10);
        ItemStack PokeballItem = pPlayer.getItemInHand(pHand);


        if ((!pLevel.isClientSide) && !(getID(pPlayer.getItemInHand(pHand)).isEmpty()))
        {
            Pokeball_Entity projectile = new Pokeball_Entity(pPlayer, pLevel, pHand);
            projectile.setItem(PokeballItem);
            projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.3F, 1.0F);
            pLevel.addFreshEntity(projectile);
        }

        if ((!pLevel.isClientSide) && (getID(pPlayer.getItemInHand(pHand)).isEmpty()))
        {
             Pokeball_Entity projectile = new Pokeball_Entity(pPlayer, pLevel, pHand);
             projectile.setItem(PokeballItem);
             projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.3F, 1.0F);
             pLevel.addFreshEntity(projectile);
             PokeballItem.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(PokeballItem, pLevel.isClientSide());

    }



}
