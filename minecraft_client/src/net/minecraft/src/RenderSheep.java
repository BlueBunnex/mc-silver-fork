package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntitySheep;
import net.minecraft.src.entity.ModelBase;

public class RenderSheep extends RenderLiving {
	public RenderSheep(ModelBase var1, ModelBase var2, float var3) {
		super(var1, var3);
		this.setRenderPassModel(var2);
	}

	protected int setWoolColorAndRender(EntitySheep var1, int var2, float var3) {
		if(var2 == 0 && !var1.getSheared()) {
			this.loadTexture("/mob/sheep_fur.png");
			float var4 = 1.0F;
			int var5 = var1.getFleeceColor();
			GL11.glColor3f(var4 * EntitySheep.fleeceColorTable[var5][0], var4 * EntitySheep.fleeceColorTable[var5][1], var4 * EntitySheep.fleeceColorTable[var5][2]);
			return 1;
		} else {
			return -1;
		}
	}

	protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.setWoolColorAndRender((EntitySheep)var1, var2, var3);
	}
}
