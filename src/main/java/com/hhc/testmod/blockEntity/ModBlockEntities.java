package com.hhc.testmod.blockEntity;

import com.hhc.testmod.TestModMain;
import com.hhc.testmod.blockEntity.custom.GemCuttingStationBlockEntity;
import com.hhc.testmod.init.BlockInit;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.logging.Logger;

public class ModBlockEntities {

    private static final Logger LOGGER = Logger.getLogger(ModBlockEntities.class.getName());

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES=
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TestModMain.MOD_ID);


    public static final RegistryObject<BlockEntityType<GemCuttingStationBlockEntity>> GEM_CUTTING_STATION_ENTITY=
            BLOCK_ENTITIES.register("gem_cutting_station_entity",()->
                    BlockEntityType.Builder.of(GemCuttingStationBlockEntity::new,
                            BlockInit.GEM_CUTTING_STATION_BLOCK.get()).build(null));
    public static  void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
        LOGGER.info("registered");
    }
}
