package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.entity.Entity;

public class TileEntityMobSpawnerRenderer extends TileEntitySpecialRenderer {
	public void renderTileEntityMobSpawner(TileEntityMobSpawner var1, double var2, double var4, double var6, float var8) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2 + 0.5F, (float)var4, (float)var6 + 0.5F);
		func_98144_a(var1.func_98049_a(), var2, var4, var6, var8);
		GL11.glPopMatrix();
	}

	public static void func_98144_a(MobSpawnerBaseLogic var0, double var1, double var3, double var5, float var7) {
		Entity var8 = var0.func_98281_h();
		if(var8 != null) {
			var8.setWorld(var0.getSpawnerWorld());
			float var9 = 7.0F / 16.0F;
			GL11.glTranslatef(0.0F, 0.4F, 0.0F);
			GL11.glRotatef((float)(var0.field_98284_d + (var0.field_98287_c - var0.field_98284_d) * (double)var7) * 10.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.0F, -0.4F, 0.0F);
			GL11.glScalef(var9, var9, var9);
			var8.setLocationAndAngles(var1, var3, var5, 0.0F, 0.0F);
			RenderManager.instance.renderEntityWithPosYaw(var8, 0.0D, 0.0D, 0.0D, 0.0F, var7);
		}

	}

	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		this.renderTileEntityMobSpawner((TileEntityMobSpawner)var1, var2, var4, var6, var8);
	}
}
