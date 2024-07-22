package net.minecraft.src.block;

import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.item.Item;

public class BlockCarrot extends BlockCrops {
	private Icon[] iconArray;

	public BlockCarrot(int var1) {
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
		return Item.carrot.itemID;
	}

	protected int getCropItem() {
		return Item.carrot.itemID;
	}

	public void registerIcons(IconRegister var1) {
		this.iconArray = new Icon[4];

		for(int var2 = 0; var2 < this.iconArray.length; ++var2) {
			this.iconArray[var2] = var1.registerIcon("carrots_" + var2);
		}

	}
}
