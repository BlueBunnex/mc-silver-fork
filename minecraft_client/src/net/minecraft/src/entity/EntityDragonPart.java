package net.minecraft.src.entity;

import net.minecraft.src.DamageSource;
import net.minecraft.src.IEntityMultiPart;
import net.minecraft.src.NBTTagCompound;

public class EntityDragonPart extends Entity {
	public final IEntityMultiPart entityDragonObj;
	public final String name;

	public EntityDragonPart(IEntityMultiPart var1, String var2, float var3, float var4) {
		super(var1.func_82194_d());
		this.setSize(var3, var4);
		this.entityDragonObj = var1;
		this.name = var2;
	}

	protected void entityInit() {
	}

	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	protected void writeEntityToNBT(NBTTagCompound var1) {
	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public boolean attackEntityFrom(DamageSource var1, int var2) {
		return this.isEntityInvulnerable() ? false : this.entityDragonObj.attackEntityFromPart(this, var1, var2);
	}

	public boolean isEntityEqual(Entity var1) {
		return this == var1 || this.entityDragonObj == var1;
	}
}
