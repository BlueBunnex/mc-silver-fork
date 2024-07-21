package net.minecraft.src;

class GuiBeaconButtonCancel extends GuiBeaconButton {
	final GuiBeacon beaconGui;

	public GuiBeaconButtonCancel(GuiBeacon var1, int var2, int var3, int var4) {
		super(var2, var3, var4, "/gui/beacon.png", 112, 220);
		this.beaconGui = var1;
	}

	public void func_82251_b(int var1, int var2) {
		this.beaconGui.drawCreativeTabHoveringText(StatCollector.translateToLocal("gui.cancel"), var1, var2);
	}
}
