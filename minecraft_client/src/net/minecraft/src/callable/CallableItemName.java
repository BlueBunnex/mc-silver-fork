package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;

public class CallableItemName implements Callable {
	
	private final ItemStack theItemStack;
	private final InventoryPlayer playerInventory;

	public CallableItemName(InventoryPlayer var1, ItemStack var2) {
		this.playerInventory = var1;
		this.theItemStack = var2;
	}

	public String callItemDisplayName() {
		return this.theItemStack.getDisplayName();
	}

	public Object call() {
		return this.callItemDisplayName();
	}
}
