package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableLvl3 implements Callable {
	final World theWorld;

	CallableLvl3(World var1) {
		this.theWorld = var1;
	}

	public String getChunkProvider() {
		return this.theWorld.chunkProvider.makeString();
	}

	public Object call() {
		return this.getChunkProvider();
	}
}
