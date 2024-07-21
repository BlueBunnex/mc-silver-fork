package net.minecraft.src.item;

import net.minecraft.src.Packet;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class ItemMapBase extends Item {
	protected ItemMapBase(int var1) {
		super(var1);
	}

	public boolean isMap() {
		return true;
	}

	public Packet createMapDataPacket(ItemStack var1, World var2, EntityPlayer var3) {
		return null;
	}
}
