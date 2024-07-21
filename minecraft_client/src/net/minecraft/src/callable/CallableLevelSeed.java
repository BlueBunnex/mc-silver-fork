package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.WorldInfo;

public class CallableLevelSeed implements Callable {
	final WorldInfo worldInfoInstance;

	public CallableLevelSeed(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelSeed() {
		return String.valueOf(this.worldInfoInstance.getSeed());
	}

	public Object call() {
		return this.callLevelSeed();
	}
}
