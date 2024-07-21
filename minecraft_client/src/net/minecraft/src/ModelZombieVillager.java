package net.minecraft.src;

import net.minecraft.src.entity.Entity;

public class ModelZombieVillager extends ModelBiped {
	public ModelZombieVillager() {
		this(0.0F, 0.0F, false);
	}

	public ModelZombieVillager(float var1, float var2, boolean var3) {
		super(var1, 0.0F, 64, var3 ? 32 : 64);
		if(var3) {
			this.bipedHead = new ModelRenderer(this, 0, 0);
			this.bipedHead.addBox(-4.0F, -10.0F, -4.0F, 8, 6, 8, var1);
			this.bipedHead.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
		} else {
			this.bipedHead = new ModelRenderer(this);
			this.bipedHead.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
			this.bipedHead.setTextureOffset(0, 32).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, var1);
			this.bipedHead.setTextureOffset(24, 32).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, var1);
		}

	}

	public int func_82897_a() {
		return 10;
	}

	public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
		super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
		float var8 = MathHelper.sin(this.onGround * (float)Math.PI);
		float var9 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * (float)Math.PI);
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F);
		this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F;
		this.bipedRightArm.rotateAngleX = (float)Math.PI * -0.5F;
		this.bipedLeftArm.rotateAngleX = (float)Math.PI * -0.5F;
		this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
		this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
		this.bipedRightArm.rotateAngleZ += MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(var3 * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(var3 * 0.067F) * 0.05F;
	}
}
