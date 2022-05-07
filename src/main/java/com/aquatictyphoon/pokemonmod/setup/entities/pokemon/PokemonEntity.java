package com.aquatictyphoon.pokemonmod.setup.entities.pokemon;

import com.aquatictyphoon.pokemonmod.setup.capability.PokemonNature;
import com.aquatictyphoon.pokemonmod.setup.capability.PokemonTypes;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.Registration;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("ALL")
public class PokemonEntity extends TamableAnimal {

    private int inLove;
    boolean isday = this.level.isDay();

    @javax.annotation.Nullable


    private static final EntityDataAccessor<Float> EVOLUTIONTIMER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> EVOLVING = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HASNICKNAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> POKEMON_LEVEL = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> SPECIESNAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> NICKNAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> SHINY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAPPINESS = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> NATURE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Integer> EVS_HP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EVS_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EVS_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EVS_SP_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EVS_SP_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EVS_SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> IVS_HP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IVS_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IVS_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IVS_SP_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IVS_SP_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IVS_SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> BASE_HP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_SP_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_SP_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> TRUE_HP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_SP_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_SP_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Integer> MOVE_SLOT_1 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> MOVE_SLOT_1_CURRENT_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2_CURRENT_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3_CURRENT_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4_CURRENT_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> MOVE_SLOT_1_MAX_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2_MAX_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3_MAX_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4_MAX_PP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> MOVE_SLOT_1_TYPE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2_TYPE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3_TYPE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4_TYPE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> MOVE_SLOT_1_CATEGORY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2_CATEGORY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3_CATEGORY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4_CATEGORY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> MOVE_SLOT_1_POWER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2_POWER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3_POWER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4_POWER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> MOVE_SLOT_1_ACCURACY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_2_ACCURACY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_3_ACCURACY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVE_SLOT_4_ACCURACY = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<String> MOVE_SLOT_1_NAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> MOVE_SLOT_2_NAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> MOVE_SLOT_3_NAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> MOVE_SLOT_4_NAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<Integer> CATCHRATE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> TYPE1 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TYPE2 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> EVOLUTIONBRANCH = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

 // 0 is no gender 1 is male and 2 is female.
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    public PokemonEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        setCustomNameVisible(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 1).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Registration.RARECANDY.get()), false));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob parent) {
        return null;
    }

    protected void setMoveSlot1(){
        //Category; 0 is physical, 1 is special, 2 is status
        int move = this.getMoveSlot1();
        int inf = (int) Math.pow(99999, 999999999);
        if(move == 0) {
            this.entityData.set(MOVE_SLOT_1_NAME , "Struggle");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10000);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NONE.ordinal());
        }else if(move == 7  ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Fire Punch");
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 8  ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ice Punch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 9  ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thunder Punch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 14 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thunder Punch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , inf );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 33 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Tackle");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 35);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 38 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Double-Edge");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 40 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Poison Sting");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 35);
            this.entityData.set(MOVE_SLOT_1_POWER , 15 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 42 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Poison Sting");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 35);
            this.entityData.set(MOVE_SLOT_1_POWER , 15 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 44 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Pin Missile");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 25 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 52 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bite");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DARK.ordinal());
        }else if(move == 53 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ember");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 56 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Flamethrower");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 58 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Hydro Pump");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 59 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Blizzard");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 110 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 70);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 63 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Hyper Beam");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 9);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 71 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Absorb");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 30 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 77 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Poision Powder");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 78 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Stun Spore");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 79 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Sleep Powder");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 80 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Petal Dance");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 84 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thundershock");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 85 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thunderbolt");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 86 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thunder Wave");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 87 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thunder");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 75);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 93 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Confusion");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 94 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Psychic");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 95 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Hypnosis");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 70);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 98 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Quick Attack");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 30);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 100 ){
            this.entityData.set(MOVE_SLOT_1_NAME , "Teleport");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 102){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mimic");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 105){
            this.entityData.set(MOVE_SLOT_1_NAME , "Recover");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 116){
            this.entityData.set(MOVE_SLOT_1_NAME , "Focus Energy");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 120){
            this.entityData.set(MOVE_SLOT_1_NAME , "Self-Destruct");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 150 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 120);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 126){
            this.entityData.set(MOVE_SLOT_1_NAME , "Fire-Blast");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 129){
            this.entityData.set(MOVE_SLOT_1_NAME , "Swift");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 135){
            this.entityData.set(MOVE_SLOT_1_NAME , "Soft-Boiled");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 139){
            this.entityData.set(MOVE_SLOT_1_NAME , "Poison Gas");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 3);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 141){
            this.entityData.set(MOVE_SLOT_1_NAME , "Leech Life");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 145){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bubble");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.WATER.ordinal());
        }else if(move == 147){
            this.entityData.set(MOVE_SLOT_1_NAME , "Spore");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 150){
            this.entityData.set(MOVE_SLOT_1_NAME , "Slash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 40);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 151){
            this.entityData.set(MOVE_SLOT_1_NAME , "Acid Armor");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 156){
            this.entityData.set(MOVE_SLOT_1_NAME , "Rest");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 157){
            this.entityData.set(MOVE_SLOT_1_NAME , "Rock Slide");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 161){
            this.entityData.set(MOVE_SLOT_1_NAME , "Tri Attack");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 163){
            this.entityData.set(MOVE_SLOT_1_NAME , "Slash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 165){
            this.entityData.set(MOVE_SLOT_1_NAME , "Struggle");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , inf);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 172){
            this.entityData.set(MOVE_SLOT_1_NAME , "Flame Wheel");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 181){
            this.entityData.set(MOVE_SLOT_1_NAME , "Powder Snow");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 183){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mach Punch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 188){
            this.entityData.set(MOVE_SLOT_1_NAME , "Sludge Bomb");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 189){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mud Slap");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 30 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 190){
            this.entityData.set(MOVE_SLOT_1_NAME , "Octazooka");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.WATER.ordinal());
        }else if(move == 191){
            this.entityData.set(MOVE_SLOT_1_NAME , "Spikes");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 196){
            this.entityData.set(MOVE_SLOT_1_NAME , "Icy Wind");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 200){
            this.entityData.set(MOVE_SLOT_1_NAME , "Outrage");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 205){
            this.entityData.set(MOVE_SLOT_1_NAME , "Rollout");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 30 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 206){
            this.entityData.set(MOVE_SLOT_1_NAME , "False Swipe");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 30);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 209){
            this.entityData.set(MOVE_SLOT_1_NAME , "Spark");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 30);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 224){
            this.entityData.set(MOVE_SLOT_1_NAME , "Megahorn");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 231){
            this.entityData.set(MOVE_SLOT_1_NAME , "Iron Tail");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 75);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 237){
            this.entityData.set(MOVE_SLOT_1_NAME , "Hidden Power");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 239){
            this.entityData.set(MOVE_SLOT_1_NAME , "Twister");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 242){
            this.entityData.set(MOVE_SLOT_1_NAME , "Crunch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DARK.ordinal());
        }else if(move == 246){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ancient Power");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 247){
            this.entityData.set(MOVE_SLOT_1_NAME , "Shadow Ball");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 249){
            this.entityData.set(MOVE_SLOT_1_NAME , "Rock Smash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 301){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ice Ball");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 310){
            this.entityData.set(MOVE_SLOT_1_NAME , "Astonish");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 30 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 314){
            this.entityData.set(MOVE_SLOT_1_NAME , "Air Cutter");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 315){
            this.entityData.set(MOVE_SLOT_1_NAME , "Overheat");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 195 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 318){
            this.entityData.set(MOVE_SLOT_1_NAME , "Silver Wind");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 45);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 326){
            this.entityData.set(MOVE_SLOT_1_NAME , "Extrasensory");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 332){
            this.entityData.set(MOVE_SLOT_1_NAME , "Aerial Ace ");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 75);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 334){
            this.entityData.set(MOVE_SLOT_1_NAME , "Iron Defence");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 337){
            this.entityData.set(MOVE_SLOT_1_NAME , "Dragon Claw");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 339){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bulk Up");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 344){
            this.entityData.set(MOVE_SLOT_1_NAME , "Volt Tackle");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 345){
            this.entityData.set(MOVE_SLOT_1_NAME , "Magical Leaf");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 75);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 347){
            this.entityData.set(MOVE_SLOT_1_NAME , "Calm Mind");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 348){
            this.entityData.set(MOVE_SLOT_1_NAME , "Leaf Blade");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 85 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 352){
            this.entityData.set(MOVE_SLOT_1_NAME , "Water Pulse");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 355){
            this.entityData.set(MOVE_SLOT_1_NAME , "Roost");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 370){
            this.entityData.set(MOVE_SLOT_1_NAME , "Close Combat");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 394){
            this.entityData.set(MOVE_SLOT_1_NAME , "Flare Blitz");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 396){
            this.entityData.set(MOVE_SLOT_1_NAME , "Aura Sphere");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 398){
            this.entityData.set(MOVE_SLOT_1_NAME , "Poision Jab");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 399){
            this.entityData.set(MOVE_SLOT_1_NAME , "Dark Pulse");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 400){
            this.entityData.set(MOVE_SLOT_1_NAME , "Night Slash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DARK.ordinal());
        }else if(move == 401){
            this.entityData.set(MOVE_SLOT_1_NAME , "Aqua Tail");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 85 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.WATER.ordinal());
        }else if(move == 403){
            this.entityData.set(MOVE_SLOT_1_NAME , "Air Slash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 404){
            this.entityData.set(MOVE_SLOT_1_NAME , "X-Scizor");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 405){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bug Buzz");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 406){
            this.entityData.set(MOVE_SLOT_1_NAME , "Dragon Pulse");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 408){
            this.entityData.set(MOVE_SLOT_1_NAME , "Power Gem");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 409){
            this.entityData.set(MOVE_SLOT_1_NAME , "Drain Punch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 412){
            this.entityData.set(MOVE_SLOT_1_NAME , "Energy Ball");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 413){
            this.entityData.set(MOVE_SLOT_1_NAME , "Brave Bird");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 414){
            this.entityData.set(MOVE_SLOT_1_NAME , "Earth Power");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 416){
            this.entityData.set(MOVE_SLOT_1_NAME , "Giga Impact");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 417){
            this.entityData.set(MOVE_SLOT_1_NAME , "Nasty Plot");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DARK.ordinal());
        }else if(move == 418){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bullet Punch");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 420){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ice Shard");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 421){
            this.entityData.set(MOVE_SLOT_1_NAME , "Shadow Claw");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 422){
            this.entityData.set(MOVE_SLOT_1_NAME , "Thunder Fang");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 423){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ice Fang");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 424){
            this.entityData.set(MOVE_SLOT_1_NAME , "Fire Fang");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 425){
            this.entityData.set(MOVE_SLOT_1_NAME , "Shadow Sneak");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 426){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mud Bomb");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 427){
            this.entityData.set(MOVE_SLOT_1_NAME , "Psycho Cut");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 428){
            this.entityData.set(MOVE_SLOT_1_NAME , "Zen Headbutt");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 430){
            this.entityData.set(MOVE_SLOT_1_NAME , "Flash Cannon");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 434){
            this.entityData.set(MOVE_SLOT_1_NAME , "Draco Meteor");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 110 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 437){
            this.entityData.set(MOVE_SLOT_1_NAME , "Leaf Storm");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 110 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 440){
            this.entityData.set(MOVE_SLOT_1_NAME , "Cross Poison");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 442){
            this.entityData.set(MOVE_SLOT_1_NAME , "Iron Head");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 444){
            this.entityData.set(MOVE_SLOT_1_NAME , "Stone Edge");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 446){
            this.entityData.set(MOVE_SLOT_1_NAME , "Stealth Rock");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 449){
            this.entityData.set(MOVE_SLOT_1_NAME , "Judgement");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 451){
            this.entityData.set(MOVE_SLOT_1_NAME , "Charge Beams");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 452){
            this.entityData.set(MOVE_SLOT_1_NAME , "Wood Hammer");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 453){
            this.entityData.set(MOVE_SLOT_1_NAME , "Aqua Jet");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.WATER.ordinal());
        }else if(move == 457){
            this.entityData.set(MOVE_SLOT_1_NAME , "Head Smash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 458){
            this.entityData.set(MOVE_SLOT_1_NAME , "Double Hit");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 459){
            this.entityData.set(MOVE_SLOT_1_NAME , "Roar of Time");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 460){
            this.entityData.set(MOVE_SLOT_1_NAME , "Spacial Rend");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DRAGON.ordinal());
        }else if(move == 462){
            this.entityData.set(MOVE_SLOT_1_NAME , "Crush Grip");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.NORMAL.ordinal());
        }else if(move == 463){
            this.entityData.set(MOVE_SLOT_1_NAME , "Magma Storm");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 75);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 464){
            this.entityData.set(MOVE_SLOT_1_NAME , "Dark Void");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DARK.ordinal());
        }else if(move == 465){
            this.entityData.set(MOVE_SLOT_1_NAME , "Seed Flare");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 85);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 466){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ominous Wind");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 467){
            this.entityData.set(MOVE_SLOT_1_NAME , "Shadow Force");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 120);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 474){
            this.entityData.set(MOVE_SLOT_1_NAME , "Venoshock");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 491){
            this.entityData.set(MOVE_SLOT_1_NAME , "Acid Spray");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 506){
            this.entityData.set(MOVE_SLOT_1_NAME , "Hex");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 522){
            this.entityData.set(MOVE_SLOT_1_NAME , "Struggle Bug");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.BUG.ordinal());
        }else if(move == 523){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bulldoze");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 528){
            this.entityData.set(MOVE_SLOT_1_NAME , "Wild Charge");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 90);
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 542){
            this.entityData.set(MOVE_SLOT_1_NAME , "Hurricane");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 75);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 555){
            this.entityData.set(MOVE_SLOT_1_NAME , "Snarl");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.DARK.ordinal());
        }else if(move == 556){
            this.entityData.set(MOVE_SLOT_1_NAME , "Icicle Crash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 577){
            this.entityData.set(MOVE_SLOT_1_NAME , "Draining Kiss");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 583){
            this.entityData.set(MOVE_SLOT_1_NAME , "Play Rough");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 85 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 584){
            this.entityData.set(MOVE_SLOT_1_NAME , "Fairy Wind");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 585){
            this.entityData.set(MOVE_SLOT_1_NAME , "Moonblast");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 595){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mystical Fire");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 605){
            this.entityData.set(MOVE_SLOT_1_NAME , "Dazzling Gleam");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 608){
            this.entityData.set(MOVE_SLOT_1_NAME , "Baby Doll Eyes");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 20);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 667){
            this.entityData.set(MOVE_SLOT_1_NAME , "High Horsepower");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 85 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 670){
            this.entityData.set(MOVE_SLOT_1_NAME , "Leafage");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 25);
            this.entityData.set(MOVE_SLOT_1_POWER , 40 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 710){
            this.entityData.set(MOVE_SLOT_1_NAME , "Liquidation");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 80 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.WATER.ordinal());
        }else if(move == 796){
            this.entityData.set(MOVE_SLOT_1_NAME , "Steel Beam");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 827){
            this.entityData.set(MOVE_SLOT_1_NAME , "Dire Claw");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 828){
            this.entityData.set(MOVE_SLOT_1_NAME , "Psyshield Bash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 829){
            this.entityData.set(MOVE_SLOT_1_NAME , "Power Shift");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 830){
            this.entityData.set(MOVE_SLOT_1_NAME , "Stone Axe");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ROCK.ordinal());
        }else if(move == 831){
            this.entityData.set(MOVE_SLOT_1_NAME , "Springtide Storm");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 95 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 115);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 832){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mystical Power");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FAIRY.ordinal());
        }else if(move == 833){
            this.entityData.set(MOVE_SLOT_1_NAME , "Raging Fury");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 95 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIRE.ordinal());
        }else if(move == 834){
            this.entityData.set(MOVE_SLOT_1_NAME , "Wave Crash");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 70 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.WATER.ordinal());
        }else if(move == 835){
            this.entityData.set(MOVE_SLOT_1_NAME , "Chloroblast");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 120 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 95);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GRASS.ordinal());
        }else if(move == 836){
            this.entityData.set(MOVE_SLOT_1_NAME , "Mountain Gale");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 95 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ICE.ordinal());
        }else if(move == 837){
            this.entityData.set(MOVE_SLOT_1_NAME , "Victory Dance");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 838){
            this.entityData.set(MOVE_SLOT_1_NAME , "Headlong Rush");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 100 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 839){
            this.entityData.set(MOVE_SLOT_1_NAME , "Barb Barrage");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.POISON.ordinal());
        }else if(move == 840){
            this.entityData.set(MOVE_SLOT_1_NAME , "Esper Wing");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 75 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 841){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bitter Malice");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 60 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 842){
            this.entityData.set(MOVE_SLOT_1_NAME , "Shelter");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 2);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 843){
            this.entityData.set(MOVE_SLOT_1_NAME , "Triple Arrow");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 50 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FIGHTING.ordinal());
        }else if(move == 844){
            this.entityData.set(MOVE_SLOT_1_NAME , "Infernal Parade");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 90 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 100);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GHOST.ordinal());
        }else if(move == 845){
            this.entityData.set(MOVE_SLOT_1_NAME , "Ceaseless Edge");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 15);
            this.entityData.set(MOVE_SLOT_1_POWER , 65 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 90);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 0);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.STEEL.ordinal());
        }else if(move == 846){
            this.entityData.set(MOVE_SLOT_1_NAME , "Bleakwind Storm");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 95 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.FLYING.ordinal());
        }else if(move == 847){
            this.entityData.set(MOVE_SLOT_1_NAME , "Wildbolt Storm");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 95 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.ELECTRIC.ordinal());
        }else if(move == 848){
            this.entityData.set(MOVE_SLOT_1_NAME , "Sandsear Storm");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 5);
            this.entityData.set(MOVE_SLOT_1_POWER , 95 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , 80);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.GROUND.ordinal());
        }else if(move == 849){
            this.entityData.set(MOVE_SLOT_1_NAME , "Lunar Blessing");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }else if(move == 850) {
            this.entityData.set(MOVE_SLOT_1_NAME , "Take Heart");
            this.entityData.set(MOVE_SLOT_1_MAX_PP , 10);
            this.entityData.set(MOVE_SLOT_1_POWER , 0 );
            this.entityData.set(MOVE_SLOT_1_ACCURACY , inf);
            this.entityData.set(MOVE_SLOT_1_CATEGORY , 1);
            this.entityData.set(MOVE_SLOT_1_TYPE , PokemonTypes.PSYCHIC.ordinal());
        }

    }


















    @SuppressWarnings({"SpellCheckingInspection"})
    protected void setPokeStats(){
        int species = this.getPokeSpecies();
        if(species == 0) {
            this.entityData.set(TYPE1, PokemonTypes.NONE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NONE.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 49);
            this.entityData.set(BASE_DEFENCE, 49);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "BadEgg");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 1) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 49);
            this.entityData.set(BASE_DEFENCE, 49);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Bulbasaur");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 2) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 62);
            this.entityData.set(BASE_DEFENCE, 63);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Ivysaur");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 3) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 80);
            this.entityData.set(BASE_ATTACK, 82);
            this.entityData.set(BASE_DEFENCE, 83);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Venusaur");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 4) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 39);
            this.entityData.set(BASE_ATTACK, 52);
            this.entityData.set(BASE_DEFENCE, 43);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Charmander");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 5) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 58);
            this.entityData.set(BASE_ATTACK, 64);
            this.entityData.set(BASE_DEFENCE, 58);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Charmeleon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 6) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 78);
            this.entityData.set(BASE_ATTACK, 84);
            this.entityData.set(BASE_DEFENCE, 78);
            this.entityData.set(BASE_SP_ATTACK, 109);
            this.entityData.set(BASE_SP_DEFENCE, 85);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Charizard");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 7) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 44);
            this.entityData.set(BASE_ATTACK, 48);
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK, 50);
            this.entityData.set(BASE_SP_DEFENCE, 64);
            this.entityData.set(BASE_SPEED, 43);
            this.entityData.set(SPECIESNAME, "Squirtle");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 8) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 59);
            this.entityData.set(BASE_ATTACK, 63);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 78);
            this.entityData.set(SPECIESNAME, "Wartortle");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 9) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 79);
            this.entityData.set(BASE_ATTACK, 83);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 108);
            this.entityData.set(BASE_SPEED, 78);
            this.entityData.set(SPECIESNAME, "Blastoise");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 10) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.BUG.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 30);
            this.entityData.set(BASE_DEFENCE,35 );
            this.entityData.set(BASE_SP_ATTACK,20 );
            this.entityData.set(BASE_SP_DEFENCE,20 );
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Caterpie");
            this.entityData.set(CATCHRATE, 255);
        }

        else if(species == 11) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.BUG.ordinal());
            this.entityData.set(BASE_HP,50 );
            this.entityData.set(BASE_ATTACK,20 );
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK, 25);
            this.entityData.set(BASE_SP_DEFENCE,25 );
            this.entityData.set(BASE_SPEED,30 );
            this.entityData.set(SPECIESNAME, "Metapod");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 12) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK, 45);
            this.entityData.set(BASE_DEFENCE,50 );
            this.entityData.set(BASE_SP_ATTACK,90 );
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Butterfree");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 13) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK,35 );
            this.entityData.set(BASE_DEFENCE,30 );
            this.entityData.set(BASE_SP_ATTACK, 20);
            this.entityData.set(BASE_SP_DEFENCE,20 );
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Weedle");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 14) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,45 );
            this.entityData.set(BASE_ATTACK,25 );
            this.entityData.set(BASE_DEFENCE,50 );
            this.entityData.set(BASE_SP_ATTACK, 25);
            this.entityData.set(BASE_SP_DEFENCE, 25);
            this.entityData.set(BASE_SPEED,35 );
            this.entityData.set(SPECIESNAME, "Kakuna");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 15) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK,90 );
            this.entityData.set(BASE_DEFENCE,40 );
            this.entityData.set(BASE_SP_ATTACK, 45);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED,75 );
            this.entityData.set(SPECIESNAME, "Beedrill");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 16) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE,40 );
            this.entityData.set(BASE_SP_ATTACK,35 );
            this.entityData.set(BASE_SP_DEFENCE,35 );
            this.entityData.set(BASE_SPEED,56 );
            this.entityData.set(SPECIESNAME, "Pidgey");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 17) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,63 );
            this.entityData.set(BASE_ATTACK,60 );
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK,50 );
            this.entityData.set(BASE_SP_DEFENCE,50 );
            this.entityData.set(BASE_SPEED,71 );
            this.entityData.set(SPECIESNAME, "Pidgeotto");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 18) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,83 );
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED,101 );
            this.entityData.set(SPECIESNAME, "Pidgeot");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species ==19) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 30);
            this.entityData.set(BASE_ATTACK, 56);
            this.entityData.set(BASE_DEFENCE,35 );
            this.entityData.set(BASE_SP_ATTACK, 25);
            this.entityData.set(BASE_SP_DEFENCE,35 );
            this.entityData.set(BASE_SPEED, 72);
            this.entityData.set(SPECIESNAME, "Rattata");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 20) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK,81 );
            this.entityData.set(BASE_DEFENCE,60 );
            this.entityData.set(BASE_SP_ATTACK, 50);
            this.entityData.set(BASE_SP_DEFENCE,70 );
            this.entityData.set(BASE_SPEED, 97);
            this.entityData.set(SPECIESNAME, "Raticate");
            this.entityData.set(CATCHRATE, 127);
        }
        else if(species == 21) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK, 60);
            this.entityData.set(BASE_DEFENCE, 30);
            this.entityData.set(BASE_SP_ATTACK, 31);
            this.entityData.set(BASE_SP_DEFENCE,31);
            this.entityData.set(BASE_SPEED,70 );
            this.entityData.set(SPECIESNAME, "Spearow");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 22) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK,90 );
            this.entityData.set(BASE_DEFENCE,65 );
            this.entityData.set(BASE_SP_ATTACK,61 );
            this.entityData.set(BASE_SP_DEFENCE, 61);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Fearow");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 23) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK,60 );
            this.entityData.set(BASE_DEFENCE,44 );
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE, 54);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Ekans");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 24) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 69);
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE,79 );
            this.entityData.set(BASE_SPEED,80 );
            this.entityData.set(SPECIESNAME, "Arbok");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 25) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP,35 );
            this.entityData.set(BASE_ATTACK,55 );
            this.entityData.set(BASE_DEFENCE,40 );
            this.entityData.set(BASE_SP_ATTACK, 50);
            this.entityData.set(BASE_SP_DEFENCE,50 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Pikachu");
            this.entityData.set(CATCHRATE, 190);

        }
        else if(species == 26) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK,90 );
            this.entityData.set(BASE_DEFENCE,55 );
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED, 110);
            this.entityData.set(SPECIESNAME, "Raichu");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 27) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,50 );
            this.entityData.set(BASE_ATTACK,75 );
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK,20 );
            this.entityData.set(BASE_SP_DEFENCE,30 );
            this.entityData.set(BASE_SPEED,40 );
            this.entityData.set(SPECIESNAME, "Sandshrew");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 28) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,75 );
            this.entityData.set(BASE_ATTACK, 100);
            this.entityData.set(BASE_DEFENCE, 110);
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED,65 );
            this.entityData.set(SPECIESNAME, "Sandslash");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 29) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,55 );
            this.entityData.set(BASE_ATTACK,47 );
            this.entityData.set(BASE_DEFENCE,52 );
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE,40 );
            this.entityData.set(BASE_SPEED,41 );
            this.entityData.set(SPECIESNAME, "Nidoran ♀");
            this.entityData.set(CATCHRATE, 235);
        }
        else if(species == 30) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,70 );
            this.entityData.set(BASE_ATTACK,62 );
            this.entityData.set(BASE_DEFENCE, 67);
            this.entityData.set(BASE_SP_ATTACK,55 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED,56 );
            this.entityData.set(SPECIESNAME, "Nidorina");
            this.entityData.set(CATCHRATE, 120);
        }

        else if(species == 31) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 92);
            this.entityData.set(BASE_DEFENCE, 87);
            this.entityData.set(BASE_SP_ATTACK,75 );
            this.entityData.set(BASE_SP_DEFENCE,85 );
            this.entityData.set(BASE_SPEED,76 );
            this.entityData.set(SPECIESNAME, "Nidoqueen");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 32) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 46);
            this.entityData.set(BASE_ATTACK,57 );
            this.entityData.set(BASE_DEFENCE,40 );
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE,40 );
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Nidoran ♂");
            this.entityData.set(CATCHRATE, 235);
        }
        else if(species == 33) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,61 );
            this.entityData.set(BASE_ATTACK,72 );
            this.entityData.set(BASE_DEFENCE, 57);
            this.entityData.set(BASE_SP_ATTACK,55 );
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED,65 );
            this.entityData.set(SPECIESNAME, "Nidorino");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 34) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 81);
            this.entityData.set(BASE_ATTACK,102 );
            this.entityData.set(BASE_DEFENCE,77 );
            this.entityData.set(BASE_SP_ATTACK,85 );
            this.entityData.set(BASE_SP_DEFENCE,75 );
            this.entityData.set(BASE_SPEED,85 );
            this.entityData.set(SPECIESNAME, "Nidoking");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 35) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP,70 );
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE, 48);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Clefairy");
            this.entityData.set(CATCHRATE, 150);
        }
        else if(species == 36) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 95);
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE,73 );
            this.entityData.set(BASE_SP_ATTACK,95 );
            this.entityData.set(BASE_SP_DEFENCE,90 );
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Clefable");
            this.entityData.set(CATCHRATE, 25);
        }
        else if(species == 37) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP,38 );
            this.entityData.set(BASE_ATTACK,41 );
            this.entityData.set(BASE_DEFENCE, 40);
            this.entityData.set(BASE_SP_ATTACK,50 );
            this.entityData.set(BASE_SP_DEFENCE,65 );
            this.entityData.set(BASE_SPEED,65 );
            this.entityData.set(SPECIESNAME, "Vulpix");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 38) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP,73 );
            this.entityData.set(BASE_ATTACK,76 );
            this.entityData.set(BASE_DEFENCE,75 );
            this.entityData.set(BASE_SP_ATTACK,81 );
            this.entityData.set(BASE_SP_DEFENCE,100 );
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Ninetales");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 39) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 115);
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE,20 );
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE,25 );
            this.entityData.set(BASE_SPEED,20 );
            this.entityData.set(SPECIESNAME, "Jigglypuff");
            this.entityData.set(CATCHRATE, 170);
        }
        else if(species == 40) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 140);
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE,45 );
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Wigglytuff");
            this.entityData.set(CATCHRATE, 50);
        }
        else if(species == 41) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE,35 );
            this.entityData.set(BASE_SP_ATTACK,30 );
            this.entityData.set(BASE_SP_DEFENCE,40 );
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Zubat");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 42) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE,70 );
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE,75 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Golbat");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 43) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,45 );
            this.entityData.set(BASE_ATTACK,50 );
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK, 75);
            this.entityData.set(BASE_SP_DEFENCE,65 );
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Oddish");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 44) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE,70 );
            this.entityData.set(BASE_SP_ATTACK,85 );
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Gloom");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 45) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,75 );
            this.entityData.set(BASE_ATTACK,80 );
            this.entityData.set(BASE_DEFENCE,85 );
            this.entityData.set(BASE_SP_ATTACK,110 );
            this.entityData.set(BASE_SP_DEFENCE,90 );
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Vileplume");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 46) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP,35 );
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED, 25);
            this.entityData.set(SPECIESNAME, "Paras");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 47) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Parasect");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 48) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 55);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Venonat");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 49) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Venomoth");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 50) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,10 );
            this.entityData.set(BASE_ATTACK,55 );
            this.entityData.set(BASE_DEFENCE, 25);
            this.entityData.set(BASE_SP_ATTACK,35 );
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED,95 );
            this.entityData.set(SPECIESNAME, "Diglett");
            this.entityData.set(CATCHRATE, 50);
        }
        else if(species == 51) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK,100 );
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 50);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED,120 );
            this.entityData.set(SPECIESNAME, "Dugtrio");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 52) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE,35 );
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE,40 );
            this.entityData.set(BASE_SPEED, 90);
            this.entityData.set(SPECIESNAME, "Meowth");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 53) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE,60 );
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE,65 );
            this.entityData.set(BASE_SPEED, 115);
            this.entityData.set(SPECIESNAME, "Persian");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 54) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,50 );
            this.entityData.set(BASE_ATTACK,52 );
            this.entityData.set(BASE_DEFENCE, 48);
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE,50 );
            this.entityData.set(BASE_SPEED,55 );
            this.entityData.set(SPECIESNAME, "Psyduck");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 55) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,80 );
            this.entityData.set(BASE_ATTACK,82 );
            this.entityData.set(BASE_DEFENCE, 78);
            this.entityData.set(BASE_SP_ATTACK, 95);
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED,85 );
            this.entityData.set(SPECIESNAME, "Golduck");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 56) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK,80 );
            this.entityData.set(BASE_DEFENCE, 35);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Mankey");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 57) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK,105 );
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 95);
            this.entityData.set(SPECIESNAME, "Primape");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 58) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Growlthie");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 59) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 110);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED,95 );
            this.entityData.set(SPECIESNAME, "Arcanine");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 60) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE,40 );
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE,40 );
            this.entityData.set(BASE_SPEED, 90);
            this.entityData.set(SPECIESNAME, "Poliwag");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 61) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE,65 );
            this.entityData.set(BASE_SP_ATTACK,50 );
            this.entityData.set(BASE_SP_DEFENCE,50 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Poliwhirl");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 62) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK,95 );
            this.entityData.set(BASE_DEFENCE,95 );
            this.entityData.set(BASE_SP_ATTACK,70 );
            this.entityData.set(BASE_SP_DEFENCE,90 );
            this.entityData.set(BASE_SPEED,70 );
            this.entityData.set(SPECIESNAME, "Poliwrath");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 63) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 25);
            this.entityData.set(BASE_ATTACK, 20);
            this.entityData.set(BASE_DEFENCE, 15);
            this.entityData.set(BASE_SP_ATTACK,105 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Abra");
            this.entityData.set(CATCHRATE, 200);
        }
        else if(species == 64) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 25);
            this.entityData.set(BASE_ATTACK,20 );
            this.entityData.set(BASE_DEFENCE,15 );
            this.entityData.set(BASE_SP_ATTACK,105 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED, 90);
            this.entityData.set(SPECIESNAME, "Kadabra");
            this.entityData.set(CATCHRATE, 100);
        }
        else if(species == 65) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,50 );
            this.entityData.set(BASE_ATTACK,50 );
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK,135 );
            this.entityData.set(BASE_SP_DEFENCE,95 );
            this.entityData.set(BASE_SPEED, 120);
            this.entityData.set(SPECIESNAME, "Alakazam");
            this.entityData.set(CATCHRATE, 50);
        }
        else if(species == 66) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK,80 );
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK,35 );
            this.entityData.set(BASE_SP_DEFENCE, 35);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Machop");
            this.entityData.set(CATCHRATE, 180);
        }
        else if(species == 67) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 80);
            this.entityData.set(BASE_ATTACK, 100);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 50);
            this.entityData.set(BASE_SP_DEFENCE,60 );
            this.entityData.set(BASE_SPEED,45 );
            this.entityData.set(SPECIESNAME, "Machoke");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 68) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 130);
            this.entityData.set(BASE_DEFENCE,80 );
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE,85 );
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Machamp");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 69) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 35);
            this.entityData.set(BASE_SP_ATTACK,70 );
            this.entityData.set(BASE_SP_DEFENCE, 30);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Bellsprout");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 70) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK,90 );
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Weepinbell");
            this.entityData.set(CATCHRATE, 120);
        }

        else if(species == 71) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,80 );
            this.entityData.set(BASE_ATTACK,105 );
            this.entityData.set(BASE_DEFENCE,65 );
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED,70 );
            this.entityData.set(SPECIESNAME, "Victreebel");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 72) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 35);
            this.entityData.set(BASE_SP_ATTACK,50 );
            this.entityData.set(BASE_SP_DEFENCE,100 );
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Tentacool");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 73) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,80 );
            this.entityData.set(BASE_ATTACK,70 );
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK,80 );
            this.entityData.set(BASE_SP_DEFENCE,120 );
            this.entityData.set(BASE_SPEED,100 );
            this.entityData.set(SPECIESNAME, "Tentacrule");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 74) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK,30 );
            this.entityData.set(BASE_SP_DEFENCE,30 );
            this.entityData.set(BASE_SPEED, 20);
            this.entityData.set(SPECIESNAME, "Geodude");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 75) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 115);
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Graveler");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 76) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,80 );
            this.entityData.set(BASE_ATTACK,120 );
            this.entityData.set(BASE_DEFENCE,130 );
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE,65 );
            this.entityData.set(BASE_SPEED,45 );
            this.entityData.set(SPECIESNAME, "Golem");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 77) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP,50 );
            this.entityData.set(BASE_ATTACK,85 );
            this.entityData.set(BASE_DEFENCE,55 );
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE,65 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Ponyta");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 78) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK, 100);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED, 105);
            this.entityData.set(SPECIESNAME, "Rapidash");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 79) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE, 40);
            this.entityData.set(BASE_SPEED,15 );
            this.entityData.set(SPECIESNAME, "Slowpoke");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 80) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 95);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 110);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED,30 );
            this.entityData.set(SPECIESNAME, "Slowbro");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 81) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.STEEL.ordinal());
            this.entityData.set(BASE_HP,25 );
            this.entityData.set(BASE_ATTACK, 35);
            this.entityData.set(BASE_DEFENCE,70 );
            this.entityData.set(BASE_SP_ATTACK,95 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Magnemite");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 82) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.STEEL.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK,60 );
            this.entityData.set(BASE_DEFENCE,95 );
            this.entityData.set(BASE_SP_ATTACK,120 );
            this.entityData.set(BASE_SP_DEFENCE,70 );
            this.entityData.set(BASE_SPEED,70 );
            this.entityData.set(SPECIESNAME, "Magneton");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 83) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 52);
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE,55 );
            this.entityData.set(BASE_SP_ATTACK,58 );
            this.entityData.set(BASE_SP_DEFENCE,62 );
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Farfetch'd");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 84) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK,85 );
            this.entityData.set(BASE_DEFENCE,45 );
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 35);
            this.entityData.set(BASE_SPEED,75);
            this.entityData.set(SPECIESNAME, "Doduo");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 85) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK,110 );
            this.entityData.set(BASE_DEFENCE,70 );
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE,60 );
            this.entityData.set(BASE_SPEED, 110);
            this.entityData.set(SPECIESNAME, "Dodrio");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 86) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE,70 );
            this.entityData.set(BASE_SPEED,45 );
            this.entityData.set(SPECIESNAME, "Seel");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 87) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ICE.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK,70 );
            this.entityData.set(BASE_DEFENCE,80 );
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE,95 );
            this.entityData.set(BASE_SPEED,70 );
            this.entityData.set(SPECIESNAME, "Dewgong");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 88) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,80 );
            this.entityData.set(BASE_ATTACK,80 );
            this.entityData.set(BASE_DEFENCE,50 );
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE,50 );
            this.entityData.set(BASE_SPEED,25 );
            this.entityData.set(SPECIESNAME, "Grimer");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 89) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,105 );
            this.entityData.set(BASE_ATTACK,105 );
            this.entityData.set(BASE_DEFENCE,75 );
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED,50 );
            this.entityData.set(SPECIESNAME, "Muk");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 90) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,30 );
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 45);
            this.entityData.set(BASE_SP_DEFENCE,25 );
            this.entityData.set(BASE_SPEED,40 );
            this.entityData.set(SPECIESNAME, "Shellder");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 91) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ICE.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE,180 );
            this.entityData.set(BASE_SP_ATTACK,85 );
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED,70 );
            this.entityData.set(SPECIESNAME, "Cloyster");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 92) {
            this.entityData.set(TYPE1, PokemonTypes.GHOST.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 30);
            this.entityData.set(BASE_ATTACK,35 );
            this.entityData.set(BASE_DEFENCE, 30);
            this.entityData.set(BASE_SP_ATTACK,100 );
            this.entityData.set(BASE_SP_DEFENCE,35 );
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Gastly");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 93) {
            this.entityData.set(TYPE1, PokemonTypes.GHOST.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK,50 );
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK,115 );
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 95);
            this.entityData.set(SPECIESNAME, "Haunter");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 94) {
            this.entityData.set(TYPE1, PokemonTypes.GHOST.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE,60 );
            this.entityData.set(BASE_SP_ATTACK,130 );
            this.entityData.set(BASE_SP_DEFENCE,75 );
            this.entityData.set(BASE_SPEED,110 );
            this.entityData.set(SPECIESNAME, "Gengar");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 95) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,35 );
            this.entityData.set(BASE_ATTACK, 45);
            this.entityData.set(BASE_DEFENCE, 160);
            this.entityData.set(BASE_SP_ATTACK, 30);
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Onix");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 96) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK, 48);
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK,43 );
            this.entityData.set(BASE_SP_DEFENCE,90 );
            this.entityData.set(BASE_SPEED, 42);
            this.entityData.set(SPECIESNAME, "Drowzee");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 97) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 85);
            this.entityData.set(BASE_ATTACK,73 );
            this.entityData.set(BASE_DEFENCE,70 );
            this.entityData.set(BASE_SP_ATTACK,73 );
            this.entityData.set(BASE_SP_DEFENCE,115 );
            this.entityData.set(BASE_SPEED, 67);
            this.entityData.set(SPECIESNAME, "Hypno");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 98) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,30 );
            this.entityData.set(BASE_ATTACK,105 );
            this.entityData.set(BASE_DEFENCE, 90);
            this.entityData.set(BASE_SP_ATTACK,25 );
            this.entityData.set(BASE_SP_DEFENCE,25 );
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Krabby");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 99) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,55 );
            this.entityData.set(BASE_ATTACK,130 );
            this.entityData.set(BASE_DEFENCE,115 );
            this.entityData.set(BASE_SP_ATTACK,50 );
            this.entityData.set(BASE_SP_DEFENCE,50 );
            this.entityData.set(BASE_SPEED,75 );
            this.entityData.set(SPECIESNAME, "Kingler");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 100) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK,30 );
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Voltob");
            this.entityData.set(CATCHRATE, 190);
        }

        else if(species == 101) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK,50 );
            this.entityData.set(BASE_DEFENCE,70 );
            this.entityData.set(BASE_SP_ATTACK,80 );
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED, 150);
            this.entityData.set(SPECIESNAME, "Electrode");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 102) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK,40 );
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Exeggcute");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 103) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,95 );
            this.entityData.set(BASE_ATTACK,95 );
            this.entityData.set(BASE_DEFENCE,85 );
            this.entityData.set(BASE_SP_ATTACK, 125);
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED,55 );
            this.entityData.set(SPECIESNAME, "Exeggutor");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 104) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK,50 );
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Cubone");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 105) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE,110 );
            this.entityData.set(BASE_SP_ATTACK,50 );
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED,45 );
            this.entityData.set(SPECIESNAME, "Marowak");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 106) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK,120 );
            this.entityData.set(BASE_DEFENCE,53 );
            this.entityData.set(BASE_SP_ATTACK,35 );
            this.entityData.set(BASE_SP_DEFENCE, 110);
            this.entityData.set(BASE_SPEED,87 );
            this.entityData.set(SPECIESNAME, "Hitmonlee");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 107) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP,50 );
            this.entityData.set(BASE_ATTACK,120 );
            this.entityData.set(BASE_DEFENCE, 53);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE,110 );
            this.entityData.set(BASE_SPEED,87 );
            this.entityData.set(SPECIESNAME, "Hitmonchan");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 108) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK,55 );
            this.entityData.set(BASE_DEFENCE,75 );
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE,75 );
            this.entityData.set(BASE_SPEED,30 );
            this.entityData.set(SPECIESNAME, "Lickitung");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 109) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Koffing");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 110) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE,120 );
            this.entityData.set(BASE_SP_ATTACK,85 );
            this.entityData.set(BASE_SP_DEFENCE,70 );
            this.entityData.set(BASE_SPEED,60 );
            this.entityData.set(SPECIESNAME, "Weezing");
            this.entityData.set(CATCHRATE, 60);
        }

        else if(species == 111) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,80 );
            this.entityData.set(BASE_ATTACK,85 );
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK,30 );
            this.entityData.set(BASE_SP_DEFENCE,30 );
            this.entityData.set(BASE_SPEED,25 );
            this.entityData.set(SPECIESNAME, "Rhyhorn");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 112) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP,105 );
            this.entityData.set(BASE_ATTACK,130 );
            this.entityData.set(BASE_DEFENCE, 120);
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED,40 );
            this.entityData.set(SPECIESNAME, "Rhydon");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 113) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP,250 );
            this.entityData.set(BASE_ATTACK, 5);
            this.entityData.set(BASE_DEFENCE,5 );
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 105);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Chansey");
            this.entityData.set(CATCHRATE, 30);
        }
        else if(species == 114) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK,55 );
            this.entityData.set(BASE_DEFENCE,155 );
            this.entityData.set(BASE_SP_ATTACK,100 );
            this.entityData.set(BASE_SP_DEFENCE, 40);
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Tangela");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 115) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 105);
            this.entityData.set(BASE_ATTACK,95 );
            this.entityData.set(BASE_DEFENCE,80 );
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Kangaskhan");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 116) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,30 );
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK,70 );
            this.entityData.set(BASE_SP_DEFENCE,25 );
            this.entityData.set(BASE_SPEED,60 );
            this.entityData.set(SPECIESNAME, "Horsea");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 117) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE,95 );
            this.entityData.set(BASE_SP_ATTACK, 95);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Seadra");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 118) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,45 );
            this.entityData.set(BASE_ATTACK,67 );
            this.entityData.set(BASE_DEFENCE,60 );
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 63);
            this.entityData.set(SPECIESNAME, "Goldeen");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 119) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 80);
            this.entityData.set(BASE_ATTACK, 92);
            this.entityData.set(BASE_DEFENCE,65 );
            this.entityData.set(BASE_SP_ATTACK,65 );
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED, 68);
            this.entityData.set(SPECIESNAME, "Seeking");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 120) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 30);
            this.entityData.set(BASE_ATTACK,45 );
            this.entityData.set(BASE_DEFENCE,55 );
            this.entityData.set(BASE_SP_ATTACK,70 );
            this.entityData.set(BASE_SP_DEFENCE,55 );
            this.entityData.set(BASE_SPEED,85 );
            this.entityData.set(SPECIESNAME, "Staryu");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 121) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,60 );
            this.entityData.set(BASE_ATTACK,75 );
            this.entityData.set(BASE_DEFENCE,85 );
            this.entityData.set(BASE_SP_ATTACK,100 );
            this.entityData.set(BASE_SP_DEFENCE,85 );
            this.entityData.set(BASE_SPEED,115 );
            this.entityData.set(SPECIESNAME, "Starmie");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 122) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP,40 );
            this.entityData.set(BASE_ATTACK, 45);
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK,100 );
            this.entityData.set(BASE_SP_DEFENCE,120 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Mr. Mime");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 123) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,70 );
            this.entityData.set(BASE_ATTACK,110 );
            this.entityData.set(BASE_DEFENCE,80 );
            this.entityData.set(BASE_SP_ATTACK,55 );
            this.entityData.set(BASE_SP_DEFENCE,80 );
            this.entityData.set(BASE_SPEED, 105);
            this.entityData.set(SPECIESNAME, "Scyther");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 124) {
            this.entityData.set(TYPE1, PokemonTypes.ICE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE,35 );
            this.entityData.set(BASE_SP_ATTACK,115 );
            this.entityData.set(BASE_SP_DEFENCE,95 );
            this.entityData.set(BASE_SPEED,95 );
            this.entityData.set(SPECIESNAME, "Jynx");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 125) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 83);
            this.entityData.set(BASE_DEFENCE,57 );
            this.entityData.set(BASE_SP_ATTACK,95 );
            this.entityData.set(BASE_SP_DEFENCE,85 );
            this.entityData.set(BASE_SPEED,105 );
            this.entityData.set(SPECIESNAME, "Electabuzz");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 126) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 57);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE,85 );
            this.entityData.set(BASE_SPEED, 93);
            this.entityData.set(SPECIESNAME, "Magmar");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 127) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.BUG.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 125);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Pinsir");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 128) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP,75 );
            this.entityData.set(BASE_ATTACK,100 );
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK,40 );
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED,110 );
            this.entityData.set(SPECIESNAME, "Tauros");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 129) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 20);
            this.entityData.set(BASE_ATTACK, 10);
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK,15 );
            this.entityData.set(BASE_SP_DEFENCE,20 );
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Magikarp");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 130) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,95 );
            this.entityData.set(BASE_ATTACK,125 );
            this.entityData.set(BASE_DEFENCE, 79);
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE,100 );
            this.entityData.set(BASE_SPEED, 81);
            this.entityData.set(SPECIESNAME, "Gyarados");
            this.entityData.set(CATCHRATE, 45);
        }

        else if(species == 131) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ICE.ordinal());
            this.entityData.set(BASE_HP, 130);
            this.entityData.set(BASE_ATTACK, 85);
            this.entityData.set(BASE_DEFENCE,80 );
            this.entityData.set(BASE_SP_ATTACK,85 );
            this.entityData.set(BASE_SP_DEFENCE,95 );
            this.entityData.set(BASE_SPEED,60 );
            this.entityData.set(SPECIESNAME, "Lapras");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 132) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP,48 );
            this.entityData.set(BASE_ATTACK, 48);
            this.entityData.set(BASE_DEFENCE, 48);
            this.entityData.set(BASE_SP_ATTACK,48 );
            this.entityData.set(BASE_SP_DEFENCE,48 );
            this.entityData.set(BASE_SPEED,48 );
            this.entityData.set(SPECIESNAME, "Ditto");
            this.entityData.set(CATCHRATE, 35);
        }
        else if(species == 133) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK,55 );
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK,45 );
            this.entityData.set(BASE_SP_DEFENCE,65 );
            this.entityData.set(BASE_SPEED,55 );
            this.entityData.set(SPECIESNAME, "Eevee");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 134) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 130);
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK,110 );
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED,65 );
            this.entityData.set(SPECIESNAME, "Vaporeon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 135) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK,65 );
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK, 110);
            this.entityData.set(BASE_SP_DEFENCE,95 );
            this.entityData.set(BASE_SPEED,130 );
            this.entityData.set(SPECIESNAME, "Jolteon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 136) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK,130 );
            this.entityData.set(BASE_DEFENCE,60 );
            this.entityData.set(BASE_SP_ATTACK, 95);
            this.entityData.set(BASE_SP_DEFENCE,110 );
            this.entityData.set(BASE_SPEED,65 );
            this.entityData.set(SPECIESNAME, "Flareon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 137) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP,65 );
            this.entityData.set(BASE_ATTACK,60 );
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE,75 );
            this.entityData.set(BASE_SPEED,40 );
            this.entityData.set(SPECIESNAME, "Porygon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 138) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK,40 );
            this.entityData.set(BASE_DEFENCE, 400);
            this.entityData.set(BASE_SP_ATTACK,90 );
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED,35 );
            this.entityData.set(SPECIESNAME, "Omanyte");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 139) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,70 );
            this.entityData.set(BASE_ATTACK,60 );
            this.entityData.set(BASE_DEFENCE,125 );
            this.entityData.set(BASE_SP_ATTACK,155 );
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Omastar");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 140) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP,30 );
            this.entityData.set(BASE_ATTACK,80 );
            this.entityData.set(BASE_DEFENCE,90 );
            this.entityData.set(BASE_SP_ATTACK,55 );
            this.entityData.set(BASE_SP_DEFENCE,45 );
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Kabuto");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 141) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 115);
            this.entityData.set(BASE_DEFENCE, 105);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Kabutops");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 142) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 80);
            this.entityData.set(BASE_ATTACK,105 );
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK,60 );
            this.entityData.set(BASE_SP_DEFENCE,75 );
            this.entityData.set(BASE_SPEED,130 );
            this.entityData.set(SPECIESNAME, "Aerodactyl");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 143) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 160);
            this.entityData.set(BASE_ATTACK,110 );
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE,110 );
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Snorlax");
            this.entityData.set(CATCHRATE, 25);
        }
        else if(species == 144) {
            this.entityData.set(TYPE1, PokemonTypes.ICE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK,85 );
            this.entityData.set(BASE_DEFENCE,100 );
            this.entityData.set(BASE_SP_ATTACK,95 );
            this.entityData.set(BASE_SP_DEFENCE,125 );
            this.entityData.set(BASE_SPEED,85 );
            this.entityData.set(SPECIESNAME, "Articuno");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 145) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,90 );
            this.entityData.set(BASE_ATTACK,90 );
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK,125 );
            this.entityData.set(BASE_SP_DEFENCE,90 );
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Zapdos");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 146) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,90 );
            this.entityData.set(BASE_ATTACK,100 );
            this.entityData.set(BASE_DEFENCE,90 );
            this.entityData.set(BASE_SP_ATTACK,125 );
            this.entityData.set(BASE_SP_DEFENCE,85 );
            this.entityData.set(BASE_SPEED,90 );
            this.entityData.set(SPECIESNAME, "Moltres");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 147) {
            this.entityData.set(TYPE1, PokemonTypes.DRAGON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.DRAGON.ordinal());
            this.entityData.set(BASE_HP,41 );
            this.entityData.set(BASE_ATTACK,64 );
            this.entityData.set(BASE_DEFENCE,45 );
            this.entityData.set(BASE_SP_ATTACK,50);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED,50 );
            this.entityData.set(SPECIESNAME, "Dratini");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 148) {
            this.entityData.set(TYPE1, PokemonTypes.DRAGON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.DRAGON.ordinal());
            this.entityData.set(BASE_HP,61 );
            this.entityData.set(BASE_ATTACK,84 );
            this.entityData.set(BASE_DEFENCE,65 );
            this.entityData.set(BASE_SP_ATTACK,70 );
            this.entityData.set(BASE_SP_DEFENCE,70 );
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Dragonair");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 149) {
            this.entityData.set(TYPE1, PokemonTypes.DRAGON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP,91);
            this.entityData.set(BASE_ATTACK,134 );
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE,100 );
            this.entityData.set(BASE_SPEED,80 );
            this.entityData.set(SPECIESNAME, "Dragonite");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 150) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,106 );
            this.entityData.set(BASE_ATTACK, 110);
            this.entityData.set(BASE_DEFENCE, 90);
            this.entityData.set(BASE_SP_ATTACK,154 );
            this.entityData.set(BASE_SP_DEFENCE,90 );
            this.entityData.set(BASE_SPEED,130 );
            this.entityData.set(SPECIESNAME, "Mewtwo");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 151) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP,100 );
            this.entityData.set(BASE_ATTACK,100 );
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK,100 );
            this.entityData.set(BASE_SP_DEFENCE,100 );
            this.entityData.set(BASE_SPEED,100 );
            this.entityData.set(SPECIESNAME, "Mew");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 152) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 25);
            this.entityData.set(BASE_ATTACK, 49);
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK, 49);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Chikorita");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 153) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 62);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 63);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Bayleef");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 154) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 80);
            this.entityData.set(BASE_ATTACK, 82);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 83);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Maganium");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 155) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 39);
            this.entityData.set(BASE_ATTACK, 52);
            this.entityData.set(BASE_DEFENCE, 43);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Cyndaquil");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 156) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 58);
            this.entityData.set(BASE_ATTACK, 64);
            this.entityData.set(BASE_DEFENCE, 58);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Quilava");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 157) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 78);
            this.entityData.set(BASE_ATTACK, 84);
            this.entityData.set(BASE_DEFENCE, 78);
            this.entityData.set(BASE_SP_ATTACK, 109);
            this.entityData.set(BASE_SP_DEFENCE, 85);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Typhlosion");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 158) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 64);
            this.entityData.set(BASE_SP_ATTACK, 44);
            this.entityData.set(BASE_SP_DEFENCE, 48);
            this.entityData.set(BASE_SPEED, 43);
            this.entityData.set(SPECIESNAME, "Totodile");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 159) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 59);
            this.entityData.set(BASE_SP_DEFENCE, 63);
            this.entityData.set(BASE_SPEED, 58);
            this.entityData.set(SPECIESNAME, "Crocanaaw");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 160) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 85);
            this.entityData.set(BASE_ATTACK, 105);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 79);
            this.entityData.set(BASE_SP_DEFENCE, 83);
            this.entityData.set(BASE_SPEED, 78);
            this.entityData.set(SPECIESNAME, "Feraligatr");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 161) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK, 46);
            this.entityData.set(BASE_DEFENCE, 34);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 20);
            this.entityData.set(SPECIESNAME, "Sentret");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 162) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 85);
            this.entityData.set(BASE_ATTACK, 76);
            this.entityData.set(BASE_DEFENCE, 64);
            this.entityData.set(BASE_SP_ATTACK, 45);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 90);
            this.entityData.set(SPECIESNAME, "Furret");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 163) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 30);
            this.entityData.set(BASE_DEFENCE, 30);
            this.entityData.set(BASE_SP_ATTACK, 36);
            this.entityData.set(BASE_SP_DEFENCE, 56);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Hoothoot");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 164) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 86);
            this.entityData.set(BASE_SP_DEFENCE, 96);
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Noctowl");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 165) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK, 20);
            this.entityData.set(BASE_DEFENCE, 30);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Ledyba");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 166) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 35);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 110);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Ledian");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 167) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK, 60);
            this.entityData.set(BASE_DEFENCE, 40);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 40);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Spinarak");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 168) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Ariados");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 169) {
            this.entityData.set(TYPE1, PokemonTypes.POISON.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 85);
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 130);
            this.entityData.set(SPECIESNAME, "Crobat");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 170) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 38);
            this.entityData.set(BASE_DEFENCE, 38);
            this.entityData.set(BASE_SP_ATTACK, 56);
            this.entityData.set(BASE_SP_DEFENCE, 56);
            this.entityData.set(BASE_SPEED, 67);
            this.entityData.set(SPECIESNAME, "Chinchou");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 171) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 125);
            this.entityData.set(BASE_ATTACK, 58);
            this.entityData.set(BASE_DEFENCE, 58);
            this.entityData.set(BASE_SP_ATTACK, 76);
            this.entityData.set(BASE_SP_DEFENCE, 76);
            this.entityData.set(BASE_SPEED, 67);
            this.entityData.set(SPECIESNAME, "Lanturn");
            this.entityData.set(CATCHRATE, 150);
        }

        else if(species == 172) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 24);
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 15);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 35);
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Pichu");
            this.entityData.set(CATCHRATE, 170);
        }
        else if(species == 173) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 25);
            this.entityData.set(BASE_DEFENCE, 28);
            this.entityData.set(BASE_SP_ATTACK, 45);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 15);
            this.entityData.set(SPECIESNAME, "Cleffa");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 174) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 30);
            this.entityData.set(BASE_DEFENCE, 15);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 20);
            this.entityData.set(BASE_SPEED, 15);
            this.entityData.set(SPECIESNAME, "Igglybuff");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 175) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK, 20);
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 20);
            this.entityData.set(SPECIESNAME, "Togepi");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 176) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 105);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Togetic");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 177) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Natu");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 178) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 95);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 95);
            this.entityData.set(SPECIESNAME, "Xatu");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 179) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 40);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Mareep");
            this.entityData.set(CATCHRATE, 235);
        }
        else if(species == 180) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 55);
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 60);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Flaaffy");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 181) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK, 115);
            this.entityData.set(BASE_SP_DEFENCE, 90);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Ampharos");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 182) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Bellossom");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 183) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 20);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 20);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Marill");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 184) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Azumarill");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 185) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ROCK.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 100);
            this.entityData.set(BASE_DEFENCE, 115);
            this.entityData.set(BASE_SP_ATTACK, 30);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Sudowoodo");
            this.entityData.set(CATCHRATE, 65);
        }
        else if(species == 186) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Politoed");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 187) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK, 35);
            this.entityData.set(BASE_DEFENCE, 40);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Hoppip");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 188) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 45);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 45);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 80);
            this.entityData.set(SPECIESNAME, "Skiploom");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 189) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 55);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED, 110);
            this.entityData.set(SPECIESNAME, "Jumpluff");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 190) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Aipom");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 191) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 30);
            this.entityData.set(BASE_ATTACK, 30);
            this.entityData.set(BASE_DEFENCE, 30);
            this.entityData.set(BASE_SP_ATTACK, 30);
            this.entityData.set(BASE_SP_DEFENCE, 30);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Sunkern");
            this.entityData.set(CATCHRATE, 235);
        }
        else if(species == 192) {
            this.entityData.set(TYPE1, PokemonTypes.GRASS.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK, 105);
            this.entityData.set(BASE_SP_DEFENCE, 85);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Sunflora");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 193) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK, 75);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 95);
            this.entityData.set(SPECIESNAME, "Yanma");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 194) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 45);
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK, 25);
            this.entityData.set(BASE_SP_DEFENCE, 25);
            this.entityData.set(BASE_SPEED, 15);
            this.entityData.set(SPECIESNAME, "Wooper");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 195) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 95);
            this.entityData.set(BASE_ATTACK, 85);
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Quagsire");
            this.entityData.set(CATCHRATE, 90);
        }
        else if(species == 196) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK, 130);
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED, 110);
            this.entityData.set(SPECIESNAME, "Espeon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 197) {
            this.entityData.set(TYPE1, PokemonTypes.DARK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.DARK.ordinal());
            this.entityData.set(BASE_HP, 95);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 110);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 130);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Umbreon");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 198) {
            this.entityData.set(TYPE1, PokemonTypes.DARK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 85);
            this.entityData.set(BASE_DEFENCE, 42);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 42);
            this.entityData.set(BASE_SPEED, 91);
            this.entityData.set(SPECIESNAME, "Murkrow");
            this.entityData.set(CATCHRATE, 30);
        }
        else if(species == 199) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 95);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE, 110);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Slowing");
            this.entityData.set(CATCHRATE, 70);
        }
        else if(species == 200) {
            this.entityData.set(TYPE1, PokemonTypes.GHOST.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GHOST.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 60);
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 85);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Misdreavus");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 201) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 48);
            this.entityData.set(BASE_ATTACK, 72);
            this.entityData.set(BASE_DEFENCE, 48);
            this.entityData.set(BASE_SP_ATTACK, 72);
            this.entityData.set(BASE_SP_DEFENCE, 48);
            this.entityData.set(BASE_SPEED, 48);
            this.entityData.set(SPECIESNAME, "Unown");
            this.entityData.set(CATCHRATE, 255);
        }

        else if(species == 202) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 190);
            this.entityData.set(BASE_ATTACK, 33);
            this.entityData.set(BASE_DEFENCE, 58);
            this.entityData.set(BASE_SP_ATTACK, 33);
            this.entityData.set(BASE_SP_DEFENCE, 58);
            this.entityData.set(BASE_SPEED, 33);
            this.entityData.set(SPECIESNAME, "Wobbuffet");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 203) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 65);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Girafarig");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 204) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.BUG.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 90);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 35);
            this.entityData.set(BASE_SPEED, 15);
            this.entityData.set(SPECIESNAME, "Pineco");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 205) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.STEEL.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE, 140);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 60);
            this.entityData.set(BASE_SPEED, 4);
            this.entityData.set(SPECIESNAME, "Forretress");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 206) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 70);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Dunsparce");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 207) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 105);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Gligar");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 208) {
            this.entityData.set(TYPE1, PokemonTypes.STEEL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 85);
            this.entityData.set(BASE_DEFENCE, 200);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Steelix");
            this.entityData.set(CATCHRATE, 25);
        }
        else if(species == 209) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 40);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Snubbull");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 210) {
            this.entityData.set(TYPE1, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FAIRY.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 120);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 60);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Granbull");
            this.entityData.set(CATCHRATE, 75);
        }

        else if(species == 211) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.POISON.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Qwilfish");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 212) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.STEEL.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 130);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 55);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Scizor");
            this.entityData.set(CATCHRATE, 25);
        }
        else if(species == 213) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ROCK.ordinal());
            this.entityData.set(BASE_HP, 20);
            this.entityData.set(BASE_ATTACK, 10);
            this.entityData.set(BASE_DEFENCE, 230);
            this.entityData.set(BASE_SP_ATTACK, 10);
            this.entityData.set(BASE_SP_DEFENCE, 230);
            this.entityData.set(BASE_SPEED, 5);
            this.entityData.set(SPECIESNAME, "Shuckle");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 214) {
            this.entityData.set(TYPE1, PokemonTypes.BUG.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 80);
            this.entityData.set(BASE_ATTACK, 125);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Heracross");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 215) {
            this.entityData.set(TYPE1, PokemonTypes.DARK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ICE.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 55);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED, 115);
            this.entityData.set(SPECIESNAME, "Sneasel");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 216) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 50);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Teddiursa");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 217) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 130);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 75);
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Ursaring");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 218) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 40);
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 40);
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE, 40);
            this.entityData.set(BASE_SPEED, 20);
            this.entityData.set(SPECIESNAME, "Slugma");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 219) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ROCK.ordinal());
            this.entityData.set(BASE_HP, 60);
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE, 120);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 30);
            this.entityData.set(SPECIESNAME, "Magcargo");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 220) {
            this.entityData.set(TYPE1, PokemonTypes.ICE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 50);
            this.entityData.set(BASE_DEFENCE, 40);
            this.entityData.set(BASE_SP_ATTACK, 30);
            this.entityData.set(BASE_SP_DEFENCE, 30);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Swinub");
            this.entityData.set(CATCHRATE, 255);
        }
        else if(species == 221) {
            this.entityData.set(TYPE1, PokemonTypes.ICE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 100);
            this.entityData.set(BASE_DEFENCE, 80);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 60);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Piloswine");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 222) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ROCK.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 55);
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Corsola");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 223) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK, 65);
            this.entityData.set(BASE_DEFENCE, 35);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 35);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Remoraid");
            this.entityData.set(CATCHRATE, 190);
        }
        else if(species == 224) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 105);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 105);
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED, 45);
            this.entityData.set(SPECIESNAME, "Octillery");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 225) {
            this.entityData.set(TYPE1, PokemonTypes.ICE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 55);
            this.entityData.set(BASE_DEFENCE, 45);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 75);
            this.entityData.set(SPECIESNAME, "Delibird");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 226) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 85);
            this.entityData.set(BASE_ATTACK, 40);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 140);
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Mantine");
            this.entityData.set(CATCHRATE, 25);
        }
        else if(species == 227) {
            this.entityData.set(TYPE1, PokemonTypes.STEEL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 65);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 140);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Skarmory");
            this.entityData.set(CATCHRATE, 25);
        }
        else if(species == 228) {
            this.entityData.set(TYPE1, PokemonTypes.DARK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 60);
            this.entityData.set(BASE_DEFENCE, 30);
            this.entityData.set(BASE_SP_ATTACK, 80);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Houndour");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 229) {
            this.entityData.set(TYPE1, PokemonTypes.DARK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 110);
            this.entityData.set(BASE_SP_DEFENCE, 80);
            this.entityData.set(BASE_SPEED, 95);
            this.entityData.set(SPECIESNAME, "Houndoom");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 230) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.DRAGON.ordinal());
            this.entityData.set(BASE_HP, 75);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK, 95);
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Kingdra");
            this.entityData.set(CATCHRATE, 45);
        }

        else if(species == 231) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 60);
            this.entityData.set(BASE_DEFENCE, 60);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 40);
            this.entityData.set(BASE_SPEED, 40);
            this.entityData.set(SPECIESNAME, "Phanpy");
            this.entityData.set(CATCHRATE, 120);
        }
        else if(species == 232) {
            this.entityData.set(TYPE1, PokemonTypes.GROUND.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 120);
            this.entityData.set(BASE_DEFENCE, 120);
            this.entityData.set(BASE_SP_ATTACK, 60);
            this.entityData.set(BASE_SP_DEFENCE, 60);
            this.entityData.set(BASE_SPEED, 50);
            this.entityData.set(SPECIESNAME, "Donphan");
            this.entityData.set(CATCHRATE, 60);
        }
        else if(species == 233) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 85);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 90);
            this.entityData.set(BASE_SP_ATTACK, 105);
            this.entityData.set(BASE_SP_DEFENCE, 95);
            this.entityData.set(BASE_SPEED, 60);
            this.entityData.set(SPECIESNAME, "Porygon 2");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 234) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 73);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 62);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Stantler");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 235) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 55);
            this.entityData.set(BASE_ATTACK, 20);
            this.entityData.set(BASE_DEFENCE, 35);
            this.entityData.set(BASE_SP_ATTACK, 20);
            this.entityData.set(BASE_SP_DEFENCE, 45);
            this.entityData.set(BASE_SPEED, 75);
            this.entityData.set(SPECIESNAME, "Smeargle");
            this.entityData.set(CATCHRATE, 75);
        }
        else if(species == 236) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 35);
            this.entityData.set(BASE_ATTACK, 35);
            this.entityData.set(BASE_DEFENCE, 35);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 35);
            this.entityData.set(BASE_SPEED, 35);
            this.entityData.set(SPECIESNAME, "Tyrogue");
        }
        else if(species == 237) {
            this.entityData.set(TYPE1, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIGHTING.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 95);
            this.entityData.set(BASE_DEFENCE, 95);
            this.entityData.set(BASE_SP_ATTACK, 35);
            this.entityData.set(BASE_SP_DEFENCE, 110);
            this.entityData.set(BASE_SPEED, 70);
            this.entityData.set(SPECIESNAME, "Hitmontop");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 238) {
            this.entityData.set(TYPE1, PokemonTypes.ICE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 30);
            this.entityData.set(BASE_DEFENCE, 15);
            this.entityData.set(BASE_SP_ATTACK, 85);
            this.entityData.set(BASE_SP_DEFENCE, 65);
            this.entityData.set(BASE_SPEED, 65);
            this.entityData.set(SPECIESNAME, "Smoochum");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 239) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 63);
            this.entityData.set(BASE_DEFENCE, 37);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 95);
            this.entityData.set(SPECIESNAME, "Elekid");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 240) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 45);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 37);
            this.entityData.set(BASE_SP_ATTACK, 70);
            this.entityData.set(BASE_SP_DEFENCE, 55);
            this.entityData.set(BASE_SPEED, 83);
            this.entityData.set(SPECIESNAME, "Magby");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 241) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 95);
            this.entityData.set(BASE_ATTACK, 80);
            this.entityData.set(BASE_DEFENCE, 105);
            this.entityData.set(BASE_SP_ATTACK, 40);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Miltank");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 242) {
            this.entityData.set(TYPE1, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.NORMAL.ordinal());
            this.entityData.set(BASE_HP, 255);
            this.entityData.set(BASE_ATTACK, 10);
            this.entityData.set(BASE_DEFENCE, 10);
            this.entityData.set(BASE_SP_ATTACK, 75);
            this.entityData.set(BASE_SP_DEFENCE, 135);
            this.entityData.set(BASE_SPEED, 55);
            this.entityData.set(SPECIESNAME, "Blissey");
            this.entityData.set(CATCHRATE, 30);
        }
        else if(species == 243) {
            this.entityData.set(TYPE1, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.ELECTRIC.ordinal());
            this.entityData.set(BASE_HP, 90);
            this.entityData.set(BASE_ATTACK, 85);
            this.entityData.set(BASE_DEFENCE, 75);
            this.entityData.set(BASE_SP_ATTACK, 115);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 115);
            this.entityData.set(SPECIESNAME, "Raikou");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 244) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FIRE.ordinal());
            this.entityData.set(BASE_HP, 115);
            this.entityData.set(BASE_ATTACK, 115);
            this.entityData.set(BASE_DEFENCE, 85);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 75);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Entei");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 245) {
            this.entityData.set(TYPE1, PokemonTypes.WATER.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.WATER.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 75);
            this.entityData.set(BASE_DEFENCE, 115);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 115);
            this.entityData.set(BASE_SPEED, 85);
            this.entityData.set(SPECIESNAME, "Suicune");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 246) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 50);
            this.entityData.set(BASE_ATTACK, 64);
            this.entityData.set(BASE_DEFENCE, 50);
            this.entityData.set(BASE_SP_ATTACK, 45);
            this.entityData.set(BASE_SP_DEFENCE, 50);
            this.entityData.set(BASE_SPEED, 41);
            this.entityData.set(SPECIESNAME, "Larvitar");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 247) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GROUND.ordinal());
            this.entityData.set(BASE_HP, 70);
            this.entityData.set(BASE_ATTACK, 84);
            this.entityData.set(BASE_DEFENCE, 70);
            this.entityData.set(BASE_SP_ATTACK, 65);
            this.entityData.set(BASE_SP_DEFENCE, 70);
            this.entityData.set(BASE_SPEED, 51);
            this.entityData.set(SPECIESNAME, "Pupitar");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 248) {
            this.entityData.set(TYPE1, PokemonTypes.ROCK.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.DARK.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 134);
            this.entityData.set(BASE_DEFENCE, 110);
            this.entityData.set(BASE_SP_ATTACK, 95);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 61);
            this.entityData.set(SPECIESNAME, "Tyranitar");
            this.entityData.set(CATCHRATE, 45);
        }
        else if(species == 249) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 106);
            this.entityData.set(BASE_ATTACK, 90);
            this.entityData.set(BASE_DEFENCE, 130);
            this.entityData.set(BASE_SP_ATTACK, 90);
            this.entityData.set(BASE_SP_DEFENCE, 154);
            this.entityData.set(BASE_SPEED, 110);
            this.entityData.set(SPECIESNAME, "Lugia");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 250) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 106);
            this.entityData.set(BASE_ATTACK, 130);
            this.entityData.set(BASE_DEFENCE, 90);
            this.entityData.set(BASE_SP_ATTACK, 110);
            this.entityData.set(BASE_SP_DEFENCE, 154);
            this.entityData.set(BASE_SPEED, 90);
            this.entityData.set(SPECIESNAME, "Ho-Ho");
            this.entityData.set(CATCHRATE, 3);
        }
        else if(species == 251) {
            this.entityData.set(TYPE1, PokemonTypes.PSYCHIC.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.GRASS.ordinal());
            this.entityData.set(BASE_HP, 100);
            this.entityData.set(BASE_ATTACK, 100);
            this.entityData.set(BASE_DEFENCE, 100);
            this.entityData.set(BASE_SP_ATTACK, 100);
            this.entityData.set(BASE_SP_DEFENCE, 100);
            this.entityData.set(BASE_SPEED, 100);
            this.entityData.set(SPECIESNAME, "Celebi");
            this.entityData.set(CATCHRATE, 45);
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 3);
        this.entityData.define(POKEMON_LEVEL, 1);
        this.entityData.define(SPECIES, 152);
        this.entityData.define(SPECIESNAME, "MissingNo");
        this.entityData.define(SHINY, 0);
        this.entityData.define(HAPPINESS, 100);
        this.entityData.define(TYPE1, 0);
        this.entityData.define(TYPE2, 0);
        this.entityData.define(NATURE, 0);
        this.entityData.define(IVS_HP, 15);
        this.entityData.define(IVS_ATTACK, 15);
        this.entityData.define(IVS_DEFENCE, 15);
        this.entityData.define(IVS_SP_ATTACK, 15);
        this.entityData.define(IVS_SP_DEFENCE, 15);
        this.entityData.define(IVS_SPEED, 15);

        this.entityData.define(BASE_HP, 80);
        this.entityData.define(BASE_ATTACK, 75);
        this.entityData.define(BASE_DEFENCE, 70);
        this.entityData.define(BASE_SP_ATTACK, 83);
        this.entityData.define(BASE_SP_DEFENCE, 83);
        this.entityData.define(BASE_SPEED, 78);

        this.entityData.define(TRUE_HP, 80);
        this.entityData.define(TRUE_ATTACK, 0);
        this.entityData.define(TRUE_DEFENCE, 0);
        this.entityData.define(TRUE_SP_ATTACK, 0);
        this.entityData.define(TRUE_SP_DEFENCE, 0);
        this.entityData.define(TRUE_SPEED, 0);

        this.entityData.define(EVS_HP, 0);
        this.entityData.define(EVS_ATTACK, 0);
        this.entityData.define(EVS_DEFENCE, 0);
        this.entityData.define(EVS_SP_ATTACK, 0);
        this.entityData.define(EVS_SP_DEFENCE, 0);
        this.entityData.define(EVS_SPEED, 78);


        this.entityData.define(MOVE_SLOT_1, 0);
        this.entityData.define(MOVE_SLOT_1_NAME, "None");
        this.entityData.define(MOVE_SLOT_1_TYPE, 0);
        this.entityData.define(MOVE_SLOT_1_ACCURACY, 0);
        this.entityData.define(MOVE_SLOT_1_POWER, 0);
        this.entityData.define(MOVE_SLOT_1_CURRENT_PP, 0);
        this.entityData.define(MOVE_SLOT_1_MAX_PP, 0);


        this.entityData.define(MOVE_SLOT_2, 0);
        this.entityData.define(MOVE_SLOT_2_NAME, "None");
        this.entityData.define(MOVE_SLOT_2_TYPE, 0);
        this.entityData.define(MOVE_SLOT_2_ACCURACY, 0);
        this.entityData.define(MOVE_SLOT_2_POWER, 0);
        this.entityData.define(MOVE_SLOT_2_CURRENT_PP, 0);
        this.entityData.define(MOVE_SLOT_2_MAX_PP, 0);

        this.entityData.define(MOVE_SLOT_3, 0);
        this.entityData.define(MOVE_SLOT_3_NAME, "None");
        this.entityData.define(MOVE_SLOT_3_TYPE, 0);
        this.entityData.define(MOVE_SLOT_3_ACCURACY, 0);
        this.entityData.define(MOVE_SLOT_3_POWER, 0);
        this.entityData.define(MOVE_SLOT_3_CURRENT_PP, 0);
        this.entityData.define(MOVE_SLOT_3_MAX_PP, 0);

        this.entityData.define(MOVE_SLOT_4, 0);
        this.entityData.define(MOVE_SLOT_4_NAME, "None");
        this.entityData.define(MOVE_SLOT_4_TYPE, 0);
        this.entityData.define(MOVE_SLOT_4_ACCURACY, 0);
        this.entityData.define(MOVE_SLOT_4_POWER, 0);
        this.entityData.define(MOVE_SLOT_4_CURRENT_PP, 0);
        this.entityData.define(MOVE_SLOT_4_MAX_PP, 0);

        this.entityData.define(CATCHRATE, 45);
        this.entityData.define(EVOLVING, 0);
        this.entityData.define(EVOLUTIONTIMER, (float)0);
        this.entityData.define(HASNICKNAME, 0);
        this.entityData.define(NICKNAME, "None");
        this.entityData.define(EVOLUTIONBRANCH, 0);
        this.entityData.define(GENDER, 0);

    }


    protected void setShinyness(int pSize) {
        int i = Mth.clamp(pSize, 0, 1);
        this.entityData.set(SHINY, i);

    }

    protected void setHasNickname(int pSize) {
        int i = Mth.clamp(pSize, 0, 1);
        this.entityData.set(HASNICKNAME, i);

    }


    protected void setHappiness(int pSize) {
        int i = Mth.clamp(pSize, 0, 250);
        this.entityData.set(HAPPINESS, i);

    }

    public int getHappiness() {
        return this.entityData.get(HAPPINESS);
    }

    public String getPokeSpeciesName() {
        return this.entityData.get(SPECIESNAME);
    }

    public String getPokeNickname() {
        return this.entityData.get(NICKNAME);
    }

    public UUID getPokeUUID() {
        return this.getUUID();
    }

    public EntityType getEntityType() {
        return this.getEntityType();
    }

    public int getPokeType1() {
        return this.entityData.get(TYPE1);
    }

    public int getPokeType2() {
        return this.entityData.get(TYPE2);
    }

    public int getCatchRate() {
        return this.entityData.get(CATCHRATE);
    }

    public void aiStep() {
        int shiny = this.getShinyness();
        int evolve = this.getEvolving();
        if (this.level.isClientSide && (shiny == 1)) {
            for (int i = 0; i < 1; ++i) {
                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }

        if (this.level.isClientSide && (evolve == 1)) {
            for (int i = 0; i < 1; ++i) {
                this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
                this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(1D), (this.random.nextDouble() - 0.5D) * 4.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 4.0D);
            }
        }

        if (this.inLove > 0) {
            --this.inLove;
            if (this.inLove % 10 == 0) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        }


        super.aiStep();
    }

    protected void setPokeName(){
        if(this.getHasNickname() == 1){
            String name = this.getPokeNickname();
            this.entityData.set(NICKNAME, name);
        }else{
            String name = this.getPokeSpeciesName();
            this.entityData.set(NICKNAME, name);
        }
    }

    public String getPokeName() {
        return this.entityData.get(NICKNAME);
    }


    protected void setPokeSpecies(int species) {
        int i = Mth.clamp(species, -1000, 1000);
        this.entityData.set(SPECIES, i);
    }

    public void setNature(int maxnature) {
        int nature = Mth.clamp(maxnature, 0, 24);
        this.entityData.set(NATURE, nature);
    }

    public void setIvsHp(int maxhp) {
        int hp = Mth.clamp(maxhp, 0, 31);
        this.entityData.set(IVS_HP, hp);
    }
    public void setIvsAttack(int maxattack) {
        int attack = Mth.clamp(maxattack, 0, 31);
        this.entityData.set(IVS_ATTACK, attack);
    }
    public void setIvsSpAttack(int maxspattack) {
        int spattack = Mth.clamp(maxspattack, 0, 31);
        this.entityData.set(IVS_SP_ATTACK, spattack);
    }
    public void setIvsDefence(int maxdefence) {
        int defence = Mth.clamp(maxdefence, 0, 31);
        this.entityData.set(IVS_DEFENCE, defence);
    }
    public void setIvsSpDefence(int maxspdefence) {
        int spdefence = Mth.clamp(maxspdefence, 0, 31);
        this.entityData.set(IVS_SP_DEFENCE, spdefence);
    }
    public void setIvsSpeed(int maxspeed ) {
        int speed = Mth.clamp(maxspeed, 0, 31);
        this.entityData.set(IVS_SPEED, speed);
    }

    protected void setEvolutionbranch(int branchsize ) {
        int branch = Mth.clamp(branchsize, 0, 100);
        this.entityData.set(EVOLUTIONBRANCH, branch);
    }


    protected void setGender(int gendervalue ) {
        int gender = Mth.clamp(gendervalue, 0, 2);
        this.entityData.set(GENDER, gender);
    }

    public void setTrueHp() {
        int true_hp = (int) (Math.floor(0.01 * (2 * (getBaseHP()) + (getIvsHP()) + Math.floor(0.25 * (getEvsHP()))) * (getPokeLevel())) + (getPokeLevel()) + 10);
        this.entityData.set(TRUE_HP, true_hp);
        Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(true_hp);
    }
    public void setTrueAttack() {
        int attack;
        int prenature_attack = (int) (Math.floor(0.01 * (2 * (getBaseAttack()) + (getIvsAttack()) + Math.floor(0.25 * (getEvsAttack()))) * (getPokeLevel())) + (getPokeLevel()) + 5);
        if((getPokeNature() == 2)||(getPokeNature() == 3)||(getPokeNature() == 4)||(getPokeNature() == 5)){
            attack = (int) (prenature_attack * 1.1);
            this.entityData.set(TRUE_ATTACK, attack);
        }else if((getPokeNature() == 1)||(getPokeNature() == 7)||(getPokeNature() == 13)||(getPokeNature() == 19)||(getPokeNature() == 25)){
            attack = prenature_attack;
            this.entityData.set(TRUE_ATTACK, attack);
        }else{
            attack = (int) (prenature_attack * 0.9);
            this.entityData.set(TRUE_ATTACK, attack);
        }
    }
    public void setTrueSpAttack() {
        int sp_attack;
        int prenature_sp_attack = (int) (Math.floor(0.01 * (2 * (getBaseSPAttack()) + (getIvsSPAttack()) + Math.floor(0.25 * (getEvsSPAttack()))) * (getPokeLevel())) + (getPokeLevel()) + 5);
        if((getPokeNature() == 16)||(getPokeNature() == 17)||(getPokeNature() == 18)||(getPokeNature() == 20)){
            sp_attack = (int) (prenature_sp_attack * 1.1);
            this.entityData.set(TRUE_SP_ATTACK, sp_attack);
        }else if((getPokeNature() == 1)||(getPokeNature() == 7)||(getPokeNature() == 13)||(getPokeNature() == 19)||(getPokeNature() == 25)){
            sp_attack = prenature_sp_attack;
            this.entityData.set(TRUE_SP_ATTACK, sp_attack);
        }else{
            sp_attack = (int) (prenature_sp_attack * 0.9);
            this.entityData.set(TRUE_SP_ATTACK, sp_attack);
        }
    }
    public void setTrueDefence() {
        int defence;
        int prenature_defence = (int) (Math.floor(0.01 * (2 * (getBaseDefence()) + (getIvsDefence()) + Math.floor(0.25 * (getEvsDefence()))) * (getPokeLevel())) + (getPokeLevel()) + 5);
        if((getPokeNature() == 6)||(getPokeNature() == 8)||(getPokeNature() == 9)||(getPokeNature() == 10)){
            defence = (int) (prenature_defence * 1.1);
            this.entityData.set(TRUE_DEFENCE, defence);
        }else if((getPokeNature() == 1)||(getPokeNature() == 7)||(getPokeNature() == 13)||(getPokeNature() == 19)||(getPokeNature() == 25)){
            defence = prenature_defence;
            this.entityData.set(TRUE_DEFENCE, defence);
        }else{
            defence = (int) (prenature_defence * 0.9);
            this.entityData.set(TRUE_DEFENCE, defence);
        }
    }
    public void setTrueSpDefence() {
        int sp_defence;
        int prenature_sp_defence = (int) (Math.floor(0.01 * (2 * (getBaseSPDefence()) + (getIvsSPDefence()) + Math.floor(0.25 * (getEvsSPDefence()))) * (getPokeLevel())) + (getPokeLevel()) + 5);
        if((getPokeNature() == 21)||(getPokeNature() == 22)||(getPokeNature() == 23)||(getPokeNature() == 24)){
            sp_defence = (int) (prenature_sp_defence * 1.1);
            this.entityData.set(TRUE_SP_DEFENCE, sp_defence);
        }else if((getPokeNature() == 1)||(getPokeNature() == 7)||(getPokeNature() == 13)||(getPokeNature() == 19)||(getPokeNature() == 25)){
            sp_defence = prenature_sp_defence;
            this.entityData.set(TRUE_SP_DEFENCE, sp_defence);
        }else{
            sp_defence = (int) (prenature_sp_defence * 0.9);
            this.entityData.set(TRUE_SP_DEFENCE, sp_defence);
        }
    }
    public void setTrueSpeed() {
        int speed;
        int prenature_speed = (int) (Math.floor(0.01 * (2 * (getBaseSpeed()) + (getIvsSpeed()) + Math.floor(0.25 * (getEvsSpeed()))) * (getPokeLevel())) + (getPokeLevel()) + 5);
        if((getPokeNature() == 11)||(getPokeNature() == 12)||(getPokeNature() == 14)||(getPokeNature() == 15)){
            speed = (int) (prenature_speed * 1.1);
            this.entityData.set(TRUE_SPEED, speed);
        }else if((getPokeNature() == 1)||(getPokeNature() == 7)||(getPokeNature() == 13)||(getPokeNature() == 19)||(getPokeNature() == 25)){
            speed = prenature_speed;
            this.entityData.set(TRUE_SPEED, speed);
        }else{
            speed = (int) (prenature_speed * 0.9);
            this.entityData.set(TRUE_SPEED, speed);
        }
    }

    protected void setPokeLevel(int level) {
        int i = Mth.clamp(level, 1, 100);
        this.entityData.set(POKEMON_LEVEL, i);
    }

    public int getPokeSpecies() {
        return this.entityData.get(SPECIES);
    }

    public int getMoveSlot1() {
        return this.entityData.get(MOVE_SLOT_1);
    }

    public int getMoveSlot2() {
        return this.entityData.get(MOVE_SLOT_2);
    }

    public int getMoveSlot3() {
        return this.entityData.get(MOVE_SLOT_3);
    }

    public int getMoveSlot4() {
        return this.entityData.get(MOVE_SLOT_4);
    }

    public int getEvolutionBranch() {
        return this.entityData.get(EVOLUTIONBRANCH);
    }

    public int getGender() {
        return this.entityData.get(GENDER);
    }

    protected void setSize(int pSize) {
        int i = Mth.clamp(pSize, 1, 5);
        this.entityData.set(ID_SIZE, i);
    }

    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    public int getPokeLevel() {
        return this.entityData.get(POKEMON_LEVEL);
    }

    public int getShinyness() {
        return this.entityData.get(SHINY);
    }

    public int getPokeNature() {
        return this.entityData.get(NATURE);
    }

    public int getIvsAttack() {
        return this.entityData.get(IVS_ATTACK);
    }
    public int getIvsSPAttack() {
        return this.entityData.get(IVS_SP_ATTACK);
    }
    public int getIvsDefence() {
        return this.entityData.get(IVS_DEFENCE);
    }
    public int getIvsSPDefence() {
        return this.entityData.get(IVS_SP_DEFENCE);
    }
    public int getIvsSpeed() {
        return this.entityData.get(IVS_SPEED);
    }
    public int getIvsHP() {
        return this.entityData.get(IVS_HP);
    }


    public int getBaseAttack() {
        return this.entityData.get(BASE_ATTACK);
    }
    public int getBaseSPAttack() {
        return this.entityData.get(BASE_SP_ATTACK);
    }
    public int getBaseDefence() {
        return this.entityData.get(BASE_DEFENCE);
    }
    public int getBaseSPDefence() {
        return this.entityData.get(BASE_SP_DEFENCE);
    }
    public int getBaseSpeed() {
        return this.entityData.get(BASE_SPEED);
    }
    public int getBaseHP() {
        return this.entityData.get(BASE_HP);
    }

    public int getTrueAttack() {
        return this.entityData.get(TRUE_ATTACK);
    }
    public int getTrueSPAttack() {
        return this.entityData.get(TRUE_SP_ATTACK);
    }
    public int getTrueDefence() {
        return this.entityData.get(TRUE_DEFENCE);
    }
    public int getTrueSPDefence() {
        return this.entityData.get(TRUE_SP_DEFENCE);
    }
    public int getTrueSpeed() {
        return this.entityData.get(TRUE_SPEED);
    }
    public int getTrueHP() {
        return this.entityData.get(TRUE_HP);
    }

    public int getEvsAttack() {
        return this.entityData.get(EVS_ATTACK);
    }
    public int getEvsSPAttack() {
        return this.entityData.get(EVS_SP_ATTACK);
    }
    public int getEvsDefence() {
        return this.entityData.get(EVS_DEFENCE);
    }
    public int getEvsSPDefence() {
        return this.entityData.get(IVS_SP_DEFENCE);
    }
    public int getEvsSpeed() {
        return this.entityData.get(IVS_SPEED);
    }
    public int getEvsHP() {
        return this.entityData.get(IVS_HP);
    }

    public int getHasNickname() {
        return this.entityData.get(HASNICKNAME);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Size", this.getSize());
        pCompound.putInt("Level", this.getPokeLevel());
        pCompound.putInt("Species", this.getPokeSpecies());
        pCompound.putInt("Type1", this.getPokeType1());
        pCompound.putInt("Type2", this.getPokeType2());
        pCompound.putInt("Shiny", this.getShinyness());
        pCompound.putInt("Nature", this.getPokeNature());
        pCompound.putInt("Ivs_Hp", this.getIvsHP());
        pCompound.putInt("Ivs_Attack", this.getIvsAttack());
        pCompound.putInt("Ivs_Defence", this.getIvsDefence());
        pCompound.putInt("Ivs_SP_Attack", this.getIvsSPAttack());
        pCompound.putInt("Ivs_SP_Defence", this.getIvsSPDefence());
        pCompound.putInt("Ivs_Speed", this.getIvsSpeed());
        pCompound.putInt("Base_Hp", this.getBaseHP());
        pCompound.putInt("Base_Attack", this.getBaseAttack());
        pCompound.putInt("Base_Defence", this.getBaseDefence());
        pCompound.putInt("Base_SP_Attack", this.getBaseSPAttack());
        pCompound.putInt("Base_SP_Defence", this.getBaseSPDefence());
        pCompound.putInt("Base_Speed", this.getBaseSpeed());
        pCompound.putInt("Hp", this.getTrueHP());
        pCompound.putInt("Attack", this.getTrueAttack());
        pCompound.putInt("Defence", this.getTrueDefence());
        pCompound.putInt("SP_Attack", this.getTrueSPAttack());
        pCompound.putInt("SP_Defence", this.getTrueSPDefence());
        pCompound.putInt("Speed", this.getTrueSpeed());
        pCompound.putInt("Move1", this.getMoveSlot1());
        pCompound.putInt("Move2", this.getMoveSlot2());
        pCompound.putInt("Move3", this.getMoveSlot3());
        pCompound.putInt("Move4", this.getMoveSlot4());
        pCompound.putInt("CatchRate", this.getCatchRate());
        pCompound.putInt("InLove", this.inLove);
        pCompound.putInt("Happiness", this.getHappiness());
        pCompound.putInt("HasNickname", this.getHasNickname());
        pCompound.putInt("Branch", this.getEvolutionBranch());
        pCompound.putInt("Gender", this.getGender());
        pCompound.putString("Nickname", this.getPokeNickname());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Fuse", 99)) {
        }
        this.setSize(pCompound.getInt("Size"));
        this.setPokeLevel(pCompound.getInt("Level"));
        this.setPokeSpecies(pCompound.getInt("Species"));
        this.setNature(pCompound.getInt("Nature"));
        this.setIvsHp(pCompound.getInt("Ivs_Hp"));
        this.setIvsAttack(pCompound.getInt("Ivs_Attack"));
        this.setIvsSpAttack(pCompound.getInt("Ivs_SP_Attack"));
        this.setIvsDefence(pCompound.getInt("Ivs_Defence"));
        this.setIvsSpDefence(pCompound.getInt("Ivs_SP_Defence"));
        this.setIvsSpeed(pCompound.getInt("Ivs_Speed"));
        this.setTrueHp();
        this.setTrueAttack();
        this.setTrueSpAttack();
        this.setTrueDefence();
        this.setTrueSpDefence();
        this.setTrueSpeed();
        this.setPokeStats();
        this.setShinyness(pCompound.getInt("Shiny"));
        this.inLove = pCompound.getInt("InLove");
        this.setHappiness(pCompound.getInt("Happiness"));
        this.setHasNickname(pCompound.getInt("HasNickname"));
        this.setPokeName();
        this.setEvolutionbranch(pCompound.getInt("Branch"));;
        this.setGender(pCompound.getInt("Gender"));;
        super.readAdditionalSaveData(pCompound);

    }

    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> pKey) {
        if (ID_SIZE.equals(pKey)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(pKey);
    }

    public @NotNull EntityDimensions getDimensions(@NotNull Pose pPose) {
        return super.getDimensions(pPose).scale((0.5F * ((float) this.getSize()) + ((float) this.getPokeLevel())/85));
    }

    public String getNatureName() {
        int nature = this.getPokeNature();
        return switch (nature) {
            case 1 -> "Lonely";
            case 2 -> "Brave";
            case 3 -> "Adamant";
            case 4 -> "Naughty";
            case 5 -> "Bold";
            case 6 -> "Docile";
            case 7 -> "Relaxed";
            case 8 -> "Impish";
            case 9 -> "Lax";
            case 10 -> "Timid";
            case 11 -> "Hasty";
            case 12 -> "Serious";
            case 13 -> "Jolly";
            case 14 -> "Naive";
            case 15 -> "Modest";
            case 16 -> "Mild";
            case 17 -> "Quiet";
            case 18 -> "Bashful";
            case 19 -> "Rash";
            case 20 -> "Calm";
            case 21 -> "Gentle";
            case 22 -> "Sassy";
            case 23 -> "Careful";
            case 24 -> "Quirky";
            default -> "Hardy";
        };

    }

    public String getType1Name(){
            int type1 = this.getPokeType1();
        return switch (type1) {
            case 1 -> "Normal";
            case 2 -> "Fire";
            case 3 -> "Fighting";
            case 4 -> "Water";
            case 5 -> "Flying";
            case 6 -> "Grass";
            case 7 -> "Poison";
            case 8 -> "Electric";
            case 9 -> "Ground";
            case 10 -> "Psychic";
            case 11 -> "Rock";
            case 12 -> "Ice";
            case 13 -> "Bug";
            case 14 -> "Dragon";
            case 15 -> "Ghost";
            case 16 -> "Dark";
            case 17 -> "Steel";
            case 18 -> "Fairy";
            case 19 -> "Shadow";
            default -> "None";
        };
    }
    public String getType2Name(){
        int type2 = this.getPokeType2();
        return switch (type2) {
            case 1 -> "Normal";
            case 2 -> "Fire";
            case 3 -> "Fighting";
            case 4 -> "Water";
            case 5 -> "Flying";
            case 6 -> "Grass";
            case 7 -> "Poison";
            case 8 -> "Electric";
            case 9 -> "Ground";
            case 10 -> "Psychic";
            case 11 -> "Rock";
            case 12 -> "Ice";
            case 13 -> "Bug";
            case 14 -> "Dragon";
            case 15 -> "Ghost";
            case 16 -> "Dark";
            case 17 -> "Steel";
            case 18 -> "Fairy";
            case 19 -> "Shadow";
            default -> "None";
        };
    }

    public String getSizeName(){
            int size = this.getSize();
        return switch (size) {
            case 1 -> "Tiny";
            case 2 -> "Small";
            case 3 -> "Normal";
            case 4 -> "Medium";
            case 5 -> "Large";
            case 6 -> "Giga";
            default -> "Ant";
        };
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        Random random = new Random();
        int shinyroll = random.nextInt(4095) + 1;
        if (shinyroll == 4069) {
            this.entityData.set(SHINY, 1);
        } else {
            this.entityData.set(SHINY, 0);
        }
        this.entityData.set(HAPPINESS, 100);
        switch (random.nextInt(8)) {
            case 0:
                setSize(1);
                break;
            case 1:
                setSize(2);
                break;
            case 2:
                setSize(4);
                break;
            case 3:
                setSize(5);
                break;
            default:
                setSize(3);
                break;
        }
        int ivsroll_hp = random.nextInt(30) + 1;
        int ivsroll_attack = random.nextInt(30) + 1;
        int ivsroll_defence = random.nextInt(30) + 1;
        int ivsroll_sp_attack = random.nextInt(30) + 1;
        int ivsroll_sp_defence = random.nextInt(30) + 1;
        int ivsroll_speed = random.nextInt(30) + 1;

        this.entityData.set(IVS_HP, ivsroll_hp);
        this.entityData.set(IVS_ATTACK, ivsroll_attack);
        this.entityData.set(IVS_DEFENCE, ivsroll_defence);
        this.entityData.set(IVS_SP_ATTACK, ivsroll_sp_attack);
        this.entityData.set(IVS_SP_DEFENCE, ivsroll_sp_defence);
        this.entityData.set(IVS_SPEED, ivsroll_speed);

        int natrueroll = random.nextInt(24) + 1;
        switch (natrueroll) {
            case 1 -> this.entityData.set(NATURE, PokemonNature.LONELY.ordinal());
            case 2 -> this.entityData.set(NATURE, PokemonNature.BRAVE.ordinal());
            case 3 -> this.entityData.set(NATURE, PokemonNature.ADAMANT.ordinal());
            case 4 -> this.entityData.set(NATURE, PokemonNature.NAUGHTY.ordinal());
            case 5 -> this.entityData.set(NATURE, PokemonNature.BOLD.ordinal());
            case 6 -> this.entityData.set(NATURE, PokemonNature.DOCILE.ordinal());
            case 7 -> this.entityData.set(NATURE, PokemonNature.RELAXED.ordinal());
            case 8 -> this.entityData.set(NATURE, PokemonNature.IMPISH.ordinal());
            case 9 -> this.entityData.set(NATURE, PokemonNature.LAX.ordinal());
            case 10 -> this.entityData.set(NATURE, PokemonNature.TIMID.ordinal());
            case 11 -> this.entityData.set(NATURE, PokemonNature.HASTY.ordinal());
            case 12 -> this.entityData.set(NATURE, PokemonNature.SERIOUS.ordinal());
            case 13 -> this.entityData.set(NATURE, PokemonNature.JOLLY.ordinal());
            case 14 -> this.entityData.set(NATURE, PokemonNature.NAIVE.ordinal());
            case 15 -> this.entityData.set(NATURE, PokemonNature.MODEST.ordinal());
            case 16 -> this.entityData.set(NATURE, PokemonNature.MILD.ordinal());
            case 17 -> this.entityData.set(NATURE, PokemonNature.QUIET.ordinal());
            case 18 -> this.entityData.set(NATURE, PokemonNature.BASHFUL.ordinal());
            case 19 -> this.entityData.set(NATURE, PokemonNature.RASH.ordinal());
            case 20 -> this.entityData.set(NATURE, PokemonNature.CALM.ordinal());
            case 21 -> this.entityData.set(NATURE, PokemonNature.GENTLE.ordinal());
            case 22 -> this.entityData.set(NATURE, PokemonNature.SASSY.ordinal());
            case 23 -> this.entityData.set(NATURE, PokemonNature.CAREFUL.ordinal());
            case 24 -> this.entityData.set(NATURE, PokemonNature.QUIRKY.ordinal());
            default -> this.entityData.set(NATURE, PokemonNature.HARDY.ordinal());
        }
        int true_hp = (int) (Math.floor(0.01 * (2 * (getBaseHP()) + (getIvsHP()) + Math.floor(0.25 * (getEvsHP()))) * (getPokeLevel())) + (getPokeLevel()) + 10);
        Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(true_hp);
        this.setHealth(true_hp);
        return pSpawnData;
    }

    public void setTame(boolean p_30443_) {
        super.setTame(p_30443_);
    }

    public void setInLove() {
        this.inLove = 200;
        this.level.broadcastEntityEvent(this, (byte) 18);
    }


    public void setInLoveTime(int pTicks) {
        this.inLove = pTicks;
    }


    public int getInLoveTime() {
        return this.inLove;
    }


    public boolean isInLove() {
        return this.inLove > 0;
    }


    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            this.setHappiness(this.entityData.get(HAPPINESS) - 5);
            return super.hurt(pSource, pAmount);
        }
    }

    public ItemStack getPickResult() {
        Player player = (Player) this.getOwner();
        if(!(player == null)){
        CompoundTag nbt = new CompoundTag();
        ItemStack PokeballItem = new ItemStack(Registration.POKEBALL.get());
        String Name = String.valueOf((this.getPokeName()));
        nbt.putUUID("UUID", getPokeUUID());
        PokeballItem.setTag(nbt);
        return PokeballItem;
        }else{
            return null;
        }
    }


    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!(this.level.isClientSide)) {
            if (pPlayer.isHolding((Registration.RARECANDY.get())) && this.isOwnedBy(pPlayer) && !(this.getPokeLevel() > 99)) {
                itemstack.shrink(1);
                this.setInLove();
                this.setPokeLevel((this.entityData.get(POKEMON_LEVEL)) + 1);
                this.setTrueHp();
                this.setHealth(Math.round((this.getTrueHP()) / this.getHealth()) * this.getHealth());
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 4);
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.ORANBERRY.get())) && this.isOwnedBy(pPlayer)) {
                itemstack.shrink(1);
                this.setHealth(this.getHealth() + 10);
                this.setInLove();
                this.setTrueHp();
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EAT, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 2);
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.POTION.get())) && this.isOwnedBy(pPlayer)) {
                itemstack.shrink(1);
                this.setHealth(this.getHealth() + 60);
                this.setInLove();
                this.setTrueHp();
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 1);
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.SUPERPOTION.get())) && this.isOwnedBy(pPlayer)) {
                itemstack.shrink(1);
                this.setHealth(this.getHealth() + 100);
                this.setInLove();
                this.setTrueHp();
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 3);
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.HYPERPOTION.get())) && this.isOwnedBy(pPlayer)) {
                itemstack.shrink(1);
                this.setHealth(this.getHealth() + 150);
                this.setInLove();
                this.setTrueHp();
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 3);
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.MAXPOTION.get())) && this.isOwnedBy(pPlayer)) {
                itemstack.shrink(1);
                this.setHealth(this.getMaxHealth());
                this.setInLove();
                this.setTrueHp();
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 4);
                return InteractionResult.SUCCESS;
            }




            if ((itemstack.getItem() == Items.AIR) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();

                if(species == 1 && level >= 16){
                    setEvolving(1);
                }
                if(species == 2 && level >= 13){
                    setEvolving(1);
                }
                if(species == 4 && level >= 16){
                    setEvolving(1);
                }
                if(species == 5 && level >= 36){
                    setEvolving(1);
                }
                if(species == 7 && level >= 16){
                    setEvolving(1);
                }
                if(species == 8 && level >= 36){
                    setEvolving(1);
                }
                if(species == 10 && level >= 7){
                    setEvolving(1);
                }
                if(species == 11 && level >= 10){
                    setEvolving(1);
                }
                if(species == 13 && level >= 7){
                    setEvolving(1);
                }
                if(species == 14 && level >= 10){
                    setEvolving(1);
                }
                if(species == 16 && level >= 18){
                    setEvolving(1);
                }
                if(species == 17 && level >= 36){
                    setEvolving(1);
                }
                if(species == 19 && level >= 20){
                    setEvolving(1);
                }
                if(species == 21 && level >= 20){
                    setEvolving(1);
                }
                if(species == 23 && level >= 22){
                    setEvolving(1);
                }
                if(species == 29 && level >= 16){
                    setEvolving(1);
                }
                if(species == 32 && level >= 16){
                    setEvolving(1);
                }
                if(species == 41 && level >= 22){
                    setEvolving(1);
                }
                if(species == 43 && level >= 21){
                    setEvolving(1);
                }
//SPLIT EVOLUTION LINE
                if(species == 46 && level >= 24){
                    setEvolving(1);
                }
                if(species == 48 && level >= 31){
                    setEvolving(1);
                }
                if(species == 50 && level >= 28){
                    setEvolving(1);
                }
                //Split Line
                if(species == 54 && level >= 33){
                    setEvolving(1);
                }
                if(species == 56 && level >= 28){
                    setEvolving(1);
                }
                if(species == 60 && level >= 25){
                    setEvolving(1);
                }
                //split line
                if(species == 63 && level >= 16){
                    setEvolving(1);
                }
                if(species == 66 && level >= 28){
                    setEvolving(1);
                }
                if(species == 69 && level >= 21){
                    setEvolving(1);
                }

                if(species == 72 && level >= 30){
                    setEvolving(1);
                }
                if(species == 74 && level >= 25){
                    setEvolving(1);
                }
                if(species == 77 && level >= 40){
                    setEvolving(1);
                }
                //split line
                if(species == 81 && level >= 30){
                    setEvolving(1);
                }
                if(species == 84 && level >= 31){
                    setEvolving(1);
                }
                if(species == 86 && level >= 34){
                    setEvolving(1);
                }
                if(species == 88 && level >= 38){
                    setEvolving(1);
                }
                if(species == 92 && level >= 25){
                    setEvolving(1);
                }
                if(species == 96 && level >= 26){
                    setEvolving(1);
                }
                if(species == 98 && level >= 28){
                    setEvolving(1);
                }
                if(species == 100 && level >= 30){
                    setEvolving(1);
                }
                //split line
                if(species == 109 && level >= 35){
                    setEvolving(1);
                }
                if(species == 111 && level >= 42){
                    setEvolving(1);
                }
                if(species == 116 && level >= 32){
                    setEvolving(1);
                }
                if(species == 118 && level >= 33){
                    setEvolving(1);
                }
                //split line
                if(species == 129 && level >= 20){
                    setEvolving(1);
                }
                //split line
                if(species == 138 && level >= 40){
                    setEvolving(1);
                }
                if(species == 140 && level >= 40){
                    setEvolving(1);
                }

                if(species == 147 && level >= 30){
                    setEvolving(1);
                }
                if(species == 148 && level >= 55){
                    setEvolving(1);
                }
                if(species == 152 && level >= 16){
                    setEvolving(1);
                }
                if(species == 153 && level >= 32){
                    setEvolving(1);
                }
                if(species == 155 && level >= 14){
                    setEvolving(1);
                }
                if(species == 156 && level >= 36){
                    setEvolving(1);
                }
                if(species == 158 && level >= 18){
                    setEvolving(1);
                }
                if(species == 159 && level >= 30){
                    setEvolving(1);
                }
                if(species == 161 && level >= 15){
                    setEvolving(1);
                }
                if(species == 163 && level >= 20){
                    setEvolving(1);
                }
                if(species == 165 && level >= 18){
                    setEvolving(1);
                }
                if(species == 167 && level >= 22){
                    setEvolving(1);
                }
                if(species == 170 && level >= 27){
                    setEvolving(1);
                }
                if(species == 177 && level >= 25){
                    setEvolving(1);
                }
                if(species == 179 && level >= 15){
                    setEvolving(1);
                }
                if(species == 180 && level >= 30){
                    setEvolving(1);
                }
                if(species == 183 && level >= 18){
                    setEvolving(1);
                }
                if(species == 187 && level >= 18){
                    setEvolving(1);
                }
                if(species == 188 && level >= 27){
                    setEvolving(1);
                }
                if(species == 360 && level >= 15){
                    setEvolving(1);
                }
                if(species == 204 && level >= 31){
                    setEvolving(1);
                }
                if(species == 209 && level >= 23){
                    setEvolving(1);
                }
//Split Line
                if(species == 216 && level >= 30){
                    setEvolving(1);
                }

                if(species == 218 && level >= 38){
                    setEvolving(1);
                }
                if(species == 220 && level >= 33){
                    setEvolving(1);
                }
                if(species == 223 && level >= 25){
                    setEvolving(1);
                }

                if(species == 228 && level >= 24){
                    setEvolving(1);
                }
                if(species == 231 && level >= 25){
                    setEvolving(1);
                }
                if(species == 246 && level >= 30){
                    setEvolving(1);
                }
                if(species == 247 && level >= 55){
                    setEvolving(1);
                }

                if(species == 42 && happiness >= 220){
                    setEvolving(1);
                }

                if(species == 113 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 172 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 173 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 174 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 175 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 133 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 133 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 446 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 175 && happiness >= 220){
                    setEvolving(1);
                }
                if(species == 79 && level >= 37){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                int attack_ivs = getIvsAttack();
                int defence_ivs = getIvsDefence();
                if(species == 236 && level >= 20 && attack_ivs > defence_ivs){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                if(species == 236 && level >= 20 && attack_ivs < defence_ivs){
                    setEvolving(1);
                    setEvolutionbranch(1);
                }
                if(species == 236 && level >= 20 && attack_ivs == defence_ivs){
                    setEvolving(1);
                    setEvolutionbranch(2);
                }
                if(species == 281 && level >= 30){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                if( species == 361 && level == 42){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                Random random = new Random();
                int cointoss = random.nextInt(0) + 1;
                if(species == 265 && level >= 7 && cointoss == 0){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                if(species == 265 && level >= 7 && cointoss == 1){
                    setEvolving(1);
                    setEvolutionbranch(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.FIRESTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if(species == 37 ||species == 58 || species == 513){
                    setEvolving(1);
                }
                if( species == 133){
                    setEvolving(1);
                    setEvolutionbranch(2);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.WATERSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if(species == 61 ||species == 90 || species == 120|| species == 271|| species == 515){
                    setEvolving(1);
                }
                if( species == 61){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                if( species == 133){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.THUNDERSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if(species == 25 || species == 603){
                    setEvolving(1);
                }
                if( species == 133){
                    setEvolving(1);
                    setEvolutionbranch(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.LEAFSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 70 ||species == 102 || species == 274|| species == 511){
                    setEvolving(1);
                }
                if( species == 44){
                    setEvolving(1);
                    setEvolutionbranch(0);
                }
                if( species == 133){
                    setEvolving(1);
                    setEvolutionbranch(3);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.MOONSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 30 || species == 33 ||species == 35 || species == 39|| species == 300 || species == 517){
                    setEvolving(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.SUNSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 191 || species == 546 ||species == 548 || species == 694){
                    setEvolving(1);
                }
                if( species == 44){
                    setEvolving(1);
                    setEvolutionbranch(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.MOONSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 30 || species == 33 ||species == 35 || species == 39|| species == 300 || species == 517){
                    setEvolving(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.SHINYSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 191 || species == 176 ||species == 572 || species == 670){
                    setEvolving(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.DUSKSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 198 || species == 200 ||species == 608 || species == 680){
                    setEvolving(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.DAWNSTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                int gender = this.getGender();
                if( species == 361 && gender == 2){
                    setEvolving(1);
                    setEvolutionbranch(1);
                }
                if(species == 281 && gender == 1 ){
                    setEvolving(1);
                    setEvolutionbranch(1);
                }
                return InteractionResult.SUCCESS;
            }
            if (pPlayer.isHolding((Registration.ICESTONE.get())) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                int species = this.getPokeSpecies();
                int level = this.getPokeLevel();
                int happiness = this.getHappiness();
                if( species == 133){
                    setEvolving(3);
                    setEvolutionbranch(4);
                }
                return InteractionResult.SUCCESS;
            }

            if (pPlayer.isHolding((Items.NAME_TAG))) {
                return InteractionResult.FAIL;
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }


    protected void setEvolving(int pSize) {
        int i = Mth.clamp(pSize, 0, 1);
        this.entityData.set(EVOLVING, i);

    }
    public int getEvolving() {
        return this.entityData.get(EVOLVING);
    }


    public void setTimer(float timer) {
        float time = Mth.clamp(timer, 0, 50);
        this.entityData.set(EVOLUTIONTIMER, timer);
    }

    public float getTimer() {
        return this.entityData.get(EVOLUTIONTIMER);
    }

    public void tick() {
        if (this.isAlive() ) {
            int species = this.getPokeSpecies();
            int branch = this.getEvolutionBranch();
            if (this.getEvolving() == 1){
                float i = this.getTimer() + 1;
                if(this.getTimer() == 1){
                    this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                }
                if(!(this.getTimer() == 50)){
                    setTimer((float) (this.getTimer()+(0.5)));
                }
                if(this.getTimer() > 49.5){

                    if(species == 44 && branch == 0) {
                        this.setPokeSpecies(45);
                    }
                    if(species == 44 && branch == 1) {
                        this.setPokeSpecies(182);
                    }
                    if(species == 61 && branch == 0) {
                        this.setPokeSpecies(62);
                    }
                    if(species == 61 && branch == 1) {
                        this.setPokeSpecies(186);
                    }
                    if(species == 79 && branch == 0) {
                        this.setPokeSpecies(80);
                    }
                    if(species == 79 && branch == 1) {
                        this.setPokeSpecies(199);
                    }
                    if(species == 133 && branch == 0) {
                        this.setPokeSpecies(134);
                    }
                    if(species == 133 && branch == 1) {
                        this.setPokeSpecies(135);
                    }
                    if(species == 133 && branch == 2) {
                        this.setPokeSpecies(136);
                    }
                    if(species == 133 && branch == 3) {
                        this.setPokeSpecies(470);
                    }
                    if(species == 133 && branch == 4) {
                        this.setPokeSpecies(471);
                    }
                    if(species == 133 && branch == 5) {
                        this.setPokeSpecies(700);
                    }
                    if(species == 133 && branch == 6) {
                        this.setPokeSpecies(196);
                    }
                    if(species == 133 && branch == 7) {
                        this.setPokeSpecies(197);
                    }

                    if(species == 236 && branch == 0) {
                        this.setPokeSpecies(106);
                    }
                    if(species == 236 && branch == 1) {
                        this.setPokeSpecies(107);
                    }
                    if(species == 236 && branch == 2) {
                        this.setPokeSpecies(237);
                    }

                    if(species == 265 && branch == 0) {
                        this.setPokeSpecies(266);
                    }
                    if(species == 265 && branch == 1) {
                        this.setPokeSpecies(268);
                    }

                    if(species == 281 && branch == 0) {
                        this.setPokeSpecies(282);
                    }
                    if(species == 281 && branch == 1) {
                        this.setPokeSpecies(475);
                    }
                    if(species == 361 && branch == 0) {
                        this.setPokeSpecies(362);
                    }
                    if(species == 361 && branch == 1) {
                        this.setPokeSpecies(478);
                    }




                    if(species == 1) {
                        this.setPokeSpecies(2);
                    }
                    if(species == 2) {
                        this.setPokeSpecies(3);
                    }
                    if(species == 4) {
                        this.setPokeSpecies(5);
                    }
                    if(species == 5) {
                        this.setPokeSpecies(6);
                    }
                    if(species == 7) {
                        this.setPokeSpecies(8);
                    }
                    if(species == 8) {
                        this.setPokeSpecies(9);
                    }
                    if(species == 10) {
                        this.setPokeSpecies(11);
                    }
                    if(species == 11) {
                        this.setPokeSpecies(12);
                    }
                    if(species == 13) {
                        this.setPokeSpecies(14);
                    }
                    if(species == 14) {
                        this.setPokeSpecies(15);
                    }
                    if(species == 16) {
                        this.setPokeSpecies(17);
                    }
                    if(species == 17) {
                        this.setPokeSpecies(18);
                    }
                    if(species == 19) {
                        this.setPokeSpecies(20);
                    }
                    if(species == 21) {
                        this.setPokeSpecies(22);
                    }
                    if(species == 23) {
                        this.setPokeSpecies(24);
                    }
                    if(species == 172) {
                        this.setPokeSpecies(25);
                    }
                    if(species == 25) {
                        this.setPokeSpecies(26);
                    }
                    if(species == 27) {
                        this.setPokeSpecies(28);
                    }
                    if(species == 29) {
                        this.setPokeSpecies(30);
                    }
                    if(species == 30) {
                        this.setPokeSpecies(31);
                    }
                    if(species == 32) {
                        this.setPokeSpecies(33);
                    }
                    if(species == 33) {
                        this.setPokeSpecies(34);
                    }
                    if(species == 173) {
                        this.setPokeSpecies(35);
                    }
                    if(species == 35) {
                        this.setPokeSpecies(36);
                    }
                    if(species == 37) {
                        this.setPokeSpecies(38);
                    }
                    if(species == 174) {
                        this.setPokeSpecies(39);
                    }
                    if(species == 39) {
                        this.setPokeSpecies(40);
                    }
                    if(species == 41) {
                        this.setPokeSpecies(42);
                    }
                    if(species == 41) {
                        this.setPokeSpecies(169);
                    }
                    if(species == 43) {
                        this.setPokeSpecies(44);
                    }
                    if(species == 46) {
                        this.setPokeSpecies(47);
                    }
                    if(species == 48) {
                        this.setPokeSpecies(49);
                    }
                    if(species == 50) {
                        this.setPokeSpecies(51);
                    }
                    //Split Line
                    if(species == 54) {
                        this.setPokeSpecies(55);
                    }
                    if(species == 56) {
                        this.setPokeSpecies(57);
                    }
                    if(species == 58) {
                        this.setPokeSpecies(59);
                    }
                    if(species == 60) {
                        this.setPokeSpecies(61);
                    }
                    //split line
                    if(species == 63) {
                        this.setPokeSpecies(64);
                    }
                    if(species == 64) {
                        this.setPokeSpecies(65);
                    }
                    if(species == 66) {
                        this.setPokeSpecies(67);
                    }
                    if(species == 67) {
                        this.setPokeSpecies(68);
                    }
                    if(species == 69) {
                        this.setPokeSpecies(70);
                    }
                    if(species == 70) {
                        this.setPokeSpecies(71);
                    }
                    if(species == 72) {
                        this.setPokeSpecies(73);
                    }
                    if(species == 74) {
                        this.setPokeSpecies(75);
                    }
                    if(species == 75) {
                        this.setPokeSpecies(76);
                    }
                    if(species == 77) {
                        this.setPokeSpecies(78);
                    }
                    //split line
                    if(species == 81) {
                        this.setPokeSpecies(82);
                    }
                    if(species == 82) {
                        this.setPokeSpecies(462);
                    }
                    if(species == 84) {
                        this.setPokeSpecies(85);
                    }
                    if(species == 86) {
                        this.setPokeSpecies(87);
                    }
                    if(species == 88) {
                        this.setPokeSpecies(89);
                    }
                    if(species == 90) {
                        this.setPokeSpecies(91);
                    }
                    if(species == 92) {
                        this.setPokeSpecies(93);
                    }
                    if(species == 93) {
                        this.setPokeSpecies(94);
                    }
                    if(species == 95) {
                        this.setPokeSpecies(208);
                    }
                    if(species == 96) {
                        this.setPokeSpecies(97);
                    }
                    if(species == 98) {
                        this.setPokeSpecies(99);
                    }
                    if(species == 100) {
                        this.setPokeSpecies(101);
                    }
                    if(species == 102) {
                        this.setPokeSpecies(103);
                    }
                    if(species == 104) {
                        this.setPokeSpecies(105);
                    }
                    //split line
                    if(species == 108) {
                        this.setPokeSpecies(463);
                    }
                    if(species == 109) {
                        this.setPokeSpecies(110);
                    }
                    if(species == 111) {
                        this.setPokeSpecies(112);
                    }
                    if(species == 112) {
                        this.setPokeSpecies(464);
                    }
                    if(species == 440) {
                        this.setPokeSpecies(113);
                    }
                    if(species == 113) {
                        this.setPokeSpecies(242);
                    }
                    if(species == 114) {
                        this.setPokeSpecies(465);
                    }
                    if(species == 116) {
                        this.setPokeSpecies(117);
                    }
                    if(species == 117) {
                        this.setPokeSpecies(230);
                    }
                    if(species == 118) {
                        this.setPokeSpecies(119);
                    }
                    if(species == 120) {
                        this.setPokeSpecies(121);
                    }
                    if(species == 439) {
                        this.setPokeSpecies(122);
                    }
                    if(species == 122) {
                        this.setPokeSpecies(866);
                    }
                    //split line
                    if(species == 238) {
                        this.setPokeSpecies(124);
                    }
                    if(species == 239) {
                        this.setPokeSpecies(125);
                    }
                    if(species == 125) {
                        this.setPokeSpecies(466);
                    }
                    if(species == 240) {
                        this.setPokeSpecies(126);
                    }
                    if(species == 126) {
                        this.setPokeSpecies(467);
                    }
                    if(species == 129) {
                        this.setPokeSpecies(130);
                    }
                    //split line
                    if(species == 137) {
                        this.setPokeSpecies(233);
                    }
                    if(species == 233) {
                        this.setPokeSpecies(474 );
                    }
                    if(species == 138) {
                        this.setPokeSpecies(139 );
                    }
                    if(species == 140) {
                        this.setPokeSpecies(141 );
                    }
                    if(species == 446) {
                        this.setPokeSpecies(143 );
                    }
                    if(species == 147) {
                        this.setPokeSpecies(148 );
                    }
                    if(species == 148) {
                        this.setPokeSpecies(149 );
                    }
                    if(species == 152) {
                        this.setPokeSpecies(153 );
                    }
                    if(species == 153) {
                        this.setPokeSpecies(154 );
                    }
                    if(species == 155) {
                        this.setPokeSpecies(156 );
                    }
                    if(species == 156) {
                        this.setPokeSpecies(157 );
                    }
                    if(species == 158) {
                        this.setPokeSpecies(159 );
                    }
                    if(species == 159) {
                        this.setPokeSpecies(160 );
                    }
                    if(species == 161) {
                        this.setPokeSpecies(162 );
                    }
                    if(species == 163) {
                        this.setPokeSpecies(164 );
                    }
                    if(species == 165) {
                        this.setPokeSpecies(166 );
                    }
                    if(species == 167) {
                        this.setPokeSpecies(168 );
                    }
                    if(species == 170) {
                        this.setPokeSpecies(171 );
                    }
                    if(species == 175) {
                        this.setPokeSpecies(176 );
                    }
                    if(species == 176) {
                        this.setPokeSpecies(468 );
                    }
                    if(species == 177) {
                        this.setPokeSpecies(178 );
                    }
                    if(species == 179) {
                        this.setPokeSpecies(180 );
                    }
                    if(species == 180) {
                        this.setPokeSpecies(181 );
                    }
                    if(species == 298) {
                        this.setPokeSpecies(183 );
                    }
                    if(species == 183) {
                        this.setPokeSpecies(184 );
                    }
                    if(species == 438) {
                        this.setPokeSpecies(185 );
                    }
                    if(species == 187) {
                        this.setPokeSpecies(188 );
                    }
                    if(species == 194) {
                        this.setPokeSpecies(195 );
                    }
                    if(species == 188) {
                        this.setPokeSpecies(189 );
                    }
                    if(species == 190) {
                        this.setPokeSpecies(424 );
                    }
                    if(species == 191) {
                        this.setPokeSpecies(192 );
                    }
                    if(species == 193) {
                        this.setPokeSpecies(469 );
                    }
                    if(species == 198) {
                        this.setPokeSpecies(430 );
                    }
                    if(species == 200) {
                        this.setPokeSpecies(429 );
                    }
                    if(species == 360) {
                        this.setPokeSpecies(202 );
                    }
                    if(species == 204) {
                        this.setPokeSpecies(205 );
                    }
                    if(species == 207) {
                        this.setPokeSpecies(472 );
                    }
                    if(species == 209) {
                        this.setPokeSpecies(210 );
                    }
                    if(species == 216) {
                        this.setPokeSpecies(217 );
                    }
                    if(species == 217) {
                        this.setPokeSpecies(901 );
                    }
                    if(species == 218) {
                        this.setPokeSpecies(219 );
                    }
                    if(species == 220) {
                        this.setPokeSpecies(221 );
                    }
                    if(species == 221) {
                        this.setPokeSpecies(473 );
                    }
                    if(species == 223) {
                        this.setPokeSpecies(224 );
                    }
                    if(species == 458) {
                        this.setPokeSpecies(226 );
                    }
                    if(species == 228) {
                        this.setPokeSpecies(229 );
                    }
                    if(species == 231) {
                        this.setPokeSpecies(232 );
                    }
                    if(species == 246) {
                        this.setPokeSpecies(247 );
                    }
                    if(species == 247) {
                        this.setPokeSpecies(248 );
                    }

                    if(species == 133 & isday) {
                        this.setPokeSpecies(196);
                        this.setEvolutionbranch(6);

                    }
                    if(species == 133 & !isday) {
                        this.setPokeSpecies(197);
                        this.setEvolutionbranch(7);
                    }

                }
                if(this.getTimer() == 50) {
                    this.setPokeStats();
                    this.setPokeName();
                    this.setHealth(this.getMaxHealth());
                    this.setTrueHp();
                    this.setTrueAttack();
                    this.setTrueDefence();
                    this.setTrueSpAttack();
                    this.setTrueSpDefence();
                    this.setTrueSpeed();
                    setTimer(0);
                    setEvolving(0);
                }
            }
        }
        super.tick();
    }

}


