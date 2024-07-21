package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.PotionEffect;
import net.minecraft.src.entity.EntityLiving;

public class CallableEffectIsAmbient implements Callable {
	
	private final PotionEffect field_102046_a;
	private final EntityLiving field_102045_b;

	public CallableEffectIsAmbient(EntityLiving var1, PotionEffect var2) {
		this.field_102045_b = var1;
		this.field_102046_a = var2;
	}

	public String func_102044_a() {
		return this.field_102046_a.getIsAmbient() + "";
	}

	public Object call() {
		return this.func_102044_a();
	}
}
