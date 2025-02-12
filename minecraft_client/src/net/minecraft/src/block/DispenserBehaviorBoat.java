package net.minecraft.src.block;

import net.minecraft.src.EnumFacing;
import net.minecraft.src.IBlockSource;
import net.minecraft.src.Material;
import net.minecraft.src.entity.EntityBoat;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

final class DispenserBehaviorBoat extends BehaviorDefaultDispenseItem {
	private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

	public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
		EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
		World var4 = var1.getWorld();
		double var5 = var1.getX() + (double)((float)var3.getFrontOffsetX() * 1.125F);
		double var7 = var1.getY() + (double)((float)var3.getFrontOffsetY() * 1.125F);
		double var9 = var1.getZ() + (double)((float)var3.getFrontOffsetZ() * 1.125F);
		int var11 = var1.getXInt() + var3.getFrontOffsetX();
		int var12 = var1.getYInt() + var3.getFrontOffsetY();
		int var13 = var1.getZInt() + var3.getFrontOffsetZ();
		Material var14 = var4.getBlockMaterial(var11, var12, var13);
		double var15;
		if(Material.water.equals(var14)) {
			var15 = 1.0D;
		} else {
			if(!Material.air.equals(var14) || !Material.water.equals(var4.getBlockMaterial(var11, var12 - 1, var13))) {
				return this.defaultDispenserItemBehavior.dispense(var1, var2);
			}

			var15 = 0.0D;
		}

		EntityBoat var17 = new EntityBoat(var4, var5, var7 + var15, var9);
		var4.spawnEntityInWorld(var17);
		var2.splitStack(1);
		return var2;
	}

	protected void playDispenseSound(IBlockSource var1) {
		var1.getWorld().playAuxSFX(1000, var1.getXInt(), var1.getYInt(), var1.getZInt(), 0);
	}
}
