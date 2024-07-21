package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class BlockStem extends BlockFlower {
	private final Block fruitType;
	private Icon theIcon;

	protected BlockStem(int var1, Block var2) {
		super(var1);
		this.fruitType = var2;
		this.setTickRandomly(true);
		float var3 = 2.0F / 16.0F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
		this.setCreativeTab((CreativeTabs)null);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int var1) {
		return var1 == Block.tilledField.blockID;
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		super.updateTick(var1, var2, var3, var4, var5);
		if(var1.getBlockLightValue(var2, var3 + 1, var4) >= 9) {
			float var6 = this.getGrowthModifier(var1, var2, var3, var4);
			if(var5.nextInt((int)(25.0F / var6) + 1) == 0) {
				int var7 = var1.getBlockMetadata(var2, var3, var4);
				if(var7 < 7) {
					++var7;
					var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
				} else {
					if(var1.getBlockId(var2 - 1, var3, var4) == this.fruitType.blockID) {
						return;
					}

					if(var1.getBlockId(var2 + 1, var3, var4) == this.fruitType.blockID) {
						return;
					}

					if(var1.getBlockId(var2, var3, var4 - 1) == this.fruitType.blockID) {
						return;
					}

					if(var1.getBlockId(var2, var3, var4 + 1) == this.fruitType.blockID) {
						return;
					}

					int var8 = var5.nextInt(4);
					int var9 = var2;
					int var10 = var4;
					if(var8 == 0) {
						var9 = var2 - 1;
					}

					if(var8 == 1) {
						++var9;
					}

					if(var8 == 2) {
						var10 = var4 - 1;
					}

					if(var8 == 3) {
						++var10;
					}

					int var11 = var1.getBlockId(var9, var3 - 1, var10);
					if(var1.getBlockId(var9, var3, var10) == 0 && (var11 == Block.tilledField.blockID || var11 == Block.dirt.blockID || var11 == Block.grass.blockID)) {
						var1.setBlock(var9, var3, var10, this.fruitType.blockID);
					}
				}
			}
		}

	}

	public void fertilizeStem(World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockMetadata(var2, var3, var4) + MathHelper.getRandomIntegerInRange(var1.rand, 2, 5);
		if(var5 > 7) {
			var5 = 7;
		}

		var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
	}

	private float getGrowthModifier(World var1, int var2, int var3, int var4) {
		float var5 = 1.0F;
		int var6 = var1.getBlockId(var2, var3, var4 - 1);
		int var7 = var1.getBlockId(var2, var3, var4 + 1);
		int var8 = var1.getBlockId(var2 - 1, var3, var4);
		int var9 = var1.getBlockId(var2 + 1, var3, var4);
		int var10 = var1.getBlockId(var2 - 1, var3, var4 - 1);
		int var11 = var1.getBlockId(var2 + 1, var3, var4 - 1);
		int var12 = var1.getBlockId(var2 + 1, var3, var4 + 1);
		int var13 = var1.getBlockId(var2 - 1, var3, var4 + 1);
		boolean var14 = var8 == this.blockID || var9 == this.blockID;
		boolean var15 = var6 == this.blockID || var7 == this.blockID;
		boolean var16 = var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID;

		for(int var17 = var2 - 1; var17 <= var2 + 1; ++var17) {
			for(int var18 = var4 - 1; var18 <= var4 + 1; ++var18) {
				int var19 = var1.getBlockId(var17, var3 - 1, var18);
				float var20 = 0.0F;
				if(var19 == Block.tilledField.blockID) {
					var20 = 1.0F;
					if(var1.getBlockMetadata(var17, var3 - 1, var18) > 0) {
						var20 = 3.0F;
					}
				}

				if(var17 != var2 || var18 != var4) {
					var20 /= 4.0F;
				}

				var5 += var20;
			}
		}

		if(var16 || var14 && var15) {
			var5 /= 2.0F;
		}

		return var5;
	}

	public int getRenderColor(int var1) {
		int var2 = var1 * 32;
		int var3 = 255 - var1 * 8;
		int var4 = var1 * 4;
		return var2 << 16 | var3 << 8 | var4;
	}

	public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
		return this.getRenderColor(var1.getBlockMetadata(var2, var3, var4));
	}

	public void setBlockBoundsForItemRender() {
		float var1 = 2.0F / 16.0F;
		this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		this.maxY = (double)((float)(var1.getBlockMetadata(var2, var3, var4) * 2 + 2) / 16.0F);
		float var5 = 2.0F / 16.0F;
		this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var5, 0.5F + var5, (float)this.maxY, 0.5F + var5);
	}

	public int getRenderType() {
		return 19;
	}

	public int getState(IBlockAccess var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockMetadata(var2, var3, var4);
		return var5 < 7 ? -1 : (var1.getBlockId(var2 - 1, var3, var4) == this.fruitType.blockID ? 0 : (var1.getBlockId(var2 + 1, var3, var4) == this.fruitType.blockID ? 1 : (var1.getBlockId(var2, var3, var4 - 1) == this.fruitType.blockID ? 2 : (var1.getBlockId(var2, var3, var4 + 1) == this.fruitType.blockID ? 3 : -1))));
	}

	public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
		super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
		if(!var1.isRemote) {
			Item var8 = null;
			if(this.fruitType == Block.pumpkin) {
				var8 = Item.pumpkinSeeds;
			}

			if(this.fruitType == Block.melon) {
				var8 = Item.melonSeeds;
			}

			for(int var9 = 0; var9 < 3; ++var9) {
				if(var1.rand.nextInt(15) <= var5) {
					this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var8));
				}
			}

		}
	}

	public int idDropped(int var1, Random var2, int var3) {
		return -1;
	}

	public int quantityDropped(Random var1) {
		return 1;
	}

	public int idPicked(World var1, int var2, int var3, int var4) {
		return this.fruitType == Block.pumpkin ? Item.pumpkinSeeds.itemID : (this.fruitType == Block.melon ? Item.melonSeeds.itemID : 0);
	}

	public void registerIcons(IconRegister var1) {
		this.blockIcon = var1.registerIcon("stem_straight");
		this.theIcon = var1.registerIcon("stem_bent");
	}

	public Icon func_94368_p() {
		return this.theIcon;
	}
}
