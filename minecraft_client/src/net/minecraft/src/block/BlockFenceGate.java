package net.minecraft.src.block;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.item.ItemStack;
import net.minecraft.src.worldgen.World;

public class BlockFenceGate extends BlockDirectional {
	public BlockFenceGate(int var1) {
		super(var1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public Icon getIcon(int var1, int var2) {
		return Block.planks.getBlockTextureFromSide(var1);
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return !var1.getBlockMaterial(var2, var3 - 1, var4).isSolid() ? false : super.canPlaceBlockAt(var1, var2, var3, var4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockMetadata(var2, var3, var4);
		return isFenceGateOpen(var5) ? null : (var5 != 2 && var5 != 0 ? AxisAlignedBB.getAABBPool().getAABB((double)((float)var2 + 6.0F / 16.0F), (double)var3, (double)var4, (double)((float)var2 + 10.0F / 16.0F), (double)((float)var3 + 1.5F), (double)(var4 + 1)) : AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)((float)var4 + 6.0F / 16.0F), (double)(var2 + 1), (double)((float)var3 + 1.5F), (double)((float)var4 + 10.0F / 16.0F)));
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		int var5 = getDirection(var1.getBlockMetadata(var2, var3, var4));
		if(var5 != 2 && var5 != 0) {
			this.setBlockBounds(6.0F / 16.0F, 0.0F, 0.0F, 10.0F / 16.0F, 1.0F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 6.0F / 16.0F, 1.0F, 1.0F, 10.0F / 16.0F);
		}

	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
		return isFenceGateOpen(var1.getBlockMetadata(var2, var3, var4));
	}

	public int getRenderType() {
		return 21;
	}

	public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6) {
		int var7 = (MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
		var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
	}

	public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
		int var10 = var1.getBlockMetadata(var2, var3, var4);
		if(isFenceGateOpen(var10)) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, var10 & -5, 2);
		} else {
			int var11 = (MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
			int var12 = getDirection(var10);
			if(var12 == (var11 + 2) % 4) {
				var10 = var11;
			}

			var1.setBlockMetadataWithNotify(var2, var3, var4, var10 | 4, 2);
		}

		var1.playAuxSFXAtEntity(var5, 1003, var2, var3, var4, 0);
		return true;
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		if(!var1.isRemote) {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			boolean var7 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
			if(var7 || var5 > 0 && Block.blocksList[var5].canProvidePower()) {
				if(var7 && !isFenceGateOpen(var6)) {
					var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 4, 2);
					var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
				} else if(!var7 && isFenceGateOpen(var6)) {
					var1.setBlockMetadataWithNotify(var2, var3, var4, var6 & -5, 2);
					var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
				}
			}

		}
	}

	public static boolean isFenceGateOpen(int var0) {
		return (var0 & 4) != 0;
	}

	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		return true;
	}

	public void registerIcons(IconRegister var1) {
	}
}
