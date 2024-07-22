package net.minecraft.src.worldgen.structure;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.ChunkPosition;

public class ComponentStrongholdStairs2 extends ComponentStrongholdStairs {
	public StructureStrongholdPieceWeight strongholdPieceWeight;
	public ComponentStrongholdPortalRoom strongholdPortalRoom;
	public ArrayList field_75026_c = new ArrayList();

	public ComponentStrongholdStairs2(int var1, Random var2, int var3, int var4) {
		super(0, var2, var3, var4);
	}

	public ChunkPosition getCenter() {
		return this.strongholdPortalRoom != null ? this.strongholdPortalRoom.getCenter() : super.getCenter();
	}
}
