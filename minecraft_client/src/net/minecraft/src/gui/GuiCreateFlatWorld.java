package net.minecraft.src.gui;

import net.minecraft.src.FlatGeneratorInfo;
import net.minecraft.src.RenderItem;
import net.minecraft.src.StatCollector;

public class GuiCreateFlatWorld extends GuiScreen {
	private static RenderItem theRenderItem = new RenderItem();
	private final GuiCreateWorld createWorldGui;
	private FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
	private String customizationTitle;
	private String layerMaterialLabel;
	private String heightLabel;
	private GuiCreateFlatWorldListSlot createFlatWorldListSlotGui;
	private GuiButton buttonAddLayer;
	private GuiButton buttonEditLayer;
	private GuiButton buttonRemoveLayer;

	public GuiCreateFlatWorld(GuiCreateWorld var1, String var2) {
		this.createWorldGui = var1;
		this.setFlatGeneratorInfo(var2);
	}

	public String getFlatGeneratorInfo() {
		return this.theFlatGeneratorInfo.toString();
	}

	public void setFlatGeneratorInfo(String var1) {
		this.theFlatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(var1);
	}

	public void initGui() {
		this.buttonList.clear();
		this.customizationTitle = StatCollector.translateToLocal("createWorld.customize.flat.title");
		this.layerMaterialLabel = StatCollector.translateToLocal("createWorld.customize.flat.tile");
		this.heightLabel = StatCollector.translateToLocal("createWorld.customize.flat.height");
		this.createFlatWorldListSlotGui = new GuiCreateFlatWorldListSlot(this);
		this.buttonList.add(this.buttonAddLayer = new GuiButton(2, this.width / 2 - 154, this.height - 52, 100, 20, StatCollector.translateToLocal("createWorld.customize.flat.addLayer") + " (NYI)"));
		this.buttonList.add(this.buttonEditLayer = new GuiButton(3, this.width / 2 - 50, this.height - 52, 100, 20, StatCollector.translateToLocal("createWorld.customize.flat.editLayer") + " (NYI)"));
		this.buttonList.add(this.buttonRemoveLayer = new GuiButton(4, this.width / 2 - 155, this.height - 52, 150, 20, StatCollector.translateToLocal("createWorld.customize.flat.removeLayer")));
		this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, StatCollector.translateToLocal("gui.done")));
		this.buttonList.add(new GuiButton(5, this.width / 2 + 5, this.height - 52, 150, 20, StatCollector.translateToLocal("createWorld.customize.presets")));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, StatCollector.translateToLocal("gui.cancel")));
		this.buttonAddLayer.drawButton = this.buttonEditLayer.drawButton = false;
		this.theFlatGeneratorInfo.func_82645_d();
		this.func_82270_g();
	}

	protected void actionPerformed(GuiButton var1) {
		int var2 = this.theFlatGeneratorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.field_82454_a - 1;
		if(var1.id == 1) {
			this.mc.displayGuiScreen(this.createWorldGui);
		} else if(var1.id == 0) {
			this.createWorldGui.generatorOptionsToUse = this.getFlatGeneratorInfo();
			this.mc.displayGuiScreen(this.createWorldGui);
		} else if(var1.id == 5) {
			this.mc.displayGuiScreen(new GuiFlatPresets(this));
		} else if(var1.id == 4 && this.func_82272_i()) {
			this.theFlatGeneratorInfo.getFlatLayers().remove(var2);
			this.createFlatWorldListSlotGui.field_82454_a = Math.min(this.createFlatWorldListSlotGui.field_82454_a, this.theFlatGeneratorInfo.getFlatLayers().size() - 1);
		}

		this.theFlatGeneratorInfo.func_82645_d();
		this.func_82270_g();
	}

	public void func_82270_g() {
		boolean var1 = this.func_82272_i();
		this.buttonRemoveLayer.enabled = var1;
		this.buttonEditLayer.enabled = var1;
		this.buttonEditLayer.enabled = false;
		this.buttonAddLayer.enabled = false;
	}

	private boolean func_82272_i() {
		return this.createFlatWorldListSlotGui.field_82454_a > -1 && this.createFlatWorldListSlotGui.field_82454_a < this.theFlatGeneratorInfo.getFlatLayers().size();
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		this.createFlatWorldListSlotGui.drawScreen(var1, var2, var3);
		this.drawCenteredString(this.fontRenderer, this.customizationTitle, this.width / 2, 8, 16777215);
		int var4 = this.width / 2 - 92 - 16;
		this.drawString(this.fontRenderer, this.layerMaterialLabel, var4, 32, 16777215);
		this.drawString(this.fontRenderer, this.heightLabel, var4 + 2 + 213 - this.fontRenderer.getStringWidth(this.heightLabel), 32, 16777215);
		super.drawScreen(var1, var2, var3);
	}

	static RenderItem getRenderItem() {
		return theRenderItem;
	}

	static FlatGeneratorInfo func_82271_a(GuiCreateFlatWorld var0) {
		return var0.theFlatGeneratorInfo;
	}
}
