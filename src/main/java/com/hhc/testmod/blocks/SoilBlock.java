package com.hhc.testmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;


import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;



import java.util.Random;
import java.util.logging.Logger;


public class SoilBlock extends Block{

    private static final Logger LOGGER = Logger.getLogger(SoilBlock.class.getName());
    public SoilBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state){
        return true;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable0)
    {
        return true;
    }


    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
        BlockState aboveState = world.getBlockState(pos.above());
        if (aboveState.getBlock() instanceof CropBlock)
        {
            LOGGER.info("above is crop");
            CropBlock crop = (CropBlock) aboveState.getBlock();
            int age = aboveState.getValue(crop.getAgeProperty());
            if (world.isRainingAt(pos.above(2)))
            {
                LOGGER.info("raining and should mature");
                world.setBlockAndUpdate(pos.above(), aboveState.setValue(crop.getAgeProperty(), crop.getMaxAge()));
            } else if (age < crop.getMaxAge()) {
                world.setBlockAndUpdate(pos.above(), aboveState.setValue(crop.getAgeProperty(), age + 1));
            }
        }
    }
}









