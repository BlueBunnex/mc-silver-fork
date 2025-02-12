package net.minecraft.src.worldgen.structure;

import java.util.Random;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityWitch;
import net.minecraft.src.worldgen.World;

public class ComponentScatteredFeatureSwampHut extends ComponentScatteredFeature {
	private boolean hasWitch;

	public ComponentScatteredFeatureSwampHut(Random var1, int var2, int var3) {
		super(var1, var2, 64, var3, 7, 5, 9);
	}

	public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
		if(!this.func_74935_a(var1, var3, 0)) {
			return false;
		} else {
			this.fillWithMetadataBlocks(var1, var3, 1, 1, 1, 5, 1, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithMetadataBlocks(var1, var3, 1, 4, 2, 5, 4, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithMetadataBlocks(var1, var3, 2, 1, 0, 4, 1, 0, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithMetadataBlocks(var1, var3, 2, 2, 2, 3, 3, 2, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithMetadataBlocks(var1, var3, 1, 2, 3, 1, 3, 6, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithMetadataBlocks(var1, var3, 5, 2, 3, 5, 3, 6, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithMetadataBlocks(var1, var3, 2, 2, 7, 4, 3, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
			this.fillWithBlocks(var1, var3, 1, 0, 2, 1, 3, 2, Block.wood.blockID, Block.wood.blockID, false);
			this.fillWithBlocks(var1, var3, 5, 0, 2, 5, 3, 2, Block.wood.blockID, Block.wood.blockID, false);
			this.fillWithBlocks(var1, var3, 1, 0, 7, 1, 3, 7, Block.wood.blockID, Block.wood.blockID, false);
			this.fillWithBlocks(var1, var3, 5, 0, 7, 5, 3, 7, Block.wood.blockID, Block.wood.blockID, false);
			this.placeBlockAtCurrentPosition(var1, Block.fence.blockID, 0, 2, 3, 2, var3);
			this.placeBlockAtCurrentPosition(var1, Block.fence.blockID, 0, 3, 3, 7, var3);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 1, 3, 4, var3);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 5, 3, 4, var3);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 5, 3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.flowerPot.blockID, 7, 1, 3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.workbench.blockID, 0, 3, 2, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cauldron.blockID, 0, 4, 2, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.fence.blockID, 0, 1, 2, 1, var3);
			this.placeBlockAtCurrentPosition(var1, Block.fence.blockID, 0, 5, 2, 1, var3);
			int var4 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
			int var5 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 1);
			int var6 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 0);
			int var7 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
			this.fillWithMetadataBlocks(var1, var3, 0, 4, 1, 6, 4, 1, Block.stairsWoodSpruce.blockID, var4, Block.stairsWoodSpruce.blockID, var4, false);
			this.fillWithMetadataBlocks(var1, var3, 0, 4, 2, 0, 4, 7, Block.stairsWoodSpruce.blockID, var6, Block.stairsWoodSpruce.blockID, var6, false);
			this.fillWithMetadataBlocks(var1, var3, 6, 4, 2, 6, 4, 7, Block.stairsWoodSpruce.blockID, var5, Block.stairsWoodSpruce.blockID, var5, false);
			this.fillWithMetadataBlocks(var1, var3, 0, 4, 8, 6, 4, 8, Block.stairsWoodSpruce.blockID, var7, Block.stairsWoodSpruce.blockID, var7, false);

			int var8;
			int var9;
			for(var8 = 2; var8 <= 7; var8 += 5) {
				for(var9 = 1; var9 <= 5; var9 += 4) {
					this.fillCurrentPositionBlocksDownwards(var1, Block.wood.blockID, 0, var9, -1, var8, var3);
				}
			}

			if(!this.hasWitch) {
				var8 = this.getXWithOffset(2, 5);
				var9 = this.getYWithOffset(2);
				int var10 = this.getZWithOffset(2, 5);
				if(var3.isVecInside(var8, var9, var10)) {
					this.hasWitch = true;
					EntityWitch var11 = new EntityWitch(var1);
					var11.setLocationAndAngles((double)var8 + 0.5D, (double)var9, (double)var10 + 0.5D, 0.0F, 0.0F);
					var11.initCreature();
					var1.spawnEntityInWorld(var11);
				}
			}

			return true;
		}
	}
}
