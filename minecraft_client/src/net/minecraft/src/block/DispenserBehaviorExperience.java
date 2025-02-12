package net.minecraft.src.block;

import net.minecraft.src.IPosition;
import net.minecraft.src.IProjectile;
import net.minecraft.src.entity.EntityExpBottle;
import net.minecraft.src.worldgen.World;

final class DispenserBehaviorExperience extends BehaviorProjectileDispense {
	protected IProjectile getProjectileEntity(World var1, IPosition var2) {
		return new EntityExpBottle(var1, var2.getX(), var2.getY(), var2.getZ());
	}

	protected float func_82498_a() {
		return super.func_82498_a() * 0.5F;
	}

	protected float func_82500_b() {
		return super.func_82500_b() * 1.25F;
	}
}
