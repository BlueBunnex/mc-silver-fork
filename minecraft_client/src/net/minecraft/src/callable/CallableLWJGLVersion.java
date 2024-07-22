package net.minecraft.src.callable;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;

public class CallableLWJGLVersion implements Callable {
	final Minecraft mc;

	public CallableLWJGLVersion(Minecraft var1) {
		this.mc = var1;
	}

	public String getType() {
		return Sys.getVersion();
	}

	public Object call() {
		return this.getType();
	}
}
