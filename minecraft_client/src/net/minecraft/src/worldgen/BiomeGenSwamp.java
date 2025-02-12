package net.minecraft.src.worldgen;

import java.util.Random;

import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.entity.EntitySlime;

public class BiomeGenSwamp extends BiomeGenBase {
	protected BiomeGenSwamp(int var1) {
		super(var1);
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.clayPerChunk = 1;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.waterColorMultiplier = 14745518;
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return this.worldGeneratorSwamp;
	}

	public int getBiomeGrassColor() {
		double var1 = (double)this.getFloatTemperature();
		double var3 = (double)this.getFloatRainfall();
		return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
	}

	public int getBiomeFoliageColor() {
		double var1 = (double)this.getFloatTemperature();
		double var3 = (double)this.getFloatRainfall();
		return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
	}
}
