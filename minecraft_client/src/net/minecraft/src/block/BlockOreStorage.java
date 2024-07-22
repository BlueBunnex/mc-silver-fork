package net.minecraft.src.block;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockOreStorage extends Block {
	public BlockOreStorage(int var1) {
		super(var1, Material.iron);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
