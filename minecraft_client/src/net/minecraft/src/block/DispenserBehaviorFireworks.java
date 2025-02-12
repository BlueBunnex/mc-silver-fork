package net.minecraft.src.block;

import net.minecraft.src.EnumFacing;
import net.minecraft.src.IBlockSource;
import net.minecraft.src.entity.EntityFireworkRocket;
import net.minecraft.src.item.ItemStack;

final class DispenserBehaviorFireworks extends BehaviorDefaultDispenseItem {
	public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
		EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
		double var4 = var1.getX() + (double)var3.getFrontOffsetX();
		double var6 = (double)((float)var1.getYInt() + 0.2F);
		double var8 = var1.getZ() + (double)var3.getFrontOffsetZ();
		EntityFireworkRocket var10 = new EntityFireworkRocket(var1.getWorld(), var4, var6, var8, var2);
		var1.getWorld().spawnEntityInWorld(var10);
		var2.splitStack(1);
		return var2;
	}

	protected void playDispenseSound(IBlockSource var1) {
		var1.getWorld().playAuxSFX(1002, var1.getXInt(), var1.getYInt(), var1.getZInt(), 0);
	}
}
