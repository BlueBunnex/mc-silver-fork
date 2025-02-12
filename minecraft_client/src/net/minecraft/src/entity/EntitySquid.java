package net.minecraft.src.entity;

import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class EntitySquid extends EntityWaterMob {
	public float squidPitch = 0.0F;
	public float prevSquidPitch = 0.0F;
	public float squidYaw = 0.0F;
	public float prevSquidYaw = 0.0F;
	public float field_70867_h = 0.0F;
	public float field_70868_i = 0.0F;
	public float tentacleAngle = 0.0F;
	public float prevTentacleAngle = 0.0F;
	private float randomMotionSpeed = 0.0F;
	private float field_70864_bA = 0.0F;
	private float field_70871_bB = 0.0F;
	private float randomMotionVecX = 0.0F;
	private float randomMotionVecY = 0.0F;
	private float randomMotionVecZ = 0.0F;

	public EntitySquid(World var1) {
		super(var1);
		this.texture = "/mob/squid.png";
		this.setSize(0.95F, 0.95F);
		this.field_70864_bA = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
	}

	public int getMaxHealth() {
		return 10;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected int getDropItemId() {
		return 0;
	}

	protected void dropFewItems(boolean var1, int var2) {
		int var3 = this.rand.nextInt(3 + var2) + 1;

		for(int var4 = 0; var4 < var3; ++var4) {
			this.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
		}

	}

	public boolean isInWater() {
		return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, (double)-0.6F, 0.0D), Material.water, this);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.prevSquidPitch = this.squidPitch;
		this.prevSquidYaw = this.squidYaw;
		this.field_70868_i = this.field_70867_h;
		this.prevTentacleAngle = this.tentacleAngle;
		this.field_70867_h += this.field_70864_bA;
		if(this.field_70867_h > (float)Math.PI * 2.0F) {
			this.field_70867_h -= (float)Math.PI * 2.0F;
			if(this.rand.nextInt(10) == 0) {
				this.field_70864_bA = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
			}
		}

		if(this.isInWater()) {
			float var1;
			if(this.field_70867_h < (float)Math.PI) {
				var1 = this.field_70867_h / (float)Math.PI;
				this.tentacleAngle = MathHelper.sin(var1 * var1 * (float)Math.PI) * (float)Math.PI * 0.25F;
				if((double)var1 > 0.75D) {
					this.randomMotionSpeed = 1.0F;
					this.field_70871_bB = 1.0F;
				} else {
					this.field_70871_bB *= 0.8F;
				}
			} else {
				this.tentacleAngle = 0.0F;
				this.randomMotionSpeed *= 0.9F;
				this.field_70871_bB *= 0.99F;
			}

			if(!this.worldObj.isRemote) {
				this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
				this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
				this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
			}

			var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
			this.squidYaw += (float)Math.PI * this.field_70871_bB * 1.5F;
			this.squidPitch += (-((float)Math.atan2((double)var1, this.motionY)) * 180.0F / (float)Math.PI - this.squidPitch) * 0.1F;
		} else {
			this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.field_70867_h)) * (float)Math.PI * 0.25F;
			if(!this.worldObj.isRemote) {
				this.motionX = 0.0D;
				this.motionY -= 0.08D;
				this.motionY *= (double)0.98F;
				this.motionZ = 0.0D;
			}

			this.squidPitch = (float)((double)this.squidPitch + (double)(-90.0F - this.squidPitch) * 0.02D);
		}

	}

	public void moveEntityWithHeading(float var1, float var2) {
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	protected void updateEntityActionState() {
		++this.entityAge;
		if(this.entityAge > 100) {
			this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
		} else if(this.rand.nextInt(50) == 0 || !this.inWater || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F) {
			float var1 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
			this.randomMotionVecX = MathHelper.cos(var1) * 0.2F;
			this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
			this.randomMotionVecZ = MathHelper.sin(var1) * 0.2F;
		}

		this.despawnEntity();
	}

	public boolean getCanSpawnHere() {
		return this.posY > 45.0D && this.posY < 63.0D && super.getCanSpawnHere();
	}
}
