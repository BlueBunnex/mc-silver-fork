package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.structure.MapGenStructure;

public class CallableIsFeatureChunk implements Callable {
	
	private final int field_85169_a;
	private final int field_85167_b;
	private final MapGenStructure theMapStructureGenerator;

	public CallableIsFeatureChunk(MapGenStructure var1, int var2, int var3) {
		this.theMapStructureGenerator = var1;
		this.field_85169_a = var2;
		this.field_85167_b = var3;
	}

	public String func_85166_a() {
		return this.theMapStructureGenerator.canSpawnStructureAtCoords(this.field_85169_a, this.field_85167_b) ? "True" : "False";
	}

	public Object call() {
		return this.func_85166_a();
	}
}
