package net.minecraft.src.gui;

import java.util.List;

import net.minecraft.src.StatCollector;
import net.minecraft.src.StatList;
import net.minecraft.src.worldgen.WorldClient;

public class GuiIngameMenu extends GuiScreen {
	private int updateCounter2 = 0;
	private int updateCounter = 0;

	public void initGui() {
		this.updateCounter2 = 0;
		this.buttonList.clear();
		byte var1 = -16;
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, StatCollector.translateToLocal("menu.returnToMenu")));
		if(!this.mc.isIntegratedServerRunning()) {
			((GuiButton)this.buttonList.get(0)).displayString = StatCollector.translateToLocal("menu.disconnect");
		}

		this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, StatCollector.translateToLocal("menu.returnToGame")));
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, StatCollector.translateToLocal("menu.options")));
		List var10000 = this.buttonList;
		GuiButton var3 = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, StatCollector.translateToLocal("menu.shareToLan"));
		var10000.add(var3);
		this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + var1, 98, 20, StatCollector.translateToLocal("gui.achievements")));
		this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + var1, 98, 20, StatCollector.translateToLocal("gui.stats")));
		var3.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
	}

	protected void actionPerformed(GuiButton var1) {
		switch(var1.id) {
		case 0:
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
			break;
		case 1:
			var1.enabled = false;
			this.mc.statFileWriter.readStat(StatList.leaveGameStat, 1);
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient)null);
			this.mc.displayGuiScreen(new GuiMainMenu());
		case 2:
		case 3:
		default:
			break;
		case 4:
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
			this.mc.sndManager.resumeAllSounds();
			break;
		case 5:
			this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
			break;
		case 6:
			this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter));
			break;
		case 7:
			this.mc.displayGuiScreen(new GuiShareToLan(this));
		}

	}

	public void updateScreen() {
		super.updateScreen();
		++this.updateCounter;
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Game menu", this.width / 2, 40, 16777215);
		super.drawScreen(var1, var2, var3);
	}
}
