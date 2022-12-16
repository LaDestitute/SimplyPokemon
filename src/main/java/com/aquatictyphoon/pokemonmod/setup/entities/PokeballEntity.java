package com.aquatictyphoon.pokemonmod.setup.entities;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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


public class PokeballEntity extends ThrowableItemProjectile {

    PokemonEntity CurrentMon;
    Boolean isFullBall;
    ServerPlayer pPlayer;

    public PokeballEntity(EntityType<PokeballEntity> type, Level level) {
        super(type, level);
    }
    public PokeballEntity(LivingEntity entity, Level level, ItemStack pStack, Boolean fullBall) {
        super(POKE_BALL.get(), entity, level);
        isFullBall = fullBall;
        pPlayer = (ServerPlayer) this.getOwner();
        if (pPlayer == null) {return;}
        pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
            CurrentMon = partyStorage.getPokemonBySlot(partyStorage.currentSlot);
        });
    }
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!level.isClientSide) {
            if (result.getEntity() instanceof PokemonEntity pTarget ) {
                if (pTarget.getSpecies() >= 0) {return;}

                if(isFullBall && CurrentMon != null){
                    CurrentMon.absMoveTo(result.getLocation().x, result.getLocation().y + 0.5f, result.getLocation().z, 0, 0);
                    level.addFreshEntity(CurrentMon);
                    DiscardBall();
                }else{
                    float maxHP = pTarget.getMaxHealth();
                    float currentHP = pTarget.getHealth();
                    float ballBonus = 1;  // Need to program Status and Ball Bonus
                    float statusBonus = 1;
                    int catchrate = pTarget.getCatchrate();
                    float calculatedCatch = ((((((3 * (maxHP)) - 2 * (currentHP)) * catchrate * ballBonus) / 3 * maxHP) * statusBonus) / 255);
                    if (CurrentMon == null) {
                        if (!pTarget.isTame()) {
                            if ((pTarget.isAlive()) && random.nextInt(256) <= calculatedCatch) {
                                pTarget.tame(pPlayer);
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
    protected void onHitBlock(BlockHitResult result) {
        if (!level.isClientSide) {
            if(CurrentMon != null) {
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