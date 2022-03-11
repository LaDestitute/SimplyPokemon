package com.aquatictyphoon.pokemonmod.setup.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import static com.aquatictyphoon.pokemonmod.setup.Registration.POKE_BALL;
import static com.aquatictyphoon.pokemonmod.setup.Registration.Pokeball;

public class Pokeball_Entity extends ThrowableItemProjectile {


    public Pokeball_Entity(EntityType<Pokeball_Entity> type, Level level) {
        super(type, level);

    }

    public Pokeball_Entity(LivingEntity entity, Level level){
        super(POKE_BALL.get(), entity, level);
    }

    public Pokeball_Entity(double x, double y, double z, Level level) {
        super(POKE_BALL.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return Pokeball.get();
    }


}
