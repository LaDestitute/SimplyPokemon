package com.aquatictyphoon.pokemonmod.setup.entities.registration;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.entities.Pokeball_Entity;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, PokemonMod.MOD_ID);

    public static final RegistryObject<EntityType<Pokeball_Entity>> POKE_BALL = ENTITIES.register("poke_ball",
            () -> EntityType.Builder.<Pokeball_Entity>of(Pokeball_Entity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("poke_ball"));

    public static final RegistryObject<EntityType<PokemonEntity>> POKEMON = ENTITIES.register("pokemon",
            () -> EntityType.Builder.<PokemonEntity>of(PokemonEntity::new, MobCategory.CREATURE)
                    .sized(1F, 1F).build("pokemon"));


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(POKEMON.get(), PokemonEntity.customAttributes().build());
    }



}
