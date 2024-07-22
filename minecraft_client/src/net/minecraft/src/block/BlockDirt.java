package net.minecraft.src.block;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockDirt extends Block {
	protected BlockDirt(int var1) {
		super(var1, Material.ground);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
