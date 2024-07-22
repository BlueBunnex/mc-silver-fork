package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.StatList;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.item.enchantment.EnchantmentHelper;
import net.minecraft.src.worldgen.World;

public class BlockIce extends BlockBreakable {
	public BlockIce(int var1) {
		super(var1, "ice", Material.ice, false);
		this.slipperiness = 0.98F;
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int getRenderBlockPass() {
		return 1;
	}

	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		return super.shouldSideBeRendered(var1, var2, var3, var4, 1 - var5);
	}

	public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
		var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		var2.addExhaustion(0.025F);
		if(this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(var2)) {
			ItemStack var9 = this.createStackedBlock(var6);
			if(var9 != null) {
				this.dropBlockAsItem_do(var1, var3, var4, var5, var9);
			}
		} else {
			if(var1.provider.isHellWorld) {
				var1.setBlockToAir(var3, var4, var5);
				return;
			}

			int var7 = EnchantmentHelper.getFortuneModifier(var2);
			this.dropBlockAsItem(var1, var3, var4, var5, var6, var7);
			Material var8 = var1.getBlockMaterial(var3, var4 - 1, var5);
			if(var8.blocksMovement() || var8.isLiquid()) {
				var1.setBlock(var3, var4, var5, Block.waterMoving.blockID);
			}
		}

	}

	public int quantityDropped(Random var1) {
		return 0;
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11 - Block.lightOpacity[this.blockID]) {
			if(var1.provider.isHellWorld) {
				var1.setBlockToAir(var2, var3, var4);
				return;
			}

			this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
			var1.setBlock(var2, var3, var4, Block.waterStill.blockID);
		}

	}

	public int getMobilityFlag() {
		return 0;
	}
}
