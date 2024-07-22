package net.minecraft.src;

import net.minecraft.src.entity.EntityPlayer;

public interface IPlayerFileData {
	void writePlayerData(EntityPlayer var1);

	NBTTagCompound readPlayerData(EntityPlayer var1);

	String[] getAvailablePlayerDat();
}
