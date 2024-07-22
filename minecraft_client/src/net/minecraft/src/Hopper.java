package net.minecraft.src;

import net.minecraft.src.worldgen.World;

public interface Hopper extends IInventory {
	World getWorldObj();

	double getXPos();

	double getYPos();

	double getZPos();
}
