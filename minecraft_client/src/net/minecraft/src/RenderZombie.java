package net.minecraft.src;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityZombie;
import net.minecraft.src.entity.ModelBiped;
import net.minecraft.src.entity.ModelZombie;
import net.minecraft.src.entity.ModelZombieVillager;

public class RenderZombie extends RenderBiped {
	private ModelBiped field_82434_o = this.modelBipedMain;
	private ModelZombieVillager field_82432_p = new ModelZombieVillager();
	protected ModelBiped field_82437_k;
	protected ModelBiped field_82435_l;
	protected ModelBiped field_82436_m;
	protected ModelBiped field_82433_n;
	private int field_82431_q = 1;

	public RenderZombie() {
		super(new ModelZombie(), 0.5F, 1.0F);
	}

	protected void func_82421_b() {
		this.field_82423_g = new ModelZombie(1.0F, true);
		this.field_82425_h = new ModelZombie(0.5F, true);
		this.field_82437_k = this.field_82423_g;
		this.field_82435_l = this.field_82425_h;
		this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
		this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
	}

	protected int func_82429_a(EntityZombie var1, int var2, float var3) {
		this.func_82427_a(var1);
		return super.shouldRenderPass(var1, var2, var3);
	}

	public void func_82426_a(EntityZombie var1, double var2, double var4, double var6, float var8, float var9) {
		this.func_82427_a(var1);
		super.doRenderLiving(var1, var2, var4, var6, var8, var9);
	}

	protected void func_82428_a(EntityZombie var1, float var2) {
		this.func_82427_a(var1);
		super.renderEquippedItems(var1, var2);
	}

	private void func_82427_a(EntityZombie var1) {
		if(var1.isVillager()) {
			if(this.field_82431_q != this.field_82432_p.func_82897_a()) {
				this.field_82432_p = new ModelZombieVillager();
				this.field_82431_q = this.field_82432_p.func_82897_a();
				this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
				this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
			}

			this.mainModel = this.field_82432_p;
			this.field_82423_g = this.field_82436_m;
			this.field_82425_h = this.field_82433_n;
		} else {
			this.mainModel = this.field_82434_o;
			this.field_82423_g = this.field_82437_k;
			this.field_82425_h = this.field_82435_l;
		}

		this.modelBipedMain = (ModelBiped)this.mainModel;
	}

	protected void func_82430_a(EntityZombie var1, float var2, float var3, float var4) {
		if(var1.isConverting()) {
			var3 += (float)(Math.cos((double)var1.ticksExisted * 3.25D) * Math.PI * 0.25D);
		}

		super.rotateCorpse(var1, var2, var3, var4);
	}

	protected void renderEquippedItems(EntityLiving var1, float var2) {
		this.func_82428_a((EntityZombie)var1, var2);
	}

	public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		this.func_82426_a((EntityZombie)var1, var2, var4, var6, var8, var9);
	}

	protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
		return this.func_82429_a((EntityZombie)var1, var2, var3);
	}

	protected void rotateCorpse(EntityLiving var1, float var2, float var3, float var4) {
		this.func_82430_a((EntityZombie)var1, var2, var3, var4);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		this.func_82426_a((EntityZombie)var1, var2, var4, var6, var8, var9);
	}
}
