package net.minecraft.src.block;

import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.worldgen.World;

public class BlockPotato extends BlockCrops {
	private Icon[] iconArray;

	public BlockPotato(int var1) {
		super(var1);
	}

	public Icon getIcon(int var1, int var2) {
		if(var2 < 7) {
			if(var2 == 6) {
				var2 = 5;
			}

			return this.iconArray[var2 >> 1];
		} else {
			return this.iconArray[3];
		}
	}

	protected int getSeedItem() {
		return Item.potato.itemID;
	}

	protected int getCropItem() {
		return Item.potato.itemID;
	}

	public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
		super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
		if(!var1.isRemote) {
			if(var5 >= 7 && var1.rand.nextInt(50) == 0) {
				this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(Item.poisonousPotato));
			}

		}
	}

	public void registerIcons(IconRegister var1) {
		this.iconArray = new Icon[4];

		for(int var2 = 0; var2 < this.iconArray.length; ++var2) {
			this.iconArray[var2] = var1.registerIcon("potatoes_" + var2);
		}

	}
}
