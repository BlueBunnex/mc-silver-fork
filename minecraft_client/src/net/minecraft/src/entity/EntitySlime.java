package net.minecraft.src.entity;

import net.minecraft.src.Chunk;
import net.minecraft.src.DamageSource;
import net.minecraft.src.IMob;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.item.Item;
import net.minecraft.src.worldgen.BiomeGenBase;
import net.minecraft.src.worldgen.World;
import net.minecraft.src.worldgen.WorldType;

public class EntitySlime extends EntityLiving implements IMob {
	private static final float[] spawnChances = new float[]{1.0F, 12.0F / 16.0F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 12.0F / 16.0F};
	public float field_70813_a;
	public float field_70811_b;
	public float field_70812_c;
	private int slimeJumpDelay = 0;

	public EntitySlime(World var1) {
		super(var1);
		this.texture = "/mob/slime.png";
		int var2 = 1 << this.rand.nextInt(3);
		this.yOffset = 0.0F;
		this.slimeJumpDelay = this.rand.nextInt(20) + 10;
		this.setSlimeSize(var2);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)1));
	}

	protected void setSlimeSize(int var1) {
		this.dataWatcher.updateObject(16, new Byte((byte)var1));
		this.setSize(0.6F * (float)var1, 0.6F * (float)var1);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.setEntityHealth(this.getMaxHealth());
		this.experienceValue = var1;
	}

	public int getMaxHealth() {
		int var1 = this.getSlimeSize();
		return var1 * var1;
	}

	public int getSlimeSize() {
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setInteger("Size", this.getSlimeSize() - 1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.setSlimeSize(var1.getInteger("Size") + 1);
	}

	protected String getSlimeParticle() {
		return "slime";
	}

	protected String getJumpSound() {
		return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
	}

	public void onUpdate() {
		if(!this.worldObj.isRemote && this.worldObj.difficultySetting == 0 && this.getSlimeSize() > 0) {
			this.isDead = true;
		}

		this.field_70811_b += (this.field_70813_a - this.field_70811_b) * 0.5F;
		this.field_70812_c = this.field_70811_b;
		boolean var1 = this.onGround;
		super.onUpdate();
		int var2;
		if(this.onGround && !var1) {
			var2 = this.getSlimeSize();

			for(int var3 = 0; var3 < var2 * 8; ++var3) {
				float var4 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float var5 = this.rand.nextFloat() * 0.5F + 0.5F;
				float var6 = MathHelper.sin(var4) * (float)var2 * 0.5F * var5;
				float var7 = MathHelper.cos(var4) * (float)var2 * 0.5F * var5;
				this.worldObj.spawnParticle(this.getSlimeParticle(), this.posX + (double)var6, this.boundingBox.minY, this.posZ + (double)var7, 0.0D, 0.0D, 0.0D);
			}

			if(this.makesSoundOnLand()) {
				this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			}

			this.field_70813_a = -0.5F;
		} else if(!this.onGround && var1) {
			this.field_70813_a = 1.0F;
		}

		this.func_70808_l();
		if(this.worldObj.isRemote) {
			var2 = this.getSlimeSize();
			this.setSize(0.6F * (float)var2, 0.6F * (float)var2);
		}

	}

	protected void updateEntityActionState() {
		this.despawnEntity();
		EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		if(var1 != null) {
			this.faceEntity(var1, 10.0F, 20.0F);
		}

		if(this.onGround && this.slimeJumpDelay-- <= 0) {
			this.slimeJumpDelay = this.getJumpDelay();
			if(var1 != null) {
				this.slimeJumpDelay /= 3;
			}

			this.isJumping = true;
			if(this.makesSoundOnJump()) {
				this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
			}

			this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
			this.moveForward = (float)(1 * this.getSlimeSize());
		} else {
			this.isJumping = false;
			if(this.onGround) {
				this.moveStrafing = this.moveForward = 0.0F;
			}
		}

	}

	protected void func_70808_l() {
		this.field_70813_a *= 0.6F;
	}

	protected int getJumpDelay() {
		return this.rand.nextInt(20) + 10;
	}

	protected EntitySlime createInstance() {
		return new EntitySlime(this.worldObj);
	}

	public void setDead() {
		int var1 = this.getSlimeSize();
		if(!this.worldObj.isRemote && var1 > 1 && this.getHealth() <= 0) {
			int var2 = 2 + this.rand.nextInt(3);

			for(int var3 = 0; var3 < var2; ++var3) {
				float var4 = ((float)(var3 % 2) - 0.5F) * (float)var1 / 4.0F;
				float var5 = ((float)(var3 / 2) - 0.5F) * (float)var1 / 4.0F;
				EntitySlime var6 = this.createInstance();
				var6.setSlimeSize(var1 / 2);
				var6.setLocationAndAngles(this.posX + (double)var4, this.posY + 0.5D, this.posZ + (double)var5, this.rand.nextFloat() * 360.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(var6);
			}
		}

		super.setDead();
	}

	public void onCollideWithPlayer(EntityPlayer var1) {
		if(this.canDamagePlayer()) {
			int var2 = this.getSlimeSize();
			if(this.canEntityBeSeen(var1) && this.getDistanceSqToEntity(var1) < 0.6D * (double)var2 * 0.6D * (double)var2 && var1.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength())) {
				this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}
		}

	}

	protected boolean canDamagePlayer() {
		return this.getSlimeSize() > 1;
	}

	protected int getAttackStrength() {
		return this.getSlimeSize();
	}

	protected String getHurtSound() {
		return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
	}

	protected String getDeathSound() {
		return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
	}

	protected int getDropItemId() {
		return this.getSlimeSize() == 1 ? Item.slimeBall.itemID : 0;
	}

	public boolean getCanSpawnHere() {
		Chunk var1 = this.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
		if(this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT && this.rand.nextInt(4) != 1) {
			return false;
		} else {
			if(this.getSlimeSize() == 1 || this.worldObj.difficultySetting > 0) {
				BiomeGenBase var2 = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
				if(var2 == BiomeGenBase.swampland && this.posY > 50.0D && this.posY < 70.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < spawnChances[this.worldObj.getMoonPhase()] && this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) <= this.rand.nextInt(8)) {
					return super.getCanSpawnHere();
				}

				if(this.rand.nextInt(10) == 0 && var1.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D) {
					return super.getCanSpawnHere();
				}
			}

			return false;
		}
	}

	protected float getSoundVolume() {
		return 0.4F * (float)this.getSlimeSize();
	}

	public int getVerticalFaceSpeed() {
		return 0;
	}

	protected boolean makesSoundOnJump() {
		return this.getSlimeSize() > 0;
	}

	protected boolean makesSoundOnLand() {
		return this.getSlimeSize() > 2;
	}
}
