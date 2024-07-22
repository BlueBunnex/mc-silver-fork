package net.minecraft.src.entity;

import net.minecraft.src.RandomPositionGenerator;
import net.minecraft.src.Vec3;

public class EntityAIWander extends EntityAIBase {
	private EntityCreature entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private float speed;

	public EntityAIWander(EntityCreature var1, float var2) {
		this.entity = var1;
		this.speed = var2;
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		if(this.entity.getAge() >= 100) {
			return false;
		} else if(this.entity.getRNG().nextInt(120) != 0) {
			return false;
		} else {
			Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
			if(var1 == null) {
				return false;
			} else {
				this.xPosition = var1.xCoord;
				this.yPosition = var1.yCoord;
				this.zPosition = var1.zCoord;
				return true;
			}
		}
	}

	public boolean continueExecuting() {
		return !this.entity.getNavigator().noPath();
	}

	public void startExecuting() {
		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	}
}
