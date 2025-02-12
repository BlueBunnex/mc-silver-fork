package net.minecraft.src.block;

import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;

public class BlockLeavesBase extends Block {
	protected boolean graphicsLevel;

	protected BlockLeavesBase(int var1, Material var2, boolean var3) {
		super(var1, var2);
		this.graphicsLevel = var3;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		int var6 = var1.getBlockId(var2, var3, var4);
		return !this.graphicsLevel && var6 == this.blockID ? false : super.shouldSideBeRendered(var1, var2, var3, var4, var5);
	}
}
