package com.aquatictyphoon.pokemonmod.setup.entities;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import static com.aquatictyphoon.pokemonmod.PokemonMod.*;
import static com.aquatictyphoon.pokemonmod.setup.sounds.ModSoundEvents.POKE_BALL_THROWN;
import static net.minecraft.world.entity.Entity.RemovalReason.CHANGED_DIMENSION;

//Have to code ball types and PC
public class PokeballEntity extends ThrowableItemProjectile {

    ItemStack thrownStack;

    private boolean hasHitGround = false;

    Integer currentSlot = 0;
    PokemonEntity CurrentMon;


    private Boolean speciesIsNull = false;

    private Boolean isFullBall = false;

    public PokeballEntity(EntityType<PokeballEntity> type, Level level) {
        super(type, level);
    }



    public PokeballEntity(LivingEntity entity, Level level, ItemStack pStack, Boolean fullBall) {
        super(POKE_BALL.get(), entity, level);

        Player pPlayer = (Player) this.getOwner();
        if (pPlayer == null) {
            return;
        }

        pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
            CurrentMon = partyStorage.getPokemonBySlot(currentSlot);
        });


        if (!level.isClientSide){

            isFullBall = fullBall;

            if(pStack == null){
                thrownStack = POKEBALL_ITEM.get().getDefaultInstance();
                this.speciesIsNull = true;
            }else {
                CompoundTag dataCheck = new CompoundTag();
                String getSpecies = "null";
                if (CurrentMon != null) {
                    getSpecies = String.valueOf(CurrentMon.getSpecies());
                    dataCheck.putString("CURRENT_SPECIES", getSpecies);
                }
                pStack.setTag(dataCheck);
                thrownStack = pStack;

                if (getSpecies == null) {
                    this.speciesIsNull = true;
                } else {
                    this.speciesIsNull = false;
                }
            }
        }
    }
    void DiscardBall(){
        if (!level.isClientSide) {
            this.setRemoved(RemovalReason.DISCARDED);
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!level.isClientSide) {
            if(pResult.getEntity() instanceof PokemonEntity) {
                PokemonEntity pTarget = (PokemonEntity) pResult.getEntity();

                Player pPlayer = (Player) this.getOwner();
                if (pPlayer == null) {
                    return;
                }
                if (!this.speciesIsNull) {
                    float maxHP = pTarget.getMaxHealth();
                    float currentHP = pTarget.getHealth();
                    float ballBonus = 1;
                    float statusBonus = 1;
                    int catchrate = pTarget.getCatchrate();
                    float calculatedCatch = ((((((3 * (maxHP)) - 2 * (currentHP)) * catchrate * ballBonus) / 3 * maxHP) * statusBonus) / 255);
                    if (CurrentMon == null) {
                        if (!pTarget.isTame()) {
                            if ((pTarget.isAlive()) && random.nextInt(256) <= calculatedCatch) {

                                pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
                                    partyStorage.addPokemon(pTarget);
                                });



                                pPlayer.displayClientMessage(Component.translatable(pPlayer.getGameProfile().getName() + " caught the wild " + pTarget.getPokeName()), false);
                                pTarget.remove(CHANGED_DIMENSION);
                            }
                            if ((pTarget.isAlive()) && random.nextInt(256) > calculatedCatch) {
                                pPlayer.displayClientMessage(Component.translatable("Oh no! The " + pTarget.getPokeName() + " broke free!"), true);
                            }
                        }
                    }
                }
            }
            DiscardBall();
        }
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
            if (CurrentMon == null) {
                playSound(POKE_BALL_THROWN, 1, 1);
            } else if (CurrentMon.isRemoved()) {
                playSound(POKE_BALL_THROWN, 1, 1);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide){
            if (CurrentMon != null) {
                if (!this.hasHitGround && !CurrentMon.isRemoved()) {
                    CurrentMon.remove(CHANGED_DIMENSION);
                    DiscardBall();
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (!level.isClientSide) {
            this.hasHitGround = true;
            Player player = (Player) this.getOwner();
            if (this.speciesIsNull != null && !level.isClientSide && this.isFullBall) {
                if (player == null || CurrentMon == null) {
                    this.setRemoved(RemovalReason.DISCARDED);
                    return;
                }

                if (!CurrentMon.isRemoved()) {
                    //Debug Code if ball somehow not removed
                    CurrentMon.remove(CHANGED_DIMENSION);
                    DiscardBall();
                } else if (!CurrentMon.isDeadOrDying()) {
                    CurrentMon.revive();
                    BlockPos blockPos = this.blockPosition();
                    CurrentMon.absMoveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0);
                    level.addFreshEntity(CurrentMon);
                    DiscardBall();
                } else {
                    player.displayClientMessage(Component.translatable(CurrentMon.getPokeName() + " is Fainted"), true);
                }
            } else {
                DiscardBall();
            }
        }
    }

    protected @NotNull Item getDefaultItem() {
        return (POKEBALL_ITEM.get());
    }

}

