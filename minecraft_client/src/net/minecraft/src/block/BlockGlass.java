package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockGlass extends BlockBreakable {
	public BlockGlass(int var1, Material var2, boolean var3) {
		super(var1, "glass", var2, var3);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int quantityDropped(Random var1) {
		return 0;
	}

	public int getRenderBlockPass() {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}
}
