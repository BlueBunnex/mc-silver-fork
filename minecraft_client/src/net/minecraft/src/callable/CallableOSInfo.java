package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReport;

public class CallableOSInfo implements Callable {
	
	private final CrashReport theCrashReport;

	public CallableOSInfo(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String getOsAsString() {
		return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
	}

	public Object call() {
		return this.getOsAsString();
	}
}
