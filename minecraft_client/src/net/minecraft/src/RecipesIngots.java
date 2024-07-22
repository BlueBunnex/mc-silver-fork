package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class RecipesIngots {
	private Object[][] recipeItems = new Object[][]{{Block.blockGold, new ItemStack(Item.ingotGold, 9)}, {Block.blockIron, new ItemStack(Item.ingotIron, 9)}, {Block.blockDiamond, new ItemStack(Item.diamond, 9)}, {Block.blockEmerald, new ItemStack(Item.emerald, 9)}, {Block.blockLapis, new ItemStack(Item.dyePowder, 9, 4)}, {Block.blockRedstone, new ItemStack(Item.redstone, 9)}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems.length; ++var2) {
			Block var3 = (Block)this.recipeItems[var2][0];
			ItemStack var4 = (ItemStack)this.recipeItems[var2][1];
			var1.addRecipe(new ItemStack(var3), new Object[]{"###", "###", "###", Character.valueOf('#'), var4});
			var1.addRecipe(var4, new Object[]{"#", Character.valueOf('#'), var3});
		}

		var1.addRecipe(new ItemStack(Item.ingotGold), new Object[]{"###", "###", "###", Character.valueOf('#'), Item.goldNugget});
		var1.addRecipe(new ItemStack(Item.goldNugget, 9), new Object[]{"#", Character.valueOf('#'), Item.ingotGold});
	}
}
