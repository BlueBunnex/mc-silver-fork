package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;
import net.minecraft.src.PathNavigate;
import net.minecraft.src.worldgen.World;

public class EntityAIFollowOwner extends EntityAIBase {
	private EntityTameable thePet;
	private EntityLiving theOwner;
	World theWorld;
	private float field_75336_f;
	private PathNavigate petPathfinder;
	private int field_75343_h;
	float maxDist;
	float minDist;
	private boolean field_75344_i;

	public EntityAIFollowOwner(EntityTameable var1, float var2, float var3, float var4) {
		this.thePet = var1;
		this.theWorld = var1.worldObj;
		this.field_75336_f = var2;
		this.petPathfinder = var1.getNavigator();
		this.minDist = var3;
		this.maxDist = var4;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		EntityLiving var1 = this.thePet.getOwner();
		if(var1 == null) {
			return false;
		} else if(this.thePet.isSitting()) {
			return false;
		} else if(this.thePet.getDistanceSqToEntity(var1) < (double)(this.minDist * this.minDist)) {
			return false;
		} else {
			this.theOwner = var1;
			return true;
		}
	}

	public boolean continueExecuting() {
		return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theOwner) > (double)(this.maxDist * this.maxDist) && !this.thePet.isSitting();
	}

	public void startExecuting() {
		this.field_75343_h = 0;
		this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
		this.thePet.getNavigator().setAvoidsWater(false);
	}

	public void resetTask() {
		this.theOwner = null;
		this.petPathfinder.clearPathEntity();
		this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
	}

	public void updateTask() {
		this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float)this.thePet.getVerticalFaceSpeed());
		if(!this.thePet.isSitting()) {
			if(--this.field_75343_h <= 0) {
				this.field_75343_h = 10;
				if(!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.field_75336_f)) {
					if(this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0D) {
						int var1 = MathHelper.floor_double(this.theOwner.posX) - 2;
						int var2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
						int var3 = MathHelper.floor_double(this.theOwner.boundingBox.minY);

						for(int var4 = 0; var4 <= 4; ++var4) {
							for(int var5 = 0; var5 <= 4; ++var5) {
								if((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && this.theWorld.doesBlockHaveSolidTopSurface(var1 + var4, var3 - 1, var2 + var5) && !this.theWorld.isBlockNormalCube(var1 + var4, var3, var2 + var5) && !this.theWorld.isBlockNormalCube(var1 + var4, var3 + 1, var2 + var5)) {
									this.thePet.setLocationAndAngles((double)((float)(var1 + var4) + 0.5F), (double)var3, (double)((float)(var2 + var5) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
									this.petPathfinder.clearPathEntity();
									return;
								}
							}
						}

					}
				}
			}
		}
	}
}
