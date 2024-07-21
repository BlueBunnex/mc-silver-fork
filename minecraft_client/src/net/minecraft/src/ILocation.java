package net.minecraft.src;

import net.minecraft.src.worldgen.World;

public interface ILocation extends IPosition {
	World getWorld();
}
