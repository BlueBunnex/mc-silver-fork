package net.minecraft.src.entity;

import net.minecraft.src.IAnimals;
import net.minecraft.src.worldgen.World;

public abstract class EntityAmbientCreature extends EntityLiving implements IAnimals {
	public EntityAmbientCreature(World var1) {
		super(var1);
	}
}
