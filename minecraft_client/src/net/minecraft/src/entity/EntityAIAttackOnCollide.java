package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;
import net.minecraft.src.PathEntity;
import net.minecraft.src.worldgen.World;

public class EntityAIAttackOnCollide extends EntityAIBase {
	World worldObj;
	EntityLiving attacker;
	EntityLiving entityTarget;
	int attackTick;
	float field_75440_e;
	boolean field_75437_f;
	PathEntity entityPathEntity;
	Class classTarget;
	private int field_75445_i;

	public EntityAIAttackOnCollide(EntityLiving var1, Class var2, float var3, boolean var4) {
		this(var1, var3, var4);
		this.classTarget = var2;
	}

	public EntityAIAttackOnCollide(EntityLiving var1, float var2, boolean var3) {
		this.attackTick = 0;
		this.attacker = var1;
		this.worldObj = var1.worldObj;
		this.field_75440_e = var2;
		this.field_75437_f = var3;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		EntityLiving var1 = this.attacker.getAttackTarget();
		if(var1 == null) {
			return false;
		} else if(this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass())) {
			return false;
		} else {
			this.entityTarget = var1;
			this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(this.entityTarget);
			return this.entityPathEntity != null;
		}
	}

	public boolean continueExecuting() {
		EntityLiving var1 = this.attacker.getAttackTarget();
		return var1 == null ? false : (!this.entityTarget.isEntityAlive() ? false : (!this.field_75437_f ? !this.attacker.getNavigator().noPath() : this.attacker.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ))));
	}

	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.entityPathEntity, this.field_75440_e);
		this.field_75445_i = 0;
	}

	public void resetTask() {
		this.entityTarget = null;
		this.attacker.getNavigator().clearPathEntity();
	}

	public void updateTask() {
		this.attacker.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
		if((this.field_75437_f || this.attacker.getEntitySenses().canSee(this.entityTarget)) && --this.field_75445_i <= 0) {
			this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
			this.attacker.getNavigator().tryMoveToEntityLiving(this.entityTarget, this.field_75440_e);
		}

		this.attackTick = Math.max(this.attackTick - 1, 0);
		double var1 = (double)(this.attacker.width * 2.0F * this.attacker.width * 2.0F);
		if(this.attacker.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= var1) {
			if(this.attackTick <= 0) {
				this.attackTick = 20;
				if(this.attacker.getHeldItem() != null) {
					this.attacker.swingItem();
				}

				this.attacker.attackEntityAsMob(this.entityTarget);
			}
		}
	}
}
