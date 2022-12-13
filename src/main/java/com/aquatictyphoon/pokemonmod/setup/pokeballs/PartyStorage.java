package com.aquatictyphoon.pokemonmod.setup.pokeballs;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
public class PartyStorage {
    public ArrayList<PokemonEntity> playerParty = new ArrayList<>();
    public final int partySize = 6;

    public void addPokemon(PokemonEntity storedPokemon){
        if(playerParty.size() < partySize) {
            playerParty.add(storedPokemon);
        }else{
            System.out.println("ERROR NO PC STORAGE PROGRAMMED");
        }
    }
    public PokemonEntity getPokemonBySlot(int currentSlot){
        if(playerParty.isEmpty()){
            return null;
        }else{
            return playerParty.get(currentSlot);
        }
    }

    public void saveNBTData(CompoundTag tag){
        ListTag listTag = new ListTag();

        for(PokemonEntity pok : playerParty){
            listTag.add(pok.serializeNBT());
        }
        if(listTag != null) {
            tag.put("playerParty", listTag);
        }
    }



    public void loadNBTData(CompoundTag compoundTag){
        ServerLevel serverLevel  = ServerLifecycleHooks.getCurrentServer().getLevel(ServerLevel.OVERWORLD);
        ListTag listTag = (ListTag) compoundTag.get("playerParty");
        if(serverLevel != null) {
            ServerLevel overWorldLevel = serverLevel.getLevel();
            for (int i = 1; i <= listTag.stream().count(); i++) {
                CompoundTag pokemonCompoundTags = (CompoundTag) listTag.get(i - 1);
                PokemonEntity pokemon = (PokemonEntity) ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(pokemonCompoundTags.getString("id"))).create(overWorldLevel);
                pokemon.deserializeNBT(pokemonCompoundTags);
                playerParty.add(pokemon);
            }
        }
    }

    public void copyFrom(PartyStorage source) {
        this.playerParty = source.playerParty;
    }
}
