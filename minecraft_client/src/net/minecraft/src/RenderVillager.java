package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityVillager;
import net.minecraft.src.entity.ModelVillager;

public class RenderVillager extends RenderLiving {
	protected ModelVillager villagerModel = (ModelVillager)this.mainModel;

	public RenderVillager() {
		super(new ModelVillager(0.0F), 0.5F);
	}

	protected int shouldVillagerRenderPass(EntityVillager var1, int var2, float var3) {
		return -1;
	}

	public void renderVillager(EntityVillager var1, double var2, double var4, double var6, float var8, float var9) {
		super.doRenderLiving(var1, var2, var4, var6, var8, var9);
	}

	protected void renderVillagerEquipedItems(EntityVillager var1, float var2) {
		super.renderEquippedItems(var1, var2);
	}

	protected void preRenderVillager(EntityVillager var1, float var2) {
		float var3 = 15.0F / 16.0F;
		if(var1.getGrowingAge() < 0) {
			var3 = (float)((double)var3 * 0.5D);
			this.shadowSize = 0.25F;
		} else {
			this.shadowSize = 0.5F;
		}

		GL11.glScalef(var3, var3, var3);
	}

	protected void preRenderCallback(EntityLiving var1, float var2) {
		this.preRenderVillager((EntityVillager)var1, var2);
	}

	protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.shouldVillagerRenderPass((EntityVillager)var1, var2, var3);
	}

	protected void renderEquippedItems(EntityLiving var1, float var2) {
		this.renderVillagerEquipedItems((EntityVillager)var1, var2);
	}

	public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderVillager((EntityVillager)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.renderVillager((EntityVillager)var1, var2, var4, var6, var8, var9);
	}
}
