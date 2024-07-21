package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.worldgen.World;

public class BlockReed extends Block {
	protected BlockReed(int var1) {
		super(var1, Material.plants);
		float var2 = 6.0F / 16.0F;
		this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 1.0F, 0.5F + var2);
		this.setTickRandomly(true);
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(var1.isAirBlock(var2, var3 + 1, var4)) {
			int var6;
			for(var6 = 1; var1.getBlockId(var2, var3 - var6, var4) == this.blockID; ++var6) {
			}

			if(var6 < 3) {
				int var7 = var1.getBlockMetadata(var2, var3, var4);
				if(var7 == 15) {
					var1.setBlock(var2, var3 + 1, var4, this.blockID);
					var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 4);
				} else {
					var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + 1, 4);
				}
			}
		}

	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockId(var2, var3 - 1, var4);
		return var5 == this.blockID ? true : (var5 != Block.grass.blockID && var5 != Block.dirt.blockID && var5 != Block.sand.blockID ? false : (var1.getBlockMaterial(var2 - 1, var3 - 1, var4) == Material.water ? true : (var1.getBlockMaterial(var2 + 1, var3 - 1, var4) == Material.water ? true : (var1.getBlockMaterial(var2, var3 - 1, var4 - 1) == Material.water ? true : var1.getBlockMaterial(var2, var3 - 1, var4 + 1) == Material.water))));
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		this.checkBlockCoordValid(var1, var2, var3, var4);
	}

	protected final void checkBlockCoordValid(World var1, int var2, int var3, int var4) {
		if(!this.canBlockStay(var1, var2, var3, var4)) {
			this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
			var1.setBlockToAir(var2, var3, var4);
		}

	}

	public boolean canBlockStay(World var1, int var2, int var3, int var4) {
		return this.canPlaceBlockAt(var1, var2, var3, var4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		return null;
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Item.reed.itemID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 1;
	}

	public int idPicked(World var1, int var2, int var3, int var4) {
		return Item.reed.itemID;
	}
}
