package com.aquatictyphoon.pokemonmod.setup.entities.registration;


import com.aquatictyphoon.pokemonmod.setup.advanced.items.PokeScannerItem;
import com.aquatictyphoon.pokemonmod.setup.advanced.items.projectiles.GreatBallItem;
import com.aquatictyphoon.pokemonmod.setup.advanced.items.projectiles.MasterBallItem;
import com.aquatictyphoon.pokemonmod.setup.advanced.items.projectiles.PokeBallItem;
import com.aquatictyphoon.pokemonmod.setup.advanced.items.RareCandyItem;
import com.aquatictyphoon.pokemonmod.setup.advanced.items.projectiles.UltraBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import  static com.aquatictyphoon.pokemonmod.PokemonMod.MOD_ID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);


    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }



    //This is where all is registered
    public static final RegistryObject<Item> POKEBALL = ITEMS.register("pokeball",
            () -> new PokeBallItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> GREATBALL = ITEMS.register("greatball",
            () -> new GreatBallItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ULTRABALL = ITEMS.register("ultraball",
            () -> new UltraBallItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> MASTERBALL = ITEMS.register("masterball",
            () -> new MasterBallItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> POTION = ITEMS.register("potion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SUPERPOTION = ITEMS.register("superpotion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HYPERPOTION = ITEMS.register("hyperpotion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> MAXPOTION = ITEMS.register("maxpotion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> FULLRESTORE = ITEMS.register("fullrestore",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> REVIVE = ITEMS.register("revive",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> MAXREVIVE = ITEMS.register("maxrevive",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ANTIDOTE = ITEMS.register("antidote",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BURNHEAL = ITEMS.register("burnheal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ICEHEAL = ITEMS.register("iceheal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> AWAKENING = ITEMS.register("awakening",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> PARALYZEHEAL = ITEMS.register("paralyzeheal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> RARECANDY = ITEMS.register("rarecandy",
            () -> new RareCandyItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().alwaysEat().build())));

    public static final RegistryObject<Item> APRICORN = ITEMS.register("apricorn",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ORANBERRY = ITEMS.register("oranberry",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(5).saturationMod(4.5f).alwaysEat().build())));

    public static final RegistryObject<Item> MEDICINALLEEK = ITEMS.register("medicinalleek",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> VIVICHOKE = ITEMS.register("vivichoke",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TUMBLESTONE = ITEMS.register("tumblestone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<PokeScannerItem> POKESCANNER = ITEMS.register("pokescanner",
            () -> new PokeScannerItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> WATERSTONE = ITEMS.register("waterstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> THUNDERSTONE = ITEMS.register("thunderstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> LEAFSTONE = ITEMS.register("leafstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> MOONSTONE = ITEMS.register("moonstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SUNSTONE = ITEMS.register("sunstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SHINYSTONE = ITEMS.register("shinystone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> DAWNSTONE = ITEMS.register("dawnstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> DUSKSTONE = ITEMS.register("duskstone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ICESTONE = ITEMS.register("icestone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}

