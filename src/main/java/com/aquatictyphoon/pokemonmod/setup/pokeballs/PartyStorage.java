package com.aquatictyphoon.pokemonmod.setup.pokeballs;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class PartyStorage {
    public static PokemonEntity slot1;
    public static PokemonEntity slot2;
    public static PokemonEntity slot3;
    public static PokemonEntity slot4;
    public static PokemonEntity slot5;
    public static PokemonEntity slot6;

    PokemonEntity[] playerParty = new PokemonEntity[]{slot1, slot2, slot3, slot4, slot5, slot6};
    String playerPartyAsString = playerParty.toString();

    public void saveNBTData(CompoundTag nbt){
        nbt.putString("Party", playerPartyAsString);
    }

    public void loadNBTData(CompoundTag nbt){
        playerPartyAsString = nbt.getString("Party");
    }

    public static void replacePokemon(PokemonEntity storedPokemon, int incomingSlot){
        int currentSlot = Mth.clamp(incomingSlot, 1, 6);

        if(currentSlot >= 1)
            slot1 = storedPokemon;
        if(currentSlot == 2)
            slot2 = storedPokemon;
        if(currentSlot == 3)
            slot3 = storedPokemon;
        if(currentSlot == 4)
            slot4 = storedPokemon;
        if(currentSlot == 5)
            slot5 = storedPokemon;
        if(currentSlot == 6)
            slot6 = storedPokemon;

        storedPokemon.setPartySlot(currentSlot);
    }

    public static void addNewPokemon(PokemonEntity storedPokemon, int incomingSlot){
        int currentSlot = Mth.clamp(incomingSlot, 1, 7);
        while (checkStoredPokemonBySlot(currentSlot) != null){
            currentSlot++;
        }
        if(currentSlot == 1)
            slot1 = storedPokemon;
        if(currentSlot == 2)
            slot2 = storedPokemon;
        if(currentSlot == 3)
            slot3 = storedPokemon;
        if(currentSlot == 4)
            slot4 = storedPokemon;
        if(currentSlot == 5)
            slot5 = storedPokemon;
        if(currentSlot == 6)
            slot6 = storedPokemon;
        storedPokemon.setPartySlot(currentSlot);
        //HANDLE PC
        if(currentSlot ==7){
            System.out.println("ERROR NO PC STORAGE PROGRAMMED");
        }

    }

    public static PokemonEntity getPokemonBySlot(int currentSlot) {
        //Could have use a switch case here lol
        if (currentSlot == 1)
           return slot1;
        if (currentSlot == 2)
            return slot2;
        if (currentSlot == 3)
            return slot3;
        if (currentSlot == 4)
            return slot4;
        if (currentSlot == 5)
            return slot5;
        if (currentSlot == 6)
            return slot6;
        return slot1;
    }

    public  static PokemonEntity checkStoredPokemonBySlot(int currentSlot){
        if(currentSlot == 1)
            return slot1;
        if(currentSlot == 2)
            return slot2;
        if(currentSlot == 1)
            return slot2;
        if(currentSlot == 1)
            return slot2;
        if(currentSlot == 3)
            return slot3;
        if(currentSlot == 3)
            return slot3;
        if(currentSlot == 4)
            return slot4;
        if(currentSlot == 5)
            return slot5;
        if(currentSlot == 6)
            return slot6;
        return null;
    }
}
