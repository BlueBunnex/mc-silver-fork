package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityEnderCrystal;
import net.minecraft.src.entity.ModelBase;
import net.minecraft.src.entity.ModelEnderCrystal;

public class RenderEnderCrystal extends Render {
	private int field_76996_a = -1;
	private ModelBase field_76995_b;

	public RenderEnderCrystal() {
		this.shadowSize = 0.5F;
	}

	public void doRenderEnderCrystal(EntityEnderCrystal var1, double var2, double var4, double var6, float var8, float var9) {
		if(this.field_76996_a != 1) {
			this.field_76995_b = new ModelEnderCrystal(0.0F, true);
			this.field_76996_a = 1;
		}

		float var10 = (float)var1.innerRotation + var9;
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2, (float)var4, (float)var6);
		this.loadTexture("/mob/enderdragon/crystal.png");
		float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
		var11 += var11 * var11;
		this.field_76995_b.render(var1, 0.0F, var10 * 3.0F, var11 * 0.2F, 0.0F, 0.0F, 1.0F / 16.0F);
		GL11.glPopMatrix();
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.doRenderEnderCrystal((EntityEnderCrystal)var1, var2, var4, var6, var8, var9);
	}
}
