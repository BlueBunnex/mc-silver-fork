package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class CraftingManager {
	private static final CraftingManager instance = new CraftingManager();
	private List recipes = new ArrayList();

	public static final CraftingManager getInstance() {
		return instance;
	}

	private CraftingManager() {
		(new RecipesTools()).addRecipes(this);
		(new RecipesWeapons()).addRecipes(this);
		(new RecipesIngots()).addRecipes(this);
		(new RecipesFood()).addRecipes(this);
		(new RecipesCrafting()).addRecipes(this);
		(new RecipesArmor()).addRecipes(this);
		(new RecipesDyes()).addRecipes(this);
		this.recipes.add(new RecipesArmorDyes());
		this.recipes.add(new RecipesMapCloning());
		this.recipes.add(new RecipesMapExtending());
		this.recipes.add(new RecipeFireworks());
		this.addRecipe(new ItemStack(Item.paper, 3), new Object[]{"###", Character.valueOf('#'), Item.reed});
		this.addShapelessRecipe(new ItemStack(Item.book, 1), new Object[]{Item.paper, Item.paper, Item.paper, Item.leather});
		this.addShapelessRecipe(new ItemStack(Item.writableBook, 1), new Object[]{Item.book, new ItemStack(Item.dyePowder, 1, 0), Item.feather});
		this.addRecipe(new ItemStack(Block.fence, 2), new Object[]{"###", "###", Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.cobblestoneWall, 6, 0), new Object[]{"###", "###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.cobblestoneWall, 6, 1), new Object[]{"###", "###", Character.valueOf('#'), Block.cobblestoneMossy});
		this.addRecipe(new ItemStack(Block.netherFence, 6), new Object[]{"###", "###", Character.valueOf('#'), Block.netherBrick});
		this.addRecipe(new ItemStack(Block.fenceGate, 1), new Object[]{"#W#", "#W#", Character.valueOf('#'), Item.stick, Character.valueOf('W'), Block.planks});
		this.addRecipe(new ItemStack(Block.jukebox, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.diamond});
		this.addRecipe(new ItemStack(Block.music, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Block.bookShelf, 1), new Object[]{"###", "XXX", "###", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.book});
		this.addRecipe(new ItemStack(Block.blockSnow, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.snowball});
		this.addRecipe(new ItemStack(Block.snow, 6), new Object[]{"###", Character.valueOf('#'), Block.blockSnow});
		this.addRecipe(new ItemStack(Block.blockClay, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.clay});
		this.addRecipe(new ItemStack(Block.brick, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.brick});
		this.addRecipe(new ItemStack(Block.glowStone, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.lightStoneDust});
		this.addRecipe(new ItemStack(Block.blockNetherQuartz, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.netherQuartz});
		this.addRecipe(new ItemStack(Block.cloth, 1), new Object[]{"##", "##", Character.valueOf('#'), Item.silk});
		this.addRecipe(new ItemStack(Block.tnt, 1), new Object[]{"X#X", "#X#", "X#X", Character.valueOf('X'), Item.gunpowder, Character.valueOf('#'), Block.sand});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), new Object[]{"###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[]{"###", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1), new Object[]{"###", Character.valueOf('#'), Block.sandStone});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 4), new Object[]{"###", Character.valueOf('#'), Block.brick});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5), new Object[]{"###", Character.valueOf('#'), Block.stoneBrick});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 6), new Object[]{"###", Character.valueOf('#'), Block.netherBrick});
		this.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 7), new Object[]{"###", Character.valueOf('#'), Block.blockNetherQuartz});
		this.addRecipe(new ItemStack(Block.woodSingleSlab, 6, 0), new Object[]{"###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 0)});
		this.addRecipe(new ItemStack(Block.woodSingleSlab, 6, 2), new Object[]{"###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 2)});
		this.addRecipe(new ItemStack(Block.woodSingleSlab, 6, 1), new Object[]{"###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 1)});
		this.addRecipe(new ItemStack(Block.woodSingleSlab, 6, 3), new Object[]{"###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 3)});
		this.addRecipe(new ItemStack(Block.ladder, 3), new Object[]{"# #", "###", "# #", Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Item.doorWood, 1), new Object[]{"##", "##", "##", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.trapdoor, 2), new Object[]{"###", "###", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Item.doorIron, 1), new Object[]{"##", "##", "##", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.sign, 3), new Object[]{"###", "###", " X ", Character.valueOf('#'), Block.planks, Character.valueOf('X'), Item.stick});
		this.addRecipe(new ItemStack(Item.cake, 1), new Object[]{"AAA", "BEB", "CCC", Character.valueOf('A'), Item.bucketMilk, Character.valueOf('B'), Item.sugar, Character.valueOf('C'), Item.wheat, Character.valueOf('E'), Item.egg});
		this.addRecipe(new ItemStack(Item.sugar, 1), new Object[]{"#", Character.valueOf('#'), Item.reed});
		this.addRecipe(new ItemStack(Block.planks, 4, 0), new Object[]{"#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 0)});
		this.addRecipe(new ItemStack(Block.planks, 4, 1), new Object[]{"#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 1)});
		this.addRecipe(new ItemStack(Block.planks, 4, 2), new Object[]{"#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 2)});
		this.addRecipe(new ItemStack(Block.planks, 4, 3), new Object[]{"#", Character.valueOf('#'), new ItemStack(Block.wood, 1, 3)});
		this.addRecipe(new ItemStack(Item.stick, 4), new Object[]{"#", "#", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"X", "#", Character.valueOf('X'), Item.coal, Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.torchWood, 4), new Object[]{"X", "#", Character.valueOf('X'), new ItemStack(Item.coal, 1, 1), Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Item.bowlEmpty, 4), new Object[]{"# #", " # ", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Item.glassBottle, 3), new Object[]{"# #", " # ", Character.valueOf('#'), Block.glass});
		this.addRecipe(new ItemStack(Block.rail, 16), new Object[]{"X X", "X#X", "X X", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.railPowered, 6), new Object[]{"X X", "X#X", "XRX", Character.valueOf('X'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('#'), Item.stick});
		this.addRecipe(new ItemStack(Block.railActivator, 6), new Object[]{"XSX", "X#X", "XSX", Character.valueOf('X'), Item.ingotIron, Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('S'), Item.stick});
		this.addRecipe(new ItemStack(Block.railDetector, 6), new Object[]{"X X", "X#X", "XRX", Character.valueOf('X'), Item.ingotIron, Character.valueOf('R'), Item.redstone, Character.valueOf('#'), Block.pressurePlateStone});
		this.addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[]{"# #", "###", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.cauldron, 1), new Object[]{"# #", "# #", "###", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.brewingStand, 1), new Object[]{" B ", "###", Character.valueOf('#'), Block.cobblestone, Character.valueOf('B'), Item.blazeRod});
		this.addRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.pumpkin, Character.valueOf('B'), Block.torchWood});
		this.addRecipe(new ItemStack(Item.minecartCrate, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.chest, Character.valueOf('B'), Item.minecartEmpty});
		this.addRecipe(new ItemStack(Item.minecartPowered, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.furnaceIdle, Character.valueOf('B'), Item.minecartEmpty});
		this.addRecipe(new ItemStack(Item.minecartTnt, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.tnt, Character.valueOf('B'), Item.minecartEmpty});
		this.addRecipe(new ItemStack(Item.minecartHopper, 1), new Object[]{"A", "B", Character.valueOf('A'), Block.hopperBlock, Character.valueOf('B'), Item.minecartEmpty});
		this.addRecipe(new ItemStack(Item.boat, 1), new Object[]{"# #", "###", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[]{"# #", " # ", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Item.flowerPot, 1), new Object[]{"# #", " # ", Character.valueOf('#'), Item.brick});
		this.addRecipe(new ItemStack(Item.flintAndSteel, 1), new Object[]{"A ", " B", Character.valueOf('A'), Item.ingotIron, Character.valueOf('B'), Item.flint});
		this.addRecipe(new ItemStack(Item.bread, 1), new Object[]{"###", Character.valueOf('#'), Item.wheat});
		this.addRecipe(new ItemStack(Block.stairsWoodOak, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 0)});
		this.addRecipe(new ItemStack(Block.stairsWoodBirch, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 2)});
		this.addRecipe(new ItemStack(Block.stairsWoodSpruce, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 1)});
		this.addRecipe(new ItemStack(Block.stairsWoodJungle, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Block.planks, 1, 3)});
		this.addRecipe(new ItemStack(Item.fishingRod, 1), new Object[]{"  #", " #X", "# X", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.silk});
		this.addRecipe(new ItemStack(Item.carrotOnAStick, 1), new Object[]{"# ", " X", Character.valueOf('#'), Item.fishingRod, Character.valueOf('X'), Item.carrot}).func_92100_c();
		this.addRecipe(new ItemStack(Block.stairsCobblestone, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.stairsBrick, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.brick});
		this.addRecipe(new ItemStack(Block.stairsStoneBrick, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.stoneBrick});
		this.addRecipe(new ItemStack(Block.stairsNetherBrick, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.netherBrick});
		this.addRecipe(new ItemStack(Block.stairsSandStone, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.sandStone});
		this.addRecipe(new ItemStack(Block.stairsNetherQuartz, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Block.blockNetherQuartz});
		this.addRecipe(new ItemStack(Item.painting, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Block.cloth});
		this.addRecipe(new ItemStack(Item.itemFrame, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.leather});
		this.addRecipe(new ItemStack(Item.appleGold, 1, 0), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.goldNugget, Character.valueOf('X'), Item.appleRed});
		this.addRecipe(new ItemStack(Item.appleGold, 1, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Block.blockGold, Character.valueOf('X'), Item.appleRed});
		this.addRecipe(new ItemStack(Item.goldenCarrot, 1, 0), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.goldNugget, Character.valueOf('X'), Item.carrot});
		this.addRecipe(new ItemStack(Block.lever, 1), new Object[]{"X", "#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.stick});
		this.addRecipe(new ItemStack(Block.tripWireSource, 2), new Object[]{"I", "S", "#", Character.valueOf('#'), Block.planks, Character.valueOf('S'), Item.stick, Character.valueOf('I'), Item.ingotIron});
		this.addRecipe(new ItemStack(Block.torchRedstoneActive, 1), new Object[]{"X", "#", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[]{"#X#", "III", Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('X'), Item.redstone, Character.valueOf('I'), Block.stone});
		this.addRecipe(new ItemStack(Item.comparator, 1), new Object[]{" # ", "#X#", "III", Character.valueOf('#'), Block.torchRedstoneActive, Character.valueOf('X'), Item.netherQuartz, Character.valueOf('I'), Block.stone});
		this.addRecipe(new ItemStack(Item.pocketSundial, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.ingotGold, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Item.compass, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), Item.redstone});
		this.addRecipe(new ItemStack(Item.emptyMap, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Item.paper, Character.valueOf('X'), Item.compass});
		this.addRecipe(new ItemStack(Block.stoneButton, 1), new Object[]{"#", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.woodenButton, 1), new Object[]{"#", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[]{"##", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[]{"##", Character.valueOf('#'), Block.planks});
		this.addRecipe(new ItemStack(Block.pressurePlateIron, 1), new Object[]{"##", Character.valueOf('#'), Item.ingotIron});
		this.addRecipe(new ItemStack(Block.pressurePlateGold, 1), new Object[]{"##", Character.valueOf('#'), Item.ingotGold});
		this.addRecipe(new ItemStack(Block.dispenser, 1), new Object[]{"###", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.bow, Character.valueOf('R'), Item.redstone});
		this.addRecipe(new ItemStack(Block.dropper, 1), new Object[]{"###", "# #", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('R'), Item.redstone});
		this.addRecipe(new ItemStack(Block.pistonBase, 1), new Object[]{"TTT", "#X#", "#R#", Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), Item.ingotIron, Character.valueOf('R'), Item.redstone, Character.valueOf('T'), Block.planks});
		this.addRecipe(new ItemStack(Block.pistonStickyBase, 1), new Object[]{"S", "P", Character.valueOf('S'), Item.slimeBall, Character.valueOf('P'), Block.pistonBase});
		this.addRecipe(new ItemStack(Item.bed, 1), new Object[]{"###", "XXX", Character.valueOf('#'), Block.cloth, Character.valueOf('X'), Block.planks});
		this.addRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[]{" B ", "D#D", "###", Character.valueOf('#'), Block.obsidian, Character.valueOf('B'), Item.book, Character.valueOf('D'), Item.diamond});
		this.addRecipe(new ItemStack(Block.anvil, 1), new Object[]{"III", " i ", "iii", Character.valueOf('I'), Block.blockIron, Character.valueOf('i'), Item.ingotIron});
		this.addShapelessRecipe(new ItemStack(Item.eyeOfEnder, 1), new Object[]{Item.enderPearl, Item.blazePowder});
		this.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.gunpowder, Item.blazePowder, Item.coal});
		this.addShapelessRecipe(new ItemStack(Item.fireballCharge, 3), new Object[]{Item.gunpowder, Item.blazePowder, new ItemStack(Item.coal, 1, 1)});
		this.addRecipe(new ItemStack(Block.daylightSensor), new Object[]{"GGG", "QQQ", "WWW", Character.valueOf('G'), Block.glass, Character.valueOf('Q'), Item.netherQuartz, Character.valueOf('W'), Block.woodSingleSlab});
		this.addRecipe(new ItemStack(Block.hopperBlock), new Object[]{"I I", "ICI", " I ", Character.valueOf('I'), Item.ingotIron, Character.valueOf('C'), Block.chest});
		Collections.sort(this.recipes, new RecipeSorter(this));
		System.out.println(this.recipes.size() + " recipes");
	}

	ShapedRecipes addRecipe(ItemStack var1, Object... var2) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if(var2[var4] instanceof String[]) {
			String[] var11 = (String[])((String[])var2[var4++]);

			for(int var8 = 0; var8 < var11.length; ++var8) {
				String var9 = var11[var8];
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else {
			while(var2[var4] instanceof String) {
				String var7 = (String)var2[var4++];
				++var6;
				var5 = var7.length();
				var3 = var3 + var7;
			}
		}

		HashMap var12;
		for(var12 = new HashMap(); var4 < var2.length; var4 += 2) {
			Character var13 = (Character)var2[var4];
			ItemStack var15 = null;
			if(var2[var4 + 1] instanceof Item) {
				var15 = new ItemStack((Item)var2[var4 + 1]);
			} else if(var2[var4 + 1] instanceof Block) {
				var15 = new ItemStack((Block)var2[var4 + 1], 1, Short.MAX_VALUE);
			} else if(var2[var4 + 1] instanceof ItemStack) {
				var15 = (ItemStack)var2[var4 + 1];
			}

			var12.put(var13, var15);
		}

		ItemStack[] var14 = new ItemStack[var5 * var6];

		for(int var16 = 0; var16 < var5 * var6; ++var16) {
			char var10 = var3.charAt(var16);
			if(var12.containsKey(Character.valueOf(var10))) {
				var14[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
			} else {
				var14[var16] = null;
			}
		}

		ShapedRecipes var17 = new ShapedRecipes(var5, var6, var14, var1);
		this.recipes.add(var17);
		return var17;
	}

	void addShapelessRecipe(ItemStack var1, Object... var2) {
		ArrayList var3 = new ArrayList();
		Object[] var4 = var2;
		int var5 = var2.length;

		for(int var6 = 0; var6 < var5; ++var6) {
			Object var7 = var4[var6];
			if(var7 instanceof ItemStack) {
				var3.add(((ItemStack)var7).copy());
			} else if(var7 instanceof Item) {
				var3.add(new ItemStack((Item)var7));
			} else {
				if(!(var7 instanceof Block)) {
					throw new RuntimeException("Invalid shapeless recipy!");
				}

				var3.add(new ItemStack((Block)var7));
			}
		}

		this.recipes.add(new ShapelessRecipes(var1, var3));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting var1, World var2) {
		int var3 = 0;
		ItemStack var4 = null;
		ItemStack var5 = null;

		int var6;
		for(var6 = 0; var6 < var1.getSizeInventory(); ++var6) {
			ItemStack var7 = var1.getStackInSlot(var6);
			if(var7 != null) {
				if(var3 == 0) {
					var4 = var7;
				}

				if(var3 == 1) {
					var5 = var7;
				}

				++var3;
			}
		}

		if(var3 == 2 && var4.itemID == var5.itemID && var4.stackSize == 1 && var5.stackSize == 1 && Item.itemsList[var4.itemID].isDamageable()) {
			Item var11 = Item.itemsList[var4.itemID];
			int var13 = var11.getMaxDamage() - var4.getItemDamageForDisplay();
			int var8 = var11.getMaxDamage() - var5.getItemDamageForDisplay();
			int var9 = var13 + var8 + var11.getMaxDamage() * 5 / 100;
			int var10 = var11.getMaxDamage() - var9;
			if(var10 < 0) {
				var10 = 0;
			}

			return new ItemStack(var4.itemID, 1, var10);
		} else {
			for(var6 = 0; var6 < this.recipes.size(); ++var6) {
				IRecipe var12 = (IRecipe)this.recipes.get(var6);
				if(var12.matches(var1, var2)) {
					return var12.getCraftingResult(var1);
				}
			}

			return null;
		}
	}

	public List getRecipeList() {
		return this.recipes;
	}
}
