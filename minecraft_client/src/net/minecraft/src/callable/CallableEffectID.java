package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.PotionEffect;

public class CallableEffectID implements Callable {
	
	private final PotionEffect field_102034_a;
	private final EntityLiving field_102033_b;

	public CallableEffectID(EntityLiving var1, PotionEffect var2) {
		this.field_102033_b = var1;
		this.field_102034_a = var2;
	}

	public String func_102032_a() {
		return this.field_102034_a.getPotionID() + "";
	}

	public Object call() {
		return this.func_102032_a();
	}
}
