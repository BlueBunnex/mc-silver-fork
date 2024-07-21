package net.minecraft.src.block;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class BlockWood extends Block {
	public static final String[] woodType = new String[]{"oak", "spruce", "birch", "jungle"};
	public static final String[] woodTextureTypes = new String[]{"wood", "wood_spruce", "wood_birch", "wood_jungle"};
	private Icon[] iconArray;

	public BlockWood(int var1) {
		super(var1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Icon getIcon(int var1, int var2) {
		if(var2 < 0 || var2 >= this.iconArray.length) {
			var2 = 0;
		}

		return this.iconArray[var2];
	}

	public int damageDropped(int var1) {
		return var1;
	}

	public void getSubBlocks(int var1, CreativeTabs var2, List var3) {
		var3.add(new ItemStack(var1, 1, 0));
		var3.add(new ItemStack(var1, 1, 1));
		var3.add(new ItemStack(var1, 1, 2));
		var3.add(new ItemStack(var1, 1, 3));
	}

	public void registerIcons(IconRegister var1) {
		this.iconArray = new Icon[woodTextureTypes.length];

		for(int var2 = 0; var2 < this.iconArray.length; ++var2) {
			this.iconArray[var2] = var1.registerIcon(woodTextureTypes[var2]);
		}

	}
}
