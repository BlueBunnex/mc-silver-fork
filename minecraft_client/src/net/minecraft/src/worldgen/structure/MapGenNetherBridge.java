package net.minecraft.src.worldgen.structure;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.entity.EntityBlaze;
import net.minecraft.src.entity.EntityMagmaCube;
import net.minecraft.src.entity.EntityPigZombie;
import net.minecraft.src.entity.EntitySkeleton;

public class MapGenNetherBridge extends MapGenStructure {
	
	private List<SpawnListEntry> spawnList = new ArrayList<SpawnListEntry>();

	public MapGenNetherBridge() {
		this.spawnList.add(new SpawnListEntry(EntityBlaze.class, 10, 2, 3));
		this.spawnList.add(new SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
		this.spawnList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
		this.spawnList.add(new SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
	}

	public List getSpawnList() {
		return this.spawnList;
	}

	public boolean canSpawnStructureAtCoords(int var1, int var2) {
		int var3 = var1 >> 4;
		int var4 = var2 >> 4;
		this.rand.setSeed((long)(var3 ^ var4 << 4) ^ this.worldObj.getSeed());
		this.rand.nextInt();
		return this.rand.nextInt(3) != 0 ? false : (var1 != (var3 << 4) + 4 + this.rand.nextInt(8) ? false : var2 == (var4 << 4) + 4 + this.rand.nextInt(8));
	}

	protected StructureStart getStructureStart(int var1, int var2) {
		return new StructureNetherBridgeStart(this.worldObj, this.rand, var1, var2);
	}
}
