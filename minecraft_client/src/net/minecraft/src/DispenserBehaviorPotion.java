package net.minecraft.src;

import net.minecraft.src.item.ItemPotion;
import net.minecraft.src.item.ItemStack;

final class DispenserBehaviorPotion implements IBehaviorDispenseItem {
	private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

	public ItemStack dispense(IBlockSource var1, ItemStack var2) {
		return ItemPotion.isSplash(var2.getItemDamage()) ? (new DispenserBehaviorPotionProjectile(this, var2)).dispense(var1, var2) : this.defaultDispenserItemBehavior.dispense(var1, var2);
	}
}
