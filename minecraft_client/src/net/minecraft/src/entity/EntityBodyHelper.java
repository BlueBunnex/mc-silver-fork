package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;

public class EntityBodyHelper {
	private EntityLiving theLiving;
	private int field_75666_b = 0;
	private float field_75667_c = 0.0F;

	public EntityBodyHelper(EntityLiving var1) {
		this.theLiving = var1;
	}

	public void func_75664_a() {
		double var1 = this.theLiving.posX - this.theLiving.prevPosX;
		double var3 = this.theLiving.posZ - this.theLiving.prevPosZ;
		if(var1 * var1 + var3 * var3 > (double)2.5000003E-7F) {
			this.theLiving.renderYawOffset = this.theLiving.rotationYaw;
			this.theLiving.rotationYawHead = this.func_75665_a(this.theLiving.renderYawOffset, this.theLiving.rotationYawHead, 75.0F);
			this.field_75667_c = this.theLiving.rotationYawHead;
			this.field_75666_b = 0;
		} else {
			float var5 = 75.0F;
			if(Math.abs(this.theLiving.rotationYawHead - this.field_75667_c) > 15.0F) {
				this.field_75666_b = 0;
				this.field_75667_c = this.theLiving.rotationYawHead;
			} else {
				++this.field_75666_b;
				if(this.field_75666_b > 10) {
					var5 = Math.max(1.0F - (float)(this.field_75666_b - 10) / 10.0F, 0.0F) * 75.0F;
				}
			}

			this.theLiving.renderYawOffset = this.func_75665_a(this.theLiving.rotationYawHead, this.theLiving.renderYawOffset, var5);
		}
	}

	private float func_75665_a(float var1, float var2, float var3) {
		float var4 = MathHelper.wrapAngleTo180_float(var1 - var2);
		if(var4 < -var3) {
			var4 = -var3;
		}

		if(var4 >= var3) {
			var4 = var3;
		}

		return var1 - var4;
	}
}
