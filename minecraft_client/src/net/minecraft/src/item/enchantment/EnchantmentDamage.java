package net.minecraft.src.item.enchantment;

import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.EnumEnchantmentType;
import net.minecraft.src.MathHelper;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.item.ItemAxe;
import net.minecraft.src.item.ItemStack;

public class EnchantmentDamage extends Enchantment {
	private static final String[] protectionName = new String[]{"all", "undead", "arthropods"};
	private static final int[] baseEnchantability = new int[]{1, 5, 5};
	private static final int[] levelEnchantability = new int[]{11, 8, 8};
	private static final int[] thresholdEnchantability = new int[]{20, 20, 20};
	public final int damageType;

	public EnchantmentDamage(int var1, int var2, int var3) {
		super(var1, var2, EnumEnchantmentType.weapon);
		this.damageType = var3;
	}

	public int getMinEnchantability(int var1) {
		return baseEnchantability[this.damageType] + (var1 - 1) * levelEnchantability[this.damageType];
	}

	public int getMaxEnchantability(int var1) {
		return this.getMinEnchantability(var1) + thresholdEnchantability[this.damageType];
	}

	public int getMaxLevel() {
		return 5;
	}

	public int calcModifierLiving(int var1, EntityLiving var2) {
		return this.damageType == 0 ? MathHelper.floor_float((float)var1 * 2.75F) : (this.damageType == 1 && var2.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? MathHelper.floor_float((float)var1 * 4.5F) : (this.damageType == 2 && var2.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD ? MathHelper.floor_float((float)var1 * 4.5F) : 0));
	}

	public String getName() {
		return "enchantment.damage." + protectionName[this.damageType];
	}

	public boolean canApplyTogether(Enchantment var1) {
		return !(var1 instanceof EnchantmentDamage);
	}

	public boolean canApply(ItemStack var1) {
		return var1.getItem() instanceof ItemAxe ? true : super.canApply(var1);
	}
}
