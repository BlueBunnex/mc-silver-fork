package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableMPL2 implements Callable {
	final WorldClient theWorldClient;

	CallableMPL2(WorldClient var1) {
		this.theWorldClient = var1;
	}

	public String getEntitySpawnQueueCountAndList() {
		return WorldClient.getEntitySpawnQueue(this.theWorldClient).size() + " total; " + WorldClient.getEntitySpawnQueue(this.theWorldClient).toString();
	}

	public Object call() {
		return this.getEntitySpawnQueueCountAndList();
	}
}
