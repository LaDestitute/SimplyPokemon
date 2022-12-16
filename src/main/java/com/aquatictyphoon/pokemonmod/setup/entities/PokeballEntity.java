package com.aquatictyphoon.pokemonmod.setup.entities;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.aquatictyphoon.pokemonmod.PokemonMod.*;
import static com.aquatictyphoon.pokemonmod.setup.sounds.ModSoundEvents.POKE_BALL_THROWN;


public class PokeballEntity extends ThrowableItemProjectile {

    PokemonEntity CurrentMon;
    Boolean isFullBall = false;
    Entity pPlayer;
    PokemonEntity TargetCapture;
    boolean didCatchMon = false;
    boolean ballShaking = false;
    int catchTimer;
    int onceShakeTime = 35;

    int ballShakeCount = 0;

    public PokeballEntity(EntityType<PokeballEntity> type, Level level) {
        super(type, level);
    }
    public PokeballEntity(LivingEntity entity, Level level, ItemStack pStack, Boolean fullBall) {
        super(POKE_BALL.get(), entity, level);
        isFullBall = fullBall;
        pPlayer = this.getOwner();
        if (pPlayer == null) {return;}
        pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
            CurrentMon = partyStorage.getPokemonBySlot(partyStorage.currentSlot);
        });
        catchTimer = 0;
    }
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!level.isClientSide && !ballShaking) {
            if (result.getEntity() instanceof PokemonEntity pTarget ) {
                if (pTarget.getSpecies() == 0) {return;}

                if(!isFullBall){
                    ballShaking = true;
                    TargetCapture = pTarget;
                    float maxHP = pTarget.getMaxHealth();
                    float currentHP = pTarget.getHealth();
                    float ballBonus = 1;  // Need to program Status and Ball Bonus
                    float statusBonus = 1;
                    int catchrate = pTarget.getCatchrate();
                    float calculatedCatch = ((((((3 * (maxHP)) - 2 * (currentHP)) * catchrate * ballBonus) / 3 * maxHP) * statusBonus) / 255);
                        if (!pTarget.isTame()) {
                            if ((pTarget.isAlive()) && random.nextInt(256) <= calculatedCatch) {
                                didCatchMon = true;
                            }
                            if ((pTarget.isAlive()) && random.nextInt(256) > calculatedCatch) {
                                didCatchMon = true;
                            }
                        }
                }else{
                    CurrentMon.absMoveTo(result.getLocation().x, result.getLocation().y + 0.5f, result.getLocation().z, 0, 0);
                    level.addFreshEntity(CurrentMon);
                    DiscardBall();
                }
            }
        }
    }



    @Override
    public void tick() {
        super.tick();
        if(ballShaking && !this.isNoGravity()) {
            ballShakeCount = random.nextInt(2);
        }

        if(ballShaking && catchTimer < onceShakeTime  + (ballShakeCount * onceShakeTime)){
            Random shakeCounter = new Random();
            catchTimer +=1;

            this.setNoGravity(true);
            this.setDeltaMovement(0, 0, 0);

            if(TargetCapture != null) {
                this.setPos(new Vec3(TargetCapture.position().x, TargetCapture.position().y +   0.035f, TargetCapture.position().z));
                TargetCapture.setNoGravity(true);
                TargetCapture.setDeltaMovement(0, 0, 0);
                TargetCapture.setNoAi(true);
                TargetCapture.setInvisible(true);
            }
        }

        if(catchTimer >= (onceShakeTime  + (ballShakeCount * onceShakeTime)) && TargetCapture != null && ballShaking){
            if(didCatchMon) {
                doCapture();
            }else{
                doFailCapture();
            }
            TargetCapture.setInvisible(false);
            TargetCapture.setNoAi(false);
            TargetCapture.setNoGravity(false);
        }
    }

    void doCapture(){
        Player player = (Player) pPlayer;
        if(TargetCapture != null){
            TargetCapture.tame(player);
            player.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
                partyStorage.addPokemon(TargetCapture);
                if (partyStorage.playerParty.size() > 5) {
                    player.displayClientMessage(Component.translatable(player.getGameProfile().getName() + " caught the wild " + TargetCapture.getPokeName() + "!"), false);
                    player.displayClientMessage(Component.translatable(TargetCapture.getPokeName() + " was sent to the PC!"), false);
                } else {
                    player.displayClientMessage(Component.translatable(player.getGameProfile().getName() + " caught the wild " + TargetCapture.getPokeName() + "!"), false);
                }
            });
            TargetCapture.remove(RemovalReason.CHANGED_DIMENSION);
        }
        ballShaking = false;
        DiscardBall();
    }

    void doFailCapture(){
        Player player = (Player) pPlayer;
        player.displayClientMessage(Component.translatable("Oh no! The " + TargetCapture.getPokeName() + " broke free!"), true);
        didCatchMon = false;
        ballShaking = false;
        DiscardBall();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
            if (!level.isClientSide) {
                if (CurrentMon != null && isFullBall) {
                    CurrentMon.revive();
                    CurrentMon.absMoveTo(result.getLocation().x, result.getLocation().y + 0.5f, result.getLocation().z, 0, 0);
                    level.addFreshEntity(CurrentMon);
                }
            }
            DiscardBall();
    }

    void DiscardBall(){
        if (!level.isClientSide) {
            this.setRemoved(RemovalReason.DISCARDED);
        }
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        playSound(POKE_BALL_THROWN, 1, 1);
    }

    protected @NotNull Item getDefaultItem() {
        return (POKEBALL_ITEM.get());
    }
}