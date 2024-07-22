package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.DedicatedServer;

public class CallableServerType implements Callable {
	
	private final DedicatedServer theDedicatedServer;

	public CallableServerType(DedicatedServer var1) {
		this.theDedicatedServer = var1;
	}

	public String callServerType() {
		return "Dedicated Server (map_server.txt)";
	}

	public Object call() {
		return this.callServerType();
	}
}
