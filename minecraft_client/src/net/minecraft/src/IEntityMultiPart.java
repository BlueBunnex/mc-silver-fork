package net.minecraft.src;

import net.minecraft.src.entity.EntityDragonPart;
import net.minecraft.src.worldgen.World;

public interface IEntityMultiPart {
	World func_82194_d();

	boolean attackEntityFromPart(EntityDragonPart var1, DamageSource var2, int var3);
}
