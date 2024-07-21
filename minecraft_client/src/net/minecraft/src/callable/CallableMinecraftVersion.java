package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReport;

public class CallableMinecraftVersion implements Callable {
	
	private final CrashReport theCrashReport;

	public CallableMinecraftVersion(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String minecraftVersion() {
		return "1.5.2";
	}

	public Object call() {
		return this.minecraftVersion();
	}
}
