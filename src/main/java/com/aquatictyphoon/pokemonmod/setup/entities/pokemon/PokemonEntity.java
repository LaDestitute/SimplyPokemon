package com.aquatictyphoon.pokemonmod.setup.entities.pokemon;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

import static com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonStats.Species.*;
import static net.minecraft.world.entity.Entity.RemovalReason.CHANGED_DIMENSION;


public class PokemonEntity extends TamableAnimal {
    private static final EntityDataAccessor<Integer> CURRENT_SPECIES = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_HEALTH = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_SPECIAL_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_SPECIAL_DEFENSE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CATCHRATE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TYPE1 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TYPE2 = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> EVOLVING = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ENTITY_SIZE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> POKE_LEVEL = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> EVO_TIMER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<String> POKE_NAME = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> NICKNAMED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.BOOLEAN);

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

    private static final EntityDataAccessor<Integer> TRUE_HP = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_SP_ATTACK = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_SP_DEFENCE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRUE_SPEED = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> NATURE = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> SHINYNESS = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.BOOLEAN);

    // 0 is no gender 1 is male and 2 is female.
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> PARTYSLOT = SynchedEntityData.defineId(PokemonEntity.class, EntityDataSerializers.INT);

    public void UpdateBaseStats(){
        this.entityData.set(EVOLVING, false);
        int getCurrentSpecies = getSpecies();
        this.entityData.set(ENTITY_SIZE, getSize());
        this.entityData.set(POKE_NAME, values()[getCurrentSpecies].NAME);
        this.entityData.set(BASE_HEALTH, PokemonStats.Species.values()[getCurrentSpecies].BASE_HP);
        this.entityData.set(BASE_ATTACK, PokemonStats.Species.values()[getCurrentSpecies].BASE_ATTACK);
        this.entityData.set(BASE_DEFENCE, PokemonStats.Species.values()[getCurrentSpecies].BASE_ATTACK);
        this.entityData.set(BASE_SPECIAL_ATTACK, PokemonStats.Species.values()[getCurrentSpecies].BASE_SPECIAL_ATTACK);
        this.entityData.set(BASE_SPECIAL_DEFENSE, PokemonStats.Species.values()[getCurrentSpecies].BASE_SPECIAL_DEFENSE);
        this.entityData.set(BASE_SPEED, PokemonStats.Species.values()[getCurrentSpecies].BASE_SPEED);
        this.entityData.set(CATCHRATE, PokemonStats.Species.values()[getCurrentSpecies].CATCHRATE);
        this.entityData.set(TYPE1, (PokemonStats.Species.values()[getCurrentSpecies].POKEMONTYPE1).ordinal());
        this.entityData.set(TYPE2, (PokemonStats.Species.values()[getCurrentSpecies].POKEMONTYPE2).ordinal());
        this.entityData.set(POKE_LEVEL, getPokeLevel());
        this.setHealth(getTrueHP());


    }

    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(PARTYSLOT, 1);

        this.entityData.define(EVO_TIMER, (float)0);
        this.entityData.define(POKE_LEVEL, 1);
        this.entityData.define(ENTITY_SIZE, 3);
        this.entityData.define(EVOLVING, false);
        this.entityData.define(CURRENT_SPECIES, 0);
        int getCurrentSpecies = this.entityData.get(CURRENT_SPECIES);
        this.entityData.define(POKE_NAME, values()[getCurrentSpecies].NAME);

        this.entityData.define(BASE_HEALTH, PokemonStats.Species.values()[getCurrentSpecies].BASE_HP);
        this.entityData.define(BASE_ATTACK, PokemonStats.Species.values()[getCurrentSpecies].BASE_ATTACK);
        this.entityData.define(BASE_DEFENCE, PokemonStats.Species.values()[getCurrentSpecies].BASE_ATTACK);
        this.entityData.define(BASE_SPECIAL_ATTACK, PokemonStats.Species.values()[getCurrentSpecies].BASE_SPECIAL_ATTACK);
        this.entityData.define(BASE_SPECIAL_DEFENSE, PokemonStats.Species.values()[getCurrentSpecies].BASE_SPECIAL_DEFENSE);
        this.entityData.define(BASE_SPEED, PokemonStats.Species.values()[getCurrentSpecies].BASE_SPEED);
        this.entityData.define(CATCHRATE, PokemonStats.Species.values()[getCurrentSpecies].CATCHRATE);
        this.entityData.define(TYPE1, (PokemonStats.Species.values()[getCurrentSpecies].POKEMONTYPE1).ordinal());
        this.entityData.define(TYPE2, (PokemonStats.Species.values()[getCurrentSpecies].POKEMONTYPE2).ordinal());
        this.entityData.define(NICKNAMED, false);
        this.entityData.define(IVS_HP, 15);
        this.entityData.define(IVS_ATTACK, 15);
        this.entityData.define(IVS_DEFENCE, 15);
        this.entityData.define(IVS_SP_ATTACK, 15);
        this.entityData.define(IVS_SP_DEFENCE, 15);
        this.entityData.define(IVS_SPEED, 15);
        this.entityData.define(EVS_HP, 0);
        this.entityData.define(EVS_ATTACK, 0);
        this.entityData.define(EVS_DEFENCE, 0);
        this.entityData.define(EVS_SP_ATTACK, 0);
        this.entityData.define(EVS_SP_DEFENCE, 0);
        this.entityData.define(EVS_SPEED, 0);
        this.entityData.define(TRUE_HP, 80);
        this.entityData.define(TRUE_ATTACK, 0);
        this.entityData.define(TRUE_DEFENCE, 0);
        this.entityData.define(TRUE_SP_ATTACK, 0);
        this.entityData.define(TRUE_SP_DEFENCE, 0);
        this.entityData.define(TRUE_SPEED, 0);
        this.entityData.define(NATURE, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(SHINYNESS, false);

    }

    public void setSpecies(int targetSpecies){
        this.entityData.set(CURRENT_SPECIES, targetSpecies);
    }

    public void setPartySlot(int partySlot){
        this.entityData.set(PARTYSLOT, partySlot);
    }
    public int getPartySlot(){
        return this.entityData.get(PARTYSLOT);
    }
    public int getCatchrate(){
        return this.entityData.get(CATCHRATE);
    }
    public int getSpecies(){
        return this.entityData.get(CURRENT_SPECIES);
    }

    public int getGender() {
        return this.entityData.get(GENDER);
    }

    protected void setGender(int gendervalue ) {
        int gender = Mth.clamp(gendervalue, 0, 2);
        this.entityData.set(GENDER, gender);
    }

    public void setNicknamed(boolean nicknamed) {
        this.entityData.set(NICKNAMED, nicknamed);
    }
    public Boolean getNicknamed() {
        return this.entityData.get(NICKNAMED);
    }

    public void setShinnyness(boolean shinnyness) {
        this.entityData.set(SHINYNESS, shinnyness);
    }


    public Boolean getShinyness() {
        return this.entityData.get(SHINYNESS);
    }

    public int getPokeNature() {
        return this.entityData.get(NATURE);
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

    public void setEvsHp(int maxhp) {
        int hp = Mth.clamp(maxhp, 0, 31);
        this.entityData.set(EVS_HP, hp);
    }
    public void setEvsAttack(int maxattack) {
        int attack = Mth.clamp(maxattack, 0, 31);
        this.entityData.set(EVS_ATTACK, attack);
    }
    public void setEvsSpAttack(int maxspattack) {
        int spattack = Mth.clamp(maxspattack, 0, 31);
        this.entityData.set(EVS_SP_ATTACK, spattack);
    }
    public void setEvsDefence(int maxdefence) {
        int defence = Mth.clamp(maxdefence, 0, 31);
        this.entityData.set(EVS_DEFENCE, defence);
    }
    public void setEvsSpDefence(int maxspdefence) {
        int spdefence = Mth.clamp(maxspdefence, 0, 31);
        this.entityData.set(EVS_SP_DEFENCE, spdefence);
    }
    public void setEvsSpeed(int maxspeed ) {
        int speed = Mth.clamp(maxspeed, 0, 31);
        this.entityData.set(EVS_SPEED, speed);
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


    public void setSize(int pSize) {
        this.entityData.set(ENTITY_SIZE, pSize);
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
        return this.entityData.get(EVS_SP_DEFENCE);
    }
    public int getEvsSpeed() {
        return this.entityData.get(EVS_SPEED);
    }
    public int getEvsHP() {
        return this.entityData.get(EVS_HP);
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
        return this.entityData.get(BASE_SPECIAL_ATTACK);
    }
    public int getBaseDefence() {
        return this.entityData.get(BASE_DEFENCE);
    }
    public int getBaseSPDefence() {
        return this.entityData.get(BASE_SPECIAL_DEFENSE);
    }
    public int getBaseSpeed() {
        return this.entityData.get(BASE_SPEED);
    }
    public int getBaseHP() {
        return this.entityData.get(BASE_HEALTH);
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
    public int getSize() {
        return this.entityData.get(ENTITY_SIZE);
    }

    public String getPokeName(){

        if (getNicknamed() && this.getCustomName().getString() != null) {
            return this.getCustomName().getString();
        }else{
            return this.entityData.get(POKE_NAME);
        }
    }
    void setPokeName(String value){
        this.entityData.set(POKE_NAME, value);
    }
    public void setPokeLevel(int value){
        this.entityData.set(POKE_LEVEL, value);
    }
    public int getPokeLevel() {
        return this.entityData.get(POKE_LEVEL);
    }

    public void setEvoTimer(float timer) {
        float time = Mth.clamp(timer, 0, 50);
        this.entityData.set(EVO_TIMER, timer);
    }

    public float getEvoTimer() {
        return this.entityData.get(EVO_TIMER);
    }


    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        pCompound.putString("NAME", getPokeName());

        pCompound.putInt("POKE_LEVEL", this.entityData.get(POKE_LEVEL));
        pCompound.putInt("ENTITY_SIZE", getSize());
        pCompound.putInt("CURRENT_SPECIES", this.entityData.get(CURRENT_SPECIES));
        pCompound.putInt("BASE_HEALTH", this.entityData.get(BASE_HEALTH));
        pCompound.putInt("BASE_ATTACK", this.entityData.get(BASE_ATTACK));
        pCompound.putInt("BASE_DEFENCE", this.entityData.get(BASE_DEFENCE));
        pCompound.putInt("BASE_SPECIAL_ATTACK", this.entityData.get(BASE_SPECIAL_ATTACK));
        pCompound.putInt("BASE_SPECIAL_DEFENSE", this.entityData.get(BASE_SPECIAL_DEFENSE));
        pCompound.putInt("BASE_SPEED", this.entityData.get(BASE_SPEED));
        pCompound.putInt("CATCHRATE", this.entityData.get(CATCHRATE));
        pCompound.putInt("TYPE1", this.entityData.get(TYPE1));
        pCompound.putInt("TYPE2", this.entityData.get(TYPE2));
        pCompound.putBoolean("EVOLVING", this.entityData.get(EVOLVING));
        pCompound.putInt("IVS_HP", this.getIvsHP());
        pCompound.putInt("IVS_ATTACK", this.getIvsAttack());
        pCompound.putInt("IVS_DEFENCE", this.getIvsDefence());
        pCompound.putInt("IVS_SPECIAL_ATTACK", this.getIvsSPAttack());
        pCompound.putInt("IVS_SPECIAL_DEFENCE", this.getIvsSPDefence());
        pCompound.putInt("IVS_SPEED", this.getIvsSpeed());
        pCompound.putInt("EVS_HP", this.getEvsHP());
        pCompound.putInt("EVS_ATTACK", this.getEvsAttack());
        pCompound.putInt("EVS_DEFENCE", this.getEvsDefence());
        pCompound.putInt("EVS_SPECIAL_ATTACK", this.getEvsSPAttack());
        pCompound.putInt("EVS_SPECIAL_DEFENCE", this.getEvsSPDefence());
        pCompound.putInt("EVS_SPEED", this.getEvsSpeed());
        this.setTrueHp();
        this.setTrueAttack();
        this.setTrueSpAttack();
        this.setTrueDefence();
        this.setTrueSpDefence();
        this.setTrueSpeed();
        pCompound.putInt("NATURE", this.getPokeNature());
        pCompound.putInt("GENDER", this.getGender());
        pCompound.putBoolean("SHINYNESS", this.getShinyness());
        pCompound.putBoolean("NICKNAMED", this.getNicknamed());

        pCompound.putInt("PARTYSLOT", getPartySlot());

    }

    public void readAdditionalSaveData(CompoundTag pCompound) {

        this.setPartySlot((pCompound.getInt("PARTYSLOT")));
        this.setPokeName((pCompound.getString("NAME")));
        this.setPokeLevel(pCompound.getInt("POKE_LEVEL"));
        this.setSize(pCompound.getInt("ENTITY_SIZE"));
        this.setSpecies(pCompound.getInt("CURRENT_SPECIES"));
        this.setIvsHp(pCompound.getInt("IVS_HP"));
        this.setIvsAttack(pCompound.getInt("IVS_ATTACK"));
        this.setIvsDefence(pCompound.getInt("IVS_DEFENCE"));
        this.setIvsSpAttack(  pCompound.getInt("IVS_SPECIAL_ATTACK"));
        this.setIvsSpDefence(   pCompound.getInt("IVS_SPECIAL_DEFENCE"));
        this.setIvsSpeed( pCompound.getInt("IVS_SPEED"));
        this.setIvsHp(  pCompound.getInt("EVS_HP"));
        this.setEvsAttack(   pCompound.getInt("EVS_ATTACK"));
        this.setEvsDefence(  pCompound.getInt("EVS_DEFENCE"));
        this.setEvsAttack(   pCompound.getInt("EVS_SPECIAL_ATTACK"));
        this.setEvsDefence(   pCompound.getInt("EVS_SPECIAL_DEFENCE"));
        this.setEvsSpeed(  pCompound.getInt("EVS_SPEED"));
        this.setNature(pCompound.getInt("NATURE"));
        this.setGender(pCompound.getInt("GENDER"));
        this.setShinnyness(pCompound.getBoolean("SHINYNESS"));
        this.setNicknamed(pCompound.getBoolean("NICKNAMED"));

        super.readAdditionalSaveData(pCompound);
    }


    public PokemonEntity(EntityType<?> pEntityType, Level pLevel) {
        super((EntityType<? extends TamableAnimal>) pEntityType, pLevel);
         int pSize = random.nextInt(3);
         setSize(Mth.clamp(pSize, 1, 3));
    }

    public void setTame(boolean isTame) {
        super.setTame(isTame);
    }



    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        Random random = new Random();
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
        int true_hp = (int) (Math.floor(0.01 * (2 * (getBaseHP()) + (getIvsHP()) + Math.floor(0.25 * (getEvsHP()))) * (getPokeLevel())) + (getPokeLevel()) + 10);
        Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(true_hp);
        this.setHealth(getTrueHP());
        int shinyroll = random.nextInt(4095) + 1;

        if(shinyroll == 4096){
            this.entityData.set(SHINYNESS, Boolean.TRUE);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public @NotNull EntityDimensions getDimensions(@NotNull Pose pPose) {
        return super.getDimensions(pPose).scale((0.5F * ((float) this.entityData.get(ENTITY_SIZE)) + ((float) this.entityData.get(POKE_LEVEL)/85)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 1).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, Ingredient.of(PokemonMod.RARECANDY.get()), false));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }

    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!(this.level.isClientSide)) {
            if (pPlayer.isHolding((PokemonMod.RARECANDY.get())) && this.isOwnedBy(pPlayer) && !(this.getPokeLevel() > 99)) {
                setPokeLevel(this.getPokeLevel() + 1);
                UpdateBaseStats();
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.AMBIENT, 3.0F, 1.0F);
                setInLove(pPlayer);
                if(!pPlayer.isCreative()){
                    itemstack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
            //this.entityData.set(EVOLVING, true);
        }
        return InteractionResult.FAIL;
    }

    public int getEvolution(){

        int NextSpecies;
        if (this.entityData.get(CURRENT_SPECIES) == BULBASAUR.ordinal()) {
            NextSpecies =  IVYSAUR.ordinal();
            return NextSpecies;
        }

        return this.entityData.get(CURRENT_SPECIES);
    }

    public void aiStep() {
        if(this.getOwner() != null) {
            this.level = this.getOwner().level;
        }

    if(this.level.isClientSide) {

            if (this.getCustomName() != null && this.getCustomName().getString() != null) {
                setNicknamed(true);
            } else {
                setNicknamed(false);
            }
        if ((getShinyness()) == true) {
            for (int i = 0; i < 1; ++i) {
                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }

        if (this.entityData.get(EVOLVING) && getEvolution() != this.entityData.get(CURRENT_SPECIES)) {
            if (this.getEvoTimer() == 1) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
            }
            if (!(this.getEvoTimer() == 50)) {
                setEvoTimer((float) (this.getEvoTimer() + (0.5)));
            } else {
                setEvoTimer(0);
                this.entityData.set(EVOLVING, false);
                this.entityData.set(CURRENT_SPECIES, getEvolution());
                UpdateBaseStats();

            }
        }
    }

        if(this.entityData.get(EVOLVING) && getEvolution() != this.entityData.get(CURRENT_SPECIES)){
            this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(1D), (this.random.nextDouble() - 0.5D) * 4.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 4.0D);
        }

        if(level.isClientSide & isInLove()) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }

        super.aiStep();
    }
}
