package com.hhc.testmod.init;

import com.hhc.testmod.TestModMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestModMain.MOD_ID); //创造一个名为ITEMS的注册器

    public static final RegistryObject<Item> SBZDY = ITEMS.register("sbzdy",()->new Item(new Item.Properties().tab(ModCreativeTab.instance)));  //在注册好的ITEMS注册器里注册一个名为sbzdy的物品

    public static class ModCreativeTab extends CreativeModeTab
    {
        private ModCreativeTab(int index,String label)
        {
            super(index,label);
        }

        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(SBZDY.get());
        }
        public static final ModCreativeTab instance= new ModCreativeTab(CreativeModeTab.TABS.length,"testmod");
    }


}

