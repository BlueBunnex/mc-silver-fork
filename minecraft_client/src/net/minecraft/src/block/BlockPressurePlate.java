package net.minecraft.src.block;

import java.util.Iterator;
import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMobType;
import net.minecraft.src.Material;
import net.minecraft.src.worldgen.World;

public class BlockPressurePlate extends BlockBasePressurePlate {
	private EnumMobType triggerMobType;

	protected BlockPressurePlate(int var1, String var2, Material var3, EnumMobType var4) {
		super(var1, var2, var3);
		this.triggerMobType = var4;
	}

	protected int getMetaFromWeight(int var1) {
		return var1 > 0 ? 1 : 0;
	}

	protected int getPowerSupply(int var1) {
		return var1 == 1 ? 15 : 0;
	}

	protected int getPlateState(World var1, int var2, int var3, int var4) {
		List var5 = null;
		if(this.triggerMobType == EnumMobType.everything) {
			var5 = var1.getEntitiesWithinAABBExcludingEntity((Entity)null, this.getSensitiveAABB(var2, var3, var4));
		}

		if(this.triggerMobType == EnumMobType.mobs) {
			var5 = var1.getEntitiesWithinAABB(EntityLiving.class, this.getSensitiveAABB(var2, var3, var4));
		}

		if(this.triggerMobType == EnumMobType.players) {
			var5 = var1.getEntitiesWithinAABB(EntityPlayer.class, this.getSensitiveAABB(var2, var3, var4));
		}

		if(!var5.isEmpty()) {
			Iterator var6 = var5.iterator();

			while(var6.hasNext()) {
				Entity var7 = (Entity)var6.next();
				if(!var7.doesEntityNotTriggerPressurePlate()) {
					return 15;
				}
			}
		}

		return 0;
	}
}
