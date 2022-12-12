package com.aquatictyphoon.pokemonmod.setup.pokeballs;

import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
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
        tag.put("playerParty", listTag);
    }

    public void loadNBTData(CompoundTag compoundTag){
        ListTag listTag = (ListTag) compoundTag.get("playerParty");
        for(int i = 1; i <= listTag.stream().count(); i++){
            CompoundTag tag = (CompoundTag) listTag.get(i - 1);
            //PokemonEntity test = (PokemonEntity) ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(tag.getString("entity"))).create(level);
            //System.out.println(test);
        }
    }
}
