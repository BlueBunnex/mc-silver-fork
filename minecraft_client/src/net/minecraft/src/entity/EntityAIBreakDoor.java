package net.minecraft.src.entity;

public class EntityAIBreakDoor extends EntityAIDoorInteract {
	private int breakingTime;
	private int field_75358_j = -1;

	public EntityAIBreakDoor(EntityLiving var1) {
		super(var1);
	}

	public boolean shouldExecute() {
		return !super.shouldExecute() ? false : (!this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") ? false : !this.targetDoor.isDoorOpen(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ));
	}

	public void startExecuting() {
		super.startExecuting();
		this.breakingTime = 0;
	}

	public boolean continueExecuting() {
		double var1 = this.theEntity.getDistanceSq((double)this.entityPosX, (double)this.entityPosY, (double)this.entityPosZ);
		return this.breakingTime <= 240 && !this.targetDoor.isDoorOpen(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ) && var1 < 4.0D;
	}

	public void resetTask() {
		super.resetTask();
		this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.entityId, this.entityPosX, this.entityPosY, this.entityPosZ, -1);
	}

	public void updateTask() {
		super.updateTask();
		if(this.theEntity.getRNG().nextInt(20) == 0) {
			this.theEntity.worldObj.playAuxSFX(1010, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
		}

		++this.breakingTime;
		int var1 = (int)((float)this.breakingTime / 240.0F * 10.0F);
		if(var1 != this.field_75358_j) {
			this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.entityId, this.entityPosX, this.entityPosY, this.entityPosZ, var1);
			this.field_75358_j = var1;
		}

		if(this.breakingTime == 240 && this.theEntity.worldObj.difficultySetting == 3) {
			this.theEntity.worldObj.setBlockToAir(this.entityPosX, this.entityPosY, this.entityPosZ);
			this.theEntity.worldObj.playAuxSFX(1012, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
			this.theEntity.worldObj.playAuxSFX(2001, this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor.blockID);
		}

	}
}
