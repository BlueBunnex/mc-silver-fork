package net.minecraft.src.worldgen;

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityMooshroom;

public class BiomeGenMushroomIsland extends BiomeGenBase {
	public BiomeGenMushroomIsland(int var1) {
		super(var1);
		this.theBiomeDecorator.treesPerChunk = -100;
		this.theBiomeDecorator.flowersPerChunk = -100;
		this.theBiomeDecorator.grassPerChunk = -100;
		this.theBiomeDecorator.mushroomsPerChunk = 1;
		this.theBiomeDecorator.bigMushroomsPerChunk = 1;
		this.topBlock = (byte)Block.mycelium.blockID;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
	}
}
