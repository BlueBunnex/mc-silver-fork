package net.minecraft.src;

import net.minecraft.src.item.Item;

final class CreativeTabSearch extends CreativeTabs {
	CreativeTabSearch(int var1, String var2) {
		super(var1, var2);
	}

	public int getTabIconItemIndex() {
		return Item.compass.itemID;
	}
}
