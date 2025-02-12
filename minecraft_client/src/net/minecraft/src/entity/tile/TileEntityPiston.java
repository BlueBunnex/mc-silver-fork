package net.minecraft.src.entity.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Facing;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.Entity;

public class TileEntityPiston extends TileEntity {
	private int storedBlockID;
	private int storedMetadata;
	private int storedOrientation;
	private boolean extending;
	private boolean shouldHeadBeRendered;
	private float progress;
	private float lastProgress;
	private List pushedObjects = new ArrayList();

	public TileEntityPiston() {
	}

	public TileEntityPiston(int var1, int var2, int var3, boolean var4, boolean var5) {
		this.storedBlockID = var1;
		this.storedMetadata = var2;
		this.storedOrientation = var3;
		this.extending = var4;
		this.shouldHeadBeRendered = var5;
	}

	public int getStoredBlockID() {
		return this.storedBlockID;
	}

	public int getBlockMetadata() {
		return this.storedMetadata;
	}

	public boolean isExtending() {
		return this.extending;
	}

	public int getPistonOrientation() {
		return this.storedOrientation;
	}

	public boolean shouldRenderHead() {
		return this.shouldHeadBeRendered;
	}

	public float getProgress(float var1) {
		if(var1 > 1.0F) {
			var1 = 1.0F;
		}

		return this.lastProgress + (this.progress - this.lastProgress) * var1;
	}

	public float getOffsetX(float var1) {
		return this.extending ? (this.getProgress(var1) - 1.0F) * (float)Facing.offsetsXForSide[this.storedOrientation] : (1.0F - this.getProgress(var1)) * (float)Facing.offsetsXForSide[this.storedOrientation];
	}

	public float getOffsetY(float var1) {
		return this.extending ? (this.getProgress(var1) - 1.0F) * (float)Facing.offsetsYForSide[this.storedOrientation] : (1.0F - this.getProgress(var1)) * (float)Facing.offsetsYForSide[this.storedOrientation];
	}

	public float getOffsetZ(float var1) {
		return this.extending ? (this.getProgress(var1) - 1.0F) * (float)Facing.offsetsZForSide[this.storedOrientation] : (1.0F - this.getProgress(var1)) * (float)Facing.offsetsZForSide[this.storedOrientation];
	}

	private void updatePushedObjects(float var1, float var2) {
		if(this.extending) {
			var1 = 1.0F - var1;
		} else {
			--var1;
		}

		AxisAlignedBB var3 = Block.pistonMoving.getAxisAlignedBB(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, var1, this.storedOrientation);
		if(var3 != null) {
			List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);
			if(!var4.isEmpty()) {
				this.pushedObjects.addAll(var4);
				Iterator var5 = this.pushedObjects.iterator();

				while(var5.hasNext()) {
					Entity var6 = (Entity)var5.next();
					var6.moveEntity((double)(var2 * (float)Facing.offsetsXForSide[this.storedOrientation]), (double)(var2 * (float)Facing.offsetsYForSide[this.storedOrientation]), (double)(var2 * (float)Facing.offsetsZForSide[this.storedOrientation]));
				}

				this.pushedObjects.clear();
			}
		}

	}

	public void clearPistonTileEntity() {
		if(this.lastProgress < 1.0F && this.worldObj != null) {
			this.lastProgress = this.progress = 1.0F;
			this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
			this.invalidate();
			if(this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.pistonMoving.blockID) {
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata, 3);
				this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
			}
		}

	}

	public void updateEntity() {
		this.lastProgress = this.progress;
		if(this.lastProgress >= 1.0F) {
			this.updatePushedObjects(1.0F, 0.25F);
			this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
			this.invalidate();
			if(this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.pistonMoving.blockID) {
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata, 3);
				this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
			}

		} else {
			this.progress += 0.5F;
			if(this.progress >= 1.0F) {
				this.progress = 1.0F;
			}

			if(this.extending) {
				this.updatePushedObjects(this.progress, this.progress - this.lastProgress + 1.0F / 16.0F);
			}

		}
	}

	public void readFromNBT(NBTTagCompound var1) {
		super.readFromNBT(var1);
		this.storedBlockID = var1.getInteger("blockId");
		this.storedMetadata = var1.getInteger("blockData");
		this.storedOrientation = var1.getInteger("facing");
		this.lastProgress = this.progress = var1.getFloat("progress");
		this.extending = var1.getBoolean("extending");
	}

	public void writeToNBT(NBTTagCompound var1) {
		super.writeToNBT(var1);
		var1.setInteger("blockId", this.storedBlockID);
		var1.setInteger("blockData", this.storedMetadata);
		var1.setInteger("facing", this.storedOrientation);
		var1.setFloat("progress", this.lastProgress);
		var1.setBoolean("extending", this.extending);
	}
}
