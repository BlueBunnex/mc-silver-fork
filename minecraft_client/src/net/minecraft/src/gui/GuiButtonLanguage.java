package net.minecraft.src.gui;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiButtonLanguage extends GuiButton {
	public GuiButtonLanguage(int var1, int var2, int var3) {
		super(var1, var2, var3, 20, 20, "");
	}

	public void drawButton(Minecraft var1, int var2, int var3) {
		if(this.drawButton) {
			var1.renderEngine.bindTexture("/gui/gui.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			boolean var4 = var2 >= this.xPosition && var3 >= this.yPosition && var2 < this.xPosition + this.width && var3 < this.yPosition + this.height;
			int var5 = 106;
			if(var4) {
				var5 += this.height;
			}

			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, var5, this.width, this.height);
		}
	}
}
