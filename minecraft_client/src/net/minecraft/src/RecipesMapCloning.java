package net.minecraft.src;

import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class RecipesMapCloning implements IRecipe {
	public boolean matches(InventoryCrafting var1, World var2) {
		int var3 = 0;
		ItemStack var4 = null;

		for(int var5 = 0; var5 < var1.getSizeInventory(); ++var5) {
			ItemStack var6 = var1.getStackInSlot(var5);
			if(var6 != null) {
				if(var6.itemID == Item.map.itemID) {
					if(var4 != null) {
						return false;
					}

					var4 = var6;
				} else {
					if(var6.itemID != Item.emptyMap.itemID) {
						return false;
					}

					++var3;
				}
			}
		}

		return var4 != null && var3 > 0;
	}

	public ItemStack getCraftingResult(InventoryCrafting var1) {
		int var2 = 0;
		ItemStack var3 = null;

		for(int var4 = 0; var4 < var1.getSizeInventory(); ++var4) {
			ItemStack var5 = var1.getStackInSlot(var4);
			if(var5 != null) {
				if(var5.itemID == Item.map.itemID) {
					if(var3 != null) {
						return null;
					}

					var3 = var5;
				} else {
					if(var5.itemID != Item.emptyMap.itemID) {
						return null;
					}

					++var2;
				}
			}
		}

		if(var3 != null && var2 >= 1) {
			ItemStack var6 = new ItemStack(Item.map, var2 + 1, var3.getItemDamage());
			if(var3.hasDisplayName()) {
				var6.setItemName(var3.getDisplayName());
			}

			return var6;
		} else {
			return null;
		}
	}

	public int getRecipeSize() {
		return 9;
	}

	public ItemStack getRecipeOutput() {
		return null;
	}
}
