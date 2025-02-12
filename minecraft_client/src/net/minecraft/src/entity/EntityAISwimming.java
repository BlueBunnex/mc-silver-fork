package net.minecraft.src.entity;

public class EntityAISwimming extends EntityAIBase {
	private EntityLiving theEntity;

	public EntityAISwimming(EntityLiving var1) {
		this.theEntity = var1;
		this.setMutexBits(4);
		var1.getNavigator().setCanSwim(true);
	}

	public boolean shouldExecute() {
		return this.theEntity.isInWater() || this.theEntity.handleLavaMovement();
	}

	public void updateTask() {
		if(this.theEntity.getRNG().nextFloat() < 0.8F) {
			this.theEntity.getJumpHelper().setJumping();
		}

	}
}
