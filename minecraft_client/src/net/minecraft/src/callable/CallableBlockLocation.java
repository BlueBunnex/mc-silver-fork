package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReportCategory;

public final class CallableBlockLocation implements Callable {
	
	private final int blockXCoord;
	private final int blockYCoord;
	private final int blockZCoord;

	public CallableBlockLocation(int var1, int var2, int var3) {
		this.blockXCoord = var1;
		this.blockYCoord = var2;
		this.blockZCoord = var3;
	}

	public String callBlockLocationInfo() {
		return CrashReportCategory.getLocationInfo(this.blockXCoord, this.blockYCoord, this.blockZCoord);
	}

	public Object call() {
		return this.callBlockLocationInfo();
	}
}
