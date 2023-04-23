package com.hhc.testmod.blocks;

import com.hhc.testmod.TestModMain;
import com.hhc.testmod.init.ItemInit;
import com.ibm.icu.lang.UCharacter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class RotateBlock extends Block {

    public static final DirectionProperty FACING= HorizontalDirectionalBlock.FACING;
    public RotateBlock(Block.Properties properties){
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING,Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block,BlockState> builder){
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){

        return this.defaultBlockState().setValue(FACING,context.getHorizontalDirection().getOpposite());

    }



    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
        ItemStack held=player.getItemInHand(hand);

        if(!world.isClientSide&&held.getItem()== ItemInit.ICON.get()){
            BlockState newState=state.setValue(FACING,state.getValue(FACING).getClockWise());
            world.setBlock(pos,newState,3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}


