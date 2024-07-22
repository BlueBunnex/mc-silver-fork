package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldClient;

public class CallableMPL1 implements Callable {
	
	private final WorldClient theWorldClient;

	public CallableMPL1(WorldClient var1) {
		this.theWorldClient = var1;
	}

	public String getEntityCountAndList() {
		return WorldClient.getEntityList(this.theWorldClient).size() + " total; " + WorldClient.getEntityList(this.theWorldClient).toString();
	}

	public Object call() {
		return this.getEntityCountAndList();
	}
}
