package net.minecraft.src.entity;

import java.util.List;

import net.minecraft.src.DamageSource;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class EntityPigZombie extends EntityZombie {
	private int angerLevel = 0;
	private int randomSoundDelay = 0;

	public EntityPigZombie(World var1) {
		super(var1);
		this.texture = "/mob/pigzombie.png";
		this.moveSpeed = 0.5F;
		this.isImmuneToFire = true;
	}

	protected boolean isAIEnabled() {
		return false;
	}

	public void onUpdate() {
		this.moveSpeed = this.entityToAttack != null ? 0.95F : 0.5F;
		if(this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
			this.playSound("mob.zombiepig.zpigangry", this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
		}

		super.onUpdate();
	}

	public String getTexture() {
		return "/mob/pigzombie.png";
	}

	public boolean getCanSpawnHere() {
		return this.worldObj.difficultySetting > 0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setShort("Anger", (short)this.angerLevel);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.angerLevel = var1.getShort("Anger");
	}

	protected Entity findPlayerToAttack() {
		return this.angerLevel == 0 ? null : super.findPlayerToAttack();
	}

	public boolean attackEntityFrom(DamageSource var1, int var2) {
		if(this.isEntityInvulnerable()) {
			return false;
		} else {
			Entity var3 = var1.getEntity();
			if(var3 instanceof EntityPlayer) {
				List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

				for(int var5 = 0; var5 < var4.size(); ++var5) {
					Entity var6 = (Entity)var4.get(var5);
					if(var6 instanceof EntityPigZombie) {
						EntityPigZombie var7 = (EntityPigZombie)var6;
						var7.becomeAngryAt(var3);
					}
				}

				this.becomeAngryAt(var3);
			}

			return super.attackEntityFrom(var1, var2);
		}
	}

	private void becomeAngryAt(Entity var1) {
		this.entityToAttack = var1;
		this.angerLevel = 400 + this.rand.nextInt(400);
		this.randomSoundDelay = this.rand.nextInt(40);
	}

	protected String getLivingSound() {
		return "mob.zombiepig.zpig";
	}

	protected String getHurtSound() {
		return "mob.zombiepig.zpighurt";
	}

	protected String getDeathSound() {
		return "mob.zombiepig.zpigdeath";
	}

	protected void dropFewItems(boolean var1, int var2) {
		int var3 = this.rand.nextInt(2 + var2);

		int var4;
		for(var4 = 0; var4 < var3; ++var4) {
			this.dropItem(Item.rottenFlesh.itemID, 1);
		}

		var3 = this.rand.nextInt(2 + var2);

		for(var4 = 0; var4 < var3; ++var4) {
			this.dropItem(Item.goldNugget.itemID, 1);
		}

	}

	public boolean interact(EntityPlayer var1) {
		return false;
	}

	protected void dropRareDrop(int var1) {
		this.dropItem(Item.ingotGold.itemID, 1);
	}

	protected int getDropItemId() {
		return Item.rottenFlesh.itemID;
	}

	protected void addRandomArmor() {
		this.setCurrentItemOrArmor(0, new ItemStack(Item.swordGold));
	}

	public void initCreature() {
		super.initCreature();
		this.setVillager(false);
	}

	public int getAttackStrength(Entity var1) {
		ItemStack var2 = this.getHeldItem();
		int var3 = 5;
		if(var2 != null) {
			var3 += var2.getDamageVsEntity(this);
		}

		return var3;
	}
}
