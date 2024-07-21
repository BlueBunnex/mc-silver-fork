package net.minecraft.src;

import net.minecraft.src.worldgen.World;

public interface IRecipe {
	boolean matches(InventoryCrafting var1, World var2);

	ItemStack getCraftingResult(InventoryCrafting var1);

	int getRecipeSize();

	ItemStack getRecipeOutput();
}
