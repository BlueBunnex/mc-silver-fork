package net.minecraft.src.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumAction;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class ItemBucketMilk extends Item {
	public ItemBucketMilk(int var1) {
		super(var1);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3) {
		if(!var3.capabilities.isCreativeMode) {
			--var1.stackSize;
		}

		if(!var2.isRemote) {
			var3.clearActivePotions();
		}

		return var1.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : var1;
	}

	public int getMaxItemUseDuration(ItemStack var1) {
		return 32;
	}

	public EnumAction getItemUseAction(ItemStack var1) {
		return EnumAction.drink;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
		return var1;
	}
}
