package net.minecraft.src.entity;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DamageSource;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagDouble;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.Vec3;
import net.minecraft.src.worldgen.World;

public abstract class EntityFireball extends Entity {
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inTile = 0;
	private boolean inGround = false;
	public EntityLiving shootingEntity;
	private int ticksAlive;
	private int ticksInAir = 0;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;

	public EntityFireball(World var1) {
		super(var1);
		this.setSize(1.0F, 1.0F);
	}

	protected void entityInit() {
	}

	public boolean isInRangeToRenderDist(double var1) {
		double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
		var3 *= 64.0D;
		return var1 < var3 * var3;
	}

	public EntityFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
		super(var1);
		this.setSize(1.0F, 1.0F);
		this.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
		this.setPosition(var2, var4, var6);
		double var14 = (double)MathHelper.sqrt_double(var8 * var8 + var10 * var10 + var12 * var12);
		this.accelerationX = var8 / var14 * 0.1D;
		this.accelerationY = var10 / var14 * 0.1D;
		this.accelerationZ = var12 / var14 * 0.1D;
	}

	public EntityFireball(World var1, EntityLiving var2, double var3, double var5, double var7) {
		super(var1);
		this.shootingEntity = var2;
		this.setSize(1.0F, 1.0F);
		this.setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = this.motionY = this.motionZ = 0.0D;
		var3 += this.rand.nextGaussian() * 0.4D;
		var5 += this.rand.nextGaussian() * 0.4D;
		var7 += this.rand.nextGaussian() * 0.4D;
		double var9 = (double)MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
		this.accelerationX = var3 / var9 * 0.1D;
		this.accelerationY = var5 / var9 * 0.1D;
		this.accelerationZ = var7 / var9 * 0.1D;
	}

	public void onUpdate() {
		if(this.worldObj.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)) {
			super.onUpdate();
			this.setFire(1);
			if(this.inGround) {
				int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
				if(var1 == this.inTile) {
					++this.ticksAlive;
					if(this.ticksAlive == 600) {
						this.setDead();
					}

					return;
				}

				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				this.ticksAlive = 0;
				this.ticksInAir = 0;
			} else {
				++this.ticksInAir;
			}

			Vec3 var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			Vec3 var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
			var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			if(var3 != null) {
				var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
			}

			Entity var4 = null;
			List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double var6 = 0.0D;

			for(int var8 = 0; var8 < var5.size(); ++var8) {
				Entity var9 = (Entity)var5.get(var8);
				if(var9.canBeCollidedWith() && (!var9.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25)) {
					float var10 = 0.3F;
					AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
					MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);
					if(var12 != null) {
						double var13 = var15.distanceTo(var12.hitVec);
						if(var13 < var6 || var6 == 0.0D) {
							var4 = var9;
							var6 = var13;
						}
					}
				}
			}

			if(var4 != null) {
				var3 = new MovingObjectPosition(var4);
			}

			if(var3 != null) {
				this.onImpact(var3);
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / (double)((float)Math.PI)) + 90.0F;

			for(this.rotationPitch = (float)(Math.atan2((double)var16, this.motionY) * 180.0D / (double)((float)Math.PI)) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
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
			float var17 = this.getMotionFactor();
			if(this.isInWater()) {
				for(int var18 = 0; var18 < 4; ++var18) {
					float var19 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var19, this.posY - this.motionY * (double)var19, this.posZ - this.motionZ * (double)var19, this.motionX, this.motionY, this.motionZ);
				}

				var17 = 0.8F;
			}

			this.motionX += this.accelerationX;
			this.motionY += this.accelerationY;
			this.motionZ += this.accelerationZ;
			this.motionX *= (double)var17;
			this.motionY *= (double)var17;
			this.motionZ *= (double)var17;
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
			this.setPosition(this.posX, this.posY, this.posZ);
		} else {
			this.setDead();
		}
	}

	protected float getMotionFactor() {
		return 0.95F;
	}

	protected abstract void onImpact(MovingObjectPosition var1);

	public void writeEntityToNBT(NBTTagCompound var1) {
		var1.setShort("xTile", (short)this.xTile);
		var1.setShort("yTile", (short)this.yTile);
		var1.setShort("zTile", (short)this.zTile);
		var1.setByte("inTile", (byte)this.inTile);
		var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		var1.setTag("direction", this.newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		this.xTile = var1.getShort("xTile");
		this.yTile = var1.getShort("yTile");
		this.zTile = var1.getShort("zTile");
		this.inTile = var1.getByte("inTile") & 255;
		this.inGround = var1.getByte("inGround") == 1;
		if(var1.hasKey("direction")) {
			NBTTagList var2 = var1.getTagList("direction");
			this.motionX = ((NBTTagDouble)var2.tagAt(0)).data;
			this.motionY = ((NBTTagDouble)var2.tagAt(1)).data;
			this.motionZ = ((NBTTagDouble)var2.tagAt(2)).data;
		} else {
			this.setDead();
		}

	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public float getCollisionBorderSize() {
		return 1.0F;
	}

	public boolean attackEntityFrom(DamageSource var1, int var2) {
		if(this.isEntityInvulnerable()) {
			return false;
		} else {
			this.setBeenAttacked();
			if(var1.getEntity() != null) {
				Vec3 var3 = var1.getEntity().getLookVec();
				if(var3 != null) {
					this.motionX = var3.xCoord;
					this.motionY = var3.yCoord;
					this.motionZ = var3.zCoord;
					this.accelerationX = this.motionX * 0.1D;
					this.accelerationY = this.motionY * 0.1D;
					this.accelerationZ = this.motionZ * 0.1D;
				}

				if(var1.getEntity() instanceof EntityLiving) {
					this.shootingEntity = (EntityLiving)var1.getEntity();
				}

				return true;
			} else {
				return false;
			}
		}
	}

	public float getShadowSize() {
		return 0.0F;
	}

	public float getBrightness(float var1) {
		return 1.0F;
	}

	public int getBrightnessForRender(float var1) {
		return 15728880;
	}
}
