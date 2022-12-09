package com.aquatictyphoon.pokemonmod.setup.server;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.entities.PokeballEntity;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import static com.aquatictyphoon.pokemonmod.PokemonMod.POKE_BALL_THROWN;

public class SendPokemonPacket {

    public SendPokemonPacket(){

    }

    public SendPokemonPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context  =supplier.get();
        final  var success = new AtomicBoolean(false);

        context.enqueueWork(() ->{
            // HERE IS SERVER
            ServerPlayer pPlayer = context.getSender();
            ServerLevel pLevel = context.getSender().getLevel();
            pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {

                ItemStack pokeball = PokemonMod.POKEBALL_ITEM.get().getDefaultInstance();
                PokeballEntity projectile = new PokeballEntity(pPlayer, pLevel, pokeball, true);
                projectile.setOwner(pPlayer);
                projectile.setItem(pokeball);
                projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.3F, 1.0F);

                pLevel.addFreshEntity(projectile);


                success.set(true);

            });
        });
        context.setPacketHandled(true);
        return  success.get();
    }
}
