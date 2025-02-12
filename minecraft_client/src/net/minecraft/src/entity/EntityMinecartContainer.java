package net.minecraft.src.entity;

import net.minecraft.src.Container;
import net.minecraft.src.DamageSource;
import net.minecraft.src.IInventory;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public abstract class EntityMinecartContainer extends EntityMinecart implements IInventory {
	private ItemStack[] minecartContainerItems = new ItemStack[36];
	private boolean dropContentsWhenDead = true;

	public EntityMinecartContainer(World var1) {
		super(var1);
	}

	public EntityMinecartContainer(World var1, double var2, double var4, double var6) {
		super(var1, var2, var4, var6);
	}

	public void killMinecart(DamageSource var1) {
		super.killMinecart(var1);

		for(int var2 = 0; var2 < this.getSizeInventory(); ++var2) {
			ItemStack var3 = this.getStackInSlot(var2);
			if(var3 != null) {
				float var4 = this.rand.nextFloat() * 0.8F + 0.1F;
				float var5 = this.rand.nextFloat() * 0.8F + 0.1F;
				float var6 = this.rand.nextFloat() * 0.8F + 0.1F;

				while(var3.stackSize > 0) {
					int var7 = this.rand.nextInt(21) + 10;
					if(var7 > var3.stackSize) {
						var7 = var3.stackSize;
					}

					var3.stackSize -= var7;
					EntityItem var8 = new EntityItem(this.worldObj, this.posX + (double)var4, this.posY + (double)var5, this.posZ + (double)var6, new ItemStack(var3.itemID, var7, var3.getItemDamage()));
					float var9 = 0.05F;
					var8.motionX = (double)((float)this.rand.nextGaussian() * var9);
					var8.motionY = (double)((float)this.rand.nextGaussian() * var9 + 0.2F);
					var8.motionZ = (double)((float)this.rand.nextGaussian() * var9);
					this.worldObj.spawnEntityInWorld(var8);
				}
			}
		}

	}

	public ItemStack getStackInSlot(int var1) {
		return this.minecartContainerItems[var1];
	}

	public ItemStack decrStackSize(int var1, int var2) {
		if(this.minecartContainerItems[var1] != null) {
			ItemStack var3;
			if(this.minecartContainerItems[var1].stackSize <= var2) {
				var3 = this.minecartContainerItems[var1];
				this.minecartContainerItems[var1] = null;
				return var3;
			} else {
				var3 = this.minecartContainerItems[var1].splitStack(var2);
				if(this.minecartContainerItems[var1].stackSize == 0) {
					this.minecartContainerItems[var1] = null;
				}

				return var3;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int var1) {
		if(this.minecartContainerItems[var1] != null) {
			ItemStack var2 = this.minecartContainerItems[var1];
			this.minecartContainerItems[var1] = null;
			return var2;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.minecartContainerItems[var1] = var2;
		if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
			var2.stackSize = this.getInventoryStackLimit();
		}

	}

	public void onInventoryChanged() {
	}

	public boolean isUseableByPlayer(EntityPlayer var1) {
		return this.isDead ? false : var1.getDistanceSqToEntity(this) <= 64.0D;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	public boolean isStackValidForSlot(int var1, ItemStack var2) {
		return true;
	}

	public String getInvName() {
		return this.isInvNameLocalized() ? this.func_95999_t() : "container.minecart";
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void travelToDimension(int var1) {
		this.dropContentsWhenDead = false;
		super.travelToDimension(var1);
	}

	public void setDead() {
		if(this.dropContentsWhenDead) {
			for(int var1 = 0; var1 < this.getSizeInventory(); ++var1) {
				ItemStack var2 = this.getStackInSlot(var1);
				if(var2 != null) {
					float var3 = this.rand.nextFloat() * 0.8F + 0.1F;
					float var4 = this.rand.nextFloat() * 0.8F + 0.1F;
					float var5 = this.rand.nextFloat() * 0.8F + 0.1F;

					while(var2.stackSize > 0) {
						int var6 = this.rand.nextInt(21) + 10;
						if(var6 > var2.stackSize) {
							var6 = var2.stackSize;
						}

						var2.stackSize -= var6;
						EntityItem var7 = new EntityItem(this.worldObj, this.posX + (double)var3, this.posY + (double)var4, this.posZ + (double)var5, new ItemStack(var2.itemID, var6, var2.getItemDamage()));
						if(var2.hasTagCompound()) {
							var7.getEntityItem().setTagCompound((NBTTagCompound)var2.getTagCompound().copy());
						}

						float var8 = 0.05F;
						var7.motionX = (double)((float)this.rand.nextGaussian() * var8);
						var7.motionY = (double)((float)this.rand.nextGaussian() * var8 + 0.2F);
						var7.motionZ = (double)((float)this.rand.nextGaussian() * var8);
						this.worldObj.spawnEntityInWorld(var7);
					}
				}
			}
		}

		super.setDead();
	}

	protected void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		NBTTagList var2 = new NBTTagList();

		for(int var3 = 0; var3 < this.minecartContainerItems.length; ++var3) {
			if(this.minecartContainerItems[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var3);
				this.minecartContainerItems[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		var1.setTag("Items", var2);
	}

	protected void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		NBTTagList var2 = var1.getTagList("Items");
		this.minecartContainerItems = new ItemStack[this.getSizeInventory()];

		for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
			int var5 = var4.getByte("Slot") & 255;
			if(var5 >= 0 && var5 < this.minecartContainerItems.length) {
				this.minecartContainerItems[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}

	}

	public boolean interact(EntityPlayer var1) {
		if(!this.worldObj.isRemote) {
			var1.displayGUIChest(this);
		}

		return true;
	}

	protected void applyDrag() {
		int var1 = 15 - Container.calcRedstoneFromInventory(this);
		float var2 = 0.98F + (float)var1 * 0.001F;
		this.motionX *= (double)var2;
		this.motionY *= 0.0D;
		this.motionZ *= (double)var2;
	}
}
