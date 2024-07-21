package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.block.Block;
import net.minecraft.src.worldgen.World;

public class EntitySheep extends EntityAnimal {
	private final InventoryCrafting field_90016_e = new InventoryCrafting(new ContainerSheep(this), 2, 1);
	public static final float[][] fleeceColorTable = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
	private int sheepTimer;
	private EntityAIEatGrass aiEatGrass = new EntityAIEatGrass(this);

	public EntitySheep(World var1) {
		super(var1);
		this.texture = "/mob/sheep.png";
		this.setSize(0.9F, 1.3F);
		float var2 = 0.23F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(2, new EntityAIMate(this, var2));
		this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25F));
		this.tasks.addTask(5, this.aiEatGrass);
		this.tasks.addTask(6, new EntityAIWander(this, var2));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.field_90016_e.setInventorySlotContents(0, new ItemStack(Item.dyePowder, 1, 0));
		this.field_90016_e.setInventorySlotContents(1, new ItemStack(Item.dyePowder, 1, 0));
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void updateAITasks() {
		this.sheepTimer = this.aiEatGrass.getEatGrassTick();
		super.updateAITasks();
	}

	public void onLivingUpdate() {
		if(this.worldObj.isRemote) {
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.onLivingUpdate();
	}

	public int getMaxHealth() {
		return 8;
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	protected void dropFewItems(boolean var1, int var2) {
		if(!this.getSheared()) {
			this.entityDropItem(new ItemStack(Block.cloth.blockID, 1, this.getFleeceColor()), 0.0F);
		}

	}

	protected int getDropItemId() {
		return Block.cloth.blockID;
	}

	public void handleHealthUpdate(byte var1) {
		if(var1 == 10) {
			this.sheepTimer = 40;
		} else {
			super.handleHealthUpdate(var1);
		}

	}

	public float func_70894_j(float var1) {
		return this.sheepTimer <= 0 ? 0.0F : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : (this.sheepTimer < 4 ? ((float)this.sheepTimer - var1) / 4.0F : -((float)(this.sheepTimer - 40) - var1) / 4.0F));
	}

	public float func_70890_k(float var1) {
		if(this.sheepTimer > 4 && this.sheepTimer <= 36) {
			float var2 = ((float)(this.sheepTimer - 4) - var1) / 32.0F;
			return (float)Math.PI * 0.2F + (float)Math.PI * 0.07F * MathHelper.sin(var2 * 28.7F);
		} else {
			return this.sheepTimer > 0 ? (float)Math.PI * 0.2F : this.rotationPitch / (180.0F / (float)Math.PI);
		}
	}

	public boolean interact(EntityPlayer var1) {
		ItemStack var2 = var1.inventory.getCurrentItem();
		if(var2 != null && var2.itemID == Item.shears.itemID && !this.getSheared() && !this.isChild()) {
			if(!this.worldObj.isRemote) {
				this.setSheared(true);
				int var3 = 1 + this.rand.nextInt(3);

				for(int var4 = 0; var4 < var3; ++var4) {
					EntityItem var5 = this.entityDropItem(new ItemStack(Block.cloth.blockID, 1, this.getFleeceColor()), 1.0F);
					var5.motionY += (double)(this.rand.nextFloat() * 0.05F);
					var5.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
					var5.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
				}
			}

			var2.damageItem(1, var1);
			this.playSound("mob.sheep.shear", 1.0F, 1.0F);
		}

		return super.interact(var1);
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setBoolean("Sheared", this.getSheared());
		var1.setByte("Color", (byte)this.getFleeceColor());
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.setSheared(var1.getBoolean("Sheared"));
		this.setFleeceColor(var1.getByte("Color"));
	}

	protected String getLivingSound() {
		return "mob.sheep.say";
	}

	protected String getHurtSound() {
		return "mob.sheep.say";
	}

	protected String getDeathSound() {
		return "mob.sheep.say";
	}

	protected void playStepSound(int var1, int var2, int var3, int var4) {
		this.playSound("mob.sheep.step", 0.15F, 1.0F);
	}

	public int getFleeceColor() {
		return this.dataWatcher.getWatchableObjectByte(16) & 15;
	}

	public void setFleeceColor(int var1) {
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 240 | var1 & 15)));
	}

	public boolean getSheared() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
	}

	public void setSheared(boolean var1) {
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if(var1) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 16)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -17)));
		}

	}

	public static int getRandomFleeceColor(Random var0) {
		int var1 = var0.nextInt(100);
		return var1 < 5 ? 15 : (var1 < 10 ? 7 : (var1 < 15 ? 8 : (var1 < 18 ? 12 : (var0.nextInt(500) == 0 ? 6 : 0))));
	}

	public EntitySheep func_90015_b(EntityAgeable var1) {
		EntitySheep var2 = (EntitySheep)var1;
		EntitySheep var3 = new EntitySheep(this.worldObj);
		int var4 = this.func_90014_a(this, var2);
		var3.setFleeceColor(15 - var4);
		return var3;
	}

	public void eatGrassBonus() {
		this.setSheared(false);
		if(this.isChild()) {
			int var1 = this.getGrowingAge() + 1200;
			if(var1 > 0) {
				var1 = 0;
			}

			this.setGrowingAge(var1);
		}

	}

	public void initCreature() {
		this.setFleeceColor(getRandomFleeceColor(this.worldObj.rand));
	}

	private int func_90014_a(EntityAnimal var1, EntityAnimal var2) {
		int var3 = this.func_90013_b(var1);
		int var4 = this.func_90013_b(var2);
		this.field_90016_e.getStackInSlot(0).setItemDamage(var3);
		this.field_90016_e.getStackInSlot(1).setItemDamage(var4);
		ItemStack var5 = CraftingManager.getInstance().findMatchingRecipe(this.field_90016_e, ((EntitySheep)var1).worldObj);
		int var6;
		if(var5 != null && var5.getItem().itemID == Item.dyePowder.itemID) {
			var6 = var5.getItemDamage();
		} else {
			var6 = this.worldObj.rand.nextBoolean() ? var3 : var4;
		}

		return var6;
	}

	private int func_90013_b(EntityAnimal var1) {
		return 15 - ((EntitySheep)var1).getFleeceColor();
	}

	public EntityAgeable createChild(EntityAgeable var1) {
		return this.func_90015_b(var1);
	}
}
