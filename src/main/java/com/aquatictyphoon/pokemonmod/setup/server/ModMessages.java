package com.aquatictyphoon.pokemonmod.setup.server;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.server.packets.SendPokemonPacket;
import com.aquatictyphoon.pokemonmod.setup.server.packets.SwapPokemonPacketDown;
import com.aquatictyphoon.pokemonmod.setup.server.packets.SwapPokemonPacketUp;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id()
    {
        return packetId ++;
    }

    public static void register(){
        SimpleChannel network = NetworkRegistry.ChannelBuilder.
                named(new ResourceLocation(PokemonMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = network;

        network.messageBuilder(SendPokemonPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendPokemonPacket::new)
                .encoder(SendPokemonPacket::toBytes)
                .consumerMainThread(SendPokemonPacket::handleSendPokemon).add();

        network.messageBuilder(SwapPokemonPacketUp.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SwapPokemonPacketUp::new)
                .encoder(SwapPokemonPacketUp::toBytes)
                .consumerMainThread(SwapPokemonPacketUp::handleSwapPokemon).add();

        network.messageBuilder(SwapPokemonPacketDown.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SwapPokemonPacketDown::new)
                .encoder(SwapPokemonPacketDown::toBytes)
                .consumerMainThread(SwapPokemonPacketDown::handleSwapPokemon).add();
    }

    public static <MSG> void  setPacketToServer(MSG packet){
        INSTANCE.sendToServer(packet);
    }

    public static <MSG> void  setPacketToPlayer(MSG packet){
        INSTANCE.sendToServer(packet);
    }
}
