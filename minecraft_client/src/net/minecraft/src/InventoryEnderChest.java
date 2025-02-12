package net.minecraft.src;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.tile.TileEntityEnderChest;
import net.minecraft.src.item.ItemStack;

public class InventoryEnderChest extends InventoryBasic {
	private TileEntityEnderChest associatedChest;

	public InventoryEnderChest() {
		super("container.enderchest", false, 27);
	}

	public void setAssociatedChest(TileEntityEnderChest var1) {
		this.associatedChest = var1;
	}

	public void loadInventoryFromNBT(NBTTagList var1) {
		int var2;
		for(var2 = 0; var2 < this.getSizeInventory(); ++var2) {
			this.setInventorySlotContents(var2, (ItemStack)null);
		}

		for(var2 = 0; var2 < var1.tagCount(); ++var2) {
			NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
			int var4 = var3.getByte("Slot") & 255;
			if(var4 >= 0 && var4 < this.getSizeInventory()) {
				this.setInventorySlotContents(var4, ItemStack.loadItemStackFromNBT(var3));
			}
		}

	}

	public NBTTagList saveInventoryToNBT() {
		NBTTagList var1 = new NBTTagList("EnderItems");

		for(int var2 = 0; var2 < this.getSizeInventory(); ++var2) {
			ItemStack var3 = this.getStackInSlot(var2);
			if(var3 != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var2);
				var3.writeToNBT(var4);
				var1.appendTag(var4);
			}
		}

		return var1;
	}

	public boolean isUseableByPlayer(EntityPlayer var1) {
		return this.associatedChest != null && !this.associatedChest.isUseableByPlayer(var1) ? false : super.isUseableByPlayer(var1);
	}

	public void openChest() {
		if(this.associatedChest != null) {
			this.associatedChest.openChest();
		}

		super.openChest();
	}

	public void closeChest() {
		if(this.associatedChest != null) {
			this.associatedChest.closeChest();
		}

		super.closeChest();
		this.associatedChest = null;
	}

	public boolean isStackValidForSlot(int var1, ItemStack var2) {
		return true;
	}
}
