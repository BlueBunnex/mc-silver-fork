package net.minecraft.src.callable;

import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;

public class CallableModded implements Callable {
	final Minecraft mc;

	public CallableModded(Minecraft var1) {
		this.mc = var1;
	}

	public String getClientProfilerEnabled() {
		String var1 = ClientBrandRetriever.getClientModName();
		return !var1.equals("vanilla") ? "Definitely; Client brand changed to \'" + var1 + "\'" : (Minecraft.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and client brand is untouched.");
	}

	public Object call() {
		return this.getClientProfilerEnabled();
	}
}
