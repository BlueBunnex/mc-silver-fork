package net.minecraft.src;

import net.minecraft.src.item.Item;

final class CreativeTabMaterial extends CreativeTabs {
	CreativeTabMaterial(int var1, String var2) {
		super(var1, var2);
	}

	public int getTabIconItemIndex() {
		return Item.stick.itemID;
	}
}
