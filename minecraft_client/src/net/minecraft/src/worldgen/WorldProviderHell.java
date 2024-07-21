package net.minecraft.src.worldgen;

import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.Vec3;

public class WorldProviderHell extends WorldProvider {
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
		this.isHellWorld = true;
		this.hasNoSky = true;
		this.dimensionId = -1;
	}

	public Vec3 getFogColor(float var1, float var2) {
		return this.worldObj.getWorldVec3Pool().getVecFromPool((double)0.2F, (double)0.03F, (double)0.03F);
	}

	protected void generateLightBrightnessTable() {
		float var1 = 0.1F;

		for(int var2 = 0; var2 <= 15; ++var2) {
			float var3 = 1.0F - (float)var2 / 15.0F;
			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}

	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
	}

	public boolean isSurfaceWorld() {
		return false;
	}

	public boolean canCoordinateBeSpawn(int var1, int var2) {
		return false;
	}

	public float calculateCelestialAngle(long var1, float var3) {
		return 0.5F;
	}

	public boolean canRespawnHere() {
		return false;
	}

	public boolean doesXZShowFog(int var1, int var2) {
		return true;
	}

	public String getDimensionName() {
		return "Nether";
	}
}
