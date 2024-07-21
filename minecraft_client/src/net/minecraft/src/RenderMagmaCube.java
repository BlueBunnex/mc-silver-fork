package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityMagmaCube;
import net.minecraft.src.entity.ModelMagmaCube;

import org.lwjgl.opengl.GL11;

public class RenderMagmaCube extends RenderLiving {
	private int field_77120_a = ((ModelMagmaCube)this.mainModel).func_78107_a();

	public RenderMagmaCube() {
		super(new ModelMagmaCube(), 0.25F);
	}

	public void renderMagmaCube(EntityMagmaCube var1, double var2, double var4, double var6, float var8, float var9) {
		int var10 = ((ModelMagmaCube)this.mainModel).func_78107_a();
		if(var10 != this.field_77120_a) {
			this.field_77120_a = var10;
			this.mainModel = new ModelMagmaCube();
			Minecraft.getMinecraft().getLogAgent().logInfo("Loaded new lava slime model");
		}

		super.doRenderLiving(var1, var2, var4, var6, var8, var9);
	}

	protected void scaleMagmaCube(EntityMagmaCube var1, float var2) {
		int var3 = var1.getSlimeSize();
		float var4 = (var1.field_70812_c + (var1.field_70811_b - var1.field_70812_c) * var2) / ((float)var3 * 0.5F + 1.0F);
		float var5 = 1.0F / (var4 + 1.0F);
		float var6 = (float)var3;
		GL11.glScalef(var5 * var6, 1.0F / var5 * var6, var5 * var6);
	}

	protected void preRenderCallback(EntityLiving var1, float var2) {
		this.scaleMagmaCube((EntityMagmaCube)var1, var2);
	}

	public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderMagmaCube((EntityMagmaCube)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderMagmaCube((EntityMagmaCube)var1, var2, var4, var6, var8, var9);
	}
}
