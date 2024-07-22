package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.entity.tile.TileEntity;

public class CallableTileEntityName implements Callable {
	
	private final TileEntity theTileEntity;

	public CallableTileEntityName(TileEntity var1) {
		this.theTileEntity = var1;
	}

	public String callTileEntityName() {
		return (String)TileEntity.getClassToNameMap().get(this.theTileEntity.getClass()) + " // " + this.theTileEntity.getClass().getCanonicalName();
	}

	public Object call() {
		return this.callTileEntityName();
	}
}
