package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.worldgen.World;

public class ChunkProviderClient implements IChunkProvider {
	private Chunk blankChunk;
	private LongHashMap chunkMapping = new LongHashMap();
	private List chunkListing = new ArrayList();
	private World worldObj;

	public ChunkProviderClient(World var1) {
		this.blankChunk = new EmptyChunk(var1, 0, 0);
		this.worldObj = var1;
	}

	public boolean chunkExists(int var1, int var2) {
		return true;
	}

	public void unloadChunk(int var1, int var2) {
		Chunk var3 = this.provideChunk(var1, var2);
		if(!var3.isEmpty()) {
			var3.onChunkUnload();
		}

		this.chunkMapping.remove(ChunkCoordIntPair.chunkXZ2Int(var1, var2));
		this.chunkListing.remove(var3);
	}

	public Chunk loadChunk(int var1, int var2) {
		Chunk var3 = new Chunk(this.worldObj, var1, var2);
		this.chunkMapping.add(ChunkCoordIntPair.chunkXZ2Int(var1, var2), var3);
		var3.isChunkLoaded = true;
		return var3;
	}

	public Chunk provideChunk(int var1, int var2) {
		Chunk var3 = (Chunk)this.chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(var1, var2));
		return var3 == null ? this.blankChunk : var3;
	}

	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	public void func_104112_b() {
	}

	public boolean unloadQueuedChunks() {
		return false;
	}

	public boolean canSave() {
		return false;
	}

	public void populate(IChunkProvider var1, int var2, int var3) {
	}

	public String makeString() {
		return "MultiplayerChunkCache: " + this.chunkMapping.getNumHashElements();
	}

	public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4) {
		return null;
	}

	public ChunkPosition findClosestStructure(World var1, String var2, int var3, int var4, int var5) {
		return null;
	}

	public int getLoadedChunkCount() {
		return this.chunkListing.size();
	}

	public void recreateStructures(int var1, int var2) {
	}
}
