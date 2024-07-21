package net.minecraft.src;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.callable.CallableChunkPosHash;
import net.minecraft.src.callable.CallableIsFeatureChunk;
import net.minecraft.src.callable.CallableStructureType;
import net.minecraft.src.worldgen.World;

public abstract class MapGenStructure extends MapGenBase {
	
	protected Map structureMap = new HashMap();

	protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, byte[] var6) {
		if(!this.structureMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(var2, var3)))) {
			this.rand.nextInt();

			try {
				if(this.canSpawnStructureAtCoords(var2, var3)) {
					StructureStart var7 = this.getStructureStart(var2, var3);
					this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(var2, var3)), var7);
				}

			} catch (Throwable var10) {
				CrashReport var8 = CrashReport.makeCrashReport(var10, "Exception preparing structure feature");
				CrashReportCategory var9 = var8.makeCategory("Feature being prepared");
				var9.addCrashSectionCallable("Is feature chunk", new CallableIsFeatureChunk(this, var2, var3));
				var9.addCrashSection("Chunk location", String.format("%d,%d", new Object[]{Integer.valueOf(var2), Integer.valueOf(var3)}));
				var9.addCrashSectionCallable("Chunk pos hash", new CallableChunkPosHash(this, var2, var3));
				var9.addCrashSectionCallable("Structure type", new CallableStructureType(this));
				throw new ReportedException(var8);
			}
		}
	}

	public boolean generateStructuresInChunk(World var1, Random var2, int var3, int var4) {
		int var5 = (var3 << 4) + 8;
		int var6 = (var4 << 4) + 8;
		boolean var7 = false;
		Iterator var8 = this.structureMap.values().iterator();

		while(var8.hasNext()) {
			StructureStart var9 = (StructureStart)var8.next();
			if(var9.isSizeableStructure() && var9.getBoundingBox().intersectsWith(var5, var6, var5 + 15, var6 + 15)) {
				var9.generateStructure(var1, var2, new StructureBoundingBox(var5, var6, var5 + 15, var6 + 15));
				var7 = true;
			}
		}

		return var7;
	}

	public boolean hasStructureAt(int var1, int var2, int var3) {
		Iterator var4 = this.structureMap.values().iterator();

		while(true) {
			StructureStart var5;
			do {
				do {
					if(!var4.hasNext()) {
						return false;
					}

					var5 = (StructureStart)var4.next();
				} while(!var5.isSizeableStructure());
			} while(!var5.getBoundingBox().intersectsWith(var1, var3, var1, var3));

			Iterator var6 = var5.getComponents().iterator();

			while(var6.hasNext()) {
				StructureComponent var7 = (StructureComponent)var6.next();
				if(var7.getBoundingBox().isVecInside(var1, var2, var3)) {
					return true;
				}
			}
		}
	}

	public ChunkPosition getNearestInstance(World var1, int var2, int var3, int var4) {
		this.worldObj = var1;
		this.rand.setSeed(var1.getSeed());
		long var5 = this.rand.nextLong();
		long var7 = this.rand.nextLong();
		long var9 = (long)(var2 >> 4) * var5;
		long var11 = (long)(var4 >> 4) * var7;
		this.rand.setSeed(var9 ^ var11 ^ var1.getSeed());
		this.recursiveGenerate(var1, var2 >> 4, var4 >> 4, 0, 0, (byte[])null);
		double var13 = Double.MAX_VALUE;
		ChunkPosition var15 = null;
		Iterator var16 = this.structureMap.values().iterator();

		ChunkPosition var19;
		int var20;
		int var21;
		int var22;
		double var23;
		while(var16.hasNext()) {
			StructureStart var17 = (StructureStart)var16.next();
			if(var17.isSizeableStructure()) {
				StructureComponent var18 = (StructureComponent)var17.getComponents().get(0);
				var19 = var18.getCenter();
				var20 = var19.x - var2;
				var21 = var19.y - var3;
				var22 = var19.z - var4;
				var23 = (double)(var20 + var20 * var21 * var21 + var22 * var22);
				if(var23 < var13) {
					var13 = var23;
					var15 = var19;
				}
			}
		}

		if(var15 != null) {
			return var15;
		} else {
			List var25 = this.getCoordList();
			if(var25 != null) {
				ChunkPosition var26 = null;
				Iterator var27 = var25.iterator();

				while(var27.hasNext()) {
					var19 = (ChunkPosition)var27.next();
					var20 = var19.x - var2;
					var21 = var19.y - var3;
					var22 = var19.z - var4;
					var23 = (double)(var20 + var20 * var21 * var21 + var22 * var22);
					if(var23 < var13) {
						var13 = var23;
						var26 = var19;
					}
				}

				return var26;
			} else {
				return null;
			}
		}
	}

	protected List getCoordList() {
		return null;
	}

	// was previously protected, idk why...?
	public abstract boolean canSpawnStructureAtCoords(int var1, int var2);

	protected abstract StructureStart getStructureStart(int var1, int var2);
}
