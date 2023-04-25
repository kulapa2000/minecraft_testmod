package com.hhc.testmod.blockEntity.custom;

import com.hhc.testmod.blockEntity.ModBlockEntities;

import com.hhc.testmod.blocks.SoilBlock;
import net.minecraft.core.BlockPos;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import net.minecraftforge.common.util.LazyOptional;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

public class GemCuttingStationBlockEntity  extends BlockEntity  {

    private static final Logger LOGGER = Logger.getLogger(GemCuttingStationBlockEntity.class.getName());
    public GemCuttingStationBlockEntity( BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEM_CUTTING_STATION_ENTITY.get(), pos, state);
    }
    final int RANGE = 5;
    private void hurtMobs() {
        BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AABB box = new AABB(topCorner, bottomCorner);

        List<Entity> entities = this.level.getEntities(null, box);
        for (Entity target : entities){
            if (target instanceof LivingEntity && !(target instanceof Player)){
                target.hurt(DamageSource.MAGIC, 2);
            }
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        GemCuttingStationBlockEntity tile = (GemCuttingStationBlockEntity) be;

        tile.hurtMobs();
        LOGGER.info("hurt");

    }


}
