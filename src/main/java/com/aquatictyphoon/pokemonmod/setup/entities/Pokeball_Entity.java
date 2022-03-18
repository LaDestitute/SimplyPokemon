package com.aquatictyphoon.pokemonmod.setup.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static com.aquatictyphoon.pokemonmod.setup.Registration.POKEBALL;
import static com.aquatictyphoon.pokemonmod.setup.Registration.POKE_BALL;

public class Pokeball_Entity extends ThrowableItemProjectile {

    public Pokeball_Entity(EntityType<Pokeball_Entity> type, Level level) {
        super(type, level);
    }

    public Pokeball_Entity(LivingEntity entity, Level level, InteractionHand pHand) {
        super(POKE_BALL.get(), entity, level);
        this.setOwner(entity);
        PokeballItem = entity.getItemInHand(pHand);
    }

    private static ItemStack PokeballItem;


    protected Item getDefaultItem() {
        return (POKEBALL.get());
    }

    public boolean containsEntity() {
        return this.PokeballItem.hasTag() && this.PokeballItem.getTag().contains("entity");
    }

    public Entity getEntityFromNBT(CompoundTag nbt, Level level, boolean withInfo) {
        @SuppressWarnings("deprecation")
        Entity entity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(nbt.getString("entity"))).create(level);
        if (withInfo)
            entity.load(nbt);
        return entity;
    }

    protected void onHitEntity(EntityHitResult result)
    {
        Entity target = result.getEntity();
        if (result.getType() == EntityHitResult.Type.ENTITY && (!(result.getEntity() instanceof Player))) {
            if (!level.isClientSide) {
                if (containsEntity())
                {
                   // Entity owned_pokemon = getEntityFromNBT(nbt, target.level, true);
                   // BlockPos blockPos = this.blockPosition();
                   // owned_pokemon.absMoveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0);
                   // level.addFreshEntity(owned_pokemon);
                    System.out.println("RELEASE SUCCESS!");
                }
                else
                {
                    CompoundTag entityid;
                    entityid = target.serializeNBT();
                    ItemStack emptyball = new ItemStack(POKEBALL.get());
                    emptyball.getOrCreateTag().put("entity", entityid);
                    ItemEntity owned_ball_drop = new ItemEntity(target.level, target.getX(), target.getY(), target.getZ(), emptyball);
                    target.level.addFreshEntity(owned_ball_drop);
                    target.discard();
                    System.out.println("CAPTURE SUCCESS!");
                }
            }
        }
    }


    protected void onHitBlock(@NotNull BlockHitResult result) {
        if (!level.isClientSide) {
            this.discard();
        }
    }
}