package net.minecraft.src.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class ItemGlassBottle extends Item {
	public ItemGlassBottle(int var1) {
		super(var1);
		this.setCreativeTab(CreativeTabs.tabBrewing);
	}

	public Icon getIconFromDamage(int var1) {
		return Item.potion.getIconFromDamage(0);
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, true);
		if(var4 == null) {
			return var1;
		} else {
			if(var4.typeOfHit == EnumMovingObjectType.TILE) {
				int var5 = var4.blockX;
				int var6 = var4.blockY;
				int var7 = var4.blockZ;
				if(!var2.canMineBlock(var3, var5, var6, var7)) {
					return var1;
				}

				if(!var3.canPlayerEdit(var5, var6, var7, var4.sideHit, var1)) {
					return var1;
				}

				if(var2.getBlockMaterial(var5, var6, var7) == Material.water) {
					--var1.stackSize;
					if(var1.stackSize <= 0) {
						return new ItemStack(Item.potion);
					}

					if(!var3.inventory.addItemStackToInventory(new ItemStack(Item.potion))) {
						var3.dropPlayerItem(new ItemStack(Item.potion.itemID, 1, 0));
					}
				}
			}

			return var1;
		}
	}

	public void registerIcons(IconRegister var1) {
	}
}
