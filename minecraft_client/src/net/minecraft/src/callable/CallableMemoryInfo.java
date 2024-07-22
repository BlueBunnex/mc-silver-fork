package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReport;

public class CallableMemoryInfo implements Callable {
	
	private final CrashReport theCrashReport;

	public CallableMemoryInfo(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String getMemoryInfoAsString() {
		Runtime var1 = Runtime.getRuntime();
		long var2 = var1.maxMemory();
		long var4 = var1.totalMemory();
		long var6 = var1.freeMemory();
		long var8 = var2 / 1024L / 1024L;
		long var10 = var4 / 1024L / 1024L;
		long var12 = var6 / 1024L / 1024L;
		return var6 + " bytes (" + var12 + " MB) / " + var4 + " bytes (" + var10 + " MB) up to " + var2 + " bytes (" + var8 + " MB)";
	}

	public Object call() {
		return this.getMemoryInfoAsString();
	}
}
