package net.minecraft.src.entity;

import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.IEntitySelector;

final class EntityWitherAttackFilter implements IEntitySelector {
	public boolean isEntityApplicable(Entity var1) {
		return var1 instanceof EntityLiving && ((EntityLiving)var1).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
	}
}
