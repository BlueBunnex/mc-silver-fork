package net.minecraft.src;

import net.minecraft.src.item.ItemStack;

public interface ISidedInventory extends IInventory {
	int[] getAccessibleSlotsFromSide(int var1);

	boolean canInsertItem(int var1, ItemStack var2, int var3);

	boolean canExtractItem(int var1, ItemStack var2, int var3);
}
