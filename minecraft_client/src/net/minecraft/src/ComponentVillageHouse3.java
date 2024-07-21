package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageHouse3 extends ComponentVillage {
	private int averageGroundLevel = -1;

	public ComponentVillageHouse3(ComponentVillageStartPiece var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
		super(var1, var2);
		this.coordBaseMode = var5;
		this.boundingBox = var4;
	}

	public static ComponentVillageHouse3 func_74921_a(ComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
		StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 7, 12, var6);
		return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null ? new ComponentVillageHouse3(var0, var7, var2, var8, var6) : null;
	}

	public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
		if(this.averageGroundLevel < 0) {
			this.averageGroundLevel = this.getAverageGroundLevel(var1, var3);
			if(this.averageGroundLevel < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 7 - 1, 0);
		}

		this.fillWithBlocks(var1, var3, 1, 1, 1, 7, 4, 4, 0, 0, false);
		this.fillWithBlocks(var1, var3, 2, 1, 6, 8, 4, 10, 0, 0, false);
		this.fillWithBlocks(var1, var3, 2, 0, 5, 8, 0, 10, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 0, 1, 7, 0, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 0, 0, 0, 0, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.fillWithBlocks(var1, var3, 8, 0, 0, 8, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 0, 0, 7, 2, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 0, 5, 2, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.fillWithBlocks(var1, var3, 2, 0, 6, 2, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.fillWithBlocks(var1, var3, 3, 0, 10, 7, 3, 10, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 2, 0, 7, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 1, 2, 5, 2, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 0, 4, 1, 8, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 0, 4, 4, 3, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 0, 5, 2, 8, 5, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 0, 4, 2, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 0, 4, 3, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 4, 2, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 4, 3, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 4, 4, var3);
		int var4 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
		int var5 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);

		int var6;
		int var7;
		for(var6 = -1; var6 <= 2; ++var6) {
			for(var7 = 0; var7 <= 8; ++var7) {
				this.placeBlockAtCurrentPosition(var1, Block.stairsWoodOak.blockID, var4, var7, 4 + var6, var6, var3);
				if((var6 > -1 || var7 <= 1) && (var6 > 0 || var7 <= 3) && (var6 > 1 || var7 <= 4 || var7 >= 6)) {
					this.placeBlockAtCurrentPosition(var1, Block.stairsWoodOak.blockID, var5, var7, 4 + var6, 5 - var6, var3);
				}
			}
		}

		this.fillWithBlocks(var1, var3, 3, 4, 5, 3, 4, 10, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 7, 4, 2, 7, 4, 10, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 4, 5, 4, 4, 5, 10, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 6, 5, 4, 6, 5, 10, Block.planks.blockID, Block.planks.blockID, false);
		this.fillWithBlocks(var1, var3, 5, 6, 3, 5, 6, 10, Block.planks.blockID, Block.planks.blockID, false);
		var6 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 0);

		int var8;
		for(var7 = 4; var7 >= 1; --var7) {
			this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, var7, 2 + var7, 7 - var7, var3);

			for(var8 = 8 - var7; var8 <= 10; ++var8) {
				this.placeBlockAtCurrentPosition(var1, Block.stairsWoodOak.blockID, var6, var7, 2 + var7, var8, var3);
			}
		}

		var7 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 1);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 6, 6, 3, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 7, 5, 4, var3);
		this.placeBlockAtCurrentPosition(var1, Block.stairsWoodOak.blockID, var7, 6, 6, 4, var3);

		int var9;
		for(var8 = 6; var8 <= 8; ++var8) {
			for(var9 = 5; var9 <= 10; ++var9) {
				this.placeBlockAtCurrentPosition(var1, Block.stairsWoodOak.blockID, var7, var8, 12 - var8, var9, var3);
			}
		}

		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 0, 2, 1, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 0, 2, 4, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 0, 2, 2, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 0, 2, 3, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 4, 2, 0, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 5, 2, 0, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 6, 2, 0, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 1, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 2, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 3, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 4, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 8, 2, 5, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 6, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 7, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 8, 2, 8, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 8, 2, 9, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 2, 2, 6, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 2, 2, 7, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 2, 2, 8, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 2, 2, 9, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 4, 4, 10, var3);
		this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 5, 4, 10, var3);
		this.placeBlockAtCurrentPosition(var1, Block.wood.blockID, 0, 6, 4, 10, var3);
		this.placeBlockAtCurrentPosition(var1, Block.planks.blockID, 0, 5, 5, 10, var3);
		this.placeBlockAtCurrentPosition(var1, 0, 0, 2, 1, 0, var3);
		this.placeBlockAtCurrentPosition(var1, 0, 0, 2, 2, 0, var3);
		this.placeBlockAtCurrentPosition(var1, Block.torchWood.blockID, 0, 2, 3, 1, var3);
		this.placeDoorAtCurrentPosition(var1, var3, var2, 2, 1, 0, this.getMetadataWithOffset(Block.doorWood.blockID, 1));
		this.fillWithBlocks(var1, var3, 1, 0, -1, 3, 2, -1, 0, 0, false);
		if(this.getBlockIdAtCurrentPosition(var1, 2, 0, -1, var3) == 0 && this.getBlockIdAtCurrentPosition(var1, 2, -1, -1, var3) != 0) {
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 0, -1, var3);
		}

		for(var8 = 0; var8 < 5; ++var8) {
			for(var9 = 0; var9 < 9; ++var9) {
				this.clearCurrentPositionBlocksUpwards(var1, var9, 7, var8, var3);
				this.fillCurrentPositionBlocksDownwards(var1, Block.cobblestone.blockID, 0, var9, -1, var8, var3);
			}
		}

		for(var8 = 5; var8 < 11; ++var8) {
			for(var9 = 2; var9 < 9; ++var9) {
				this.clearCurrentPositionBlocksUpwards(var1, var9, 7, var8, var3);
				this.fillCurrentPositionBlocksDownwards(var1, Block.cobblestone.blockID, 0, var9, -1, var8, var3);
			}
		}

		this.spawnVillagers(var1, var3, 4, 1, 2, 2);
		return true;
	}
}
