package com.aquatictyphoon.pokemonmod.setup.server;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.entities.PokeballEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context  =supplier.get();
        final var success = new AtomicBoolean(false);

        context.enqueueWork(() ->{
            // HERE IS SERVER
            ServerPlayer pPlayer = context.getSender();
            ServerLevel pLevel = context.getSender().getLevel();
            pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
                PokemonEntity CurrentMon = partyStorage.getPokemonBySlot(partyStorage.currentSlot);
                if (!CurrentMon.isDeadOrDying()) {
                    if (!CurrentMon.isRemoved() && !pPlayer.hasDisconnected()){
                        CurrentMon.remove(CHANGED_DIMENSION);
                    }else {
                        CurrentMon.revive();
                        ItemStack pokeball = PokemonMod.POKEBALL_ITEM.get().getDefaultInstance();
                        PokeballEntity projectile = new PokeballEntity(pPlayer, pLevel, pokeball, true);
                        projectile.setOwner(pPlayer);
                        projectile.setItem(pokeball);
                        projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.3F, 1.0F);
                        pLevel.addFreshEntity(projectile);
                    }
                }else{
                    pPlayer.displayClientMessage(Component.translatable(CurrentMon.getPokeName() + " is Fainted"), true);
                }
                success.set(true);

            });
        });
        context.setPacketHandled(true);
        return success.get();
    }
}
