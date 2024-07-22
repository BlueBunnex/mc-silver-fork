package net.minecraft.src.gui;

public class GuiPlayerInfo {
	public final String name;
	private final String nameinLowerCase;
	public int responseTime;

	public GuiPlayerInfo(String var1) {
		this.name = var1;
		this.nameinLowerCase = var1.toLowerCase();
	}
}
