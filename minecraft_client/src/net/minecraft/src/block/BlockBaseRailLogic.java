package net.minecraft.src.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.ChunkPosition;
import net.minecraft.src.worldgen.World;

public class BlockBaseRailLogic {
	private World logicWorld;
	private int railX;
	private int railY;
	private int railZ;
	private final boolean isStraightRail;
	private List railChunkPosition;
	final BlockRailBase theRail;

	public BlockBaseRailLogic(BlockRailBase var1, World var2, int var3, int var4, int var5) {
		this.theRail = var1;
		this.railChunkPosition = new ArrayList();
		this.logicWorld = var2;
		this.railX = var3;
		this.railY = var4;
		this.railZ = var5;
		int var6 = var2.getBlockId(var3, var4, var5);
		int var7 = var2.getBlockMetadata(var3, var4, var5);
		if(((BlockRailBase)Block.blocksList[var6]).isPowered) {
			this.isStraightRail = true;
			var7 &= -9;
		} else {
			this.isStraightRail = false;
		}

		this.setBasicRail(var7);
	}

	private void setBasicRail(int var1) {
		this.railChunkPosition.clear();
		if(var1 == 0) {
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		} else if(var1 == 1) {
			this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
		} else if(var1 == 2) {
			this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY + 1, this.railZ));
		} else if(var1 == 3) {
			this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY + 1, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
		} else if(var1 == 4) {
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ - 1));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		} else if(var1 == 5) {
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ + 1));
		} else if(var1 == 6) {
			this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		} else if(var1 == 7) {
			this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		} else if(var1 == 8) {
			this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
		} else if(var1 == 9) {
			this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
			this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
		}

	}

	private void refreshConnectedTracks() {
		for(int var1 = 0; var1 < this.railChunkPosition.size(); ++var1) {
			BlockBaseRailLogic var2 = this.getRailLogic((ChunkPosition)this.railChunkPosition.get(var1));
			if(var2 != null && var2.isRailChunkPositionCorrect(this)) {
				this.railChunkPosition.set(var1, new ChunkPosition(var2.railX, var2.railY, var2.railZ));
			} else {
				this.railChunkPosition.remove(var1--);
			}
		}

	}

	private boolean isMinecartTrack(int var1, int var2, int var3) {
		return BlockRailBase.isRailBlockAt(this.logicWorld, var1, var2, var3) ? true : (BlockRailBase.isRailBlockAt(this.logicWorld, var1, var2 + 1, var3) ? true : BlockRailBase.isRailBlockAt(this.logicWorld, var1, var2 - 1, var3));
	}

	private BlockBaseRailLogic getRailLogic(ChunkPosition var1) {
		return BlockRailBase.isRailBlockAt(this.logicWorld, var1.x, var1.y, var1.z) ? new BlockBaseRailLogic(this.theRail, this.logicWorld, var1.x, var1.y, var1.z) : (BlockRailBase.isRailBlockAt(this.logicWorld, var1.x, var1.y + 1, var1.z) ? new BlockBaseRailLogic(this.theRail, this.logicWorld, var1.x, var1.y + 1, var1.z) : (BlockRailBase.isRailBlockAt(this.logicWorld, var1.x, var1.y - 1, var1.z) ? new BlockBaseRailLogic(this.theRail, this.logicWorld, var1.x, var1.y - 1, var1.z) : null));
	}

	private boolean isRailChunkPositionCorrect(BlockBaseRailLogic var1) {
		for(int var2 = 0; var2 < this.railChunkPosition.size(); ++var2) {
			ChunkPosition var3 = (ChunkPosition)this.railChunkPosition.get(var2);
			if(var3.x == var1.railX && var3.z == var1.railZ) {
				return true;
			}
		}

		return false;
	}

	private boolean isPartOfTrack(int var1, int var2, int var3) {
		for(int var4 = 0; var4 < this.railChunkPosition.size(); ++var4) {
			ChunkPosition var5 = (ChunkPosition)this.railChunkPosition.get(var4);
			if(var5.x == var1 && var5.z == var3) {
				return true;
			}
		}

		return false;
	}

	protected int getNumberOfAdjacentTracks() {
		int var1 = 0;
		if(this.isMinecartTrack(this.railX, this.railY, this.railZ - 1)) {
			++var1;
		}

		if(this.isMinecartTrack(this.railX, this.railY, this.railZ + 1)) {
			++var1;
		}

		if(this.isMinecartTrack(this.railX - 1, this.railY, this.railZ)) {
			++var1;
		}

		if(this.isMinecartTrack(this.railX + 1, this.railY, this.railZ)) {
			++var1;
		}

		return var1;
	}

	private boolean canConnectTo(BlockBaseRailLogic var1) {
		return this.isRailChunkPositionCorrect(var1) ? true : (this.railChunkPosition.size() == 2 ? false : (this.railChunkPosition.isEmpty() ? true : true));
	}

	private void connectToNeighbor(BlockBaseRailLogic var1) {
		this.railChunkPosition.add(new ChunkPosition(var1.railX, var1.railY, var1.railZ));
		boolean var2 = this.isPartOfTrack(this.railX, this.railY, this.railZ - 1);
		boolean var3 = this.isPartOfTrack(this.railX, this.railY, this.railZ + 1);
		boolean var4 = this.isPartOfTrack(this.railX - 1, this.railY, this.railZ);
		boolean var5 = this.isPartOfTrack(this.railX + 1, this.railY, this.railZ);
		byte var6 = -1;
		if(var2 || var3) {
			var6 = 0;
		}

		if(var4 || var5) {
			var6 = 1;
		}

		if(!this.isStraightRail) {
			if(var3 && var5 && !var2 && !var4) {
				var6 = 6;
			}

			if(var3 && var4 && !var2 && !var5) {
				var6 = 7;
			}

			if(var2 && var4 && !var3 && !var5) {
				var6 = 8;
			}

			if(var2 && var5 && !var3 && !var4) {
				var6 = 9;
			}
		}

		if(var6 == 0) {
			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ - 1)) {
				var6 = 4;
			}

			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ + 1)) {
				var6 = 5;
			}
		}

		if(var6 == 1) {
			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX + 1, this.railY + 1, this.railZ)) {
				var6 = 2;
			}

			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX - 1, this.railY + 1, this.railZ)) {
				var6 = 3;
			}
		}

		if(var6 < 0) {
			var6 = 0;
		}

		int var7 = var6;
		if(this.isStraightRail) {
			var7 = this.logicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | var6;
		}

		this.logicWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, var7, 3);
	}

	private boolean canConnectFrom(int var1, int var2, int var3) {
		BlockBaseRailLogic var4 = this.getRailLogic(new ChunkPosition(var1, var2, var3));
		if(var4 == null) {
			return false;
		} else {
			var4.refreshConnectedTracks();
			return var4.canConnectTo(this);
		}
	}

	public void func_94511_a(boolean var1, boolean var2) {
		boolean var3 = this.canConnectFrom(this.railX, this.railY, this.railZ - 1);
		boolean var4 = this.canConnectFrom(this.railX, this.railY, this.railZ + 1);
		boolean var5 = this.canConnectFrom(this.railX - 1, this.railY, this.railZ);
		boolean var6 = this.canConnectFrom(this.railX + 1, this.railY, this.railZ);
		byte var7 = -1;
		if((var3 || var4) && !var5 && !var6) {
			var7 = 0;
		}

		if((var5 || var6) && !var3 && !var4) {
			var7 = 1;
		}

		if(!this.isStraightRail) {
			if(var4 && var6 && !var3 && !var5) {
				var7 = 6;
			}

			if(var4 && var5 && !var3 && !var6) {
				var7 = 7;
			}

			if(var3 && var5 && !var4 && !var6) {
				var7 = 8;
			}

			if(var3 && var6 && !var4 && !var5) {
				var7 = 9;
			}
		}

		if(var7 == -1) {
			if(var3 || var4) {
				var7 = 0;
			}

			if(var5 || var6) {
				var7 = 1;
			}

			if(!this.isStraightRail) {
				if(var1) {
					if(var4 && var6) {
						var7 = 6;
					}

					if(var5 && var4) {
						var7 = 7;
					}

					if(var6 && var3) {
						var7 = 9;
					}

					if(var3 && var5) {
						var7 = 8;
					}
				} else {
					if(var3 && var5) {
						var7 = 8;
					}

					if(var6 && var3) {
						var7 = 9;
					}

					if(var5 && var4) {
						var7 = 7;
					}

					if(var4 && var6) {
						var7 = 6;
					}
				}
			}
		}

		if(var7 == 0) {
			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ - 1)) {
				var7 = 4;
			}

			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ + 1)) {
				var7 = 5;
			}
		}

		if(var7 == 1) {
			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX + 1, this.railY + 1, this.railZ)) {
				var7 = 2;
			}

			if(BlockRailBase.isRailBlockAt(this.logicWorld, this.railX - 1, this.railY + 1, this.railZ)) {
				var7 = 3;
			}
		}

		if(var7 < 0) {
			var7 = 0;
		}

		this.setBasicRail(var7);
		int var8 = var7;
		if(this.isStraightRail) {
			var8 = this.logicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | var7;
		}

		if(var2 || this.logicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) != var8) {
			this.logicWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, var8, 3);

			for(int var9 = 0; var9 < this.railChunkPosition.size(); ++var9) {
				BlockBaseRailLogic var10 = this.getRailLogic((ChunkPosition)this.railChunkPosition.get(var9));
				if(var10 != null) {
					var10.refreshConnectedTracks();
					if(var10.canConnectTo(this)) {
						var10.connectToNeighbor(this);
					}
				}
			}
		}

	}
}
