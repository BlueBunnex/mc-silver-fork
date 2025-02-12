package net.minecraft.src.entity;

import net.minecraft.src.worldgen.World;

public class EntityAIOcelotAttack extends EntityAIBase {
	World theWorld;
	EntityLiving theEntity;
	EntityLiving theVictim;
	int attackCountdown = 0;

	public EntityAIOcelotAttack(EntityLiving var1) {
		this.theEntity = var1;
		this.theWorld = var1.worldObj;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		EntityLiving var1 = this.theEntity.getAttackTarget();
		if(var1 == null) {
			return false;
		} else {
			this.theVictim = var1;
			return true;
		}
	}

	public boolean continueExecuting() {
		return !this.theVictim.isEntityAlive() ? false : (this.theEntity.getDistanceSqToEntity(this.theVictim) > 225.0D ? false : !this.theEntity.getNavigator().noPath() || this.shouldExecute());
	}

	public void resetTask() {
		this.theVictim = null;
		this.theEntity.getNavigator().clearPathEntity();
	}

	public void updateTask() {
		this.theEntity.getLookHelper().setLookPositionWithEntity(this.theVictim, 30.0F, 30.0F);
		double var1 = (double)(this.theEntity.width * 2.0F * this.theEntity.width * 2.0F);
		double var3 = this.theEntity.getDistanceSq(this.theVictim.posX, this.theVictim.boundingBox.minY, this.theVictim.posZ);
		float var5 = 0.23F;
		if(var3 > var1 && var3 < 16.0D) {
			var5 = 0.4F;
		} else if(var3 < 225.0D) {
			var5 = 0.18F;
		}

		this.theEntity.getNavigator().tryMoveToEntityLiving(this.theVictim, var5);
		this.attackCountdown = Math.max(this.attackCountdown - 1, 0);
		if(var3 <= var1) {
			if(this.attackCountdown <= 0) {
				this.attackCountdown = 20;
				this.theEntity.attackEntityAsMob(this.theVictim);
			}
		}
	}
}
