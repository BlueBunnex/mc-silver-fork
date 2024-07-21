package net.minecraft.src;

import net.minecraft.src.block.BlockDispenser;
import net.minecraft.src.block.BlockRailBase;

public class ItemMinecart extends Item {
	private static final IBehaviorDispenseItem dispenserMinecartBehavior = new BehaviorDispenseMinecart();
	public int minecartType;

	public ItemMinecart(int var1, int var2) {
		super(var1);
		this.maxStackSize = 1;
		this.minecartType = var2;
		this.setCreativeTab(CreativeTabs.tabTransport);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserMinecartBehavior);
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
		int var11 = var3.getBlockId(var4, var5, var6);
		if(BlockRailBase.isRailBlock(var11)) {
			if(!var3.isRemote) {
				EntityMinecart var12 = EntityMinecart.createMinecart(var3, (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), this.minecartType);
				if(var1.hasDisplayName()) {
					var12.func_96094_a(var1.getDisplayName());
				}

				var3.spawnEntityInWorld(var12);
			}

			--var1.stackSize;
			return true;
		} else {
			return false;
		}
	}
}
