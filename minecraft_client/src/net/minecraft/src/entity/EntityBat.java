package net.minecraft.src.entity;

import java.util.Calendar;

import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.DamageSource;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.worldgen.World;

public class EntityBat extends EntityAmbientCreature {
	private ChunkCoordinates currentFlightTarget;

	public EntityBat(World var1) {
		super(var1);
		this.texture = "/mob/bat.png";
		this.setSize(0.5F, 0.9F);
		this.setIsBatHanging(true);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	protected float getSoundVolume() {
		return 0.1F;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	protected String getLivingSound() {
		return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
	}

	protected String getHurtSound() {
		return "mob.bat.hurt";
	}

	protected String getDeathSound() {
		return "mob.bat.death";
	}

	public boolean canBePushed() {
		return false;
	}

	protected void collideWithEntity(Entity var1) {
	}

	protected void func_85033_bc() {
	}

	public int getMaxHealth() {
		return 6;
	}

	public boolean getIsBatHanging() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setIsBatHanging(boolean var1) {
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if(var1) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
		}

	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		super.onUpdate();
		if(this.getIsBatHanging()) {
			this.motionX = this.motionY = this.motionZ = 0.0D;
			this.posY = (double)MathHelper.floor_double(this.posY) + 1.0D - (double)this.height;
		} else {
			this.motionY *= (double)0.6F;
		}

	}

	protected void updateAITasks() {
		super.updateAITasks();
		if(this.getIsBatHanging()) {
			if(!this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ))) {
				this.setIsBatHanging(false);
				this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
			} else {
				if(this.rand.nextInt(200) == 0) {
					this.rotationYawHead = (float)this.rand.nextInt(360);
				}

				if(this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null) {
					this.setIsBatHanging(false);
					this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
				}
			}
		} else {
			if(this.currentFlightTarget != null && (!this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1)) {
				this.currentFlightTarget = null;
			}

			if(this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
			}

			double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
			double var3 = (double)this.currentFlightTarget.posY + 0.1D - this.posY;
			double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * (double)0.1F;
			this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * (double)0.1F;
			this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * (double)0.1F;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / (double)((float)Math.PI)) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += var8;
			if(this.rand.nextInt(100) == 0 && this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ))) {
				this.setIsBatHanging(true);
			}
		}

	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void fall(float var1) {
	}

	protected void updateFallState(double var1, boolean var3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public boolean attackEntityFrom(DamageSource var1, int var2) {
		if(this.isEntityInvulnerable()) {
			return false;
		} else {
			if(!this.worldObj.isRemote && this.getIsBatHanging()) {
				this.setIsBatHanging(false);
			}

			return super.attackEntityFrom(var1, var2);
		}
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.dataWatcher.updateObject(16, Byte.valueOf(var1.getByte("BatFlags")));
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
	}

	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.boundingBox.minY);
		if(var1 >= 63) {
			return false;
		} else {
			int var2 = MathHelper.floor_double(this.posX);
			int var3 = MathHelper.floor_double(this.posZ);
			int var4 = this.worldObj.getBlockLightValue(var2, var1, var3);
			byte var5 = 4;
			Calendar var6 = this.worldObj.getCurrentDate();
			if((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3)) {
				if(this.rand.nextBoolean()) {
					return false;
				}
			} else {
				var5 = 7;
			}

			return var4 > this.rand.nextInt(var5) ? false : super.getCanSpawnHere();
		}
	}

	public void initCreature() {
	}
}
