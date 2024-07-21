package net.minecraft.src;

import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPig;

public class ItemSaddle extends Item {
	public ItemSaddle(int var1) {
		super(var1);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {
		if(var2 instanceof EntityPig) {
			EntityPig var3 = (EntityPig)var2;
			if(!var3.getSaddled() && !var3.isChild()) {
				var3.setSaddled(true);
				--var1.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3) {
		this.itemInteractionForEntity(var1, var2);
		return true;
	}
}
