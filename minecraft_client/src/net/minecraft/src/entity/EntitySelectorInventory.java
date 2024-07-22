package net.minecraft.src.entity;

import net.minecraft.src.IEntitySelector;
import net.minecraft.src.IInventory;

public final class EntitySelectorInventory implements IEntitySelector {
	
	public boolean isEntityApplicable(Entity entity) {
		return entity instanceof IInventory && entity.isEntityAlive();
	}
}
