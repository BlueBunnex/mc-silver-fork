package net.minecraft.src;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class GameWindowListener extends WindowAdapter {
	public void windowClosing(WindowEvent var1) {
		System.err.println("Someone is closing me!");
		System.exit(1);
	}
}
