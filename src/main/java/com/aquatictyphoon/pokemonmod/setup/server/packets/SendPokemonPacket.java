package com.aquatictyphoon.pokemonmod.setup.server.packets;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.entities.PokeballEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import static net.minecraft.world.entity.Entity.RemovalReason.CHANGED_DIMENSION;

public class SendPokemonPacket {

    public SendPokemonPacket(){

    }

    public SendPokemonPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handleSendPokemon(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context  =supplier.get();
        final var success = new AtomicBoolean(false);

        context.enqueueWork(() ->{
            // HERE IS SERVER
            ServerPlayer pPlayer = context.getSender();
            ServerLevel pLevel = context.getSender().getLevel();
            ItemStack pokeball = PokemonMod.POKEBALL_ITEM.get().getDefaultInstance();
            PokeballEntity projectile = new PokeballEntity(pPlayer, pLevel, pokeball, true);
            
            pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
                PokemonEntity currentMon = partyStorage.getPokemonBySlot(partyStorage.currentSlot);
                if (!currentMon.isDeadOrDying()) {
                    PokemonEntity pokemonInWorld = null;
                    for(Entity entity : pLevel.getAllEntities()){
                        if (entity instanceof PokemonEntity levelPokemon) {
                            if (levelPokemon.getUUID().equals(currentMon.getUUID()) && !levelPokemon.isRemoved()) {
                                levelPokemon.remove(CHANGED_DIMENSION);
                                currentMon = levelPokemon;
                                pokemonInWorld = levelPokemon;
                                success.set(true);
                                break;
                            }
                        }
                    }

                    if (pokemonInWorld == null) {
                        currentMon.revive();
                        projectile.setOwner(pPlayer);
                        projectile.setItem(pokeball);
                        projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.3F, 1.0F);
                        pLevel.addFreshEntity(projectile);
                        success.set(true);
                    }
                }else{
                    pPlayer.displayClientMessage(Component.translatable(currentMon.getPokeName() + " is Fainted"), true);
                }
            });
        });
        context.setPacketHandled(true);
        return success.get();
    }
}
