package net.minecraft.src.block;

import net.minecraft.src.IBlockSource;
import net.minecraft.src.item.ItemStack;

final class BehaviorDispenseItemProvider implements IBehaviorDispenseItem {
	public ItemStack dispense(IBlockSource var1, ItemStack var2) {
		return var2;
	}
}
