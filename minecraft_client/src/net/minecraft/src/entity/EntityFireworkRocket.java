package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class EntityFireworkRocket extends Entity {
	private int fireworkAge;
	private int lifetime;

	public EntityFireworkRocket(World var1) {
		super(var1);
		this.setSize(0.25F, 0.25F);
	}

	protected void entityInit() {
		this.dataWatcher.addObjectByDataType(8, 5);
	}

	public boolean isInRangeToRenderDist(double var1) {
		return var1 < 4096.0D;
	}

	public EntityFireworkRocket(World var1, double var2, double var4, double var6, ItemStack var8) {
		super(var1);
		this.fireworkAge = 0;
		this.setSize(0.25F, 0.25F);
		this.setPosition(var2, var4, var6);
		this.yOffset = 0.0F;
		int var9 = 1;
		if(var8 != null && var8.hasTagCompound()) {
			this.dataWatcher.updateObject(8, var8);
			NBTTagCompound var10 = var8.getTagCompound();
			NBTTagCompound var11 = var10.getCompoundTag("Fireworks");
			if(var11 != null) {
				var9 += var11.getByte("Flight");
			}
		}

		this.motionX = this.rand.nextGaussian() * 0.001D;
		this.motionZ = this.rand.nextGaussian() * 0.001D;
		this.motionY = 0.05D;
		this.lifetime = 10 * var9 + this.rand.nextInt(6) + this.rand.nextInt(7);
	}

	public void setVelocity(double var1, double var3, double var5) {
		this.motionX = var1;
		this.motionY = var3;
		this.motionZ = var5;
		if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / (double)((float)Math.PI));
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(var3, (double)var7) * 180.0D / (double)((float)Math.PI));
		}

	}

	public void onUpdate() {
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		this.motionX *= 1.15D;
		this.motionZ *= 1.15D;
		this.motionY += 0.04D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / (double)((float)Math.PI));

		for(this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var1) * 180.0D / (double)((float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
		}

		while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		if(this.fireworkAge == 0) {
			this.worldObj.playSoundAtEntity(this, "fireworks.launch", 3.0F, 1.0F);
		}

		++this.fireworkAge;
		if(this.worldObj.isRemote && this.fireworkAge % 2 < 2) {
			this.worldObj.spawnParticle("fireworksSpark", this.posX, this.posY - 0.3D, this.posZ, this.rand.nextGaussian() * 0.05D, -this.motionY * 0.5D, this.rand.nextGaussian() * 0.05D);
		}

		if(!this.worldObj.isRemote && this.fireworkAge > this.lifetime) {
			this.worldObj.setEntityState(this, (byte)17);
			this.setDead();
		}

	}

	public void handleHealthUpdate(byte var1) {
		if(var1 == 17 && this.worldObj.isRemote) {
			ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
			NBTTagCompound var3 = null;
			if(var2 != null && var2.hasTagCompound()) {
				var3 = var2.getTagCompound().getCompoundTag("Fireworks");
			}

			this.worldObj.func_92088_a(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, var3);
		}

		super.handleHealthUpdate(var1);
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		var1.setInteger("Life", this.fireworkAge);
		var1.setInteger("LifeTime", this.lifetime);
		ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
		if(var2 != null) {
			NBTTagCompound var3 = new NBTTagCompound();
			var2.writeToNBT(var3);
			var1.setCompoundTag("FireworksItem", var3);
		}

	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		this.fireworkAge = var1.getInteger("Life");
		this.lifetime = var1.getInteger("LifeTime");
		NBTTagCompound var2 = var1.getCompoundTag("FireworksItem");
		if(var2 != null) {
			ItemStack var3 = ItemStack.loadItemStackFromNBT(var2);
			if(var3 != null) {
				this.dataWatcher.updateObject(8, var3);
			}
		}

	}

	public float getShadowSize() {
		return 0.0F;
	}

	public float getBrightness(float var1) {
		return super.getBrightness(var1);
	}

	public int getBrightnessForRender(float var1) {
		return super.getBrightnessForRender(var1);
	}

	public boolean canAttackWithItem() {
		return false;
	}
}
