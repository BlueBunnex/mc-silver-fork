package net.minecraft.src;

import net.minecraft.src.entity.Entity;

public class ModelEnderman extends ModelBiped {
	public boolean isCarrying = false;
	public boolean isAttacking = false;

	public ModelEnderman() {
		super(0.0F, -14.0F, 64, 32);
		float var1 = -14.0F;
		float var2 = 0.0F;
		this.bipedHeadwear = new ModelRenderer(this, 0, 16);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var2 - 0.5F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + var1, 0.0F);
		this.bipedBody = new ModelRenderer(this, 32, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var2);
		this.bipedBody.setRotationPoint(0.0F, 0.0F + var1, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 56, 0);
		this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, var2);
		this.bipedRightArm.setRotationPoint(-3.0F, 2.0F + var1, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 56, 0);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, var2);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + var1, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 56, 0);
		this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, var2);
		this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + var1, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 56, 0);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, var2);
		this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + var1, 0.0F);
	}

	public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
		super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
		this.bipedHead.showModel = true;
		float var8 = -14.0F;
		this.bipedBody.rotateAngleX = 0.0F;
		this.bipedBody.rotationPointY = var8;
		this.bipedBody.rotationPointZ = -0.0F;
		this.bipedRightLeg.rotateAngleX -= 0.0F;
		this.bipedLeftLeg.rotateAngleX -= 0.0F;
		this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX * 0.5D);
		this.bipedLeftArm.rotateAngleX = (float)((double)this.bipedLeftArm.rotateAngleX * 0.5D);
		this.bipedRightLeg.rotateAngleX = (float)((double)this.bipedRightLeg.rotateAngleX * 0.5D);
		this.bipedLeftLeg.rotateAngleX = (float)((double)this.bipedLeftLeg.rotateAngleX * 0.5D);
		float var9 = 0.4F;
		if(this.bipedRightArm.rotateAngleX > var9) {
			this.bipedRightArm.rotateAngleX = var9;
		}

		if(this.bipedLeftArm.rotateAngleX > var9) {
			this.bipedLeftArm.rotateAngleX = var9;
		}

		if(this.bipedRightArm.rotateAngleX < -var9) {
			this.bipedRightArm.rotateAngleX = -var9;
		}

		if(this.bipedLeftArm.rotateAngleX < -var9) {
			this.bipedLeftArm.rotateAngleX = -var9;
		}

		if(this.bipedRightLeg.rotateAngleX > var9) {
			this.bipedRightLeg.rotateAngleX = var9;
		}

		if(this.bipedLeftLeg.rotateAngleX > var9) {
			this.bipedLeftLeg.rotateAngleX = var9;
		}

		if(this.bipedRightLeg.rotateAngleX < -var9) {
			this.bipedRightLeg.rotateAngleX = -var9;
		}

		if(this.bipedLeftLeg.rotateAngleX < -var9) {
			this.bipedLeftLeg.rotateAngleX = -var9;
		}

		if(this.isCarrying) {
			this.bipedRightArm.rotateAngleX = -0.5F;
			this.bipedLeftArm.rotateAngleX = -0.5F;
			this.bipedRightArm.rotateAngleZ = 0.05F;
			this.bipedLeftArm.rotateAngleZ = -0.05F;
		}

		this.bipedRightArm.rotationPointZ = 0.0F;
		this.bipedLeftArm.rotationPointZ = 0.0F;
		this.bipedRightLeg.rotationPointZ = 0.0F;
		this.bipedLeftLeg.rotationPointZ = 0.0F;
		this.bipedRightLeg.rotationPointY = 9.0F + var8;
		this.bipedLeftLeg.rotationPointY = 9.0F + var8;
		this.bipedHead.rotationPointZ = -0.0F;
		this.bipedHead.rotationPointY = var8 + 1.0F;
		this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
		this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
		this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
		this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
		this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;
		if(this.isAttacking) {
			float var10 = 1.0F;
			this.bipedHead.rotationPointY -= var10 * 5.0F;
		}

	}
}
