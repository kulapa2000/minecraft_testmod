package com.hhc.testmod.init;


import com.hhc.testmod.TestModMain;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.inventory.FurnaceFuelSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.hhc.testmod.items.FuelItem;
import com.hhc.testmod.items.Teleport;


public class ItemInit {

    // here to create a register
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestModMain.MOD_ID); //创造一个名为ITEMS的注册器

    //here to use register created to register items
    public static final RegistryObject<Item> SBZDY = ITEMS.register("sbzdy",()->new Item(new Item.Properties().tab(ModCreativeTab.instance)));  //在注册好的ITEMS注册器里注册一个名为sbzdy的物品
    public static final RegistryObject<Item> ICON= ITEMS.register("icon",()->new Item(new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> STHTOEAT= ITEMS.register("sthtoeat",()->new Item(new Item.Properties().tab(ModCreativeTab.instance).
            food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(2).effect(()->new MobEffectInstance(MobEffects.POISON,200,0),1.0F).build())));

    public static final RegistryObject<Item> FUEL= ITEMS.register("fuel",()->new FuelItem(new Item.Properties().tab(ModCreativeTab.instance),3200));

    public static final RegistryObject<Item> TELEPORT=ITEMS.register("teleport",()->new Teleport(new Item.Properties().tab(ModCreativeTab.instance)));
    //the constructor of customized creative mode tab
    public static class ModCreativeTab extends CreativeModeTab
    {
        private ModCreativeTab(int index,String label)
        {
            super(index,label);
        }

        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ICON.get());
        }
        public static final ModCreativeTab instance= new ModCreativeTab(CreativeModeTab.TABS.length,"testmod");
    }


}

