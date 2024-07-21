package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.worldgen.structure.MapGenStructure;

public class CallableChunkPosHash implements Callable {
	
	private final int field_85165_a;
	private final int field_85163_b;
	private final MapGenStructure theMapStructureGenerator;

	public CallableChunkPosHash(MapGenStructure var1, int var2, int var3) {
		this.theMapStructureGenerator = var1;
		this.field_85165_a = var2;
		this.field_85163_b = var3;
	}

	public String callChunkPositionHash() {
		return String.valueOf(ChunkCoordIntPair.chunkXZ2Int(this.field_85165_a, this.field_85163_b));
	}

	public Object call() {
		return this.callChunkPositionHash();
	}
}
