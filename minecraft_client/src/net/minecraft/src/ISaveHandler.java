package net.minecraft.src;

import java.io.File;

import net.minecraft.src.worldgen.WorldInfo;
import net.minecraft.src.worldgen.WorldProvider;

public interface ISaveHandler {
	WorldInfo loadWorldInfo();

	void checkSessionLock() throws MinecraftException;

	IChunkLoader getChunkLoader(WorldProvider var1);

	void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2);

	void saveWorldInfo(WorldInfo var1);

	IPlayerFileData getSaveHandler();

	void flush();

	File getMapFileFromName(String var1);

	String getWorldDirectoryName();
}
