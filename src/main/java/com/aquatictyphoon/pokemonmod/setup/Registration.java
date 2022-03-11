package com.aquatictyphoon.pokemonmod.setup;


import com.aquatictyphoon.pokemonmod.setup.advanced.items.PokeballItem;
import com.aquatictyphoon.pokemonmod.setup.advanced.items.RareCandyItem;
import com.aquatictyphoon.pokemonmod.setup.entities.Pokeball_Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
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
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);


    public static  void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITY_TYPES.register(bus);
    }

    //Projectiles
    public static final RegistryObject<EntityType<Pokeball_Entity>> POKE_BALL = ENTITY_TYPES.register("pokeball",
            () -> EntityType.Builder.<Pokeball_Entity>of(Pokeball_Entity::new,
                    MobCategory.MISC).sized(0.25F, 0.25F).build("pokeball"));




    //This is where all is registered
    public static final RegistryObject<Item> Pokeball = ITEMS.register("pokeball",
            () -> new PokeballItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

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
            () -> new RareCandyItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().alwaysEat().build())));

    public static final RegistryObject<Item> Apricorn = ITEMS.register("apricorn",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Oranberry = ITEMS.register("oranberry",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(5).saturationMod(4.5f).alwaysEat().build())));

    public static final RegistryObject<Item> Medicinalleek = ITEMS.register("medicinalleek",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Vivichoke = ITEMS.register("vivichoke",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Tumblestone = ITEMS.register("tumblestone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
