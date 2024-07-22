package net.minecraft.src;

import java.applet.Applet;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MinecraftFakeLauncher extends Applet implements AppletStub {
	final Map arguments;

	public MinecraftFakeLauncher(Map var1) {
		this.arguments = var1;
	}

	public void appletResize(int var1, int var2) {
	}

	public boolean isActive() {
		return true;
	}

	public URL getDocumentBase() {
		try {
			return new URL("http://www.minecraft.net/game/");
		} catch (MalformedURLException var2) {
			var2.printStackTrace();
			return null;
		}
	}

	public String getParameter(String var1) {
		if(this.arguments.containsKey(var1)) {
			return (String)this.arguments.get(var1);
		} else {
			System.err.println("Client asked for parameter: " + var1);
			return null;
		}
	}
}
