package net.minecraft.src;

import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.EntitySheep;

// what the FUCK IS THIS CLASS??
public class ContainerSheep extends Container {
	
	private final EntitySheep field_90034_a;

	public ContainerSheep(EntitySheep var1) {
		this.field_90034_a = var1;
	}

	public boolean canInteractWith(EntityPlayer var1) {
		return false;
	}
}
