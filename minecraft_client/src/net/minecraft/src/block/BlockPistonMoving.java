package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Facing;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityPiston;
import net.minecraft.src.worldgen.World;

public class BlockPistonMoving extends BlockContainer {
	public BlockPistonMoving(int var1) {
		super(var1, Material.piston);
		this.setHardness(-1.0F);
	}

	public TileEntity createNewTileEntity(World var1) {
		return null;
	}

	public void onBlockAdded(World var1, int var2, int var3, int var4) {
	}

	public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6) {
		TileEntity var7 = var1.getBlockTileEntity(var2, var3, var4);
		if(var7 instanceof TileEntityPiston) {
			((TileEntityPiston)var7).clearPistonTileEntity();
		} else {
			super.breakBlock(var1, var2, var3, var4, var5, var6);
		}

	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return false;
	}

	public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
		return false;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
		if(!var1.isRemote && var1.getBlockTileEntity(var2, var3, var4) == null) {
			var1.setBlockToAir(var2, var3, var4);
			return true;
		} else {
			return false;
		}
	}

	public int idDropped(int var1, Random var2, int var3) {
		return 0;
	}

	public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
		if(!var1.isRemote) {
			TileEntityPiston var8 = this.getTileEntityAtLocation(var1, var2, var3, var4);
			if(var8 != null) {
				Block.blocksList[var8.getStoredBlockID()].dropBlockAsItem(var1, var2, var3, var4, var8.getBlockMetadata(), 0);
			}
		}
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		if(!var1.isRemote && var1.getBlockTileEntity(var2, var3, var4) == null) {
		}

	}

	public static TileEntity getTileEntity(int var0, int var1, int var2, boolean var3, boolean var4) {
		return new TileEntityPiston(var0, var1, var2, var3, var4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		TileEntityPiston var5 = this.getTileEntityAtLocation(var1, var2, var3, var4);
		if(var5 == null) {
			return null;
		} else {
			float var6 = var5.getProgress(0.0F);
			if(var5.isExtending()) {
				var6 = 1.0F - var6;
			}

			return this.getAxisAlignedBB(var1, var2, var3, var4, var5.getStoredBlockID(), var6, var5.getPistonOrientation());
		}
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		TileEntityPiston var5 = this.getTileEntityAtLocation(var1, var2, var3, var4);
		if(var5 != null) {
			Block var6 = Block.blocksList[var5.getStoredBlockID()];
			if(var6 == null || var6 == this) {
				return;
			}

			var6.setBlockBoundsBasedOnState(var1, var2, var3, var4);
			float var7 = var5.getProgress(0.0F);
			if(var5.isExtending()) {
				var7 = 1.0F - var7;
			}

			int var8 = var5.getPistonOrientation();
			this.minX = var6.getBlockBoundsMinX() - (double)((float)Facing.offsetsXForSide[var8] * var7);
			this.minY = var6.getBlockBoundsMinY() - (double)((float)Facing.offsetsYForSide[var8] * var7);
			this.minZ = var6.getBlockBoundsMinZ() - (double)((float)Facing.offsetsZForSide[var8] * var7);
			this.maxX = var6.getBlockBoundsMaxX() - (double)((float)Facing.offsetsXForSide[var8] * var7);
			this.maxY = var6.getBlockBoundsMaxY() - (double)((float)Facing.offsetsYForSide[var8] * var7);
			this.maxZ = var6.getBlockBoundsMaxZ() - (double)((float)Facing.offsetsZForSide[var8] * var7);
		}

	}

	public AxisAlignedBB getAxisAlignedBB(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
		if(var5 != 0 && var5 != this.blockID) {
			AxisAlignedBB var8 = Block.blocksList[var5].getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
			if(var8 == null) {
				return null;
			} else {
				if(Facing.offsetsXForSide[var7] < 0) {
					var8.minX -= (double)((float)Facing.offsetsXForSide[var7] * var6);
				} else {
					var8.maxX -= (double)((float)Facing.offsetsXForSide[var7] * var6);
				}

				if(Facing.offsetsYForSide[var7] < 0) {
					var8.minY -= (double)((float)Facing.offsetsYForSide[var7] * var6);
				} else {
					var8.maxY -= (double)((float)Facing.offsetsYForSide[var7] * var6);
				}

				if(Facing.offsetsZForSide[var7] < 0) {
					var8.minZ -= (double)((float)Facing.offsetsZForSide[var7] * var6);
				} else {
					var8.maxZ -= (double)((float)Facing.offsetsZForSide[var7] * var6);
				}

				return var8;
			}
		} else {
			return null;
		}
	}

	private TileEntityPiston getTileEntityAtLocation(IBlockAccess var1, int var2, int var3, int var4) {
		TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);
		return var5 instanceof TileEntityPiston ? (TileEntityPiston)var5 : null;
	}

	public int idPicked(World var1, int var2, int var3, int var4) {
		return 0;
	}

	public void registerIcons(IconRegister var1) {
		this.blockIcon = var1.registerIcon("piston_top");
	}
}
