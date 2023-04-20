package com.hhc.testmod.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class FuelItem extends Item {
    private final int burnTicks;
    public FuelItem(Properties properties,int burnTImeInTicks)
    {
        super(properties);
        this.burnTicks=burnTImeInTicks;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return this.burnTicks;
    }

}
