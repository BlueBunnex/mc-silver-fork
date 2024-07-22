package net.minecraft.src.item.enchantment;

import net.minecraft.src.entity.EntityLiving;

final class EnchantmentModifierLiving implements IEnchantmentModifier {
	
	public int livingModifier;
	public EntityLiving entityLiving;

	public void calculateModifier(Enchantment var1, int var2) {
		this.livingModifier += var1.calcModifierLiving(var2, this.entityLiving);
	}
}
