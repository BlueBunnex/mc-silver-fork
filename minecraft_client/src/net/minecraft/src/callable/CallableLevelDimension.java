package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldInfo;

public class CallableLevelDimension implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelDimension(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelDimension() {
		return String.valueOf(WorldInfo.func_85122_i(this.worldInfoInstance));
	}

	public Object call() {
		return this.callLevelDimension();
	}
}
