package com.hhc.testmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class Teleport extends Item {
    public Teleport(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos();
        player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
        return super.use(world,player,hand);
    }

}
