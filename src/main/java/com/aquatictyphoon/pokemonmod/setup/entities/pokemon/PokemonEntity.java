package com.aquatictyphoon.pokemonmod.setup.entities.pokemon;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("EntityConstructor")
public class PokemonEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> POKEMON_LEVEL = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    public PokemonEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        setCustomNameVisible(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public void setTame(boolean p_30443_) {
        super.setTame(p_30443_);
        if (p_30443_) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
            this.setHealth(17.0F);
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Registration.RARECANDY.get()), false));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return null;
    }


    public String getPokeName(){

        int species = this.getPokeSpecies();

        if(species == 1) {
            String getPokeName = ("Bulbasaur");
            return getPokeName;
        }
        else if(species == 2) {
            String getPokeName = ("Ivysaur");
            return getPokeName;
        }
        else{
            String getPokeName = ("MissingNo");
            return getPokeName;
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 3);
        this.entityData.define(POKEMON_LEVEL, 1);
        this.entityData.define(SPECIES, 1);
    }

    protected void setPokeSpecies(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(pSize, 0, 151);
        this.entityData.set(SPECIES, i);
    }


    protected void setPokeLevel(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(pSize, 1, 100);
        this.entityData.set(POKEMON_LEVEL, i);
    }

    public int getPokeSpecies() {
        return this.entityData.get(SPECIES);
    }

    protected void setSize(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(pSize, 1, 5);
        this.entityData.set(ID_SIZE, i);
    }

    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    public int getPokeLevel() {
        return this.entityData.get(POKEMON_LEVEL);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Size", this.getSize() - 1);
        pCompound.putInt("Level", this.getPokeLevel() - 1);
        pCompound.putInt("Species", this.getPokeSpecies() - 1);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.setSize(pCompound.getInt("Size") + 1, false);
        this.setPokeLevel(pCompound.getInt("Level") + 1, false);
        this.setPokeSpecies(pCompound.getInt("Species") + 1, false);
        super.readAdditionalSaveData(pCompound);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (ID_SIZE.equals(pKey)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(pKey);
    }

    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose).scale(0.25F * (float) this.getSize());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        Random random = new Random();
        Integer randomchance = random.nextInt(99) + 1;
        Integer randomchance2 = random.nextInt(99) + 1;

        if (randomchance < 50) {
            this.setSize(3, false);
        }
        if ((randomchance > 50) && (randomchance < 75)) {
            if (randomchance2 < 50) {
                this.setSize(4, false);
            } else {
                this.setSize(2, false);
            }

        }
        if ((randomchance > 75) && (randomchance < 100)) {
            if (randomchance2 < 50) {
                this.setSize(1, false);
            } else {
                this.setSize(5, false);
            }
        }
        return pSpawnData;
    }


    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (!(this.level.isClientSide)) {
            if (pPlayer.isHolding((Registration.RARECANDY.get())) && this.isOwnedBy(pPlayer) && !(this.getPokeLevel() > 99 )) {
                itemstack.shrink(1);
                this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY(), this.getZ(), 1.0D, 0.0D, 0.0D);
                this.setPokeLevel((this.entityData.get(POKEMON_LEVEL))+1, false);
                this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.AMBIENT, 3.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }

            if (pPlayer.isHolding((Items.NAME_TAG))){
                return InteractionResult.FAIL;
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }
}
