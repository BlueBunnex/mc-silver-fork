package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityCommandBlock;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class BlockCommandBlock extends BlockContainer {
	public BlockCommandBlock(int var1) {
		super(var1, Material.iron);
	}

	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityCommandBlock();
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		if(!var1.isRemote) {
			boolean var6 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
			int var7 = var1.getBlockMetadata(var2, var3, var4);
			boolean var8 = (var7 & 1) != 0;
			if(var6 && !var8) {
				var1.setBlockMetadataWithNotify(var2, var3, var4, var7 | 1, 4);
				var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
			} else if(!var6 && var8) {
				var1.setBlockMetadataWithNotify(var2, var3, var4, var7 & -2, 4);
			}
		}

	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
		if(var6 != null && var6 instanceof TileEntityCommandBlock) {
			TileEntityCommandBlock var7 = (TileEntityCommandBlock)var6;
			var7.func_96102_a(var7.executeCommandOnPowered(var1));
			var1.func_96440_m(var2, var3, var4, this.blockID);
		}

	}

	public int tickRate(World var1) {
		return 1;
	}

	public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
		TileEntityCommandBlock var10 = (TileEntityCommandBlock)var1.getBlockTileEntity(var2, var3, var4);
		if(var10 != null) {
			var5.displayGUIEditSign(var10);
		}

		return true;
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
		TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
		return var6 != null && var6 instanceof TileEntityCommandBlock ? ((TileEntityCommandBlock)var6).func_96103_d() : 0;
	}

	public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6) {
		TileEntityCommandBlock var7 = (TileEntityCommandBlock)var1.getBlockTileEntity(var2, var3, var4);
		if(var6.hasDisplayName()) {
			var7.setCommandSenderName(var6.getDisplayName());
		}

	}
}
