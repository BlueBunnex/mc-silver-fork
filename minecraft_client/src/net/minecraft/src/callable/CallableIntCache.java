package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReport;
import net.minecraft.src.IntCache;

public class CallableIntCache implements Callable {
	
	private final CrashReport theCrashReport;

	public CallableIntCache(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String func_85083_a() {
		return IntCache.func_85144_b();
	}

	public Object call() {
		return this.func_85083_a();
	}
}
