package net.minecraft.src.worldgen;

import java.util.Random;

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.entity.EntityWolf;

public class BiomeGenForest extends BiomeGenBase {
	public BiomeGenForest(int var1) {
		super(var1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 2;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(5) == 0 ? this.worldGeneratorForest : (var1.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
	}
}
