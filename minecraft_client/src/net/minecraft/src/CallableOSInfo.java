package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableOSInfo implements Callable {
	final CrashReport theCrashReport;

	CallableOSInfo(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String getOsAsString() {
		return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
	}

	public Object call() {
		return this.getOsAsString();
	}
}
