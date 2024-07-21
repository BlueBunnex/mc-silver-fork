package net.minecraft.src.entity;

import net.minecraft.src.IEntitySelector;

public final class EntitySelectorAlive implements IEntitySelector {
	
	public boolean isEntityApplicable(Entity entity) {
		return entity.isEntityAlive();
	}
}
