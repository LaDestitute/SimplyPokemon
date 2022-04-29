package com.aquatictyphoon.pokemonmod.setup.entities.pokemon;

import com.aquatictyphoon.pokemonmod.setup.capability.PokemonNature;
import com.aquatictyphoon.pokemonmod.setup.capability.PokemonTypes;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.Registration;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

@SuppressWarnings("EntityConstructor")
public class PokemonEntity extends TamableAnimal {

    private int inLove;
    @javax.annotation.Nullable
    private UUID loveCause;

    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> POKEMON_LEVEL = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
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

    private static final EntityDataAccessor<Integer> TYPE1 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TYPE2 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

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
            this.entityData.set(NICKNAME, "BadEgg");
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
            this.entityData.set(NICKNAME, "Bulbasaur");
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
            this.entityData.set(NICKNAME, "Ivysaur");
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
            this.entityData.set(NICKNAME, "Venusaur");
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
            this.entityData.set(NICKNAME, "Charmander");
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
            this.entityData.set(NICKNAME, "Charmeleon");
        }
        else if(species == 6) {
            this.entityData.set(TYPE1, PokemonTypes.FIRE.ordinal());
            this.entityData.set(TYPE2, PokemonTypes.FLYING.ordinal());
            this.entityData.set(BASE_HP, 78);
            this.entityData.set(BASE_ATTACK, 84);
            this.entityData.set(BASE_DEFENCE, 78);
            this.entityData.set(BASE_SP_ATTACK, 109);
            this.entityData.set(BASE_SP_DEFENCE, 85);
            this.entityData.set(BASE_SPEED, 1000);
            this.entityData.set(NICKNAME, "Charizard");
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
            this.entityData.set(NICKNAME, "Squirtle");
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
            this.entityData.set(NICKNAME, "Wartortle");
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
            this.entityData.set(NICKNAME, "Blastoise");
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
            this.entityData.set(NICKNAME, "Caterpie");
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
            this.entityData.set(NICKNAME, "Metapod");
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
            this.entityData.set(NICKNAME, "Butterfree");
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
            this.entityData.set(NICKNAME, "Weedle");
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
            this.entityData.set(NICKNAME, "Kakuna");
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
            this.entityData.set(NICKNAME, "Beedrill");
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
            this.entityData.set(NICKNAME, "Pidgey");
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
            this.entityData.set(NICKNAME, "Pidgeotto");
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
            this.entityData.set(NICKNAME, "Pidgeot");
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
            this.entityData.set(NICKNAME, "Rattata");
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
            this.entityData.set(NICKNAME, "Raticate");
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
            this.entityData.set(NICKNAME, "Spearow");
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
            this.entityData.set(NICKNAME, "Fearow");
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
            this.entityData.set(NICKNAME, "Ekans");
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
            this.entityData.set(NICKNAME, "Arbok");
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
            this.entityData.set(NICKNAME, "Pikachu");
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
            this.entityData.set(NICKNAME, "Raichu");
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
            this.entityData.set(NICKNAME, "Sandshrew");
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
            this.entityData.set(NICKNAME, "Sandslash");
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
            this.entityData.set(NICKNAME, "Nidoran ♀");
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
            this.entityData.set(NICKNAME, "Nidorina");
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
            this.entityData.set(NICKNAME, "Nidoqueen");
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
            this.entityData.set(NICKNAME, "Nidoran ♂");
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
            this.entityData.set(NICKNAME, "Nidorino");
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
            this.entityData.set(NICKNAME, "Nidoking");
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
            this.entityData.set(NICKNAME, "Clefairy");
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
            this.entityData.set(NICKNAME, "Clefable");
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
            this.entityData.set(NICKNAME, "Vulpix");
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
            this.entityData.set(NICKNAME, "Ninetales");
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
            this.entityData.set(NICKNAME, "Jigglypuff");
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
            this.entityData.set(NICKNAME, "Wigglytuff");
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
            this.entityData.set(NICKNAME, "Zubat");
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
            this.entityData.set(NICKNAME, "Golbat");
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
            this.entityData.set(NICKNAME, "Oddish");
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
            this.entityData.set(NICKNAME, "Gloom");
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
            this.entityData.set(NICKNAME, "Vileplume");
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
            this.entityData.set(NICKNAME, "Paras");
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
            this.entityData.set(NICKNAME, "Parasect");
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
            this.entityData.set(NICKNAME, "Venonat");
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
            this.entityData.set(NICKNAME, "Venomoth");
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
            this.entityData.set(NICKNAME, "Diglett");
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
            this.entityData.set(NICKNAME, "Dugtrio");
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
            this.entityData.set(NICKNAME, "Meowth");
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
            this.entityData.set(NICKNAME, "Persian");
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
            this.entityData.set(NICKNAME, "Psyduck");
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
            this.entityData.set(NICKNAME, "Golduck");
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
            this.entityData.set(NICKNAME, "Mankey");
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
            this.entityData.set(NICKNAME, "Primape");
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
            this.entityData.set(NICKNAME, "Growlthie");
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
            this.entityData.set(NICKNAME, "Arcanine");
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
            this.entityData.set(NICKNAME, "Poliwag");
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
            this.entityData.set(NICKNAME, "Poliwhirl");
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
            this.entityData.set(NICKNAME, "Poliwrath");
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
            this.entityData.set(NICKNAME, "Abra");
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
            this.entityData.set(NICKNAME, "Kadabra");
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
            this.entityData.set(NICKNAME, "Alakazam");
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
            this.entityData.set(NICKNAME, "Machop");
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
            this.entityData.set(NICKNAME, "Machoke");
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
            this.entityData.set(NICKNAME, "Machamp");
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
            this.entityData.set(NICKNAME, "Bellsprout");
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
            this.entityData.set(NICKNAME, "Weepinbell");
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
            this.entityData.set(NICKNAME, "Victreebel");
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
            this.entityData.set(NICKNAME, "Tentacool");
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
            this.entityData.set(NICKNAME, "Tentacrule");
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
            this.entityData.set(NICKNAME, "Geodude");
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
            this.entityData.set(NICKNAME, "Graveler");
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
            this.entityData.set(NICKNAME, "Golem");
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
            this.entityData.set(NICKNAME, "Ponyta");
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
            this.entityData.set(NICKNAME, "Rapidash");
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
            this.entityData.set(NICKNAME, "Slowpoke");
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
            this.entityData.set(NICKNAME, "Slowbro");
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
            this.entityData.set(NICKNAME, "Magnemite");
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
            this.entityData.set(NICKNAME, "Magneton");
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
            this.entityData.set(NICKNAME, "Farfetch'd");
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
            this.entityData.set(NICKNAME, "Doduo");
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
            this.entityData.set(NICKNAME, "Dodrio");
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
            this.entityData.set(NICKNAME, "Seel");
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
            this.entityData.set(NICKNAME, "Dewgong");
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
            this.entityData.set(NICKNAME, "Grimer");
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
            this.entityData.set(NICKNAME, "Muk");
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
            this.entityData.set(NICKNAME, "Shellder");
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
            this.entityData.set(NICKNAME, "Cloyster");
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
            this.entityData.set(NICKNAME, "Gastly");
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
            this.entityData.set(NICKNAME, "Haunter");
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
            this.entityData.set(NICKNAME, "Gengar");
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
            this.entityData.set(NICKNAME, "Onix");
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
            this.entityData.set(NICKNAME, "Drowzee");
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
            this.entityData.set(NICKNAME, "Hypno");
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
            this.entityData.set(NICKNAME, "Krabby");
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
            this.entityData.set(NICKNAME, "Kingler");
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
            this.entityData.set(NICKNAME, "Voltob");
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
            this.entityData.set(NICKNAME, "Electrode");
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
            this.entityData.set(NICKNAME, "Exeggcute");
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
            this.entityData.set(NICKNAME, "Exeggutor");
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
            this.entityData.set(NICKNAME, "Cubone");
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
            this.entityData.set(NICKNAME, "Marowak");
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
            this.entityData.set(NICKNAME, "Hitmonlee");
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
            this.entityData.set(NICKNAME, "Hitmonchan");
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
            this.entityData.set(NICKNAME, "Lickitung");
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
            this.entityData.set(NICKNAME, "Koffing");
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
            this.entityData.set(NICKNAME, "Weezing");
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
            this.entityData.set(NICKNAME, "Rhyhorn");
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
            this.entityData.set(NICKNAME, "Rhydon");
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
            this.entityData.set(NICKNAME, "Chansey");
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
            this.entityData.set(NICKNAME, "Tangela");
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
            this.entityData.set(NICKNAME, "Kangaskhan");
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
            this.entityData.set(NICKNAME, "Horsea");
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
            this.entityData.set(NICKNAME, "Seadra");
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
            this.entityData.set(NICKNAME, "Goldeen");
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
            this.entityData.set(NICKNAME, "Seeking");
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
            this.entityData.set(NICKNAME, "Staryu");
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
            this.entityData.set(NICKNAME, "Starmie");
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
            this.entityData.set(NICKNAME, "Mr. Mime");
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
            this.entityData.set(NICKNAME, "Scyther");
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
            this.entityData.set(NICKNAME, "Jynx");
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
            this.entityData.set(NICKNAME, "Electabuzz");
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
            this.entityData.set(NICKNAME, "Magmar");
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
            this.entityData.set(NICKNAME, "Pinsir");
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
            this.entityData.set(NICKNAME, "Tauros");
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
            this.entityData.set(NICKNAME, "Magikarp");
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
            this.entityData.set(NICKNAME, "Gyarados");
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
            this.entityData.set(NICKNAME, "Lapras");
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
            this.entityData.set(NICKNAME, "Ditto");
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
            this.entityData.set(NICKNAME, "Eevee");
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
            this.entityData.set(NICKNAME, "Vaporeon");
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
            this.entityData.set(NICKNAME, "Jolteon");
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
            this.entityData.set(NICKNAME, "Flareon");
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
            this.entityData.set(NICKNAME, "Porygon");
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
            this.entityData.set(NICKNAME, "Omanyte");
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
            this.entityData.set(NICKNAME, "Omastar");
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
            this.entityData.set(NICKNAME, "Kabuto");
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
            this.entityData.set(NICKNAME, "Kabutops");
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
            this.entityData.set(NICKNAME, "Aerodactyl");
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
            this.entityData.set(NICKNAME, "Snorlax");
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
            this.entityData.set(NICKNAME, "Articuno");
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
            this.entityData.set(NICKNAME, "Zapdos");
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
            this.entityData.set(NICKNAME, "Moltres");
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
            this.entityData.set(NICKNAME, "Dratini");
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
            this.entityData.set(NICKNAME, "Dragonair");
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
            this.entityData.set(NICKNAME, "Dragonite");
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
            this.entityData.set(NICKNAME, "Mewtwo");
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
            this.entityData.set(NICKNAME, "Mew");
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
            this.entityData.set(NICKNAME, "Chikorita");
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
            this.entityData.set(NICKNAME, "Bayleef");
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
            this.entityData.set(NICKNAME, "Maganium");
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
            this.entityData.set(NICKNAME, "Cyndaquil");
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
            this.entityData.set(NICKNAME, "Quilava");
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
            this.entityData.set(NICKNAME, "Typhlosion");
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
            this.entityData.set(NICKNAME, "Totodile");
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
            this.entityData.set(NICKNAME, "Crocanaaw");
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
            this.entityData.set(NICKNAME, "Feraligatr");
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
            this.entityData.set(NICKNAME, "Sentret");
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
            this.entityData.set(NICKNAME, "Furret");
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
            this.entityData.set(NICKNAME, "Hoothoot");
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
            this.entityData.set(NICKNAME, "Noctowl");
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
            this.entityData.set(NICKNAME, "Ledyba");
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
            this.entityData.set(NICKNAME, "Ledian");
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
            this.entityData.set(NICKNAME, "Spinarak");
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
            this.entityData.set(NICKNAME, "Ariados");
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
            this.entityData.set(NICKNAME, "Crobat");
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
            this.entityData.set(NICKNAME, "Chinchou");
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
            this.entityData.set(NICKNAME, "Lanturn");
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
            this.entityData.set(NICKNAME, "Pichu");
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
            this.entityData.set(NICKNAME, "Cleffa");
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
            this.entityData.set(NICKNAME, "Igglybuff");
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
            this.entityData.set(NICKNAME, "Togepi");
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
            this.entityData.set(NICKNAME, "Togetic");
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
            this.entityData.set(NICKNAME, "Natu");
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
            this.entityData.set(NICKNAME, "Xatu");
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
            this.entityData.set(NICKNAME, "Mareep");
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
            this.entityData.set(NICKNAME, "Flaaffy");
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
            this.entityData.set(NICKNAME, "Ampharos");
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
            this.entityData.set(NICKNAME, "Bellossom");
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
            this.entityData.set(NICKNAME, "Marill");
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
            this.entityData.set(NICKNAME, "Azumarill");
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
            this.entityData.set(NICKNAME, "Sudowoodo");
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
            this.entityData.set(NICKNAME, "Politoed");
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
            this.entityData.set(NICKNAME, "Hoppip");
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
            this.entityData.set(NICKNAME, "Skiploom");
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
            this.entityData.set(NICKNAME, "Jumpluff");
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
            this.entityData.set(NICKNAME, "Aipom");
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
            this.entityData.set(NICKNAME, "Sunkern");
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
            this.entityData.set(NICKNAME, "Sunflora");
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
            this.entityData.set(NICKNAME, "Yanma");
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
            this.entityData.set(NICKNAME, "Wooper");
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
            this.entityData.set(NICKNAME, "Quagsire");
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
            this.entityData.set(NICKNAME, "Espeon");
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
            this.entityData.set(NICKNAME, "Umbreon");
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
            this.entityData.set(NICKNAME, "Murkrow");
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
            this.entityData.set(NICKNAME, "Slowing");
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
            this.entityData.set(NICKNAME, "Misdreavus");
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
            this.entityData.set(NICKNAME, "Unown");
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
            this.entityData.set(NICKNAME, "Wobbuffet");
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
            this.entityData.set(NICKNAME, "Girafarig");
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
            this.entityData.set(NICKNAME, "Pineco");
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
            this.entityData.set(NICKNAME, "Forretress");
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
            this.entityData.set(NICKNAME, "Dunsparce");
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
            this.entityData.set(NICKNAME, "Gligar");
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
            this.entityData.set(NICKNAME, "Steelix");
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
            this.entityData.set(NICKNAME, "Snubbull");
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
            this.entityData.set(NICKNAME, "Granbull");
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
            this.entityData.set(NICKNAME, "Qwilfish");
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
            this.entityData.set(NICKNAME, "Scizor");
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
            this.entityData.set(NICKNAME, "Shuckle");
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
            this.entityData.set(NICKNAME, "Heracross");
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
            this.entityData.set(NICKNAME, "Sneasel");
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
            this.entityData.set(NICKNAME, "Teddiursa");
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
            this.entityData.set(NICKNAME, "Ursaring");
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
            this.entityData.set(NICKNAME, "Slugma");
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
            this.entityData.set(NICKNAME, "Magcargo");
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
            this.entityData.set(NICKNAME, "Swinub");
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
            this.entityData.set(NICKNAME, "Piloswine");
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
            this.entityData.set(NICKNAME, "Corsola");
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
            this.entityData.set(NICKNAME, "Remoraid");
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
            this.entityData.set(NICKNAME, "Octillery");
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
            this.entityData.set(NICKNAME, "Delibird");
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
            this.entityData.set(NICKNAME, "Mantine");
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
            this.entityData.set(NICKNAME, "Skarmory");
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
            this.entityData.set(NICKNAME, "Houndour");
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
            this.entityData.set(NICKNAME, "Houndoom");
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
            this.entityData.set(NICKNAME, "Kingdra");
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
            this.entityData.set(NICKNAME, "Phanpy");
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
            this.entityData.set(NICKNAME, "Donphan");
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
            this.entityData.set(NICKNAME, "Porygon 2");
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
            this.entityData.set(NICKNAME, "Stantler");
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
            this.entityData.set(NICKNAME, "Smeargle");
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
            this.entityData.set(NICKNAME, "Tyrogue");
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
            this.entityData.set(NICKNAME, "Hitmontop");
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
            this.entityData.set(NICKNAME, "Smoochum");
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
            this.entityData.set(NICKNAME, "Elekid");
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
            this.entityData.set(NICKNAME, "Magby");
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
            this.entityData.set(NICKNAME, "Miltank");
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
            this.entityData.set(NICKNAME, "Blissey");
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
            this.entityData.set(NICKNAME, "Raikou");
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
            this.entityData.set(NICKNAME, "Entei");
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
            this.entityData.set(NICKNAME, "Suicune");
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
            this.entityData.set(NICKNAME, "Larvitar");
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
            this.entityData.set(NICKNAME, "Pupitar");
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
            this.entityData.set(NICKNAME, "Tyranitar");
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
            this.entityData.set(NICKNAME, "Lugia");
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
            this.entityData.set(NICKNAME, "Ho-Ho");
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
            this.entityData.set(NICKNAME, "Celebi");
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 3);
        this.entityData.define(POKEMON_LEVEL, 1);
        this.entityData.define(SPECIES, 152);
        this.entityData.define(NICKNAME, "MissingNo");
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

    }


    protected void setShinyness(int pSize) {
        int i = Mth.clamp(pSize, 0, 1);
        this.entityData.set(SHINY, i);

    }

    protected void setHappiness(int pSize) {
        int i = Mth.clamp(pSize, 0, 250);
        this.entityData.set(HAPPINESS, i);

    }

    public int getHappiness() {
        return this.entityData.get(HAPPINESS);
    }

    public String getPokeName() {
        return this.entityData.get(NICKNAME);
    }

    public int getPokeType1() {
        return this.entityData.get(TYPE1);
    }

    public int getPokeType2() {
        return this.entityData.get(TYPE2);
    }

    public void aiStep() {
        int shiny = this.getShinyness();
        if (this.level.isClientSide && (shiny == 1)) {
            for (int i = 0; i < 1; ++i) {
                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
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

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Size", this.getSize());
        pCompound.putInt("Level", this.getPokeLevel());
        pCompound.putInt("Species", this.getPokeSpecies());
        pCompound.putString("Nickname", this.getPokeName());
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


        pCompound.putInt("InLove", this.inLove);
        if (this.loveCause != null) {
            pCompound.putUUID("LoveCause", this.loveCause);
        }
        pCompound.putInt("Happiness", this.getHappiness());

    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
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
        this.loveCause = pCompound.hasUUID("LoveCause") ? pCompound.getUUID("LoveCause") : null;
        this.setHappiness(pCompound.getInt("Happiness"));


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
        int shinyroll = random.nextInt(4096) + 1;
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

    public void setInLove(@javax.annotation.Nullable Player pPlayer) {
        this.inLove = 200;
        if (pPlayer != null) {
            this.loveCause = pPlayer.getUUID();
        }
        this.level.broadcastEntityEvent(this, (byte) 18);
    }

    public void setInLoveTime(int pTicks) {
        this.inLove = pTicks;
    }

    public int getInLoveTime() {
        return this.inLove;
    }

    @javax.annotation.Nullable
    public ServerPlayer getLoveCause() {
        if (this.loveCause == null) {
            return null;
        } else {
            Player player = this.level.getPlayerByUUID(this.loveCause);
            return player instanceof ServerPlayer ? (ServerPlayer) player : null;
        }
    }

    public boolean isInLove() {
        return this.inLove > 0;
    }

    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            this.setHappiness(this.entityData.get(HAPPINESS) + 5);
            return super.hurt(pSource, pAmount);
        }
    }


    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!(this.level.isClientSide)) {
            if (pPlayer.isHolding((Registration.RARECANDY.get())) && this.isOwnedBy(pPlayer) && !(this.getPokeLevel() > 99)) {
                itemstack.shrink(1);
                this.setInLove(pPlayer);
                this.setPokeLevel((this.entityData.get(POKEMON_LEVEL)) + 1);
                this.setTrueHp();
                this.setTrueAttack();
                this.setTrueDefence();
                this.setTrueSpAttack();
                this.setTrueSpDefence();
                this.setTrueSpeed();


                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 4);
                return InteractionResult.SUCCESS;

            }
            if ((itemstack.getItem() == Items.AIR) && this.isOwnedBy(pPlayer) && Objects.requireNonNull(getOwner()).isShiftKeyDown()){
                if(this.entityData.get(HAPPINESS) > 250){
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " couldn't possibly love you more!"), true);
                }else if ((this.entityData.get(HAPPINESS) > 200) && (this.entityData.get(HAPPINESS) < 250)){
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is very friendly with you!"), true);
                }else if ((this.entityData.get(HAPPINESS) > 150) && (this.entityData.get(HAPPINESS) < 200)){
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is friendly with you, but wants more attention!"), true);
                }
                else if ((this.entityData.get(HAPPINESS) > 100) && (this.entityData.get(HAPPINESS) < 150)){
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is getting used to you, but it believes in you!"), true);
                }
                else if ((this.entityData.get(HAPPINESS) > 50) && (this.entityData.get(HAPPINESS) < 100)){
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " does not have strong feelings to you"), true);
                }
                else if ((this.entityData.get(HAPPINESS) > 1) && (this.entityData.get(HAPPINESS) < 50)){
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " doesn't like you nor trust you."), true);
                }else{
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " absolutely hates you."), true);
                }

                return InteractionResult.SUCCESS;
            }


            if (pPlayer.isHolding((Items.NAME_TAG))) {
                return InteractionResult.FAIL;
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }
}


