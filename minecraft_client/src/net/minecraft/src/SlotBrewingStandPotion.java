package net.minecraft.src;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

class SlotBrewingStandPotion extends Slot {
	private EntityPlayer player;

	public SlotBrewingStandPotion(EntityPlayer var1, IInventory var2, int var3, int var4, int var5) {
		super(var2, var3, var4, var5);
		this.player = var1;
	}

	public boolean isItemValid(ItemStack var1) {
		return canHoldPotion(var1);
	}

	public int getSlotStackLimit() {
		return 1;
	}

	public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
		if(var2.itemID == Item.potion.itemID && var2.getItemDamage() > 0) {
			this.player.addStat(AchievementList.potion, 1);
		}

		super.onPickupFromSlot(var1, var2);
	}

	public static boolean canHoldPotion(ItemStack var0) {
		return var0 != null && (var0.itemID == Item.potion.itemID || var0.itemID == Item.glassBottle.itemID);
	}
}
