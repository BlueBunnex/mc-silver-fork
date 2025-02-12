package net.minecraft.src.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import org.lwjgl.opengl.GL11;

import net.minecraft.src.ContainerBeacon;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.StatCollector;
import net.minecraft.src.entity.tile.TileEntityBeacon;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;

public class GuiBeacon extends GuiContainer {
	private TileEntityBeacon beacon;
	private GuiBeaconButtonConfirm beaconConfirmButton;
	private boolean buttonsNotDrawn;

	public GuiBeacon(InventoryPlayer var1, TileEntityBeacon var2) {
		super(new ContainerBeacon(var1, var2));
		this.beacon = var2;
		this.xSize = 230;
		this.ySize = 219;
	}

	public void initGui() {
		super.initGui();
		this.buttonList.add(this.beaconConfirmButton = new GuiBeaconButtonConfirm(this, -1, this.guiLeft + 164, this.guiTop + 107));
		this.buttonList.add(new GuiBeaconButtonCancel(this, -2, this.guiLeft + 190, this.guiTop + 107));
		this.buttonsNotDrawn = true;
		this.beaconConfirmButton.enabled = false;
	}

	public void updateScreen() {
		super.updateScreen();
		if(this.buttonsNotDrawn && this.beacon.getLevels() >= 0) {
			this.buttonsNotDrawn = false;

			int var2;
			int var3;
			int var4;
			int var5;
			GuiBeaconButtonPower var6;
			for(int var1 = 0; var1 <= 2; ++var1) {
				var2 = TileEntityBeacon.effectsList[var1].length;
				var3 = var2 * 22 + (var2 - 1) * 2;

				for(var4 = 0; var4 < var2; ++var4) {
					var5 = TileEntityBeacon.effectsList[var1][var4].id;
					var6 = new GuiBeaconButtonPower(this, var1 << 8 | var5, this.guiLeft + 76 + var4 * 24 - var3 / 2, this.guiTop + 22 + var1 * 25, var5, var1);
					this.buttonList.add(var6);
					if(var1 >= this.beacon.getLevels()) {
						var6.enabled = false;
					} else if(var5 == this.beacon.getPrimaryEffect()) {
						var6.func_82254_b(true);
					}
				}
			}

			byte var7 = 3;
			var2 = TileEntityBeacon.effectsList[var7].length + 1;
			var3 = var2 * 22 + (var2 - 1) * 2;

			for(var4 = 0; var4 < var2 - 1; ++var4) {
				var5 = TileEntityBeacon.effectsList[var7][var4].id;
				var6 = new GuiBeaconButtonPower(this, var7 << 8 | var5, this.guiLeft + 167 + var4 * 24 - var3 / 2, this.guiTop + 47, var5, var7);
				this.buttonList.add(var6);
				if(var7 >= this.beacon.getLevels()) {
					var6.enabled = false;
				} else if(var5 == this.beacon.getSecondaryEffect()) {
					var6.func_82254_b(true);
				}
			}

			if(this.beacon.getPrimaryEffect() > 0) {
				GuiBeaconButtonPower var8 = new GuiBeaconButtonPower(this, var7 << 8 | this.beacon.getPrimaryEffect(), this.guiLeft + 167 + (var2 - 1) * 24 - var3 / 2, this.guiTop + 47, this.beacon.getPrimaryEffect(), var7);
				this.buttonList.add(var8);
				if(var7 >= this.beacon.getLevels()) {
					var8.enabled = false;
				} else if(this.beacon.getPrimaryEffect() == this.beacon.getSecondaryEffect()) {
					var8.func_82254_b(true);
				}
			}
		}

		this.beaconConfirmButton.enabled = this.beacon.getStackInSlot(0) != null && this.beacon.getPrimaryEffect() > 0;
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == -2) {
			this.mc.displayGuiScreen((GuiScreen)null);
		} else if(var1.id == -1) {
			String var2 = "MC|Beacon";
			ByteArrayOutputStream var3 = new ByteArrayOutputStream();
			DataOutputStream var4 = new DataOutputStream(var3);

			try {
				var4.writeInt(this.beacon.getPrimaryEffect());
				var4.writeInt(this.beacon.getSecondaryEffect());
				this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(var2, var3.toByteArray()));
			} catch (Exception var6) {
				var6.printStackTrace();
			}

			this.mc.displayGuiScreen((GuiScreen)null);
		} else if(var1 instanceof GuiBeaconButtonPower) {
			if(((GuiBeaconButtonPower)var1).func_82255_b()) {
				return;
			}

			int var7 = var1.id;
			int var8 = var7 & 255;
			int var9 = var7 >> 8;
			if(var9 < 3) {
				this.beacon.setPrimaryEffect(var8);
			} else {
				this.beacon.setSecondaryEffect(var8);
			}

			this.buttonList.clear();
			this.initGui();
			this.updateScreen();
		}

	}

	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		RenderHelper.disableStandardItemLighting();
		this.drawCenteredString(this.fontRenderer, StatCollector.translateToLocal("tile.beacon.primary"), 62, 10, 14737632);
		this.drawCenteredString(this.fontRenderer, StatCollector.translateToLocal("tile.beacon.secondary"), 169, 10, 14737632);
		Iterator var3 = this.buttonList.iterator();

		while(var3.hasNext()) {
			GuiButton var4 = (GuiButton)var3.next();
			if(var4.func_82252_a()) {
				var4.func_82251_b(var1 - this.guiLeft, var2 - this.guiTop);
				break;
			}
		}

		RenderHelper.enableGUIStandardItemLighting();
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/gui/beacon.png");
		int var4 = (this.width - this.xSize) / 2;
		int var5 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
		itemRenderer.zLevel = 100.0F;
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.emerald), var4 + 42, var5 + 109);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.diamond), var4 + 42 + 22, var5 + 109);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.ingotGold), var4 + 42 + 44, var5 + 109);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.ingotIron), var4 + 42 + 66, var5 + 109);
		itemRenderer.zLevel = 0.0F;
	}
}
