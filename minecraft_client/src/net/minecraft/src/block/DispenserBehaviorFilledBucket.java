package net.minecraft.src.block;

import net.minecraft.src.EnumFacing;
import net.minecraft.src.IBlockSource;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemBucket;
import net.minecraft.src.item.ItemStack;

final class DispenserBehaviorFilledBucket extends BehaviorDefaultDispenseItem {
	private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

	public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
		ItemBucket var3 = (ItemBucket)var2.getItem();
		int var4 = var1.getXInt();
		int var5 = var1.getYInt();
		int var6 = var1.getZInt();
		EnumFacing var7 = BlockDispenser.getFacing(var1.getBlockMetadata());
		if(var3.tryPlaceContainedLiquid(var1.getWorld(), (double)var4, (double)var5, (double)var6, var4 + var7.getFrontOffsetX(), var5 + var7.getFrontOffsetY(), var6 + var7.getFrontOffsetZ())) {
			var2.itemID = Item.bucketEmpty.itemID;
			var2.stackSize = 1;
			return var2;
		} else {
			return this.defaultDispenserItemBehavior.dispense(var1, var2);
		}
	}
}
