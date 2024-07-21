package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.IntegratedServer;

public class CallableType3 implements Callable {
	
	private final IntegratedServer theIntegratedServer;

	public CallableType3(IntegratedServer var1) {
		this.theIntegratedServer = var1;
	}

	public String getType() {
		return "Integrated Server (map_client.txt)";
	}

	public Object call() {
		return this.getType();
	}
}
