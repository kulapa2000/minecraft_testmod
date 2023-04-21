package com.hhc.testmod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class Teleport extends Item {
    public Teleport(Properties properties)
    {
        super(properties.durability(10));
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        BlockHitResult ray = rayTrace(world, player, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
        player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
        player.getCooldowns().addCooldown(this,60);
        player.fallDistance=0F;
        world.playSound(player,player.getX(),player.getY(),player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS,1.0F,1.0F);

        ItemStack stack =player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue()+1);
        if(stack.getDamageValue()>=stack.getMaxDamage()){stack.setCount(0);}
        return super.use(world,player,hand);
    }
    protected static BlockHitResult rayTrace(Level world, Player player, ClipContext.Fluid fluidMode) {
        double range=15;

        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;

        Vec3 vec31 = vec3.add((double)f6 * range, (double)f5 * range   , (double)f7 * range);
        return world.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluidMode, player));
    }

    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag FlagIn)
    {
        tooltip.add(new TextComponent("teleport tool"));
        super.appendHoverText(stack,world,tooltip,FlagIn);
    }


}
