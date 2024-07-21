package net.minecraft.src.worldgen;

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.entity.EntityGhast;
import net.minecraft.src.entity.EntityMagmaCube;
import net.minecraft.src.entity.EntityPigZombie;

public class BiomeGenHell extends BiomeGenBase {
	public BiomeGenHell(int var1) {
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	}
}
