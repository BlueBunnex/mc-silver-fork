package net.minecraft.src;

import net.minecraft.src.entity.tile.TileEntity;

public interface IBlockSource extends ILocatableSource {
	double getX();

	double getY();

	double getZ();

	int getXInt();

	int getYInt();

	int getZInt();

	int getBlockMetadata();

	TileEntity getBlockTileEntity();
}
