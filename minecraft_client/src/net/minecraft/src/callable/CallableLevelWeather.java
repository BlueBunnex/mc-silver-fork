package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.WorldInfo;

public class CallableLevelWeather implements Callable {
	
	final WorldInfo worldInfoInstance;

	public CallableLevelWeather(WorldInfo worldInfo) {
		this.worldInfoInstance = worldInfo;
	}

	public String callLevelWeatherInfo() {
		return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", new Object[]{Integer.valueOf(WorldInfo.getRainTime(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.getRaining(this.worldInfoInstance)), Integer.valueOf(WorldInfo.getThunderTime(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.getThundering(this.worldInfoInstance))});
	}

	public Object call() {
		return this.callLevelWeatherInfo();
	}
}
