package com.aquatictyphoon.pokemonmod.setup.pokeballs;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PartyPokeballProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PartyStorage> PLAYER_PARTY = CapabilityManager.get(new CapabilityToken<>() {

    });
    private PartyStorage party = null;
    private final LazyOptional<PartyStorage> optional = LazyOptional.of(this::createPlayerParty);


    private PartyStorage createPlayerParty() {
        if (party == null){
            this.party = new PartyStorage();
        }
        return this.party;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap ==PLAYER_PARTY) {
            return optional.cast();
        }
        return null;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerParty().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerParty().loadNBTData(nbt);
    }
}
