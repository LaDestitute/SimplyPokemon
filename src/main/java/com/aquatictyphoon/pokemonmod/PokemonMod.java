package com.aquatictyphoon.pokemonmod;

import com.aquatictyphoon.pokemonmod.setup.client.ClientEventBusSubscriber;
import com.aquatictyphoon.pokemonmod.setup.entities.PokeballEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.items.RareCandyItem;
import com.aquatictyphoon.pokemonmod.setup.pokeballs.PokeBallItem;
import com.aquatictyphoon.pokemonmod.setup.server.ModMessages;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PokemonMod.MODID)
public class PokemonMod
{
    public static final String MODID = "pokemonmod";

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PokemonMod.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<Item> POKEBALL_ITEM = ITEMS.register("pokeball",
            () -> new PokeBallItem(new Item.Properties()));

    //Registry goes here
    public static final RegistryObject<Item> RARECANDY = ITEMS.register("rarecandy",
            () -> new RareCandyItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<EntityType<PokemonEntity>> POKEMON = ENTITIES.register("pokemon",
            () -> EntityType.Builder.<PokemonEntity>of(PokemonEntity::new, MobCategory.CREATURE).sized(0.5F, 0.8F).build("pokemon"));


    public static final RegistryObject<EntityType<PokeballEntity>> POKE_BALL = ENTITIES.register("poke_ball",
            () -> EntityType.Builder.<PokeballEntity>of(PokeballEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("poke_ball"));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(POKEMON.get(), PokemonEntity.createLivingAttributes().build());
    }

    //public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

   // public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));


    public PokemonMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEventBusSubscriber::initRenders);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        //LOGGER.info("HELLO from server starting");
    }
}
