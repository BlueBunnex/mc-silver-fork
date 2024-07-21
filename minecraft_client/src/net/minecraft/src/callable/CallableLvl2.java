package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.worldgen.World;

public class CallableLvl2 implements Callable {
	
	final World theWorld;

	public CallableLvl2(World var1) {
		this.theWorld = var1;
	}

	public String getPlayerEntities() {
		return this.theWorld.playerEntities.size() + " total; " + this.theWorld.playerEntities.toString();
	}

	public Object call() {
		return this.getPlayerEntities();
	}
}
