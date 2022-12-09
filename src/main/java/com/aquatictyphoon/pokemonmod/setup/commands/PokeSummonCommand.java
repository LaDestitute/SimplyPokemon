package com.aquatictyphoon.pokemonmod.setup.commands;

import com.aquatictyphoon.pokemonmod.PokemonMod;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonEntity;
import com.aquatictyphoon.pokemonmod.setup.entities.pokemon.PokemonStats;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.server.command.EnumArgument;

import static net.minecraft.world.entity.MobSpawnType.COMMAND;

public class PokeSummonCommand {

    String GetNamesOption(CommandContext commandContext){
        String[] split = commandContext.getInput().split(" ");
        return split[1];
    }
    String GetShinyness(CommandContext commandContext){
        String[] split = commandContext.getInput().split(" ");
        return split[2];
    }

    public PokeSummonCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("pokesummon").then
                (Commands.argument(EnumArgument.enumArgument(PokemonStats.Species.class).toString(), EnumArgument.enumArgument(PokemonStats.Species.class)
                ).executes((commandContext) -> {
            return execute(commandContext, GetNamesOption(commandContext), "False");
        }).then (Commands.argument(EnumArgument.enumArgument(PokemonStats.ShinyOptions.class).toString(), EnumArgument.enumArgument(PokemonStats.ShinyOptions.class)).executes((commandContext) -> {
            return execute(commandContext, GetNamesOption(commandContext), GetShinyness(commandContext));
         }))));

    }
    private static int execute(CommandContext<CommandSourceStack> command, String speciesName, String Shinyness){
        ServerPlayer player = (ServerPlayer) command.getSource().getEntity();
        if(command.getSource().getEntity() instanceof Player && !player.level.isClientSide){


            ServerLevel serverLevel = (ServerLevel) player.level;
            PokemonEntity entity = new PokemonEntity(PokemonMod.POKEMON.get(), player.level);
            EntityType entityType = entity.getType();
            PokemonEntity pokemonEntity = (PokemonEntity) entityType.spawn(serverLevel, null, null, player.blockPosition(), COMMAND, false, false);

            if(Shinyness.equals("SHINY")){
                pokemonEntity.setShinnyness(true);
            }

            pokemonEntity.setSpecies(PokemonStats.Species.valueOf(speciesName).ordinal());
            pokemonEntity.UpdateBaseStats();

        }

        return Command.SINGLE_SUCCESS;
    }

}
