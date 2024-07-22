package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldInfo;

public class CallableLevelGamemode implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelGamemode(WorldInfo var1) {
		this.worldInfoInstance = var1;
	}

	public String callLevelGameModeInfo() {
		return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[]{WorldInfo.getGameType(this.worldInfoInstance).getName(), Integer.valueOf(WorldInfo.getGameType(this.worldInfoInstance).getID()), Boolean.valueOf(WorldInfo.func_85117_p(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.func_85131_q(this.worldInfoInstance))});
	}

	public Object call() {
		return this.callLevelGameModeInfo();
	}
}
