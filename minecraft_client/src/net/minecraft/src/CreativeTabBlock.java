package net.minecraft.src;

import net.minecraft.src.block.Block;

final class CreativeTabBlock extends CreativeTabs {
	CreativeTabBlock(int var1, String var2) {
		super(var1, var2);
	}

	public int getTabIconItemIndex() {
		return Block.brick.blockID;
	}
}
