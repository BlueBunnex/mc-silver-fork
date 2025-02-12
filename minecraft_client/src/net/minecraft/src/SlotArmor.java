package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemArmor;
import net.minecraft.src.item.ItemStack;

class SlotArmor extends Slot {
	final int armorType;
	final ContainerPlayer parent;

	SlotArmor(ContainerPlayer var1, IInventory var2, int var3, int var4, int var5, int var6) {
		super(var2, var3, var4, var5);
		this.parent = var1;
		this.armorType = var6;
	}

	public int getSlotStackLimit() {
		return 1;
	}

	public boolean isItemValid(ItemStack var1) {
		return var1 == null ? false : (var1.getItem() instanceof ItemArmor ? ((ItemArmor)var1.getItem()).armorType == this.armorType : (var1.getItem().itemID != Block.pumpkin.blockID && var1.getItem().itemID != Item.skull.itemID ? false : this.armorType == 0));
	}

	public Icon getBackgroundIconIndex() {
		return ItemArmor.func_94602_b(this.armorType);
	}
}
