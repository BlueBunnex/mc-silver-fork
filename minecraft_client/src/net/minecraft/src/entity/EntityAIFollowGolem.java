package net.minecraft.src.entity;

import java.util.Iterator;
import java.util.List;

public class EntityAIFollowGolem extends EntityAIBase {
	private EntityVillager theVillager;
	private EntityIronGolem theGolem;
	private int takeGolemRoseTick;
	private boolean tookGolemRose = false;

	public EntityAIFollowGolem(EntityVillager var1) {
		this.theVillager = var1;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		if(this.theVillager.getGrowingAge() >= 0) {
			return false;
		} else if(!this.theVillager.worldObj.isDaytime()) {
			return false;
		} else {
			List var1 = this.theVillager.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, this.theVillager.boundingBox.expand(6.0D, 2.0D, 6.0D));
			if(var1.isEmpty()) {
				return false;
			} else {
				Iterator var2 = var1.iterator();

				while(var2.hasNext()) {
					EntityIronGolem var3 = (EntityIronGolem)var2.next();
					if(var3.getHoldRoseTick() > 0) {
						this.theGolem = var3;
						break;
					}
				}

				return this.theGolem != null;
			}
		}
	}

	public boolean continueExecuting() {
		return this.theGolem.getHoldRoseTick() > 0;
	}

	public void startExecuting() {
		this.takeGolemRoseTick = this.theVillager.getRNG().nextInt(320);
		this.tookGolemRose = false;
		this.theGolem.getNavigator().clearPathEntity();
	}

	public void resetTask() {
		this.theGolem = null;
		this.theVillager.getNavigator().clearPathEntity();
	}

	public void updateTask() {
		this.theVillager.getLookHelper().setLookPositionWithEntity(this.theGolem, 30.0F, 30.0F);
		if(this.theGolem.getHoldRoseTick() == this.takeGolemRoseTick) {
			this.theVillager.getNavigator().tryMoveToEntityLiving(this.theGolem, 0.15F);
			this.tookGolemRose = true;
		}

		if(this.tookGolemRose && this.theVillager.getDistanceSqToEntity(this.theGolem) < 4.0D) {
			this.theGolem.setHoldingRose(false);
			this.theVillager.getNavigator().clearPathEntity();
		}

	}
}
