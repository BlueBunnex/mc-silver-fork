package net.minecraft.src;

import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntitySelectorAlive;
import net.minecraft.src.entity.EntitySelectorInventory;

public interface IEntitySelector {
	
	IEntitySelector selectAnything = new EntitySelectorAlive();
	IEntitySelector selectInventories = new EntitySelectorInventory();

	boolean isEntityApplicable(Entity var1);
}
