package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.block.Block;

public final class CallableBlockType implements Callable {
	
	private final int blockID;

	public CallableBlockType(int var1) {
		this.blockID = var1;
	}

	public String callBlockType() {
		try {
			return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(this.blockID), Block.blocksList[this.blockID].getUnlocalizedName(), Block.blocksList[this.blockID].getClass().getCanonicalName()});
		} catch (Throwable var2) {
			return "ID #" + this.blockID;
		}
	}

	public Object call() {
		return this.callBlockType();
	}
}
