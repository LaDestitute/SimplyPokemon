package com.aquatictyphoon.pokemonmod.setup.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import static com.aquatictyphoon.pokemonmod.setup.Registration.POKE_BALL;
import static com.aquatictyphoon.pokemonmod.setup.Registration.Pokeball;

public class Pokeball_Entity extends ThrowableItemProjectile {


    public Pokeball_Entity(EntityType<Pokeball_Entity> type, Level level) {
        super(type, level);

    }

    public Pokeball_Entity(LivingEntity entity, Level level) {
        super(POKE_BALL.get(), entity, level);
    }


    protected void onHitEntity(EntityHitResult result) {
        EntityHitResult entityRayTrace = result;
        Entity target = entityRayTrace.getEntity();
            if (result.getType() == EntityHitResult.Type.ENTITY && (!(target instanceof Player))) {

                String entityID = EntityType.getKey(target.getType()).toString();
                ItemStack owned_ball = new ItemStack(Pokeball.get());
                CompoundTag nbt;
                nbt = target.serializeNBT();
                nbt.putString("entity", entityID);
                owned_ball.getOrCreateTag().put("entity_data", nbt);
                ItemEntity owned_ball_drop = new ItemEntity(target.level, target.getX(), target.getY(), target.getZ(), owned_ball);
                level.addFreshEntity(owned_ball_drop);
                target.discard();
        }
    }



    protected void onHitBlock(BlockHitResult result) {
        if (!level.isClientSide) {
            this.discard();
        }
    }

    protected @NotNull Item getDefaultItem() {
        return Pokeball.get();
    }

}
