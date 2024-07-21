package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.WorldInfo;

public class CallableLevelGeneratorOptions implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelGeneratorOptions(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelGeneratorOptions() {
		return WorldInfo.getWorldGeneratorOptions(this.worldInfoInstance);
	}

	public Object call() {
		return this.callLevelGeneratorOptions();
	}
}
