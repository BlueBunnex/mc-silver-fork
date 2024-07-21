package net.minecraft.src.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class BlockWoodSlab extends BlockHalfSlab {
	public static final String[] woodType = new String[]{"oak", "spruce", "birch", "jungle"};

	public BlockWoodSlab(int var1, boolean var2) {
		super(var1, var2, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Icon getIcon(int var1, int var2) {
		return Block.planks.getIcon(var1, var2 & 7);
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Block.woodSingleSlab.blockID;
	}

	protected ItemStack createStackedBlock(int var1) {
		return new ItemStack(Block.woodSingleSlab.blockID, 2, var1 & 7);
	}

	public String getFullSlabName(int var1) {
		if(var1 < 0 || var1 >= woodType.length) {
			var1 = 0;
		}

		return super.getUnlocalizedName() + "." + woodType[var1];
	}

	public void getSubBlocks(int var1, CreativeTabs var2, List var3) {
		if(var1 != Block.woodDoubleSlab.blockID) {
			for(int var4 = 0; var4 < 4; ++var4) {
				var3.add(new ItemStack(var1, 1, var4));
			}

		}
	}

	public void registerIcons(IconRegister var1) {
	}
}
