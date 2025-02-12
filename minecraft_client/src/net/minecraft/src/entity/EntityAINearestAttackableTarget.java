package net.minecraft.src.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.src.IEntitySelector;

public class EntityAINearestAttackableTarget extends EntityAITarget {
	EntityLiving targetEntity;
	Class targetClass;
	int targetChance;
	private final IEntitySelector field_82643_g;
	private EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;

	public EntityAINearestAttackableTarget(EntityLiving var1, Class var2, float var3, int var4, boolean var5) {
		this(var1, var2, var3, var4, var5, false);
	}

	public EntityAINearestAttackableTarget(EntityLiving var1, Class var2, float var3, int var4, boolean var5, boolean var6) {
		this(var1, var2, var3, var4, var5, var6, (IEntitySelector)null);
	}

	public EntityAINearestAttackableTarget(EntityLiving var1, Class var2, float var3, int var4, boolean var5, boolean var6, IEntitySelector var7) {
		super(var1, var3, var5, var6);
		this.targetClass = var2;
		this.targetDistance = var3;
		this.targetChance = var4;
		this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTargetSorter(this, var1);
		this.field_82643_g = var7;
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		if(this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
			return false;
		} else {
			if(this.targetClass == EntityPlayer.class) {
				EntityPlayer var1 = this.taskOwner.worldObj.getClosestVulnerablePlayerToEntity(this.taskOwner, (double)this.targetDistance);
				if(this.isSuitableTarget(var1, false)) {
					this.targetEntity = var1;
					return true;
				}
			} else {
				List var5 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand((double)this.targetDistance, 4.0D, (double)this.targetDistance), this.field_82643_g);
				Collections.sort(var5, this.theNearestAttackableTargetSorter);
				Iterator var2 = var5.iterator();

				while(var2.hasNext()) {
					Entity var3 = (Entity)var2.next();
					EntityLiving var4 = (EntityLiving)var3;
					if(this.isSuitableTarget(var4, false)) {
						this.targetEntity = var4;
						return true;
					}
				}
			}

			return false;
		}
	}

	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}
}
