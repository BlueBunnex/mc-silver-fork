package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.entity.tile.TileEntity;

public class CallableTileEntityData implements Callable {
	
	private final TileEntity theTileEntity;

	public CallableTileEntityData(TileEntity var1) {
		this.theTileEntity = var1;
	}

	public String callTileEntityDataInfo() {
		int var1 = this.theTileEntity.getWorldObj().getBlockMetadata(this.theTileEntity.xCoord, this.theTileEntity.yCoord, this.theTileEntity.zCoord);
		if(var1 < 0) {
			return "Unknown? (Got " + var1 + ")";
		} else {
			String var2 = String.format("%4s", new Object[]{Integer.toBinaryString(var1)}).replace(" ", "0");
			return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[]{Integer.valueOf(var1), var2});
		}
	}

	public Object call() {
		return this.callTileEntityDataInfo();
	}
}
