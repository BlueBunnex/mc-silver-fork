package net.minecraft.src.item;

import net.minecraft.src.block.Block;
import net.minecraft.src.block.BlockAnvil;

public class ItemAnvilBlock extends ItemMultiTextureTile {
	public ItemAnvilBlock(Block var1) {
		super(var1.blockID - 256, var1, BlockAnvil.statuses);
	}

	public int getMetadata(int var1) {
		return var1 << 2;
	}
}
