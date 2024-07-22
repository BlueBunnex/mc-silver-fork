package net.minecraft.src;

import net.minecraft.src.entity.Entity;

final class FilterIMob implements IEntitySelector {
	public boolean isEntityApplicable(Entity var1) {
		return var1 instanceof IMob;
	}
}
