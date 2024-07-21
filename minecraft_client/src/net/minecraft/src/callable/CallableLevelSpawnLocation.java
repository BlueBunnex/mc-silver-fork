package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.CrashReportCategory;
import net.minecraft.src.worldgen.WorldInfo;

public class CallableLevelSpawnLocation implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelSpawnLocation(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelSpawnLocation() {
		return CrashReportCategory.getLocationInfo(WorldInfo.getSpawnXCoordinate(this.worldInfoInstance), WorldInfo.getSpawnYCoordinate(this.worldInfoInstance), WorldInfo.getSpawnZCoordinate(this.worldInfoInstance));
	}

	public Object call() {
		return this.callLevelSpawnLocation();
	}
}
