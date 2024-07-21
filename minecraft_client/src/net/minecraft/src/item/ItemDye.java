package net.minecraft.src.item;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.MathHelper;
import net.minecraft.src.block.Block;
import net.minecraft.src.block.BlockCloth;
import net.minecraft.src.block.BlockCocoa;
import net.minecraft.src.block.BlockCrops;
import net.minecraft.src.block.BlockDirectional;
import net.minecraft.src.block.BlockLog;
import net.minecraft.src.block.BlockMushroom;
import net.minecraft.src.block.BlockSapling;
import net.minecraft.src.block.BlockStem;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.entity.EntitySheep;
import net.minecraft.src.worldgen.World;

public class ItemDye extends Item {
	public static final String[] dyeColorNames = new String[]{"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
	public static final String[] field_94595_b = new String[]{"dyePowder_black", "dyePowder_red", "dyePowder_green", "dyePowder_brown", "dyePowder_blue", "dyePowder_purple", "dyePowder_cyan", "dyePowder_silver", "dyePowder_gray", "dyePowder_pink", "dyePowder_lime", "dyePowder_yellow", "dyePowder_lightBlue", "dyePowder_magenta", "dyePowder_orange", "dyePowder_white"};
	public static final int[] dyeColors = new int[]{1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};
	private Icon[] field_94594_d;

	public ItemDye(int var1) {
		super(var1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public Icon getIconFromDamage(int var1) {
		int var2 = MathHelper.clamp_int(var1, 0, 15);
		return this.field_94594_d[var2];
	}

	public String getUnlocalizedName(ItemStack var1) {
		int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + dyeColorNames[var2];
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
		if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
			return false;
		} else {
			if(var1.getItemDamage() == 15) {
				if(func_96604_a(var1, var3, var4, var5, var6)) {
					if(!var3.isRemote) {
						var3.playAuxSFX(2005, var4, var5, var6, 0);
					}

					return true;
				}
			} else if(var1.getItemDamage() == 3) {
				int var11 = var3.getBlockId(var4, var5, var6);
				int var12 = var3.getBlockMetadata(var4, var5, var6);
				if(var11 == Block.wood.blockID && BlockLog.limitToValidMetadata(var12) == 3) {
					if(var7 == 0) {
						return false;
					}

					if(var7 == 1) {
						return false;
					}

					if(var7 == 2) {
						--var6;
					}

					if(var7 == 3) {
						++var6;
					}

					if(var7 == 4) {
						--var4;
					}

					if(var7 == 5) {
						++var4;
					}

					if(var3.isAirBlock(var4, var5, var6)) {
						int var13 = Block.blocksList[Block.cocoaPlant.blockID].onBlockPlaced(var3, var4, var5, var6, var7, var8, var9, var10, 0);
						var3.setBlock(var4, var5, var6, Block.cocoaPlant.blockID, var13, 2);
						if(!var2.capabilities.isCreativeMode) {
							--var1.stackSize;
						}
					}

					return true;
				}
			}

			return false;
		}
	}

	public static boolean func_96604_a(ItemStack var0, World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockId(var2, var3, var4);
		if(var5 == Block.sapling.blockID) {
			if(!var1.isRemote) {
				if((double)var1.rand.nextFloat() < 0.45D) {
					((BlockSapling)Block.sapling).markOrGrowMarked(var1, var2, var3, var4, var1.rand);
				}

				--var0.stackSize;
			}

			return true;
		} else if(var5 != Block.mushroomBrown.blockID && var5 != Block.mushroomRed.blockID) {
			if(var5 != Block.melonStem.blockID && var5 != Block.pumpkinStem.blockID) {
				if(var5 > 0 && Block.blocksList[var5] instanceof BlockCrops) {
					if(var1.getBlockMetadata(var2, var3, var4) == 7) {
						return false;
					} else {
						if(!var1.isRemote) {
							((BlockCrops)Block.blocksList[var5]).fertilize(var1, var2, var3, var4);
							--var0.stackSize;
						}

						return true;
					}
				} else {
					int var6;
					int var7;
					int var8;
					if(var5 == Block.cocoaPlant.blockID) {
						var6 = var1.getBlockMetadata(var2, var3, var4);
						var7 = BlockDirectional.getDirection(var6);
						var8 = BlockCocoa.func_72219_c(var6);
						if(var8 >= 2) {
							return false;
						} else {
							if(!var1.isRemote) {
								++var8;
								var1.setBlockMetadataWithNotify(var2, var3, var4, var8 << 2 | var7, 2);
								--var0.stackSize;
							}

							return true;
						}
					} else if(var5 != Block.grass.blockID) {
						return false;
					} else {
						if(!var1.isRemote) {
							--var0.stackSize;

							label102:
							for(var6 = 0; var6 < 128; ++var6) {
								var7 = var2;
								var8 = var3 + 1;
								int var9 = var4;

								for(int var10 = 0; var10 < var6 / 16; ++var10) {
									var7 += itemRand.nextInt(3) - 1;
									var8 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
									var9 += itemRand.nextInt(3) - 1;
									if(var1.getBlockId(var7, var8 - 1, var9) != Block.grass.blockID || var1.isBlockNormalCube(var7, var8, var9)) {
										continue label102;
									}
								}

								if(var1.getBlockId(var7, var8, var9) == 0) {
									if(itemRand.nextInt(10) != 0) {
										if(Block.tallGrass.canBlockStay(var1, var7, var8, var9)) {
											var1.setBlock(var7, var8, var9, Block.tallGrass.blockID, 1, 3);
										}
									} else if(itemRand.nextInt(3) != 0) {
										if(Block.plantYellow.canBlockStay(var1, var7, var8, var9)) {
											var1.setBlock(var7, var8, var9, Block.plantYellow.blockID);
										}
									} else if(Block.plantRed.canBlockStay(var1, var7, var8, var9)) {
										var1.setBlock(var7, var8, var9, Block.plantRed.blockID);
									}
								}
							}
						}

						return true;
					}
				}
			} else if(var1.getBlockMetadata(var2, var3, var4) == 7) {
				return false;
			} else {
				if(!var1.isRemote) {
					((BlockStem)Block.blocksList[var5]).fertilizeStem(var1, var2, var3, var4);
					--var0.stackSize;
				}

				return true;
			}
		} else {
			if(!var1.isRemote) {
				if((double)var1.rand.nextFloat() < 0.4D) {
					((BlockMushroom)Block.blocksList[var5]).fertilizeMushroom(var1, var2, var3, var4, var1.rand);
				}

				--var0.stackSize;
			}

			return true;
		}
	}

	public static void func_96603_a(World var0, int var1, int var2, int var3, int var4) {
		int var5 = var0.getBlockId(var1, var2, var3);
		if(var4 == 0) {
			var4 = 15;
		}

		Block var6 = var5 > 0 && var5 < Block.blocksList.length ? Block.blocksList[var5] : null;
		if(var6 != null) {
			var6.setBlockBoundsBasedOnState(var0, var1, var2, var3);

			for(int var7 = 0; var7 < var4; ++var7) {
				double var8 = itemRand.nextGaussian() * 0.02D;
				double var10 = itemRand.nextGaussian() * 0.02D;
				double var12 = itemRand.nextGaussian() * 0.02D;
				var0.spawnParticle("happyVillager", (double)((float)var1 + itemRand.nextFloat()), (double)var2 + (double)itemRand.nextFloat() * var6.getBlockBoundsMaxY(), (double)((float)var3 + itemRand.nextFloat()), var8, var10, var12);
			}

		}
	}

	public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {
		if(var2 instanceof EntitySheep) {
			EntitySheep var3 = (EntitySheep)var2;
			int var4 = BlockCloth.getBlockFromDye(var1.getItemDamage());
			if(!var3.getSheared() && var3.getFleeceColor() != var4) {
				var3.setFleeceColor(var4);
				--var1.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	public void getSubItems(int var1, CreativeTabs var2, List var3) {
		for(int var4 = 0; var4 < 16; ++var4) {
			var3.add(new ItemStack(var1, 1, var4));
		}

	}

	public void registerIcons(IconRegister var1) {
		this.field_94594_d = new Icon[field_94595_b.length];

		for(int var2 = 0; var2 < field_94595_b.length; ++var2) {
			this.field_94594_d[var2] = var1.registerIcon(field_94595_b[var2]);
		}

	}
}
