package net.minecraft.src.block;

import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

public class BlockRail extends BlockRailBase {
	private Icon theIcon;

	protected BlockRail(int var1) {
		super(var1, false);
	}

	public Icon getIcon(int var1, int var2) {
		return var2 >= 6 ? this.theIcon : this.blockIcon;
	}

	public void registerIcons(IconRegister var1) {
		super.registerIcons(var1);
		this.theIcon = var1.registerIcon("rail_turn");
	}

	protected void func_94358_a(World var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		if(var7 > 0 && Block.blocksList[var7].canProvidePower() && (new BlockBaseRailLogic(this, var1, var2, var3, var4)).getNumberOfAdjacentTracks() == 3) {
			this.refreshTrackShape(var1, var2, var3, var4, false);
		}

	}
}
