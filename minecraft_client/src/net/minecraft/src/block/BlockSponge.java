package net.minecraft.src.block;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockSponge extends Block {
	protected BlockSponge(int var1) {
		super(var1, Material.sponge);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
