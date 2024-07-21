package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class BlockFarmland extends Block {
	private Icon field_94441_a;
	private Icon field_94440_b;

	protected BlockFarmland(int var1) {
		super(var1, Material.ground);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 15.0F / 16.0F, 1.0F);
		this.setLightOpacity(255);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		return AxisAlignedBB.getAABBPool().getAABB((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 0), (double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1));
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public Icon getIcon(int var1, int var2) {
		return var1 == 1 ? (var2 > 0 ? this.field_94441_a : this.field_94440_b) : Block.dirt.getBlockTextureFromSide(var1);
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(!this.isWaterNearby(var1, var2, var3, var4) && !var1.canLightningStrikeAt(var2, var3 + 1, var4)) {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			if(var6 > 0) {
				var1.setBlockMetadataWithNotify(var2, var3, var4, var6 - 1, 2);
			} else if(!this.isCropsNearby(var1, var2, var3, var4)) {
				var1.setBlock(var2, var3, var4, Block.dirt.blockID);
			}
		} else {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 7, 2);
		}

	}

	public void onFallenUpon(World var1, int var2, int var3, int var4, Entity var5, float var6) {
		if(!var1.isRemote && var1.rand.nextFloat() < var6 - 0.5F) {
			if(!(var5 instanceof EntityPlayer) && !var1.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
				return;
			}

			var1.setBlock(var2, var3, var4, Block.dirt.blockID);
		}

	}

	private boolean isCropsNearby(World var1, int var2, int var3, int var4) {
		byte var5 = 0;

		for(int var6 = var2 - var5; var6 <= var2 + var5; ++var6) {
			for(int var7 = var4 - var5; var7 <= var4 + var5; ++var7) {
				int var8 = var1.getBlockId(var6, var3 + 1, var7);
				if(var8 == Block.crops.blockID || var8 == Block.melonStem.blockID || var8 == Block.pumpkinStem.blockID || var8 == Block.potato.blockID || var8 == Block.carrot.blockID) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isWaterNearby(World var1, int var2, int var3, int var4) {
		for(int var5 = var2 - 4; var5 <= var2 + 4; ++var5) {
			for(int var6 = var3; var6 <= var3 + 1; ++var6) {
				for(int var7 = var4 - 4; var7 <= var4 + 4; ++var7) {
					if(var1.getBlockMaterial(var5, var6, var7) == Material.water) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		super.onNeighborBlockChange(var1, var2, var3, var4, var5);
		Material var6 = var1.getBlockMaterial(var2, var3 + 1, var4);
		if(var6.isSolid()) {
			var1.setBlock(var2, var3, var4, Block.dirt.blockID);
		}

	}

	public int idDropped(int var1, Random var2, int var3) {
		return Block.dirt.idDropped(0, var2, var3);
	}

	public int idPicked(World var1, int var2, int var3, int var4) {
		return Block.dirt.blockID;
	}

	public void registerIcons(IconRegister var1) {
		this.field_94441_a = var1.registerIcon("farmland_wet");
		this.field_94440_b = var1.registerIcon("farmland_dry");
	}
}
