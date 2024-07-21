package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityFallingSand;
import net.minecraft.src.Material;
import net.minecraft.src.worldgen.World;

public class BlockSand extends Block {
	public static boolean fallInstantly = false;

	public BlockSand(int var1) {
		super(var1, Material.sand);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockSand(int var1, Material var2) {
		super(var1, var2);
	}

	public void onBlockAdded(World var1, int var2, int var3, int var4) {
		var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(!var1.isRemote) {
			this.tryToFall(var1, var2, var3, var4);
		}

	}

	private void tryToFall(World var1, int var2, int var3, int var4) {
		if(canFallBelow(var1, var2, var3 - 1, var4) && var3 >= 0) {
			byte var8 = 32;
			if(!fallInstantly && var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
				if(!var1.isRemote) {
					EntityFallingSand var9 = new EntityFallingSand(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), this.blockID, var1.getBlockMetadata(var2, var3, var4));
					this.onStartFalling(var9);
					var1.spawnEntityInWorld(var9);
				}
			} else {
				var1.setBlockToAir(var2, var3, var4);

				while(canFallBelow(var1, var2, var3 - 1, var4) && var3 > 0) {
					--var3;
				}

				if(var3 > 0) {
					var1.setBlock(var2, var3, var4, this.blockID);
				}
			}
		}

	}

	protected void onStartFalling(EntityFallingSand var1) {
	}

	public int tickRate(World var1) {
		return 2;
	}

	public static boolean canFallBelow(World var0, int var1, int var2, int var3) {
		int var4 = var0.getBlockId(var1, var2, var3);
		if(var4 == 0) {
			return true;
		} else if(var4 == Block.fire.blockID) {
			return true;
		} else {
			Material var5 = Block.blocksList[var4].blockMaterial;
			return var5 == Material.water ? true : var5 == Material.lava;
		}
	}

	public void onFinishFalling(World var1, int var2, int var3, int var4, int var5) {
	}
}
