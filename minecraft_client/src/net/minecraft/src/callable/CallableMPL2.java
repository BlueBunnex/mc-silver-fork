package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldClient;

public class CallableMPL2 implements Callable {
	
	private final WorldClient theWorldClient;

	public CallableMPL2(WorldClient var1) {
		this.theWorldClient = var1;
	}

	public String getEntitySpawnQueueCountAndList() {
		return WorldClient.getEntitySpawnQueue(this.theWorldClient).size() + " total; " + WorldClient.getEntitySpawnQueue(this.theWorldClient).toString();
	}

	public Object call() {
		return this.getEntitySpawnQueueCountAndList();
	}
}
