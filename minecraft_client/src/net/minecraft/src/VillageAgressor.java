package net.minecraft.src;

import net.minecraft.src.entity.EntityLiving;

class VillageAgressor {
	public EntityLiving agressor;
	public int agressionTime;
	final Village villageObj;

	VillageAgressor(Village var1, EntityLiving var2, int var3) {
		this.villageObj = var1;
		this.agressor = var2;
		this.agressionTime = var3;
	}
}
