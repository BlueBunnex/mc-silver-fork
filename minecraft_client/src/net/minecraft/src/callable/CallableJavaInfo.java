package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReport;

public class CallableJavaInfo implements Callable {
	
	private final CrashReport theCrashReport;

	public CallableJavaInfo(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String getJavaInfoAsString() {
		return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
	}

	public Object call() {
		return this.getJavaInfoAsString();
	}
}
