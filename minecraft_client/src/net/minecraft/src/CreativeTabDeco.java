package net.minecraft.src;

import net.minecraft.src.block.Block;

final class CreativeTabDeco extends CreativeTabs {
	CreativeTabDeco(int var1, String var2) {
		super(var1, var2);
	}

	public int getTabIconItemIndex() {
		return Block.plantRed.blockID;
	}
}
