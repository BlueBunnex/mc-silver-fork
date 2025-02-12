package net.minecraft.src.entity;

public class EntityAIRestrictSun extends EntityAIBase {
	private EntityCreature theEntity;

	public EntityAIRestrictSun(EntityCreature var1) {
		this.theEntity = var1;
	}

	public boolean shouldExecute() {
		return this.theEntity.worldObj.isDaytime();
	}

	public void startExecuting() {
		this.theEntity.getNavigator().setAvoidSun(true);
	}

	public void resetTask() {
		this.theEntity.getNavigator().setAvoidSun(false);
	}
}
