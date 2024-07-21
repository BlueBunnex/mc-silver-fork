package net.minecraft.src.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.worldgen.World;

public class BlockPane extends Block {
	private final String sideTextureIndex;
	private final boolean canDropItself;
	private final String field_94402_c;
	private Icon theIcon;

	protected BlockPane(int var1, String var2, String var3, Material var4, boolean var5) {
		super(var1, var4);
		this.sideTextureIndex = var3;
		this.canDropItself = var5;
		this.field_94402_c = var2;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public int idDropped(int var1, Random var2, int var3) {
		return !this.canDropItself ? 0 : super.idDropped(var1, var2, var3);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 18;
	}

	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		int var6 = var1.getBlockId(var2, var3, var4);
		return var6 == this.blockID ? false : super.shouldSideBeRendered(var1, var2, var3, var4, var5);
	}

	public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
		boolean var8 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 - 1));
		boolean var9 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 + 1));
		boolean var10 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 - 1, var3, var4));
		boolean var11 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 + 1, var3, var4));
		if((!var10 || !var11) && (var10 || var11 || var8 || var9)) {
			if(var10 && !var11) {
				this.setBlockBounds(0.0F, 0.0F, 7.0F / 16.0F, 0.5F, 1.0F, 9.0F / 16.0F);
				super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			} else if(!var10 && var11) {
				this.setBlockBounds(0.5F, 0.0F, 7.0F / 16.0F, 1.0F, 1.0F, 9.0F / 16.0F);
				super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			}
		} else {
			this.setBlockBounds(0.0F, 0.0F, 7.0F / 16.0F, 1.0F, 1.0F, 9.0F / 16.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
		}

		if((!var8 || !var9) && (var10 || var11 || var8 || var9)) {
			if(var8 && !var9) {
				this.setBlockBounds(7.0F / 16.0F, 0.0F, 0.0F, 9.0F / 16.0F, 1.0F, 0.5F);
				super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			} else if(!var8 && var9) {
				this.setBlockBounds(7.0F / 16.0F, 0.0F, 0.5F, 9.0F / 16.0F, 1.0F, 1.0F);
				super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
			}
		} else {
			this.setBlockBounds(7.0F / 16.0F, 0.0F, 0.0F, 9.0F / 16.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
		}

	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		float var5 = 7.0F / 16.0F;
		float var6 = 9.0F / 16.0F;
		float var7 = 7.0F / 16.0F;
		float var8 = 9.0F / 16.0F;
		boolean var9 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 - 1));
		boolean var10 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 + 1));
		boolean var11 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 - 1, var3, var4));
		boolean var12 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 + 1, var3, var4));
		if((!var11 || !var12) && (var11 || var12 || var9 || var10)) {
			if(var11 && !var12) {
				var5 = 0.0F;
			} else if(!var11 && var12) {
				var6 = 1.0F;
			}
		} else {
			var5 = 0.0F;
			var6 = 1.0F;
		}

		if((!var9 || !var10) && (var11 || var12 || var9 || var10)) {
			if(var9 && !var10) {
				var7 = 0.0F;
			} else if(!var9 && var10) {
				var8 = 1.0F;
			}
		} else {
			var7 = 0.0F;
			var8 = 1.0F;
		}

		this.setBlockBounds(var5, 0.0F, var7, var6, 1.0F, var8);
	}

	public Icon getSideTextureIndex() {
		return this.theIcon;
	}

	public final boolean canThisPaneConnectToThisBlockID(int var1) {
		return Block.opaqueCubeLookup[var1] || var1 == this.blockID || var1 == Block.glass.blockID;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	protected ItemStack createStackedBlock(int var1) {
		return new ItemStack(this.blockID, 1, var1);
	}

	public void registerIcons(IconRegister var1) {
		this.blockIcon = var1.registerIcon(this.field_94402_c);
		this.theIcon = var1.registerIcon(this.sideTextureIndex);
	}
}
