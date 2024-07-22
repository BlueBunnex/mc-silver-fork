package net.minecraft.src.entity;

import net.minecraft.src.MathHelper;
import net.minecraft.src.block.Block;
import net.minecraft.src.worldgen.World;

public class EntityAIEatGrass extends EntityAIBase {
	private EntityLiving theEntity;
	private World theWorld;
	int eatGrassTick = 0;

	public EntityAIEatGrass(EntityLiving var1) {
		this.theEntity = var1;
		this.theWorld = var1.worldObj;
		this.setMutexBits(7);
	}

	public boolean shouldExecute() {
		if(this.theEntity.getRNG().nextInt(this.theEntity.isChild() ? 50 : 1000) != 0) {
			return false;
		} else {
			int var1 = MathHelper.floor_double(this.theEntity.posX);
			int var2 = MathHelper.floor_double(this.theEntity.posY);
			int var3 = MathHelper.floor_double(this.theEntity.posZ);
			return this.theWorld.getBlockId(var1, var2, var3) == Block.tallGrass.blockID && this.theWorld.getBlockMetadata(var1, var2, var3) == 1 ? true : this.theWorld.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID;
		}
	}

	public void startExecuting() {
		this.eatGrassTick = 40;
		this.theWorld.setEntityState(this.theEntity, (byte)10);
		this.theEntity.getNavigator().clearPathEntity();
	}

	public void resetTask() {
		this.eatGrassTick = 0;
	}

	public boolean continueExecuting() {
		return this.eatGrassTick > 0;
	}

	public int getEatGrassTick() {
		return this.eatGrassTick;
	}

	public void updateTask() {
		this.eatGrassTick = Math.max(0, this.eatGrassTick - 1);
		if(this.eatGrassTick == 4) {
			int var1 = MathHelper.floor_double(this.theEntity.posX);
			int var2 = MathHelper.floor_double(this.theEntity.posY);
			int var3 = MathHelper.floor_double(this.theEntity.posZ);
			if(this.theWorld.getBlockId(var1, var2, var3) == Block.tallGrass.blockID) {
				this.theWorld.destroyBlock(var1, var2, var3, false);
				this.theEntity.eatGrassBonus();
			} else if(this.theWorld.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID) {
				this.theWorld.playAuxSFX(2001, var1, var2 - 1, var3, Block.grass.blockID);
				this.theWorld.setBlock(var1, var2 - 1, var3, Block.dirt.blockID, 0, 2);
				this.theEntity.eatGrassBonus();
			}

		}
	}
}
