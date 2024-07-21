package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldInfo;

public class CallableLevelTime implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelTime(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelTime() {
		return String.format("%d game time, %d day time", new Object[]{Long.valueOf(WorldInfo.func_85126_g(this.worldInfoInstance)), Long.valueOf(WorldInfo.getWorldTime(this.worldInfoInstance))});
	}

	public Object call() {
		return this.callLevelTime();
	}
}
