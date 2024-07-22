package net.minecraft.src.callable;

import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public class CallableIsServerModded implements Callable {
	final MinecraftServer mcServer;

	public CallableIsServerModded(MinecraftServer var1) {
		this.mcServer = var1;
	}

	public String func_96558_a() {
		return this.mcServer.theProfiler.profilingEnabled ? this.mcServer.theProfiler.getNameOfLastSection() : "N/A (disabled)";
	}

	public Object call() {
		return this.func_96558_a();
	}
}
