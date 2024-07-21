package net.minecraft.src;

import java.util.Random;

public class BlockBookshelf extends Block {
	public BlockBookshelf(int var1) {
		super(var1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Icon getIcon(int var1, int var2) {
		return var1 != 1 && var1 != 0 ? super.getIcon(var1, var2) : Block.planks.getBlockTextureFromSide(var1);
	}

	public int quantityDropped(Random var1) {
		return 3;
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Item.book.itemID;
	}
}
