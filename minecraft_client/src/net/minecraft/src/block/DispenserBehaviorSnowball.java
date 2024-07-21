package net.minecraft.src.block;

import net.minecraft.src.IPosition;
import net.minecraft.src.IProjectile;
import net.minecraft.src.entity.EntitySnowball;
import net.minecraft.src.worldgen.World;

final class DispenserBehaviorSnowball extends BehaviorProjectileDispense {
	protected IProjectile getProjectileEntity(World var1, IPosition var2) {
		return new EntitySnowball(var1, var2.getX(), var2.getY(), var2.getZ());
	}
}
