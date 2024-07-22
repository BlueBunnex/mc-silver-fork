package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReport;

public class CallableJavaInfo2 implements Callable {
	
	private final CrashReport theCrashReport;

	public CallableJavaInfo2(CrashReport var1) {
		this.theCrashReport = var1;
	}

	public String getJavaVMInfoAsString() {
		return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
	}

	public Object call() {
		return this.getJavaVMInfoAsString();
	}
}
