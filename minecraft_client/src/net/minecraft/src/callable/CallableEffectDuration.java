package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.PotionEffect;

public class CallableEffectDuration implements Callable {
	
	private final PotionEffect field_102037_a;
	private final EntityLiving field_102036_b;

	public CallableEffectDuration(EntityLiving var1, PotionEffect var2) {
		this.field_102036_b = var1;
		this.field_102037_a = var2;
	}

	public String func_102035_a() {
		return this.field_102037_a.getDuration() + "";
	}

	public Object call() {
		return this.func_102035_a();
	}
}
