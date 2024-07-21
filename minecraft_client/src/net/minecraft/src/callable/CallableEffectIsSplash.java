package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.PotionEffect;

public class CallableEffectIsSplash implements Callable {
	
	private final PotionEffect field_102043_a;
	private final EntityLiving field_102042_b;

	public CallableEffectIsSplash(EntityLiving var1, PotionEffect var2) {
		this.field_102042_b = var1;
		this.field_102043_a = var2;
	}

	public String func_102041_a() {
		return this.field_102043_a.isSplashPotionEffect() + "";
	}

	public Object call() {
		return this.func_102041_a();
	}
}
