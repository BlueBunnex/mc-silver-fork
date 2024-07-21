package net.minecraft.src.block;

import java.util.Random;

public class BlockObsidian extends BlockStone {
	public BlockObsidian(int var1) {
		super(var1);
	}

	public int quantityDropped(Random var1) {
		return 1;
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Block.obsidian.blockID;
	}
}
