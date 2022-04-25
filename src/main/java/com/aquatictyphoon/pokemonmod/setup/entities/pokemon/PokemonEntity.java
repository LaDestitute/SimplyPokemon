package com.aquatictyphoon.pokemonmod.setup.entities.pokemon;

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

    private static final EntityDataAccessor<Integer> HP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SP_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SP_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> NATURE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    public PokemonEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        setCustomNameVisible(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public void setTame(boolean p_30443_) {
        super.setTame(p_30443_);
        if (p_30443_) {
            Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(8.0D);
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


    public String getPokeName() {
        int species = this.getPokeSpecies();

        if (species == 0) {
            return ("BAD EGG");
        }
        else if (species == 1) {
            return ("Bulbasaur");
        } else if (species == 152) {
            return ("Chikorita");
        } else if (species == 155) {
            return ("Cyndaquil");
        } else if (species == 158) {
            return ("Totodile");
        } else {
            return ("MissingNo");
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 3);
        this.entityData.define(POKEMON_LEVEL, 1);
        this.entityData.define(SPECIES, 1);
        this.entityData.define(NICKNAME, getPokeName());
        this.entityData.define(SHINY, 0);
        this.entityData.define(HAPPINESS, 100);
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


    protected void setNickname(String string) {
        this.entityData.set(NICKNAME, getPokeName());
    }

    protected void setPokeSpecies(int pSize) {
        int i = Mth.clamp(pSize, -1000, 1000);
        this.entityData.set(SPECIES, i);
    }


    protected void setPokeLevel(int pSize) {
        int i = Mth.clamp(pSize, 1, 100);
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

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Size", this.getSize());
        pCompound.putInt("Level", this.getPokeLevel());
        pCompound.putInt("Species", this.getPokeSpecies());
        pCompound.putString("Nickname", this.getPokeName());
        pCompound.putInt("Shiny", this.getShinyness());
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
        this.setNickname(pCompound.getString("Nickname"));
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
        return super.getDimensions(pPose).scale(0.25F * (float) this.getSize());
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

        int randomchance = random.nextInt(99) + 1;
        int randomchance2 = random.nextInt(99) + 1;

        if (randomchance < 50) {
            this.setSize(3);
        }
        if ((randomchance > 50) && (randomchance < 75)) {
            if (randomchance2 < 50) {
                this.setSize(4);
            } else {
                this.setSize(2);
            }

        }
        if (randomchance > 75) {
            if (randomchance2 < 50) {
                this.setSize(1);
            } else {
                this.setSize(5);
            }
        }
        return pSpawnData;
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

    public boolean hurt(DamageSource pSource, float pAmount) {
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
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.AMBIENT, 3.0F, 1.0F);
                this.setHappiness(this.entityData.get(HAPPINESS) + 4);
                return InteractionResult.SUCCESS;

            }
            if ((itemstack.getItem() == Items.AIR) && this.isOwnedBy(pPlayer) && getOwner().isShiftKeyDown()){
                if (this.entityData.get(HAPPINESS) > 250) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " couldn't possibly love you more!"), true);
                } else if ((this.entityData.get(HAPPINESS) > 200) && (this.entityData.get(HAPPINESS) < 250)) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is very friendly with you!"), true);
                } else if ((this.entityData.get(HAPPINESS) > 150) && (this.entityData.get(HAPPINESS) < 200)) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is friendly with you, but wants more attention!"), true);
                } else if ((this.entityData.get(HAPPINESS) > 100) && (this.entityData.get(HAPPINESS) < 150)) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is getting used to you, but it believes in you!"), true);
                } else if ((this.entityData.get(HAPPINESS) > 50) && (this.entityData.get(HAPPINESS) < 90)) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " does not have strong feelings to you"), true);
                } else if ((this.entityData.get(HAPPINESS) > 1) && (this.entityData.get(HAPPINESS) < 50)) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " doesn't like you nor trust you."), true);
                } else if ((this.entityData.get(HAPPINESS) > 0)) {
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " absolutely hates you."), true);
                }else{
                    pPlayer.displayClientMessage(new TranslatableComponent(getPokeName() + " is curious about you."), true);
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


