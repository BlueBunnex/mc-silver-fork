package net.minecraft.src.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class ItemFireball extends Item {
	public ItemFireball(int var1) {
		super(var1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
		if(var3.isRemote) {
			return true;
		} else {
			if(var7 == 0) {
				--var5;
			}

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
			} else {
				int var11 = var3.getBlockId(var4, var5, var6);
				if(var11 == 0) {
					var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
					var3.setBlock(var4, var5, var6, Block.fire.blockID);
				}

				if(!var2.capabilities.isCreativeMode) {
					--var1.stackSize;
				}

				return true;
			}
		}
	}
}
