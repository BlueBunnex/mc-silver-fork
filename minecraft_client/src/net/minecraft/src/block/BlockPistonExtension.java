package net.minecraft.src.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.Facing;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.worldgen.World;

public class BlockPistonExtension extends Block {
	private Icon headTexture = null;

	public BlockPistonExtension(int var1) {
		super(var1, Material.piston);
		this.setStepSound(soundStoneFootstep);
		this.setHardness(0.5F);
	}

	public void setHeadTexture(Icon var1) {
		this.headTexture = var1;
	}

	public void clearHeadTexture() {
		this.headTexture = null;
	}

	public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6) {
		super.breakBlock(var1, var2, var3, var4, var5, var6);
		int var7 = Facing.oppositeSide[getDirectionMeta(var6)];
		var2 += Facing.offsetsXForSide[var7];
		var3 += Facing.offsetsYForSide[var7];
		var4 += Facing.offsetsZForSide[var7];
		int var8 = var1.getBlockId(var2, var3, var4);
		if(var8 == Block.pistonBase.blockID || var8 == Block.pistonStickyBase.blockID) {
			var6 = var1.getBlockMetadata(var2, var3, var4);
			if(BlockPistonBase.isExtended(var6)) {
				Block.blocksList[var8].dropBlockAsItem(var1, var2, var3, var4, var6, 0);
				var1.setBlockToAir(var2, var3, var4);
			}
		}

	}

	public Icon getIcon(int var1, int var2) {
		int var3 = getDirectionMeta(var2);
		return var1 == var3 ? (this.headTexture != null ? this.headTexture : ((var2 & 8) != 0 ? BlockPistonBase.func_94496_b("piston_top_sticky") : BlockPistonBase.func_94496_b("piston_top"))) : (var3 < 6 && var1 == Facing.oppositeSide[var3] ? BlockPistonBase.func_94496_b("piston_top") : BlockPistonBase.func_94496_b("piston_side"));
	}

	public void registerIcons(IconRegister var1) {
	}

	public int getRenderType() {
		return 17;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return false;
	}

	public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
		return false;
	}

	public int quantityDropped(Random var1) {
		return 0;
	}

	public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
		int var8 = var1.getBlockMetadata(var2, var3, var4);
		switch(getDirectionMeta(var8)) {
		case 0:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			this.setBlockBounds(6.0F / 16.0F, 0.25F, 6.0F / 16.0F, 10.0F / 16.0F, 1.0F, 10.0F / 16.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			break;
		case 1:
			this.setBlockBounds(0.0F, 12.0F / 16.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			this.setBlockBounds(6.0F / 16.0F, 0.0F, 6.0F / 16.0F, 10.0F / 16.0F, 12.0F / 16.0F, 10.0F / 16.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			break;
		case 2:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			this.setBlockBounds(0.25F, 6.0F / 16.0F, 0.25F, 12.0F / 16.0F, 10.0F / 16.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			break;
		case 3:
			this.setBlockBounds(0.0F, 0.0F, 12.0F / 16.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			this.setBlockBounds(0.25F, 6.0F / 16.0F, 0.0F, 12.0F / 16.0F, 10.0F / 16.0F, 12.0F / 16.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			break;
		case 4:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			this.setBlockBounds(6.0F / 16.0F, 0.25F, 0.25F, 10.0F / 16.0F, 12.0F / 16.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			break;
		case 5:
			this.setBlockBounds(12.0F / 16.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			this.setBlockBounds(0.0F, 6.0F / 16.0F, 0.25F, 12.0F / 16.0F, 10.0F / 16.0F, 12.0F / 16.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockMetadata(var2, var3, var4);
		switch(getDirectionMeta(var5)) {
		case 0:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
			break;
		case 1:
			this.setBlockBounds(0.0F, 12.0F / 16.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			break;
		case 2:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
			break;
		case 3:
			this.setBlockBounds(0.0F, 0.0F, 12.0F / 16.0F, 1.0F, 1.0F, 1.0F);
			break;
		case 4:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			break;
		case 5:
			this.setBlockBounds(12.0F / 16.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		int var6 = getDirectionMeta(var1.getBlockMetadata(var2, var3, var4));
		int var7 = var1.getBlockId(var2 - Facing.offsetsXForSide[var6], var3 - Facing.offsetsYForSide[var6], var4 - Facing.offsetsZForSide[var6]);
		if(var7 != Block.pistonBase.blockID && var7 != Block.pistonStickyBase.blockID) {
			var1.setBlockToAir(var2, var3, var4);
		} else {
			Block.blocksList[var7].onNeighborBlockChange(var1, var2 - Facing.offsetsXForSide[var6], var3 - Facing.offsetsYForSide[var6], var4 - Facing.offsetsZForSide[var6], var5);
		}

	}

	public static int getDirectionMeta(int var0) {
		return var0 & 7;
	}

	public int idPicked(World var1, int var2, int var3, int var4) {
		return 0;
	}
}
