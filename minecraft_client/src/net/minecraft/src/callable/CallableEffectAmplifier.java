package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.PotionEffect;
import net.minecraft.src.entity.EntityLiving;

public class CallableEffectAmplifier implements Callable {
	
	private final PotionEffect field_102040_a;
	private final EntityLiving field_102039_b;

	public CallableEffectAmplifier(EntityLiving var1, PotionEffect var2) {
		this.field_102039_b = var1;
		this.field_102040_a = var2;
	}

	public String func_102038_a() {
		return this.field_102040_a.getAmplifier() + "";
	}

	public Object call() {
		return this.func_102038_a();
	}
}
