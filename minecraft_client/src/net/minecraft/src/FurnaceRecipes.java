package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class FurnaceRecipes {
	private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();

	public static final FurnaceRecipes smelting() {
		return smeltingBase;
	}

	private FurnaceRecipes() {
		this.addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron), 0.7F);
		this.addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold), 1.0F);
		this.addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond), 1.0F);
		this.addSmelting(Block.sand.blockID, new ItemStack(Block.glass), 0.1F);
		this.addSmelting(Item.porkRaw.itemID, new ItemStack(Item.porkCooked), 0.35F);
		this.addSmelting(Item.beefRaw.itemID, new ItemStack(Item.beefCooked), 0.35F);
		this.addSmelting(Item.chickenRaw.itemID, new ItemStack(Item.chickenCooked), 0.35F);
		this.addSmelting(Item.fishRaw.itemID, new ItemStack(Item.fishCooked), 0.35F);
		this.addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone), 0.1F);
		this.addSmelting(Item.clay.itemID, new ItemStack(Item.brick), 0.3F);
		this.addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
		this.addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		this.addSmelting(Block.oreEmerald.blockID, new ItemStack(Item.emerald), 1.0F);
		this.addSmelting(Item.potato.itemID, new ItemStack(Item.bakedPotato), 0.35F);
		this.addSmelting(Block.netherrack.blockID, new ItemStack(Item.netherrackBrick), 0.1F);
		this.addSmelting(Block.oreCoal.blockID, new ItemStack(Item.coal), 0.1F);
		this.addSmelting(Block.oreRedstone.blockID, new ItemStack(Item.redstone), 0.7F);
		this.addSmelting(Block.oreLapis.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
		this.addSmelting(Block.oreNetherQuartz.blockID, new ItemStack(Item.netherQuartz), 0.2F);
	}

	public void addSmelting(int var1, ItemStack var2, float var3) {
		this.smeltingList.put(Integer.valueOf(var1), var2);
		this.experienceList.put(Integer.valueOf(var2.itemID), Float.valueOf(var3));
	}

	public ItemStack getSmeltingResult(int var1) {
		return (ItemStack)this.smeltingList.get(Integer.valueOf(var1));
	}

	public Map getSmeltingList() {
		return this.smeltingList;
	}

	public float getExperience(int var1) {
		return this.experienceList.containsKey(Integer.valueOf(var1)) ? ((Float)this.experienceList.get(Integer.valueOf(var1))).floatValue() : 0.0F;
	}
}
