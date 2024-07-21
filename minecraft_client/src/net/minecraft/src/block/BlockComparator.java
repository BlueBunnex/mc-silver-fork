package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.Direction;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ITileEntityProvider;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityComparator;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.item.Item;
import net.minecraft.src.worldgen.World;

public class BlockComparator extends BlockRedstoneLogic implements ITileEntityProvider {
	public BlockComparator(int var1, boolean var2) {
		super(var1, var2);
		this.isBlockContainer = true;
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Item.comparator.itemID;
	}

	public int idPicked(World var1, int var2, int var3, int var4) {
		return Item.comparator.itemID;
	}

	protected int func_94481_j_(int var1) {
		return 2;
	}

	protected BlockRedstoneLogic func_94485_e() {
		return Block.redstoneComparatorActive;
	}

	protected BlockRedstoneLogic func_94484_i() {
		return Block.redstoneComparatorIdle;
	}

	public int getRenderType() {
		return 37;
	}

	public Icon getIcon(int var1, int var2) {
		boolean var3 = this.isRepeaterPowered || (var2 & 8) != 0;
		return var1 == 0 ? (var3 ? Block.torchRedstoneActive.getBlockTextureFromSide(var1) : Block.torchRedstoneIdle.getBlockTextureFromSide(var1)) : (var1 == 1 ? (var3 ? Block.redstoneComparatorActive.blockIcon : this.blockIcon) : Block.stoneDoubleSlab.getBlockTextureFromSide(1));
	}

	protected boolean func_96470_c(int var1) {
		return this.isRepeaterPowered || (var1 & 8) != 0;
	}

	protected int func_94480_d(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		return this.getTileEntityComparator(var1, var2, var3, var4).func_96100_a();
	}

	private int func_94491_m(World var1, int var2, int var3, int var4, int var5) {
		return !this.func_94490_c(var5) ? this.getInputStrength(var1, var2, var3, var4, var5) : Math.max(this.getInputStrength(var1, var2, var3, var4, var5) - this.func_94482_f(var1, var2, var3, var4, var5), 0);
	}

	public boolean func_94490_c(int var1) {
		return (var1 & 4) == 4;
	}

	protected boolean func_94478_d(World var1, int var2, int var3, int var4, int var5) {
		int var6 = this.getInputStrength(var1, var2, var3, var4, var5);
		if(var6 >= 15) {
			return true;
		} else if(var6 == 0) {
			return false;
		} else {
			int var7 = this.func_94482_f(var1, var2, var3, var4, var5);
			return var7 == 0 ? true : var6 >= var7;
		}
	}

	protected int getInputStrength(World var1, int var2, int var3, int var4, int var5) {
		int var6 = super.getInputStrength(var1, var2, var3, var4, var5);
		int var7 = getDirection(var5);
		int var8 = var2 + Direction.offsetX[var7];
		int var9 = var4 + Direction.offsetZ[var7];
		int var10 = var1.getBlockId(var8, var3, var9);
		if(var10 > 0) {
			if(Block.blocksList[var10].hasComparatorInputOverride()) {
				var6 = Block.blocksList[var10].getComparatorInputOverride(var1, var8, var3, var9, Direction.rotateOpposite[var7]);
			} else if(var6 < 15 && Block.isNormalCube(var10)) {
				var8 += Direction.offsetX[var7];
				var9 += Direction.offsetZ[var7];
				var10 = var1.getBlockId(var8, var3, var9);
				if(var10 > 0 && Block.blocksList[var10].hasComparatorInputOverride()) {
					var6 = Block.blocksList[var10].getComparatorInputOverride(var1, var8, var3, var9, Direction.rotateOpposite[var7]);
				}
			}
		}

		return var6;
	}

	public TileEntityComparator getTileEntityComparator(IBlockAccess var1, int var2, int var3, int var4) {
		return (TileEntityComparator)var1.getBlockTileEntity(var2, var3, var4);
	}

	public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
		int var10 = var1.getBlockMetadata(var2, var3, var4);
		boolean var11 = this.isRepeaterPowered | (var10 & 8) != 0;
		boolean var12 = !this.func_94490_c(var10);
		int var13 = var12 ? 4 : 0;
		var13 |= var11 ? 8 : 0;
		var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, var12 ? 0.55F : 0.5F);
		var1.setBlockMetadataWithNotify(var2, var3, var4, var13 | var10 & 3, 2);
		this.func_96476_c(var1, var2, var3, var4, var1.rand);
		return true;
	}

	protected void func_94479_f(World var1, int var2, int var3, int var4, int var5) {
		if(!var1.isBlockTickScheduled(var2, var3, var4, this.blockID)) {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			int var7 = this.func_94491_m(var1, var2, var3, var4, var6);
			int var8 = this.getTileEntityComparator(var1, var2, var3, var4).func_96100_a();
			if(var7 != var8 || this.func_96470_c(var6) != this.func_94478_d(var1, var2, var3, var4, var6)) {
				if(this.func_83011_d(var1, var2, var3, var4, var6)) {
					var1.func_82740_a(var2, var3, var4, this.blockID, this.func_94481_j_(0), -1);
				} else {
					var1.func_82740_a(var2, var3, var4, this.blockID, this.func_94481_j_(0), 0);
				}
			}
		}

	}

	private void func_96476_c(World var1, int var2, int var3, int var4, Random var5) {
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		int var7 = this.func_94491_m(var1, var2, var3, var4, var6);
		int var8 = this.getTileEntityComparator(var1, var2, var3, var4).func_96100_a();
		this.getTileEntityComparator(var1, var2, var3, var4).func_96099_a(var7);
		if(var8 != var7 || !this.func_94490_c(var6)) {
			boolean var9 = this.func_94478_d(var1, var2, var3, var4, var6);
			boolean var10 = this.isRepeaterPowered || (var6 & 8) != 0;
			if(var10 && !var9) {
				var1.setBlockMetadataWithNotify(var2, var3, var4, var6 & -9, 2);
			} else if(!var10 && var9) {
				var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 8, 2);
			}

			this.func_94483_i_(var1, var2, var3, var4);
		}

	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(this.isRepeaterPowered) {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			var1.setBlock(var2, var3, var4, this.func_94484_i().blockID, var6 | 8, 4);
		}

		this.func_96476_c(var1, var2, var3, var4, var5);
	}

	public void onBlockAdded(World var1, int var2, int var3, int var4) {
		super.onBlockAdded(var1, var2, var3, var4);
		var1.setBlockTileEntity(var2, var3, var4, this.createNewTileEntity(var1));
	}

	public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6) {
		super.breakBlock(var1, var2, var3, var4, var5, var6);
		var1.removeBlockTileEntity(var2, var3, var4);
		this.func_94483_i_(var1, var2, var3, var4);
	}

	public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6) {
		super.onBlockEventReceived(var1, var2, var3, var4, var5, var6);
		TileEntity var7 = var1.getBlockTileEntity(var2, var3, var4);
		return var7 != null ? var7.receiveClientEvent(var5, var6) : false;
	}

	public void registerIcons(IconRegister var1) {
		this.blockIcon = var1.registerIcon(this.isRepeaterPowered ? "comparator_lit" : "comparator");
	}

	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityComparator();
	}
}
