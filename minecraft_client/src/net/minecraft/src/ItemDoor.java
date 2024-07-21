package net.minecraft.src;

import net.minecraft.src.block.Block;

public class ItemDoor extends Item {
	private Material doorMaterial;

	public ItemDoor(int var1, Material var2) {
		super(var1);
		this.doorMaterial = var2;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
		if(var7 != 1) {
			return false;
		} else {
			++var5;
			Block var11;
			if(this.doorMaterial == Material.wood) {
				var11 = Block.doorWood;
			} else {
				var11 = Block.doorIron;
			}

			if(var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1)) {
				if(!var11.canPlaceBlockAt(var3, var4, var5, var6)) {
					return false;
				} else {
					int var12 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
					placeDoorBlock(var3, var4, var5, var6, var12, var11);
					--var1.stackSize;
					return true;
				}
			} else {
				return false;
			}
		}
	}

	public static void placeDoorBlock(World var0, int var1, int var2, int var3, int var4, Block var5) {
		byte var6 = 0;
		byte var7 = 0;
		if(var4 == 0) {
			var7 = 1;
		}

		if(var4 == 1) {
			var6 = -1;
		}

		if(var4 == 2) {
			var7 = -1;
		}

		if(var4 == 3) {
			var6 = 1;
		}

		int var8 = (var0.isBlockNormalCube(var1 - var6, var2, var3 - var7) ? 1 : 0) + (var0.isBlockNormalCube(var1 - var6, var2 + 1, var3 - var7) ? 1 : 0);
		int var9 = (var0.isBlockNormalCube(var1 + var6, var2, var3 + var7) ? 1 : 0) + (var0.isBlockNormalCube(var1 + var6, var2 + 1, var3 + var7) ? 1 : 0);
		boolean var10 = var0.getBlockId(var1 - var6, var2, var3 - var7) == var5.blockID || var0.getBlockId(var1 - var6, var2 + 1, var3 - var7) == var5.blockID;
		boolean var11 = var0.getBlockId(var1 + var6, var2, var3 + var7) == var5.blockID || var0.getBlockId(var1 + var6, var2 + 1, var3 + var7) == var5.blockID;
		boolean var12 = false;
		if(var10 && !var11) {
			var12 = true;
		} else if(var9 > var8) {
			var12 = true;
		}

		var0.setBlock(var1, var2, var3, var5.blockID, var4, 2);
		var0.setBlock(var1, var2 + 1, var3, var5.blockID, 8 | (var12 ? 1 : 0), 2);
		var0.notifyBlocksOfNeighborChange(var1, var2, var3, var5.blockID);
		var0.notifyBlocksOfNeighborChange(var1, var2 + 1, var3, var5.blockID);
	}
}
