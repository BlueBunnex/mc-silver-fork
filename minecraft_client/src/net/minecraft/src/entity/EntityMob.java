package net.minecraft.src.entity;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.IMob;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Potion;
import net.minecraft.src.item.enchantment.EnchantmentHelper;
import net.minecraft.src.item.enchantment.EnchantmentThorns;
import net.minecraft.src.worldgen.World;

public abstract class EntityMob extends EntityCreature implements IMob {
	public EntityMob(World var1) {
		super(var1);
		this.experienceValue = 5;
	}

	public void onLivingUpdate() {
		this.updateArmSwingProgress();
		float var1 = this.getBrightness(1.0F);
		if(var1 > 0.5F) {
			this.entityAge += 2;
		}

		super.onLivingUpdate();
	}

	public void onUpdate() {
		super.onUpdate();
		if(!this.worldObj.isRemote && this.worldObj.difficultySetting == 0) {
			this.setDead();
		}

	}

	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1 != null && this.canEntityBeSeen(var1) ? var1 : null;
	}

	public boolean attackEntityFrom(DamageSource var1, int var2) {
		if(this.isEntityInvulnerable()) {
			return false;
		} else if(super.attackEntityFrom(var1, var2)) {
			Entity var3 = var1.getEntity();
			if(this.riddenByEntity != var3 && this.ridingEntity != var3) {
				if(var3 != this) {
					this.entityToAttack = var3;
				}

				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean attackEntityAsMob(Entity var1) {
		int var2 = this.getAttackStrength(var1);
		if(this.isPotionActive(Potion.damageBoost)) {
			var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if(this.isPotionActive(Potion.weakness)) {
			var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		int var3 = 0;
		if(var1 instanceof EntityLiving) {
			var2 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLiving)var1);
			var3 += EnchantmentHelper.getKnockbackModifier(this, (EntityLiving)var1);
		}

		boolean var4 = var1.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
		if(var4) {
			if(var3 > 0) {
				var1.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var3 * 0.5F));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			int var5 = EnchantmentHelper.getFireAspectModifier(this);
			if(var5 > 0) {
				var1.setFire(var5 * 4);
			}

			if(var1 instanceof EntityLiving) {
				EnchantmentThorns.func_92096_a(this, (EntityLiving)var1, this.rand);
			}
		}

		return var4;
	}

	protected void attackEntity(Entity var1, float var2) {
		if(this.attackTime <= 0 && var2 < 2.0F && var1.boundingBox.maxY > this.boundingBox.minY && var1.boundingBox.minY < this.boundingBox.maxY) {
			this.attackTime = 20;
			this.attackEntityAsMob(var1);
		}

	}

	public float getBlockPathWeight(int var1, int var2, int var3) {
		return 0.5F - this.worldObj.getLightBrightness(var1, var2, var3);
	}

	protected boolean isValidLightLevel() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		if(this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32)) {
			return false;
		} else {
			int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
			if(this.worldObj.isThundering()) {
				int var5 = this.worldObj.skylightSubtracted;
				this.worldObj.skylightSubtracted = 10;
				var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
				this.worldObj.skylightSubtracted = var5;
			}

			return var4 <= this.rand.nextInt(8);
		}
	}

	public boolean getCanSpawnHere() {
		return this.isValidLightLevel() && super.getCanSpawnHere();
	}

	public int getAttackStrength(Entity var1) {
		return 2;
	}
}
