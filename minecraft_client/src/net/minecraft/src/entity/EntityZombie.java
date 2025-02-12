package net.minecraft.src.entity;

import java.util.Calendar;

import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.block.Block;
import net.minecraft.src.item.Item;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class EntityZombie extends EntityMob {
	private int conversionTime = 0;

	public EntityZombie(World var1) {
		super(var1);
		this.texture = "/mob/zombie.png";
		this.moveSpeed = 0.23F;
		this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, this.moveSpeed, true));
		this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, this.moveSpeed, false));
		this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));
	}

	protected int func_96121_ay() {
		return 40;
	}

	public float getSpeedModifier() {
		return super.getSpeedModifier() * (this.isChild() ? 1.5F : 1.0F);
	}

	protected void entityInit() {
		super.entityInit();
		this.getDataWatcher().addObject(12, Byte.valueOf((byte)0));
		this.getDataWatcher().addObject(13, Byte.valueOf((byte)0));
		this.getDataWatcher().addObject(14, Byte.valueOf((byte)0));
	}

	public String getTexture() {
		return this.isVillager() ? "/mob/zombie_villager.png" : "/mob/zombie.png";
	}

	public int getMaxHealth() {
		return 20;
	}

	public int getTotalArmorValue() {
		int var1 = super.getTotalArmorValue() + 2;
		if(var1 > 20) {
			var1 = 20;
		}

		return var1;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public boolean isChild() {
		return this.getDataWatcher().getWatchableObjectByte(12) == 1;
	}

	public void setChild(boolean var1) {
		this.getDataWatcher().updateObject(12, Byte.valueOf((byte)1));
	}

	public boolean isVillager() {
		return this.getDataWatcher().getWatchableObjectByte(13) == 1;
	}

	public void setVillager(boolean var1) {
		this.getDataWatcher().updateObject(13, Byte.valueOf((byte)(var1 ? 1 : 0)));
	}

	public void onLivingUpdate() {
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild()) {
			float var1 = this.getBrightness(1.0F);
			if(var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
				boolean var2 = true;
				ItemStack var3 = this.getCurrentItemOrArmor(4);
				if(var3 != null) {
					if(var3.isItemStackDamageable()) {
						var3.setItemDamage(var3.getItemDamageForDisplay() + this.rand.nextInt(2));
						if(var3.getItemDamageForDisplay() >= var3.getMaxDamage()) {
							this.renderBrokenItemStack(var3);
							this.setCurrentItemOrArmor(4, (ItemStack)null);
						}
					}

					var2 = false;
				}

				if(var2) {
					this.setFire(8);
				}
			}
		}

		super.onLivingUpdate();
	}

	public void onUpdate() {
		if(!this.worldObj.isRemote && this.isConverting()) {
			int var1 = this.getConversionTimeBoost();
			this.conversionTime -= var1;
			if(this.conversionTime <= 0) {
				this.convertToVillager();
			}
		}

		super.onUpdate();
	}

	public boolean attackEntityAsMob(Entity var1) {
		boolean var2 = super.attackEntityAsMob(var1);
		if(var2 && this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)this.worldObj.difficultySetting * 0.3F) {
			var1.setFire(2 * this.worldObj.difficultySetting);
		}

		return var2;
	}

	public int getAttackStrength(Entity var1) {
		ItemStack var2 = this.getHeldItem();
		float var3 = (float)(this.getMaxHealth() - this.getHealth()) / (float)this.getMaxHealth();
		int var4 = 3 + MathHelper.floor_float(var3 * 4.0F);
		if(var2 != null) {
			var4 += var2.getDamageVsEntity(this);
		}

		return var4;
	}

	protected String getLivingSound() {
		return "mob.zombie.say";
	}

	protected String getHurtSound() {
		return "mob.zombie.hurt";
	}

	protected String getDeathSound() {
		return "mob.zombie.death";
	}

	protected void playStepSound(int var1, int var2, int var3, int var4) {
		this.playSound("mob.zombie.step", 0.15F, 1.0F);
	}

	protected int getDropItemId() {
		return Item.rottenFlesh.itemID;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	protected void dropRareDrop(int var1) {
		switch(this.rand.nextInt(3)) {
		case 0:
			this.dropItem(Item.ingotIron.itemID, 1);
			break;
		case 1:
			this.dropItem(Item.carrot.itemID, 1);
			break;
		case 2:
			this.dropItem(Item.potato.itemID, 1);
		}

	}

	protected void addRandomArmor() {
		super.addRandomArmor();
		if(this.rand.nextFloat() < (this.worldObj.difficultySetting == 3 ? 0.05F : 0.01F)) {
			int var1 = this.rand.nextInt(3);
			if(var1 == 0) {
				this.setCurrentItemOrArmor(0, new ItemStack(Item.swordIron));
			} else {
				this.setCurrentItemOrArmor(0, new ItemStack(Item.shovelIron));
			}
		}

	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		if(this.isChild()) {
			var1.setBoolean("IsBaby", true);
		}

		if(this.isVillager()) {
			var1.setBoolean("IsVillager", true);
		}

		var1.setInteger("ConversionTime", this.isConverting() ? this.conversionTime : -1);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		if(var1.getBoolean("IsBaby")) {
			this.setChild(true);
		}

		if(var1.getBoolean("IsVillager")) {
			this.setVillager(true);
		}

		if(var1.hasKey("ConversionTime") && var1.getInteger("ConversionTime") > -1) {
			this.startConversion(var1.getInteger("ConversionTime"));
		}

	}

	public void onKillEntity(EntityLiving var1) {
		super.onKillEntity(var1);
		if(this.worldObj.difficultySetting >= 2 && var1 instanceof EntityVillager) {
			if(this.worldObj.difficultySetting == 2 && this.rand.nextBoolean()) {
				return;
			}

			EntityZombie var2 = new EntityZombie(this.worldObj);
			var2.func_82149_j(var1);
			this.worldObj.removeEntity(var1);
			var2.initCreature();
			var2.setVillager(true);
			if(var1.isChild()) {
				var2.setChild(true);
			}

			this.worldObj.spawnEntityInWorld(var2);
			this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
		}

	}

	public void initCreature() {
		this.setCanPickUpLoot(this.rand.nextFloat() < pickUpLootProability[this.worldObj.difficultySetting]);
		if(this.worldObj.rand.nextFloat() < 0.05F) {
			this.setVillager(true);
		}

		this.addRandomArmor();
		this.func_82162_bC();
		if(this.getCurrentItemOrArmor(4) == null) {
			Calendar var1 = this.worldObj.getCurrentDate();
			if(var1.get(2) + 1 == 10 && var1.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
				this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Block.pumpkinLantern : Block.pumpkin));
				this.equipmentDropChances[4] = 0.0F;
			}
		}

	}

	public boolean interact(EntityPlayer var1) {
		ItemStack var2 = var1.getCurrentEquippedItem();
		if(var2 != null && var2.getItem() == Item.appleGold && var2.getItemDamage() == 0 && this.isVillager() && this.isPotionActive(Potion.weakness)) {
			if(!var1.capabilities.isCreativeMode) {
				--var2.stackSize;
			}

			if(var2.stackSize <= 0) {
				var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
			}

			if(!this.worldObj.isRemote) {
				this.startConversion(this.rand.nextInt(2401) + 3600);
			}

			return true;
		} else {
			return false;
		}
	}

	protected void startConversion(int var1) {
		this.conversionTime = var1;
		this.getDataWatcher().updateObject(14, Byte.valueOf((byte)1));
		this.removePotionEffect(Potion.weakness.id);
		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, var1, Math.min(this.worldObj.difficultySetting - 1, 0)));
		this.worldObj.setEntityState(this, (byte)16);
	}

	public void handleHealthUpdate(byte var1) {
		if(var1 == 16) {
			this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "mob.zombie.remedy", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
		} else {
			super.handleHealthUpdate(var1);
		}

	}

	public boolean isConverting() {
		return this.getDataWatcher().getWatchableObjectByte(14) == 1;
	}

	protected void convertToVillager() {
		EntityVillager var1 = new EntityVillager(this.worldObj);
		var1.func_82149_j(this);
		var1.initCreature();
		var1.func_82187_q();
		if(this.isChild()) {
			var1.setGrowingAge(-24000);
		}

		this.worldObj.removeEntity(this);
		this.worldObj.spawnEntityInWorld(var1);
		var1.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
		this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
	}

	protected int getConversionTimeBoost() {
		int var1 = 1;
		if(this.rand.nextFloat() < 0.01F) {
			int var2 = 0;

			for(int var3 = (int)this.posX - 4; var3 < (int)this.posX + 4 && var2 < 14; ++var3) {
				for(int var4 = (int)this.posY - 4; var4 < (int)this.posY + 4 && var2 < 14; ++var4) {
					for(int var5 = (int)this.posZ - 4; var5 < (int)this.posZ + 4 && var2 < 14; ++var5) {
						int var6 = this.worldObj.getBlockId(var3, var4, var5);
						if(var6 == Block.fenceIron.blockID || var6 == Block.bed.blockID) {
							if(this.rand.nextFloat() < 0.3F) {
								++var1;
							}

							++var2;
						}
					}
				}
			}
		}

		return var1;
	}
}
