package net.minecraft.src;

import net.minecraft.src.worldgen.World;

public abstract class EntityWaterMob extends EntityCreature implements IAnimals {
	public EntityWaterMob(World var1) {
		super(var1);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean getCanSpawnHere() {
		return this.worldObj.checkNoEntityCollision(this.boundingBox);
	}

	public int getTalkInterval() {
		return 120;
	}

	protected boolean canDespawn() {
		return true;
	}

	protected int getExperiencePoints(EntityPlayer var1) {
		return 1 + this.worldObj.rand.nextInt(3);
	}

	public void onEntityUpdate() {
		int var1 = this.getAir();
		super.onEntityUpdate();
		if(this.isEntityAlive() && !this.isInsideOfMaterial(Material.water)) {
			--var1;
			this.setAir(var1);
			if(this.getAir() == -20) {
				this.setAir(0);
				this.attackEntityFrom(DamageSource.drown, 2);
			}
		} else {
			this.setAir(300);
		}

	}
}
