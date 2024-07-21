package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.structure.MapGenStructure;

public class CallableStructureType implements Callable {
	
	private final MapGenStructure theMapStructureGenerator;

	public CallableStructureType(MapGenStructure var1) {
		this.theMapStructureGenerator = var1;
	}

	public String callStructureType() {
		return this.theMapStructureGenerator.getClass().getCanonicalName();
	}

	public Object call() {
		return this.callStructureType();
	}
}
