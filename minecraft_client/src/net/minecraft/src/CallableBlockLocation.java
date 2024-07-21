package net.minecraft.src;

import java.util.concurrent.Callable;

final class CallableBlockLocation implements Callable {
	final int blockXCoord;
	final int blockYCoord;
	final int blockZCoord;

	CallableBlockLocation(int var1, int var2, int var3) {
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
