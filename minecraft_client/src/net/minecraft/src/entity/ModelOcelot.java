package net.minecraft.src.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.MathHelper;

public class ModelOcelot extends ModelBase {
	ModelRenderer ocelotBackLeftLeg;
	ModelRenderer ocelotBackRightLeg;
	ModelRenderer ocelotFrontLeftLeg;
	ModelRenderer ocelotFrontRightLeg;
	ModelRenderer ocelotTail;
	ModelRenderer ocelotTail2;
	ModelRenderer ocelotHead;
	ModelRenderer ocelotBody;
	int field_78163_i = 1;

	public ModelOcelot() {
		this.setTextureOffset("head.main", 0, 0);
		this.setTextureOffset("head.nose", 0, 24);
		this.setTextureOffset("head.ear1", 0, 10);
		this.setTextureOffset("head.ear2", 6, 10);
		this.ocelotHead = new ModelRenderer(this, "head");
		this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
		this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
		this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
		this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
		this.ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);
		this.ocelotBody = new ModelRenderer(this, 20, 0);
		this.ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
		this.ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);
		this.ocelotTail = new ModelRenderer(this, 0, 15);
		this.ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
		this.ocelotTail.rotateAngleX = 0.9F;
		this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);
		this.ocelotTail2 = new ModelRenderer(this, 4, 15);
		this.ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
		this.ocelotTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
		this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
		this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
		this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
		this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
		this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
		this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
		this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
		this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
		this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
		this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
		this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
		this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
	}

	public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
		if(this.isChild) {
			float var8 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
			GL11.glTranslatef(0.0F, 10.0F * var7, 4.0F * var7);
			this.ocelotHead.render(var7);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
			GL11.glTranslatef(0.0F, 24.0F * var7, 0.0F);
			this.ocelotBody.render(var7);
			this.ocelotBackLeftLeg.render(var7);
			this.ocelotBackRightLeg.render(var7);
			this.ocelotFrontLeftLeg.render(var7);
			this.ocelotFrontRightLeg.render(var7);
			this.ocelotTail.render(var7);
			this.ocelotTail2.render(var7);
			GL11.glPopMatrix();
		} else {
			this.ocelotHead.render(var7);
			this.ocelotBody.render(var7);
			this.ocelotTail.render(var7);
			this.ocelotTail2.render(var7);
			this.ocelotBackLeftLeg.render(var7);
			this.ocelotBackRightLeg.render(var7);
			this.ocelotFrontLeftLeg.render(var7);
			this.ocelotFrontRightLeg.render(var7);
		}

	}

	public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
		this.ocelotHead.rotateAngleX = var5 / (180.0F / (float)Math.PI);
		this.ocelotHead.rotateAngleY = var4 / (180.0F / (float)Math.PI);
		if(this.field_78163_i != 3) {
			this.ocelotBody.rotateAngleX = (float)Math.PI * 0.5F;
			if(this.field_78163_i == 2) {
				this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.0F * var2;
				this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + 0.3F) * 1.0F * var2;
				this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI + 0.3F) * 1.0F * var2;
				this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.0F * var2;
				this.ocelotTail2.rotateAngleX = (float)Math.PI * 0.55F + (float)Math.PI * 0.1F * MathHelper.cos(var1) * var2;
			} else {
				this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.0F * var2;
				this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.0F * var2;
				this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.0F * var2;
				this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.0F * var2;
				if(this.field_78163_i == 1) {
					this.ocelotTail2.rotateAngleX = (float)Math.PI * 0.55F + (float)Math.PI * 0.25F * MathHelper.cos(var1) * var2;
				} else {
					this.ocelotTail2.rotateAngleX = (float)Math.PI * 0.55F + (float)Math.PI * 0.15F * MathHelper.cos(var1) * var2;
				}
			}
		}

	}

	public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4) {
		EntityOcelot var5 = (EntityOcelot)var1;
		this.ocelotBody.rotationPointY = 12.0F;
		this.ocelotBody.rotationPointZ = -10.0F;
		this.ocelotHead.rotationPointY = 15.0F;
		this.ocelotHead.rotationPointZ = -9.0F;
		this.ocelotTail.rotationPointY = 15.0F;
		this.ocelotTail.rotationPointZ = 8.0F;
		this.ocelotTail2.rotationPointY = 20.0F;
		this.ocelotTail2.rotationPointZ = 14.0F;
		this.ocelotFrontLeftLeg.rotationPointY = this.ocelotFrontRightLeg.rotationPointY = 13.8F;
		this.ocelotFrontLeftLeg.rotationPointZ = this.ocelotFrontRightLeg.rotationPointZ = -5.0F;
		this.ocelotBackLeftLeg.rotationPointY = this.ocelotBackRightLeg.rotationPointY = 18.0F;
		this.ocelotBackLeftLeg.rotationPointZ = this.ocelotBackRightLeg.rotationPointZ = 5.0F;
		this.ocelotTail.rotateAngleX = 0.9F;
		if(var5.isSneaking()) {
			++this.ocelotBody.rotationPointY;
			this.ocelotHead.rotationPointY += 2.0F;
			++this.ocelotTail.rotationPointY;
			this.ocelotTail2.rotationPointY += -4.0F;
			this.ocelotTail2.rotationPointZ += 2.0F;
			this.ocelotTail.rotateAngleX = (float)Math.PI * 0.5F;
			this.ocelotTail2.rotateAngleX = (float)Math.PI * 0.5F;
			this.field_78163_i = 0;
		} else if(var5.isSprinting()) {
			this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
			this.ocelotTail2.rotationPointZ += 2.0F;
			this.ocelotTail.rotateAngleX = (float)Math.PI * 0.5F;
			this.ocelotTail2.rotateAngleX = (float)Math.PI * 0.5F;
			this.field_78163_i = 2;
		} else if(var5.isSitting()) {
			this.ocelotBody.rotateAngleX = (float)Math.PI * 0.25F;
			this.ocelotBody.rotationPointY += -4.0F;
			this.ocelotBody.rotationPointZ += 5.0F;
			this.ocelotHead.rotationPointY += -3.3F;
			++this.ocelotHead.rotationPointZ;
			this.ocelotTail.rotationPointY += 8.0F;
			this.ocelotTail.rotationPointZ += -2.0F;
			this.ocelotTail2.rotationPointY += 2.0F;
			this.ocelotTail2.rotationPointZ += -0.8F;
			this.ocelotTail.rotateAngleX = (float)Math.PI * 0.55F;
			this.ocelotTail2.rotateAngleX = (float)Math.PI * 0.85F;
			this.ocelotFrontLeftLeg.rotateAngleX = this.ocelotFrontRightLeg.rotateAngleX = (float)Math.PI * -0.05F;
			this.ocelotFrontLeftLeg.rotationPointY = this.ocelotFrontRightLeg.rotationPointY = 15.8F;
			this.ocelotFrontLeftLeg.rotationPointZ = this.ocelotFrontRightLeg.rotationPointZ = -7.0F;
			this.ocelotBackLeftLeg.rotateAngleX = this.ocelotBackRightLeg.rotateAngleX = (float)Math.PI * -0.5F;
			this.ocelotBackLeftLeg.rotationPointY = this.ocelotBackRightLeg.rotationPointY = 21.0F;
			this.ocelotBackLeftLeg.rotationPointZ = this.ocelotBackRightLeg.rotationPointZ = 1.0F;
			this.field_78163_i = 3;
		} else {
			this.field_78163_i = 1;
		}

	}
}
