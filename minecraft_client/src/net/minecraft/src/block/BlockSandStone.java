package net.minecraft.src.block;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.item.ItemStack;

public class BlockSandStone extends Block {
	public static final String[] SAND_STONE_TYPES = new String[]{"default", "chiseled", "smooth"};
	private static final String[] field_94405_b = new String[]{"sandstone_side", "sandstone_carved", "sandstone_smooth"};
	private Icon[] field_94406_c;
	private Icon field_94403_cO;
	private Icon field_94404_cP;

	public BlockSandStone(int var1) {
		super(var1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Icon getIcon(int var1, int var2) {
		if(var1 != 1 && (var1 != 0 || var2 != 1 && var2 != 2)) {
			if(var1 == 0) {
				return this.field_94404_cP;
			} else {
				if(var2 < 0 || var2 >= this.field_94406_c.length) {
					var2 = 0;
				}

				return this.field_94406_c[var2];
			}
		} else {
			return this.field_94403_cO;
		}
	}

	public int damageDropped(int var1) {
		return var1;
	}

	public void getSubBlocks(int var1, CreativeTabs var2, List var3) {
		var3.add(new ItemStack(var1, 1, 0));
		var3.add(new ItemStack(var1, 1, 1));
		var3.add(new ItemStack(var1, 1, 2));
	}

	public void registerIcons(IconRegister var1) {
		this.field_94406_c = new Icon[field_94405_b.length];

		for(int var2 = 0; var2 < this.field_94406_c.length; ++var2) {
			this.field_94406_c[var2] = var1.registerIcon(field_94405_b[var2]);
		}

		this.field_94403_cO = var1.registerIcon("sandstone_top");
		this.field_94404_cP = var1.registerIcon("sandstone_bottom");
	}
}
