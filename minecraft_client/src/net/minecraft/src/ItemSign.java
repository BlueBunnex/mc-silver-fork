package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class ItemSign extends Item {
	public ItemSign(int var1) {
		super(var1);
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
		if(var7 == 0) {
			return false;
		} else if(!var3.getBlockMaterial(var4, var5, var6).isSolid()) {
			return false;
		} else {
			if(var7 == 1) {
				++var5;
			}

			if(var7 == 2) {
				--var6;
			}

			if(var7 == 3) {
				++var6;
			}

			if(var7 == 4) {
				--var4;
			}

			if(var7 == 5) {
				++var4;
			}

			if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
				return false;
			} else if(!Block.signPost.canPlaceBlockAt(var3, var4, var5, var6)) {
				return false;
			} else {
				if(var7 == 1) {
					int var11 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
					var3.setBlock(var4, var5, var6, Block.signPost.blockID, var11, 2);
				} else {
					var3.setBlock(var4, var5, var6, Block.signWall.blockID, var7, 2);
				}

				--var1.stackSize;
				TileEntitySign var12 = (TileEntitySign)var3.getBlockTileEntity(var4, var5, var6);
				if(var12 != null) {
					var2.displayGUIEditSign(var12);
				}

				return true;
			}
		}
	}
}
