package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;
import net.minecraft.src.Village;
import net.minecraft.src.worldgen.World;

public class EntityAIVillagerMate extends EntityAIBase {
	private EntityVillager villagerObj;
	private EntityVillager mate;
	private World worldObj;
	private int matingTimeout = 0;
	Village villageObj;

	public EntityAIVillagerMate(EntityVillager var1) {
		this.villagerObj = var1;
		this.worldObj = var1.worldObj;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		if(this.villagerObj.getGrowingAge() != 0) {
			return false;
		} else if(this.villagerObj.getRNG().nextInt(500) != 0) {
			return false;
		} else {
			this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.villagerObj.posX), MathHelper.floor_double(this.villagerObj.posY), MathHelper.floor_double(this.villagerObj.posZ), 0);
			if(this.villageObj == null) {
				return false;
			} else if(!this.checkSufficientDoorsPresentForNewVillager()) {
				return false;
			} else {
				Entity var1 = this.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.villagerObj.boundingBox.expand(8.0D, 3.0D, 8.0D), this.villagerObj);
				if(var1 == null) {
					return false;
				} else {
					this.mate = (EntityVillager)var1;
					return this.mate.getGrowingAge() == 0;
				}
			}
		}
	}

	public void startExecuting() {
		this.matingTimeout = 300;
		this.villagerObj.setMating(true);
	}

	public void resetTask() {
		this.villageObj = null;
		this.mate = null;
		this.villagerObj.setMating(false);
	}

	public boolean continueExecuting() {
		return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getGrowingAge() == 0;
	}

	public void updateTask() {
		--this.matingTimeout;
		this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);
		if(this.villagerObj.getDistanceSqToEntity(this.mate) > 2.25D) {
			this.villagerObj.getNavigator().tryMoveToEntityLiving(this.mate, 0.25F);
		} else if(this.matingTimeout == 0 && this.mate.isMating()) {
			this.giveBirth();
		}

		if(this.villagerObj.getRNG().nextInt(35) == 0) {
			this.worldObj.setEntityState(this.villagerObj, (byte)12);
		}

	}

	private boolean checkSufficientDoorsPresentForNewVillager() {
		if(!this.villageObj.isMatingSeason()) {
			return false;
		} else {
			int var1 = (int)((double)((float)this.villageObj.getNumVillageDoors()) * 0.35D);
			return this.villageObj.getNumVillagers() < var1;
		}
	}

	private void giveBirth() {
		EntityVillager var1 = this.villagerObj.func_90012_b(this.mate);
		this.mate.setGrowingAge(6000);
		this.villagerObj.setGrowingAge(6000);
		var1.setGrowingAge(-24000);
		var1.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
		this.worldObj.spawnEntityInWorld(var1);
		this.worldObj.setEntityState(var1, (byte)12);
	}
}
