package net.minecraft.src.item.enchantment;

import net.minecraft.src.DamageSource;

final class EnchantmentModifierDamage implements IEnchantmentModifier {
	
	public int damageModifier;
	public DamageSource source;

	public void calculateModifier(Enchantment var1, int var2) {
		this.damageModifier += var1.calcModifierDamage(var2, this.source);
	}
}
