package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldInfo;

public class CallableLevelStorageVersion implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelStorageVersion(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelStorageFormat() {
		String var1 = "Unknown?";

		try {
			switch(WorldInfo.getSaveVersion(this.worldInfoInstance)) {
			case 19132:
				var1 = "McRegion";
				break;
			case 19133:
				var1 = "Anvil";
			}
		} catch (Throwable var3) {
		}

		return String.format("0x%05X - %s", new Object[]{Integer.valueOf(WorldInfo.getSaveVersion(this.worldInfoInstance)), var1});
	}

	public Object call() {
		return this.callLevelStorageFormat();
	}
}
