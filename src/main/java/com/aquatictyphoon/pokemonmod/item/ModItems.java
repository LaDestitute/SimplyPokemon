package com.aquatictyphoon.pokemonmod.item;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PokemonMod.MOD_ID);

    public static final RegistryObject<Item> Pokeball = ITEMS.register("pokeball",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Greatball = ITEMS.register("greatball",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Ultraball = ITEMS.register("ultraball",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Masterball = ITEMS.register("masterball",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Potion = ITEMS.register("potion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Superpotion = ITEMS.register("superpotion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Hyperpotion = ITEMS.register("hyperpotion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Maxpotion = ITEMS.register("maxpotion",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Fullrestore = ITEMS.register("fullrestore",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Revive = ITEMS.register("revive",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Maxrevive = ITEMS.register("maxrevive",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Antidote = ITEMS.register("antidote",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Burnheal = ITEMS.register("burnheal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Iceheal = ITEMS.register("iceheal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Awakening = ITEMS.register("awakening",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Paralyzeheal = ITEMS.register("paralyzeheal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Rarecandy = ITEMS.register("rarecandy",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Apricorn = ITEMS.register("apricorn",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Oranberry = ITEMS.register("oranberry",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(5).saturationMod(4.5f).alwaysEat().build())));

    public static final RegistryObject<Item> Medicinalleek = ITEMS.register("medicinalleek",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Vivichoke = ITEMS.register("vivichoke",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
