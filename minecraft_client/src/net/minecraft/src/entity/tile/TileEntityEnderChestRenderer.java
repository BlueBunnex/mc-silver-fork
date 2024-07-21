package net.minecraft.src.entity.tile;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.src.entity.ModelChest;

public class TileEntityEnderChestRenderer extends TileEntitySpecialRenderer {
	private ModelChest theEnderChestModel = new ModelChest();

	public void renderEnderChest(TileEntityEnderChest var1, double var2, double var4, double var6, float var8) {
		int var9 = 0;
		if(var1.func_70309_m()) {
			var9 = var1.getBlockMetadata();
		}

		this.bindTextureByName("/item/enderchest.png");
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short var10 = 0;
		if(var9 == 2) {
			var10 = 180;
		}

		if(var9 == 3) {
			var10 = 0;
		}

		if(var9 == 4) {
			var10 = 90;
		}

		if(var9 == 5) {
			var10 = -90;
		}

		GL11.glRotatef((float)var10, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float var11 = var1.prevLidAngle + (var1.lidAngle - var1.prevLidAngle) * var8;
		var11 = 1.0F - var11;
		var11 = 1.0F - var11 * var11 * var11;
		this.theEnderChestModel.chestLid.rotateAngleX = -(var11 * (float)Math.PI / 2.0F);
		this.theEnderChestModel.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		this.renderEnderChest((TileEntityEnderChest)var1, var2, var4, var6, var8);
	}
}
