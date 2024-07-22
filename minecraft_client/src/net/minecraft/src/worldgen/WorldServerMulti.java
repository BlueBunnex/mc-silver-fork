package net.minecraft.src.worldgen;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.DerivedWorldInfo;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.ISaveHandler;
import net.minecraft.src.MinecraftException;
import net.minecraft.src.Profiler;

public class WorldServerMulti extends WorldServer {
	public WorldServerMulti(MinecraftServer var1, ISaveHandler var2, String var3, int var4, WorldSettings var5, WorldServer var6, Profiler var7, ILogAgent var8) {
		super(var1, var2, var3, var4, var5, var7, var8);
		this.mapStorage = var6.mapStorage;
		this.worldScoreboard = var6.getScoreboard();
		this.worldInfo = new DerivedWorldInfo(var6.getWorldInfo());
	}

	protected void saveLevel() throws MinecraftException {
	}
}
