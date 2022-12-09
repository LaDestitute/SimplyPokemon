package com.aquatictyphoon.pokemonmod.setup.server;

import com.aquatictyphoon.pokemonmod.PokemonMod;
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
                .consumerMainThread(SendPokemonPacket::handle).add();
    }

    public static <MSG> void  setPacketToServer(MSG packet){
        INSTANCE.sendToServer(packet);
    }

    public static <MSG> void  setPacketToPlayer(MSG packet){
        INSTANCE.sendToServer(packet);
    }
}
