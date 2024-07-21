package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.World;

public class CallableLvl3 implements Callable {
	
	final World theWorld;

	public CallableLvl3(World var1) {
		this.theWorld = var1;
	}

	public String getChunkProvider() {
		return this.theWorld.getChunkProvider().makeString();
	}

	public Object call() {
		return this.getChunkProvider();
	}
}
