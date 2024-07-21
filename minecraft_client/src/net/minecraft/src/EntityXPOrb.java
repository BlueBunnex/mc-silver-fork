package net.minecraft.src;

public class EntityXPOrb extends Entity {
	public int xpColor;
	public int xpOrbAge = 0;
	public int field_70532_c;
	private int xpOrbHealth = 5;
	private int xpValue;
	private EntityPlayer closestPlayer;
	private int xpTargetColor;

	public EntityXPOrb(World var1, double var2, double var4, double var6, int var8) {
		super(var1);
		this.setSize(0.5F, 0.5F);
		this.yOffset = this.height / 2.0F;
		this.setPosition(var2, var4, var6);
		this.rotationYaw = (float)(Math.random() * 360.0D);
		this.motionX = (double)((float)(Math.random() * (double)0.2F - (double)0.1F) * 2.0F);
		this.motionY = (double)((float)(Math.random() * 0.2D) * 2.0F);
		this.motionZ = (double)((float)(Math.random() * (double)0.2F - (double)0.1F) * 2.0F);
		this.xpValue = var8;
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public EntityXPOrb(World var1) {
		super(var1);
		this.setSize(0.25F, 0.25F);
		this.yOffset = this.height / 2.0F;
	}

	protected void entityInit() {
	}

	public int getBrightnessForRender(float var1) {
		float var2 = 0.5F;
		if(var2 < 0.0F) {
			var2 = 0.0F;
		}

		if(var2 > 1.0F) {
			var2 = 1.0F;
		}

		int var3 = super.getBrightnessForRender(var1);
		int var4 = var3 & 255;
		int var5 = var3 >> 16 & 255;
		var4 += (int)(var2 * 15.0F * 16.0F);
		if(var4 > 240) {
			var4 = 240;
		}

		return var4 | var5 << 16;
	}

	public void onUpdate() {
		super.onUpdate();
		if(this.field_70532_c > 0) {
			--this.field_70532_c;
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= (double)0.03F;
		if(this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) == Material.lava) {
			this.motionY = (double)0.2F;
			this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
			this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
			this.playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
		}

		this.pushOutOfBlocks(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D, this.posZ);
		double var1 = 8.0D;
		if(this.xpTargetColor < this.xpColor - 20 + this.entityId % 100) {
			if(this.closestPlayer == null || this.closestPlayer.getDistanceSqToEntity(this) > var1 * var1) {
				this.closestPlayer = this.worldObj.getClosestPlayerToEntity(this, var1);
			}

			this.xpTargetColor = this.xpColor;
		}

		if(this.closestPlayer != null) {
			double var3 = (this.closestPlayer.posX - this.posX) / var1;
			double var5 = (this.closestPlayer.posY + (double)this.closestPlayer.getEyeHeight() - this.posY) / var1;
			double var7 = (this.closestPlayer.posZ - this.posZ) / var1;
			double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
			double var11 = 1.0D - var9;
			if(var11 > 0.0D) {
				var11 *= var11;
				this.motionX += var3 / var9 * var11 * 0.1D;
				this.motionY += var5 / var9 * var11 * 0.1D;
				this.motionZ += var7 / var9 * var11 * 0.1D;
			}
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		float var13 = 0.98F;
		if(this.onGround) {
			var13 = 0.1F * 0.1F * 58.8F;
			int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
			if(var4 > 0) {
				var13 = Block.blocksList[var4].slipperiness * 0.98F;
			}
		}

		this.motionX *= (double)var13;
		this.motionY *= (double)0.98F;
		this.motionZ *= (double)var13;
		if(this.onGround) {
			this.motionY *= (double)-0.9F;
		}

		++this.xpColor;
		++this.xpOrbAge;
		if(this.xpOrbAge >= 6000) {
			this.setDead();
		}

	}

	public boolean handleWaterMovement() {
		return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
	}

	protected void dealFireDamage(int var1) {
		this.attackEntityFrom(DamageSource.inFire, var1);
	}

	public boolean attackEntityFrom(DamageSource var1, int var2) {
		if(this.isEntityInvulnerable()) {
			return false;
		} else {
			this.setBeenAttacked();
			this.xpOrbHealth -= var2;
			if(this.xpOrbHealth <= 0) {
				this.setDead();
			}

			return false;
		}
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		var1.setShort("Health", (short)((byte)this.xpOrbHealth));
		var1.setShort("Age", (short)this.xpOrbAge);
		var1.setShort("Value", (short)this.xpValue);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		this.xpOrbHealth = var1.getShort("Health") & 255;
		this.xpOrbAge = var1.getShort("Age");
		this.xpValue = var1.getShort("Value");
	}

	public void onCollideWithPlayer(EntityPlayer var1) {
		if(!this.worldObj.isRemote) {
			if(this.field_70532_c == 0 && var1.xpCooldown == 0) {
				var1.xpCooldown = 2;
				this.playSound("random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
				var1.onItemPickup(this, 1);
				var1.addExperience(this.xpValue);
				this.setDead();
			}

		}
	}

	public int getXpValue() {
		return this.xpValue;
	}

	public int getTextureByXP() {
		return this.xpValue >= 2477 ? 10 : (this.xpValue >= 1237 ? 9 : (this.xpValue >= 617 ? 8 : (this.xpValue >= 307 ? 7 : (this.xpValue >= 149 ? 6 : (this.xpValue >= 73 ? 5 : (this.xpValue >= 37 ? 4 : (this.xpValue >= 17 ? 3 : (this.xpValue >= 7 ? 2 : (this.xpValue >= 3 ? 1 : 0)))))))));
	}

	public static int getXPSplit(int var0) {
		return var0 >= 2477 ? 2477 : (var0 >= 1237 ? 1237 : (var0 >= 617 ? 617 : (var0 >= 307 ? 307 : (var0 >= 149 ? 149 : (var0 >= 73 ? 73 : (var0 >= 37 ? 37 : (var0 >= 17 ? 17 : (var0 >= 7 ? 7 : (var0 >= 3 ? 3 : 1)))))))));
	}

	public boolean canAttackWithItem() {
		return false;
	}
}
