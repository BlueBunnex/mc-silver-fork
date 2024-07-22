package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.DedicatedServer;

public class CallableType implements Callable {
	
	final DedicatedServer theDecitatedServer;

	public CallableType(DedicatedServer var1) {
		this.theDecitatedServer = var1;
	}

	public String getType() {
		String var1 = this.theDecitatedServer.getServerModName();
		return !var1.equals("vanilla") ? "Definitely; Server brand changed to \'" + var1 + "\'" : "Unknown (can\'t tell)";
	}

	public Object call() {
		return this.getType();
	}
}
