package net.minecraft.src;

import java.io.IOException;

import net.minecraft.src.worldgen.World;

public interface IChunkLoader {
	Chunk loadChunk(World var1, int var2, int var3) throws IOException;

	void saveChunk(World var1, Chunk var2) throws IOException, MinecraftException;

	void saveExtraChunkData(World var1, Chunk var2);

	void chunkTick();

	void saveExtraData();
}
