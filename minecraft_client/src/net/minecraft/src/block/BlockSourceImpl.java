package net.minecraft.src.block;

import net.minecraft.src.IBlockSource;
import net.minecraft.src.entity.tile.TileEntity;
import net.minecraft.src.worldgen.World;

public class BlockSourceImpl implements IBlockSource {
	private final World worldObj;
	private final int xPos;
	private final int yPos;
	private final int zPos;

	public BlockSourceImpl(World var1, int var2, int var3, int var4) {
		this.worldObj = var1;
		this.xPos = var2;
		this.yPos = var3;
		this.zPos = var4;
	}

	public World getWorld() {
		return this.worldObj;
	}

	public double getX() {
		return (double)this.xPos + 0.5D;
	}

	public double getY() {
		return (double)this.yPos + 0.5D;
	}

	public double getZ() {
		return (double)this.zPos + 0.5D;
	}

	public int getXInt() {
		return this.xPos;
	}

	public int getYInt() {
		return this.yPos;
	}

	public int getZInt() {
		return this.zPos;
	}

	public int getBlockMetadata() {
		return this.worldObj.getBlockMetadata(this.xPos, this.yPos, this.zPos);
	}

	public TileEntity getBlockTileEntity() {
		return this.worldObj.getBlockTileEntity(this.xPos, this.yPos, this.zPos);
	}
}
