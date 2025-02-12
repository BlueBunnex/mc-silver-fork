package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.worldgen.WorldServer;

public class Teleporter {
	private final WorldServer worldServerInstance;
	private final Random random;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();

	public Teleporter(WorldServer var1) {
		this.worldServerInstance = var1;
		this.random = new Random(var1.getSeed());
	}

	public void placeInPortal(Entity var1, double var2, double var4, double var6, float var8) {
		if(this.worldServerInstance.provider.dimensionId != 1) {
			if(!this.placeInExistingPortal(var1, var2, var4, var6, var8)) {
				this.makePortal(var1);
				this.placeInExistingPortal(var1, var2, var4, var6, var8);
			}
		} else {
			int var9 = MathHelper.floor_double(var1.posX);
			int var10 = MathHelper.floor_double(var1.posY) - 1;
			int var11 = MathHelper.floor_double(var1.posZ);
			byte var12 = 1;
			byte var13 = 0;

			for(int var14 = -2; var14 <= 2; ++var14) {
				for(int var15 = -2; var15 <= 2; ++var15) {
					for(int var16 = -1; var16 < 3; ++var16) {
						int var17 = var9 + var15 * var12 + var14 * var13;
						int var18 = var10 + var16;
						int var19 = var11 + var15 * var13 - var14 * var12;
						boolean var20 = var16 < 0;
						this.worldServerInstance.setBlock(var17, var18, var19, var20 ? Block.obsidian.blockID : 0);
					}
				}
			}

			var1.setLocationAndAngles((double)var9, (double)var10, (double)var11, var1.rotationYaw, 0.0F);
			var1.motionX = var1.motionY = var1.motionZ = 0.0D;
		}
	}

	public boolean placeInExistingPortal(Entity var1, double var2, double var4, double var6, float var8) {
		short var9 = 128;
		double var10 = -1.0D;
		int var12 = 0;
		int var13 = 0;
		int var14 = 0;
		int var15 = MathHelper.floor_double(var1.posX);
		int var16 = MathHelper.floor_double(var1.posZ);
		long var17 = ChunkCoordIntPair.chunkXZ2Int(var15, var16);
		boolean var19 = true;
		double var27;
		int var48;
		if(this.destinationCoordinateCache.containsItem(var17)) {
			PortalPosition var20 = (PortalPosition)this.destinationCoordinateCache.getValueByKey(var17);
			var10 = 0.0D;
			var12 = var20.posX;
			var13 = var20.posY;
			var14 = var20.posZ;
			var20.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			var19 = false;
		} else {
			for(var48 = var15 - var9; var48 <= var15 + var9; ++var48) {
				double var21 = (double)var48 + 0.5D - var1.posX;

				for(int var23 = var16 - var9; var23 <= var16 + var9; ++var23) {
					double var24 = (double)var23 + 0.5D - var1.posZ;

					for(int var26 = this.worldServerInstance.getActualHeight() - 1; var26 >= 0; --var26) {
						if(this.worldServerInstance.getBlockId(var48, var26, var23) == Block.portal.blockID) {
							while(this.worldServerInstance.getBlockId(var48, var26 - 1, var23) == Block.portal.blockID) {
								--var26;
							}

							var27 = (double)var26 + 0.5D - var1.posY;
							double var29 = var21 * var21 + var27 * var27 + var24 * var24;
							if(var10 < 0.0D || var29 < var10) {
								var10 = var29;
								var12 = var48;
								var13 = var26;
								var14 = var23;
							}
						}
					}
				}
			}
		}

		if(var10 >= 0.0D) {
			if(var19) {
				this.destinationCoordinateCache.add(var17, new PortalPosition(this, var12, var13, var14, this.worldServerInstance.getTotalWorldTime()));
				this.destinationCoordinateKeys.add(Long.valueOf(var17));
			}

			double var49 = (double)var12 + 0.5D;
			double var25 = (double)var13 + 0.5D;
			var27 = (double)var14 + 0.5D;
			int var50 = -1;
			if(this.worldServerInstance.getBlockId(var12 - 1, var13, var14) == Block.portal.blockID) {
				var50 = 2;
			}

			if(this.worldServerInstance.getBlockId(var12 + 1, var13, var14) == Block.portal.blockID) {
				var50 = 0;
			}

			if(this.worldServerInstance.getBlockId(var12, var13, var14 - 1) == Block.portal.blockID) {
				var50 = 3;
			}

			if(this.worldServerInstance.getBlockId(var12, var13, var14 + 1) == Block.portal.blockID) {
				var50 = 1;
			}

			int var30 = var1.getTeleportDirection();
			if(var50 > -1) {
				int var31 = Direction.rotateLeft[var50];
				int var32 = Direction.offsetX[var50];
				int var33 = Direction.offsetZ[var50];
				int var34 = Direction.offsetX[var31];
				int var35 = Direction.offsetZ[var31];
				boolean var36 = !this.worldServerInstance.isAirBlock(var12 + var32 + var34, var13, var14 + var33 + var35) || !this.worldServerInstance.isAirBlock(var12 + var32 + var34, var13 + 1, var14 + var33 + var35);
				boolean var37 = !this.worldServerInstance.isAirBlock(var12 + var32, var13, var14 + var33) || !this.worldServerInstance.isAirBlock(var12 + var32, var13 + 1, var14 + var33);
				if(var36 && var37) {
					var50 = Direction.rotateOpposite[var50];
					var31 = Direction.rotateOpposite[var31];
					var32 = Direction.offsetX[var50];
					var33 = Direction.offsetZ[var50];
					var34 = Direction.offsetX[var31];
					var35 = Direction.offsetZ[var31];
					var48 = var12 - var34;
					var49 -= (double)var34;
					int var22 = var14 - var35;
					var27 -= (double)var35;
					var36 = !this.worldServerInstance.isAirBlock(var48 + var32 + var34, var13, var22 + var33 + var35) || !this.worldServerInstance.isAirBlock(var48 + var32 + var34, var13 + 1, var22 + var33 + var35);
					var37 = !this.worldServerInstance.isAirBlock(var48 + var32, var13, var22 + var33) || !this.worldServerInstance.isAirBlock(var48 + var32, var13 + 1, var22 + var33);
				}

				float var38 = 0.5F;
				float var39 = 0.5F;
				if(!var36 && var37) {
					var38 = 1.0F;
				} else if(var36 && !var37) {
					var38 = 0.0F;
				} else if(var36 && var37) {
					var39 = 0.0F;
				}

				var49 += (double)((float)var34 * var38 + var39 * (float)var32);
				var27 += (double)((float)var35 * var38 + var39 * (float)var33);
				float var40 = 0.0F;
				float var41 = 0.0F;
				float var42 = 0.0F;
				float var43 = 0.0F;
				if(var50 == var30) {
					var40 = 1.0F;
					var41 = 1.0F;
				} else if(var50 == Direction.rotateOpposite[var30]) {
					var40 = -1.0F;
					var41 = -1.0F;
				} else if(var50 == Direction.rotateRight[var30]) {
					var42 = 1.0F;
					var43 = -1.0F;
				} else {
					var42 = -1.0F;
					var43 = 1.0F;
				}

				double var44 = var1.motionX;
				double var46 = var1.motionZ;
				var1.motionX = var44 * (double)var40 + var46 * (double)var43;
				var1.motionZ = var44 * (double)var42 + var46 * (double)var41;
				var1.rotationYaw = var8 - (float)(var30 * 90) + (float)(var50 * 90);
			} else {
				var1.motionX = var1.motionY = var1.motionZ = 0.0D;
			}

			var1.setLocationAndAngles(var49, var25, var27, var1.rotationYaw, var1.rotationPitch);
			return true;
		} else {
			return false;
		}
	}

	public boolean makePortal(Entity var1) {
		byte var2 = 16;
		double var3 = -1.0D;
		int var5 = MathHelper.floor_double(var1.posX);
		int var6 = MathHelper.floor_double(var1.posY);
		int var7 = MathHelper.floor_double(var1.posZ);
		int var8 = var5;
		int var9 = var6;
		int var10 = var7;
		int var11 = 0;
		int var12 = this.random.nextInt(4);

		int var13;
		double var14;
		int var16;
		double var17;
		int var19;
		int var20;
		int var21;
		int var22;
		int var23;
		int var24;
		int var25;
		int var26;
		int var27;
		double var31;
		double var32;
		for(var13 = var5 - var2; var13 <= var5 + var2; ++var13) {
			var14 = (double)var13 + 0.5D - var1.posX;

			for(var16 = var7 - var2; var16 <= var7 + var2; ++var16) {
				var17 = (double)var16 + 0.5D - var1.posZ;

				label296:
				for(var19 = this.worldServerInstance.getActualHeight() - 1; var19 >= 0; --var19) {
					if(this.worldServerInstance.isAirBlock(var13, var19, var16)) {
						while(var19 > 0 && this.worldServerInstance.isAirBlock(var13, var19 - 1, var16)) {
							--var19;
						}

						for(var20 = var12; var20 < var12 + 4; ++var20) {
							var21 = var20 % 2;
							var22 = 1 - var21;
							if(var20 % 4 >= 2) {
								var21 = -var21;
								var22 = -var22;
							}

							for(var23 = 0; var23 < 3; ++var23) {
								for(var24 = 0; var24 < 4; ++var24) {
									for(var25 = -1; var25 < 4; ++var25) {
										var26 = var13 + (var24 - 1) * var21 + var23 * var22;
										var27 = var19 + var25;
										int var28 = var16 + (var24 - 1) * var22 - var23 * var21;
										if(var25 < 0 && !this.worldServerInstance.getBlockMaterial(var26, var27, var28).isSolid() || var25 >= 0 && !this.worldServerInstance.isAirBlock(var26, var27, var28)) {
											continue label296;
										}
									}
								}
							}

							var31 = (double)var19 + 0.5D - var1.posY;
							var32 = var14 * var14 + var31 * var31 + var17 * var17;
							if(var3 < 0.0D || var32 < var3) {
								var3 = var32;
								var8 = var13;
								var9 = var19;
								var10 = var16;
								var11 = var20 % 4;
							}
						}
					}
				}
			}
		}

		if(var3 < 0.0D) {
			for(var13 = var5 - var2; var13 <= var5 + var2; ++var13) {
				var14 = (double)var13 + 0.5D - var1.posX;

				for(var16 = var7 - var2; var16 <= var7 + var2; ++var16) {
					var17 = (double)var16 + 0.5D - var1.posZ;

					label234:
					for(var19 = this.worldServerInstance.getActualHeight() - 1; var19 >= 0; --var19) {
						if(this.worldServerInstance.isAirBlock(var13, var19, var16)) {
							while(var19 > 0 && this.worldServerInstance.isAirBlock(var13, var19 - 1, var16)) {
								--var19;
							}

							for(var20 = var12; var20 < var12 + 2; ++var20) {
								var21 = var20 % 2;
								var22 = 1 - var21;

								for(var23 = 0; var23 < 4; ++var23) {
									for(var24 = -1; var24 < 4; ++var24) {
										var25 = var13 + (var23 - 1) * var21;
										var26 = var19 + var24;
										var27 = var16 + (var23 - 1) * var22;
										if(var24 < 0 && !this.worldServerInstance.getBlockMaterial(var25, var26, var27).isSolid() || var24 >= 0 && !this.worldServerInstance.isAirBlock(var25, var26, var27)) {
											continue label234;
										}
									}
								}

								var31 = (double)var19 + 0.5D - var1.posY;
								var32 = var14 * var14 + var31 * var31 + var17 * var17;
								if(var3 < 0.0D || var32 < var3) {
									var3 = var32;
									var8 = var13;
									var9 = var19;
									var10 = var16;
									var11 = var20 % 2;
								}
							}
						}
					}
				}
			}
		}

		int var29 = var8;
		int var15 = var9;
		var16 = var10;
		int var30 = var11 % 2;
		int var18 = 1 - var30;
		if(var11 % 4 >= 2) {
			var30 = -var30;
			var18 = -var18;
		}

		boolean var33;
		if(var3 < 0.0D) {
			if(var9 < 70) {
				var9 = 70;
			}

			if(var9 > this.worldServerInstance.getActualHeight() - 10) {
				var9 = this.worldServerInstance.getActualHeight() - 10;
			}

			var15 = var9;

			for(var19 = -1; var19 <= 1; ++var19) {
				for(var20 = 1; var20 < 3; ++var20) {
					for(var21 = -1; var21 < 3; ++var21) {
						var22 = var29 + (var20 - 1) * var30 + var19 * var18;
						var23 = var15 + var21;
						var24 = var16 + (var20 - 1) * var18 - var19 * var30;
						var33 = var21 < 0;
						this.worldServerInstance.setBlock(var22, var23, var24, var33 ? Block.obsidian.blockID : 0);
					}
				}
			}
		}

		for(var19 = 0; var19 < 4; ++var19) {
			for(var20 = 0; var20 < 4; ++var20) {
				for(var21 = -1; var21 < 4; ++var21) {
					var22 = var29 + (var20 - 1) * var30;
					var23 = var15 + var21;
					var24 = var16 + (var20 - 1) * var18;
					var33 = var20 == 0 || var20 == 3 || var21 == -1 || var21 == 3;
					this.worldServerInstance.setBlock(var22, var23, var24, var33 ? Block.obsidian.blockID : Block.portal.blockID, 0, 2);
				}
			}

			for(var20 = 0; var20 < 4; ++var20) {
				for(var21 = -1; var21 < 4; ++var21) {
					var22 = var29 + (var20 - 1) * var30;
					var23 = var15 + var21;
					var24 = var16 + (var20 - 1) * var18;
					this.worldServerInstance.notifyBlocksOfNeighborChange(var22, var23, var24, this.worldServerInstance.getBlockId(var22, var23, var24));
				}
			}
		}

		return true;
	}

	public void removeStalePortalLocations(long var1) {
		if(var1 % 100L == 0L) {
			Iterator var3 = this.destinationCoordinateKeys.iterator();
			long var4 = var1 - 600L;

			while(true) {
				Long var6;
				PortalPosition var7;
				do {
					if(!var3.hasNext()) {
						return;
					}

					var6 = (Long)var3.next();
					var7 = (PortalPosition)this.destinationCoordinateCache.getValueByKey(var6.longValue());
				} while(var7 != null && var7.lastUpdateTime >= var4);

				var3.remove();
				this.destinationCoordinateCache.remove(var6.longValue());
			}
		}
	}
}
