package com.aquatictyphoon.pokemonmod.setup.server;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.client.KeyBinds;
import com.aquatictyphoon.pokemonmod.setup.commands.PokeSummonCommand;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyStorage;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerNegotiationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = PokemonMod.MODID)

public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        new PokeSummonCommand(event.getDispatcher());
        ConfigCommand.register((event.getDispatcher()));
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PartyPokeballProvider.PLAYER_PARTY).isPresent()) {
                event.addCapability(new ResourceLocation(PokemonMod.MODID, "properties"), new PartyPokeballProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PartyStorage.class);
    }

    @Mod.EventBusSubscriber(modid = PokemonMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinds.POKEBALL_KEY.consumeClick()) {
                ModMessages.setPacketToServer(new SendPokemonPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = PokemonMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinds.POKEBALL_KEY);
        }
    }
}
