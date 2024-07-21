package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.TileEntity;
import net.minecraft.src.block.Block;

public class CallableTileEntityID implements Callable {
	
	private final TileEntity theTileEntity;

	public CallableTileEntityID(TileEntity var1) {
		this.theTileEntity = var1;
	}

	public String callTileEntityID() {
		int var1 = this.theTileEntity.getWorldObj().getBlockId(this.theTileEntity.xCoord, this.theTileEntity.yCoord, this.theTileEntity.zCoord);

		try {
			return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(var1), Block.blocksList[var1].getUnlocalizedName(), Block.blocksList[var1].getClass().getCanonicalName()});
		} catch (Throwable var3) {
			return "ID #" + var1;
		}
	}

	public Object call() {
		return this.callTileEntityID();
	}
}
