package net.minecraft.src;

import net.minecraft.src.worldgen.World;

public interface ITileEntityProvider {
	TileEntity createNewTileEntity(World var1);
}
