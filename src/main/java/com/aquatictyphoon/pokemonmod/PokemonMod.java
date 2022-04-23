package com.aquatictyphoon.pokemonmod;

import com.aquatictyphoon.pokemonmod.setup.client.ClientEventBusSubscriber;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.Registration;
import com.aquatictyphoon.pokemonmod.setup.entities.registration.EntityTypeInit;
import com.aquatictyphoon.pokemonmod.util.CommonEventBusSubscriber;
import net.minecraftforge.eventbus.api.IEventBus;
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
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityTypeInit.ENTITIES.register(modEventBus);

        //Register the setup method for mod loading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(CommonEventBusSubscriber::onStaticCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEventBusSubscriber::initRenders);

    }
}
