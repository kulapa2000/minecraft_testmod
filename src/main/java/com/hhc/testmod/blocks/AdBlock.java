package com.hhc.testmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;
import java.util.logging.Logger;

public class AdBlock extends Block {

    private static final Logger LOGGER = Logger.getLogger(AdBlock.class.getName());
    public AdBlock(Block.Properties properties){
        super(properties.explosionResistance(0F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        ItemStack held=player.getItemInHand(hand);

        if(!world.isClientSide()&&held.getItem()== Items.GUNPOWDER){
            world.explode(player,pos.getX(),pos.getY(),pos.getZ(),4.0F,true, Explosion.BlockInteraction.DESTROY);
            held.shrink(1);
            LOGGER.info("explosion happened");
            return InteractionResult.CONSUME;
        }
        return super.use(state,world,pos,player,hand,hit);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state){
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand){
        BlockState above=world.getBlockState(pos.above());
        if(above.isAir()){
            world.setBlockAndUpdate(pos.above(), Blocks.CACTUS.defaultBlockState());
        }
    }

    public static final DirectionProperty FACING= HorizontalDirectionalBlock.FACING;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block,BlockState> builder){
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        return this.defaultBlockState().setValue(FACING,context.getHorizontalDirection().getOpposite());
    }
}
