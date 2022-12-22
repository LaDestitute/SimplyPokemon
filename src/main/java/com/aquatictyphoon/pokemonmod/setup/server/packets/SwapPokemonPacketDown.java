package com.aquatictyphoon.pokemonmod.setup.server.packets;

import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class SwapPokemonPacketDown {

    public SwapPokemonPacketDown(){

    }

    public SwapPokemonPacketDown(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handleSwapPokemon(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context  =supplier.get();
        final var success = new AtomicBoolean(false);

        context.enqueueWork(() ->{
            // HERE IS SERVER
            ServerPlayer pPlayer = context.getSender();
            ServerLevel pLevel = context.getSender().getLevel();
            
            pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
                if(partyStorage.currentSlot > 0){
                    partyStorage.currentSlot = partyStorage.currentSlot - 1;
                }
            });
        });
        context.setPacketHandled(true);
        return success.get();
    }
}
