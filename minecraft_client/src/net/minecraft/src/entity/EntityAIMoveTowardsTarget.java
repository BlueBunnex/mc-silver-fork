package net.minecraft.src.entity;

import net.minecraft.src.RandomPositionGenerator;
import net.minecraft.src.Vec3;

public class EntityAIMoveTowardsTarget extends EntityAIBase {
	private EntityCreature theEntity;
	private EntityLiving targetEntity;
	private double movePosX;
	private double movePosY;
	private double movePosZ;
	private float field_75425_f;
	private float field_75426_g;

	public EntityAIMoveTowardsTarget(EntityCreature var1, float var2, float var3) {
		this.theEntity = var1;
		this.field_75425_f = var2;
		this.field_75426_g = var3;
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		this.targetEntity = this.theEntity.getAttackTarget();
		if(this.targetEntity == null) {
			return false;
		} else if(this.targetEntity.getDistanceSqToEntity(this.theEntity) > (double)(this.field_75426_g * this.field_75426_g)) {
			return false;
		} else {
			Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));
			if(var1 == null) {
				return false;
			} else {
				this.movePosX = var1.xCoord;
				this.movePosY = var1.yCoord;
				this.movePosZ = var1.zCoord;
				return true;
			}
		}
	}

	public boolean continueExecuting() {
		return !this.theEntity.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSqToEntity(this.theEntity) < (double)(this.field_75426_g * this.field_75426_g);
	}

	public void resetTask() {
		this.targetEntity = null;
	}

	public void startExecuting() {
		this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.field_75425_f);
	}
}
