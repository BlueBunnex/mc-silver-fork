package net.minecraft.src.worldgen.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.src.MathHelper;
import net.minecraft.src.block.Block;
import net.minecraft.src.worldgen.World;

public class ComponentVillageField extends ComponentVillage {
	private int averageGroundLevel = -1;
	private int cropTypeA;
	private int cropTypeB;
	private int cropTypeC;
	private int cropTypeD;

	public ComponentVillageField(ComponentVillageStartPiece var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
		super(var1, var2);
		this.coordBaseMode = var5;
		this.boundingBox = var4;
		this.cropTypeA = this.getRandomCrop(var3);
		this.cropTypeB = this.getRandomCrop(var3);
		this.cropTypeC = this.getRandomCrop(var3);
		this.cropTypeD = this.getRandomCrop(var3);
	}

	private int getRandomCrop(Random var1) {
		switch(var1.nextInt(5)) {
		case 0:
			return Block.carrot.blockID;
		case 1:
			return Block.potato.blockID;
		default:
			return Block.crops.blockID;
		}
	}

	public static ComponentVillageField func_74900_a(ComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
		StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 13, 4, 9, var6);
		return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null ? new ComponentVillageField(var0, var7, var2, var8, var6) : null;
	}

	public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
		if(this.averageGroundLevel < 0) {
			this.averageGroundLevel = this.getAverageGroundLevel(var1, var3);
			if(this.averageGroundLevel < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
		}

		this.fillWithBlocks(var1, var3, 0, 1, 0, 12, 4, 8, 0, 0, false);
		this.fillWithBlocks(var1, var3, 1, 0, 1, 2, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.fillWithBlocks(var1, var3, 4, 0, 1, 5, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.fillWithBlocks(var1, var3, 7, 0, 1, 8, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.fillWithBlocks(var1, var3, 10, 0, 1, 11, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.fillWithBlocks(var1, var3, 0, 0, 0, 0, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(var1, var3, 6, 0, 0, 6, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(var1, var3, 12, 0, 0, 12, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 0, 0, 11, 0, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 0, 8, 11, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(var1, var3, 3, 0, 1, 3, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);
		this.fillWithBlocks(var1, var3, 9, 0, 1, 9, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);

		int var4;
		for(var4 = 1; var4 <= 7; ++var4) {
			this.placeBlockAtCurrentPosition(var1, this.cropTypeA, MathHelper.getRandomIntegerInRange(var2, 2, 7), 1, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeA, MathHelper.getRandomIntegerInRange(var2, 2, 7), 2, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeB, MathHelper.getRandomIntegerInRange(var2, 2, 7), 4, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeB, MathHelper.getRandomIntegerInRange(var2, 2, 7), 5, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeC, MathHelper.getRandomIntegerInRange(var2, 2, 7), 7, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeC, MathHelper.getRandomIntegerInRange(var2, 2, 7), 8, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeD, MathHelper.getRandomIntegerInRange(var2, 2, 7), 10, 1, var4, var3);
			this.placeBlockAtCurrentPosition(var1, this.cropTypeD, MathHelper.getRandomIntegerInRange(var2, 2, 7), 11, 1, var4, var3);
		}

		for(var4 = 0; var4 < 9; ++var4) {
			for(int var5 = 0; var5 < 13; ++var5) {
				this.clearCurrentPositionBlocksUpwards(var1, var5, 4, var4, var3);
				this.fillCurrentPositionBlocksDownwards(var1, Block.dirt.blockID, 0, var5, -1, var4, var3);
			}
		}

		return true;
	}
}
