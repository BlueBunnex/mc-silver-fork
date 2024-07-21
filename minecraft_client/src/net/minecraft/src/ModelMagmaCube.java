package net.minecraft.src;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityMagmaCube;

public class ModelMagmaCube extends ModelBase {
	ModelRenderer[] field_78109_a = new ModelRenderer[8];
	ModelRenderer field_78108_b;

	public ModelMagmaCube() {
		for(int var1 = 0; var1 < this.field_78109_a.length; ++var1) {
			byte var2 = 0;
			int var3 = var1;
			if(var1 == 2) {
				var2 = 24;
				var3 = 10;
			} else if(var1 == 3) {
				var2 = 24;
				var3 = 19;
			}

			this.field_78109_a[var1] = new ModelRenderer(this, var2, var3);
			this.field_78109_a[var1].addBox(-4.0F, (float)(16 + var1), -4.0F, 8, 1, 8);
		}

		this.field_78108_b = new ModelRenderer(this, 0, 16);
		this.field_78108_b.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
	}

	public int func_78107_a() {
		return 5;
	}

	public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4) {
		EntityMagmaCube var5 = (EntityMagmaCube)var1;
		float var6 = var5.field_70812_c + (var5.field_70811_b - var5.field_70812_c) * var4;
		if(var6 < 0.0F) {
			var6 = 0.0F;
		}

		for(int var7 = 0; var7 < this.field_78109_a.length; ++var7) {
			this.field_78109_a[var7].rotationPointY = (float)(-(4 - var7)) * var6 * 1.7F;
		}

	}

	public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
		this.field_78108_b.render(var7);

		for(int var8 = 0; var8 < this.field_78109_a.length; ++var8) {
			this.field_78109_a[var8].render(var7);
		}

	}
}
