package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;

public class ModelBat extends ModelBase {
	private ModelRenderer batHead;
	private ModelRenderer batBody;
	private ModelRenderer batRightWing;
	private ModelRenderer batLeftWing;
	private ModelRenderer batOuterRightWing;
	private ModelRenderer batOuterLeftWing;

	public ModelBat() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.batHead = new ModelRenderer(this, 0, 0);
		this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
		ModelRenderer var1 = new ModelRenderer(this, 24, 0);
		var1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
		this.batHead.addChild(var1);
		ModelRenderer var2 = new ModelRenderer(this, 24, 0);
		var2.mirror = true;
		var2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
		this.batHead.addChild(var2);
		this.batBody = new ModelRenderer(this, 0, 16);
		this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
		this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
		this.batRightWing = new ModelRenderer(this, 42, 0);
		this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
		this.batOuterRightWing = new ModelRenderer(this, 24, 16);
		this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
		this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
		this.batLeftWing = new ModelRenderer(this, 42, 0);
		this.batLeftWing.mirror = true;
		this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
		this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
		this.batOuterLeftWing.mirror = true;
		this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
		this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
		this.batBody.addChild(this.batRightWing);
		this.batBody.addChild(this.batLeftWing);
		this.batRightWing.addChild(this.batOuterRightWing);
		this.batLeftWing.addChild(this.batOuterLeftWing);
	}

	public int getBatSize() {
		return 36;
	}

	public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		EntityBat var8 = (EntityBat)var1;
		if(var8.getIsBatHanging()) {
			this.batHead.rotateAngleX = var6 / (180.0F / (float)Math.PI);
			this.batHead.rotateAngleY = (float)Math.PI - var5 / (180.0F / (float)Math.PI);
			this.batHead.rotateAngleZ = (float)Math.PI;
			this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
			this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
			this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
			this.batBody.rotateAngleX = (float)Math.PI;
			this.batRightWing.rotateAngleX = (float)Math.PI * -0.05F;
			this.batRightWing.rotateAngleY = (float)Math.PI * -0.4F;
			this.batOuterRightWing.rotateAngleY = (float)Math.PI * -0.55F;
			this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
			this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
			this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
		} else {
			this.batHead.rotateAngleX = var6 / (180.0F / (float)Math.PI);
			this.batHead.rotateAngleY = var5 / (180.0F / (float)Math.PI);
			this.batHead.rotateAngleZ = 0.0F;
			this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.batBody.rotateAngleX = (float)Math.PI * 0.25F + MathHelper.cos(var4 * 0.1F) * 0.15F;
			this.batBody.rotateAngleY = 0.0F;
			this.batRightWing.rotateAngleY = MathHelper.cos(var4 * 1.3F) * (float)Math.PI * 0.25F;
			this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
			this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
			this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
		}

		this.batHead.render(var7);
		this.batBody.render(var7);
	}
}
