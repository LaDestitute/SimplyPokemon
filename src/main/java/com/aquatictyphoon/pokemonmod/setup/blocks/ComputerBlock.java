package com.aquatictyphoon.pokemonmod.setup.blocks;

import com.aquatictyphoon.pokemonmod.setup.pokeballs.PartyPokeballProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ComputerBlock extends Block {
    public ComputerBlock(Properties pProperties) {
        super(pProperties);

    }

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(CLICKED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if(!pLevel.isClientSide){
            pPlayer.getCapability(PartyPokeballProvider.PLAYER_PARTY).ifPresent(partyStorage -> {
                if(partyStorage.playerParty.get(0).getSpecies() == 0){
                    // There party is Empty so we return to avoid a crash
                    return;
                }
                System.out.println(partyStorage.currentSlot);
            });
        }
        return  InteractionResult.SUCCESS;
    }
}
