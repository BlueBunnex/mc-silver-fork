package net.minecraft.src.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.minecraft.src.EnumOptions;
import net.minecraft.src.GameSettings;
import net.minecraft.src.StatCollector;

public class GuiSnooper extends GuiScreen {
	private final GuiScreen snooperGuiScreen;
	private final GameSettings snooperGameSettings;
	private final List field_74098_c = new ArrayList();
	private final List field_74096_d = new ArrayList();
	private String snooperTitle;
	private String[] field_74101_n;
	private GuiSnooperList snooperList;
	private GuiButton buttonAllowSnooping;

	public GuiSnooper(GuiScreen var1, GameSettings var2) {
		this.snooperGuiScreen = var1;
		this.snooperGameSettings = var2;
	}

	public void initGui() {
		this.snooperTitle = StatCollector.translateToLocal("options.snooper.title");
		String var1 = StatCollector.translateToLocal("options.snooper.desc");
		ArrayList var2 = new ArrayList();
		Iterator var3 = this.fontRenderer.listFormattedStringToWidth(var1, this.width - 30).iterator();

		while(var3.hasNext()) {
			String var4 = (String)var3.next();
			var2.add(var4);
		}

		this.field_74101_n = (String[])var2.toArray(new String[0]);
		this.field_74098_c.clear();
		this.field_74096_d.clear();
		this.buttonList.add(this.buttonAllowSnooping = new GuiButton(1, this.width / 2 - 152, this.height - 30, 150, 20, this.snooperGameSettings.getKeyBinding(EnumOptions.SNOOPER_ENABLED)));
		this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height - 30, 150, 20, StatCollector.translateToLocal("gui.done")));
		boolean var6 = this.mc.getIntegratedServer() != null && this.mc.getIntegratedServer().getPlayerUsageSnooper() != null;
		Iterator var7 = (new TreeMap(this.mc.getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

		Entry var5;
		while(var7.hasNext()) {
			var5 = (Entry)var7.next();
			this.field_74098_c.add((var6 ? "C " : "") + (String)var5.getKey());
			this.field_74096_d.add(this.fontRenderer.trimStringToWidth((String)var5.getValue(), this.width - 220));
		}

		if(var6) {
			var7 = (new TreeMap(this.mc.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

			while(var7.hasNext()) {
				var5 = (Entry)var7.next();
				this.field_74098_c.add("S " + (String)var5.getKey());
				this.field_74096_d.add(this.fontRenderer.trimStringToWidth((String)var5.getValue(), this.width - 220));
			}
		}

		this.snooperList = new GuiSnooperList(this);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 2) {
				this.snooperGameSettings.saveOptions();
				this.snooperGameSettings.saveOptions();
				this.mc.displayGuiScreen(this.snooperGuiScreen);
			}

			if(var1.id == 1) {
				this.snooperGameSettings.setOptionValue(EnumOptions.SNOOPER_ENABLED, 1);
				this.buttonAllowSnooping.displayString = this.snooperGameSettings.getKeyBinding(EnumOptions.SNOOPER_ENABLED);
			}

		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		this.snooperList.drawScreen(var1, var2, var3);
		this.drawCenteredString(this.fontRenderer, this.snooperTitle, this.width / 2, 8, 16777215);
		int var4 = 22;
		String[] var5 = this.field_74101_n;
		int var6 = var5.length;

		for(int var7 = 0; var7 < var6; ++var7) {
			String var8 = var5[var7];
			this.drawCenteredString(this.fontRenderer, var8, this.width / 2, var4, 8421504);
			var4 += this.fontRenderer.FONT_HEIGHT;
		}

		super.drawScreen(var1, var2, var3);
	}

	static List func_74095_a(GuiSnooper var0) {
		return var0.field_74098_c;
	}

	static List func_74094_b(GuiSnooper var0) {
		return var0.field_74096_d;
	}
}
