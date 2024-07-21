package net.minecraft.src.callable;

import java.util.concurrent.Callable;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;

public class CallableEffectName implements Callable {
	
	private final PotionEffect field_102031_a;
	private final EntityLiving field_102030_b;

	public CallableEffectName(EntityLiving var1, PotionEffect var2) {
		this.field_102030_b = var1;
		this.field_102031_a = var2;
	}

	public String func_102029_a() {
		return Potion.potionTypes[this.field_102031_a.getPotionID()].getName();
	}

	public Object call() {
		return this.func_102029_a();
	}
}
