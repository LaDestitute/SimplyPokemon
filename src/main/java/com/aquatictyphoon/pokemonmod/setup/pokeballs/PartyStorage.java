package com.aquatictyphoon.pokemonmod.setup.pokeballs;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;

public class PartyStorage {
    public ArrayList<PokemonEntity> playerParty = new ArrayList<>();
    public final int partySize = 6;
    String playerPartyAsString = playerParty.toString();
    public void addPokemon(PokemonEntity storedPokemon){
        if(playerParty.size() > partySize) {
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

    public void saveNBTData(CompoundTag nbt){
        nbt.putString("Party", playerPartyAsString);
    }

    public void loadNBTData(CompoundTag nbt){
        playerPartyAsString = nbt.getString("Party");
    }
}
