package net.minecraft.src;

import net.minecraft.src.block.BlockDispenser;
import net.minecraft.src.block.BlockRailBase;
import net.minecraft.src.worldgen.World;

final class BehaviorDispenseMinecart extends BehaviorDefaultDispenseItem {
	private final BehaviorDefaultDispenseItem field_96465_b = new BehaviorDefaultDispenseItem();

	public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
		EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
		World var4 = var1.getWorld();
		double var5 = var1.getX() + (double)((float)var3.getFrontOffsetX() * 1.125F);
		double var7 = var1.getY() + (double)((float)var3.getFrontOffsetY() * 1.125F);
		double var9 = var1.getZ() + (double)((float)var3.getFrontOffsetZ() * 1.125F);
		int var11 = var1.getXInt() + var3.getFrontOffsetX();
		int var12 = var1.getYInt() + var3.getFrontOffsetY();
		int var13 = var1.getZInt() + var3.getFrontOffsetZ();
		int var14 = var4.getBlockId(var11, var12, var13);
		double var15;
		if(BlockRailBase.isRailBlock(var14)) {
			var15 = 0.0D;
		} else {
			if(var14 != 0 || !BlockRailBase.isRailBlock(var4.getBlockId(var11, var12 - 1, var13))) {
				return this.field_96465_b.dispense(var1, var2);
			}

			var15 = -1.0D;
		}

		EntityMinecart var17 = EntityMinecart.createMinecart(var4, var5, var7 + var15, var9, ((ItemMinecart)var2.getItem()).minecartType);
		var4.spawnEntityInWorld(var17);
		var2.splitStack(1);
		return var2;
	}

	protected void playDispenseSound(IBlockSource var1) {
		var1.getWorld().playAuxSFX(1000, var1.getXInt(), var1.getYInt(), var1.getZInt(), 0);
	}
}
