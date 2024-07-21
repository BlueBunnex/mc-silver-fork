package net.minecraft.src;

import java.util.Random;

public class BlockGravel extends BlockSand {
	public BlockGravel(int var1) {
		super(var1);
	}

	public int idDropped(int var1, Random var2, int var3) {
		if(var3 > 3) {
			var3 = 3;
		}

		return var2.nextInt(10 - var3 * 3) == 0 ? Item.flint.itemID : this.blockID;
	}
}
