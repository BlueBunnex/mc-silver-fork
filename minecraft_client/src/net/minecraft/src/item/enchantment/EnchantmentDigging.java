package net.minecraft.src.item.enchantment;

import net.minecraft.src.EnumEnchantmentType;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class EnchantmentDigging extends Enchantment {
	protected EnchantmentDigging(int var1, int var2) {
		super(var1, var2, EnumEnchantmentType.digger);
		this.setName("digging");
	}

	public int getMinEnchantability(int var1) {
		return 1 + 10 * (var1 - 1);
	}

	public int getMaxEnchantability(int var1) {
		return super.getMinEnchantability(var1) + 50;
	}

	public int getMaxLevel() {
		return 5;
	}

	public boolean canApply(ItemStack var1) {
		return var1.getItem().itemID == Item.shears.itemID ? true : super.canApply(var1);
	}
}
