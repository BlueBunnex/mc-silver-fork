package net.minecraft.src;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.item.ItemStack;

public interface IInventory {
	int getSizeInventory();

	ItemStack getStackInSlot(int var1);

	ItemStack decrStackSize(int var1, int var2);

	ItemStack getStackInSlotOnClosing(int var1);

	void setInventorySlotContents(int var1, ItemStack var2);

	String getInvName();

	boolean isInvNameLocalized();

	int getInventoryStackLimit();

	void onInventoryChanged();

	boolean isUseableByPlayer(EntityPlayer var1);

	void openChest();

	void closeChest();

	boolean isStackValidForSlot(int var1, ItemStack var2);
}
