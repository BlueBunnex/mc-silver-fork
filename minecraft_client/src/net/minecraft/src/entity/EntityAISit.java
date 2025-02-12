package net.minecraft.src.entity;

public class EntityAISit extends EntityAIBase {
	private EntityTameable theEntity;
	private boolean isSitting = false;

	public EntityAISit(EntityTameable var1) {
		this.theEntity = var1;
		this.setMutexBits(5);
	}

	public boolean shouldExecute() {
		if(!this.theEntity.isTamed()) {
			return false;
		} else if(this.theEntity.isInWater()) {
			return false;
		} else if(!this.theEntity.onGround) {
			return false;
		} else {
			EntityLiving var1 = this.theEntity.getOwner();
			return var1 == null ? true : (this.theEntity.getDistanceSqToEntity(var1) < 144.0D && var1.getAITarget() != null ? false : this.isSitting);
		}
	}

	public void startExecuting() {
		this.theEntity.getNavigator().clearPathEntity();
		this.theEntity.setSitting(true);
	}

	public void resetTask() {
		this.theEntity.setSitting(false);
	}

	public void setSitting(boolean var1) {
		this.isSitting = var1;
	}
}
