package net.minecraft.src.worldgen;

import java.util.Random;

import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;

public class BiomeGenTaiga extends BiomeGenBase {
	public BiomeGenTaiga(int var1) {
		super(var1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 1;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
	}
}
