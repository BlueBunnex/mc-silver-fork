package net.minecraft.src.worldgen;

import java.util.Random;

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityChicken;
import net.minecraft.src.entity.EntityOcelot;

public class BiomeGenJungle extends BiomeGenBase {
	public BiomeGenJungle(int var1) {
		super(var1);
		this.theBiomeDecorator.treesPerChunk = 50;
		this.theBiomeDecorator.grassPerChunk = 25;
		this.theBiomeDecorator.flowersPerChunk = 4;
		this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(10) == 0 ? this.worldGeneratorBigTree : (var1.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (var1.nextInt(3) == 0 ? new WorldGenHugeTrees(false, 10 + var1.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + var1.nextInt(7), 3, 3, true))));
	}

	public WorldGenerator getRandomWorldGenForGrass(Random var1) {
		return var1.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	public void decorate(World var1, Random var2, int var3, int var4) {
		super.decorate(var1, var2, var3, var4);
		WorldGenVines var5 = new WorldGenVines();

		for(int var6 = 0; var6 < 50; ++var6) {
			int var7 = var3 + var2.nextInt(16) + 8;
			byte var8 = 64;
			int var9 = var4 + var2.nextInt(16) + 8;
			var5.generate(var1, var2, var7, var8, var9);
		}

	}
}
