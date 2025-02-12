package net.minecraft.src.entity;

import net.minecraft.src.worldgen.World;

public class EntityGiantZombie extends EntityMob {
	public EntityGiantZombie(World var1) {
		super(var1);
		this.texture = "/mob/zombie.png";
		this.moveSpeed = 0.5F;
		this.yOffset *= 6.0F;
		this.setSize(this.width * 6.0F, this.height * 6.0F);
	}

	public int getMaxHealth() {
		return 100;
	}

	public float getBlockPathWeight(int var1, int var2, int var3) {
		return this.worldObj.getLightBrightness(var1, var2, var3) - 0.5F;
	}

	public int getAttackStrength(Entity var1) {
		return 50;
	}
}
