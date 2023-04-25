package com.hhc.testmod.init;

import com.hhc.testmod.TestModMain;
import com.hhc.testmod.blocks.AdBlock;
import com.hhc.testmod.blocks.GemCuttingStationBlock;
import com.hhc.testmod.blocks.RotateBlock;
import com.hhc.testmod.blocks.SoilBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestModMain.MOD_ID);

    public static final RegistryObject<Block> FIRST_BLOCK=BLOCKS.register("first_block",
            ()->new Block(Block.Properties.of(Material.STONE).strength(4f,1200f).requiresCorrectToolForDrops().lightLevel((state)->15)));

    public static final RegistryObject<Block> AD_BLOCK=BLOCKS.register("ad_block",
            ()->new AdBlock(Block.Properties.copy(BlockInit.FIRST_BLOCK.get())));

    public static final RegistryObject<Block> ROTATE_BLOCK=BLOCKS.register("rotate_block",
            ()->new RotateBlock(Block.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block>SOIL_BLOCK=BLOCKS.register("soil_block",
            ()->new SoilBlock(Block.Properties.copy(Blocks.DIRT)));


    public static final RegistryObject<Block>GEM_CUTTING_STATION_BLOCK=BLOCKS.register("gem_cutting_station_block",
            ()->new GemCuttingStationBlock(Block.Properties.copy(Blocks.STONE)));


    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry= event.getRegistry();

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach((block -> {
            final Item.Properties properties=new Item.Properties().tab(ItemInit.ModCreativeTab.instance);
            final BlockItem blockItem=new BlockItem(block,properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        }));

    }
}
