package com.hhc.testmod.blocks;


import com.hhc.testmod.MyDirection;
import com.hhc.testmod.TestModMain;
import com.hhc.testmod.init.ItemInit;
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

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.block.state.properties.DirectionProperty;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.lwjgl.system.CallbackI;


public class RotateBlock extends Block {

    public static final EnumProperty<MyDirection> MYDIRECTION = EnumProperty.create("mydirection", MyDirection.class);



    public RotateBlock(Block.Properties properties){
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MYDIRECTION,MyDirection.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block,BlockState> builder){
        builder.add(MYDIRECTION);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        MyDirection facing=MyDirection.getOpposite(context);
        return this.defaultBlockState().setValue(MYDIRECTION,facing);
    }



    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
        ItemStack held=player.getItemInHand(hand);

        if(!world.isClientSide&&held.getItem()== ItemInit.ICON.get()){
            BlockState newState=state.setValue(MYDIRECTION,state.getValue(MYDIRECTION).rotateClockwise());
            world.setBlock(pos,newState,3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}


