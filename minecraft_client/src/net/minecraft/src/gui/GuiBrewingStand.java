package net.minecraft.src.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.ContainerBrewingStand;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.entity.tile.TileEntityBrewingStand;

public class GuiBrewingStand extends GuiContainer {
	private TileEntityBrewingStand brewingStand;

	public GuiBrewingStand(InventoryPlayer var1, TileEntityBrewingStand var2) {
		super(new ContainerBrewingStand(var1, var2));
		this.brewingStand = var2;
	}

	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		String var3 = this.brewingStand.isInvNameLocalized() ? this.brewingStand.getInvName() : StatCollector.translateToLocal(this.brewingStand.getInvName());
		this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/gui/alchemy.png");
		int var4 = (this.width - this.xSize) / 2;
		int var5 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
		int var6 = this.brewingStand.getBrewTime();
		if(var6 > 0) {
			int var7 = (int)(28.0F * (1.0F - (float)var6 / 400.0F));
			if(var7 > 0) {
				this.drawTexturedModalRect(var4 + 97, var5 + 16, 176, 0, 9, var7);
			}

			int var8 = var6 / 2 % 7;
			switch(var8) {
			case 0:
				var7 = 29;
				break;
			case 1:
				var7 = 24;
				break;
			case 2:
				var7 = 20;
				break;
			case 3:
				var7 = 16;
				break;
			case 4:
				var7 = 11;
				break;
			case 5:
				var7 = 6;
				break;
			case 6:
				var7 = 0;
			}

			if(var7 > 0) {
				this.drawTexturedModalRect(var4 + 65, var5 + 14 + 29 - var7, 185, 29 - var7, 12, var7);
			}
		}

	}
}
