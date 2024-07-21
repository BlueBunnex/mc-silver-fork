package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.WorldInfo;

public class CallableLevelGenerator implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelGenerator(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelGeneratorInfo() {
		return String.format("ID %02d - %s, ver %d. Features enabled: %b", new Object[]{Integer.valueOf(WorldInfo.getTerrainTypeOfWorld(this.worldInfoInstance).getWorldTypeID()), WorldInfo.getTerrainTypeOfWorld(this.worldInfoInstance).getWorldTypeName(), Integer.valueOf(WorldInfo.getTerrainTypeOfWorld(this.worldInfoInstance).getGeneratorVersion()), Boolean.valueOf(WorldInfo.getMapFeaturesEnabled(this.worldInfoInstance))});
	}

	public Object call() {
		return this.callLevelGeneratorInfo();
	}
}
