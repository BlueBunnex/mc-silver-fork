package net.minecraft.src;

import java.util.Random;

public class BlockLockedChest extends Block {
	protected BlockLockedChest(int var1) {
		super(var1, Material.wood);
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return true;
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		var1.setBlockToAir(var2, var3, var4);
	}

	public void registerIcons(IconRegister var1) {
	}
}
