package net.minecraft.src;

import net.minecraft.src.item.Item;

final class CreativeTabFood extends CreativeTabs {
	CreativeTabFood(int var1, String var2) {
		super(var1, var2);
	}

	public int getTabIconItemIndex() {
		return Item.appleRed.itemID;
	}
}
