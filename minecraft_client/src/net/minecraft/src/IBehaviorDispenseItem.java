package net.minecraft.src;

import net.minecraft.src.item.ItemStack;

public interface IBehaviorDispenseItem {
	IBehaviorDispenseItem itemDispenseBehaviorProvider = new BehaviorDispenseItemProvider();

	ItemStack dispense(IBlockSource var1, ItemStack var2);
}
