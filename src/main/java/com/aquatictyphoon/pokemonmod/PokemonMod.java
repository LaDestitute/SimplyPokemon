package com.aquatictyphoon.pokemonmod;

import com.aquatictyphoon.pokemonmod.setup.ModSetup;
import com.aquatictyphoon.pokemonmod.setup.ClientSetup;
import com.aquatictyphoon.pokemonmod.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")

@Mod(PokemonMod.MOD_ID)
public class PokemonMod {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_NAME = "Simply Pokemon";
    public static final java.lang.String MOD_ID = "pokemonmod";


    public PokemonMod() {
        //Register the deferred registry
        Registration.init();

        //Register the setup method for mod loading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::initRenders);

    }
}
