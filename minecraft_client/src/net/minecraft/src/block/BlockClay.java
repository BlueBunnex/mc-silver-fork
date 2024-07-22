package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.item.Item;

public class BlockClay extends Block {
	public BlockClay(int var1) {
		super(var1, Material.clay);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Item.clay.itemID;
	}

	public int quantityDropped(Random var1) {
		return 4;
	}
}
