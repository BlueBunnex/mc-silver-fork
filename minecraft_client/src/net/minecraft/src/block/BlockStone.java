package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockStone extends Block {
	public BlockStone(int var1) {
		super(var1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Block.cobblestone.blockID;
	}
}
