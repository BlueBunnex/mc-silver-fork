package net.minecraft.src.worldgen.structure;

import java.util.Random;

import net.minecraft.src.WeightedRandomChestContent;
import net.minecraft.src.block.Block;
import net.minecraft.src.block.BlockLever;
import net.minecraft.src.item.Item;
import net.minecraft.src.worldgen.World;

public class ComponentScatteredFeatureJunglePyramid extends ComponentScatteredFeature {
	private boolean field_74947_h;
	private boolean field_74948_i;
	private boolean field_74945_j;
	private boolean field_74946_k;
	private static final WeightedRandomChestContent[] junglePyramidsChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 10), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 2, 7, 15), new WeightedRandomChestContent(Item.emerald.itemID, 0, 1, 3, 2), new WeightedRandomChestContent(Item.bone.itemID, 0, 4, 6, 20), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 3, 7, 16)};
	private static final WeightedRandomChestContent[] junglePyramidsDispenserContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.arrow.itemID, 0, 2, 7, 30)};
	private static StructureScatteredFeatureStones junglePyramidsRandomScatteredStones = new StructureScatteredFeatureStones((ComponentScatteredFeaturePieces2)null);

	public ComponentScatteredFeatureJunglePyramid(Random var1, int var2, int var3) {
		super(var1, var2, 64, var3, 12, 10, 15);
	}

	public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
		if(!this.func_74935_a(var1, var3, 0)) {
			return false;
		} else {
			int var4 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3);
			int var5 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 2);
			int var6 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 0);
			int var7 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 1);
			this.fillWithRandomizedBlocks(var1, var3, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 2, 1, 2, 9, 2, 2, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 2, 1, 12, 9, 2, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 2, 1, 3, 2, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 9, 1, 3, 9, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 1, 3, 1, 10, 6, 1, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 1, 3, 13, 10, 6, 13, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 1, 3, 2, 1, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 10, 3, 2, 10, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 2, 3, 2, 9, 3, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 2, 6, 2, 9, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 3, 7, 3, 8, 7, 11, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 4, 8, 4, 7, 8, 10, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithAir(var1, var3, 3, 1, 3, 8, 2, 11);
			this.fillWithAir(var1, var3, 4, 3, 6, 7, 3, 9);
			this.fillWithAir(var1, var3, 2, 4, 2, 9, 5, 12);
			this.fillWithAir(var1, var3, 4, 6, 5, 7, 6, 9);
			this.fillWithAir(var1, var3, 5, 7, 6, 6, 7, 8);
			this.fillWithAir(var1, var3, 5, 1, 2, 6, 2, 2);
			this.fillWithAir(var1, var3, 5, 2, 12, 6, 2, 12);
			this.fillWithAir(var1, var3, 5, 5, 1, 6, 5, 1);
			this.fillWithAir(var1, var3, 5, 5, 13, 6, 5, 13);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 1, 5, 5, var3);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 10, 5, 5, var3);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 1, 5, 9, var3);
			this.placeBlockAtCurrentPosition(var1, 0, 0, 10, 5, 9, var3);

			int var8;
			for(var8 = 0; var8 <= 14; var8 += 14) {
				this.fillWithRandomizedBlocks(var1, var3, 2, 4, var8, 2, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
				this.fillWithRandomizedBlocks(var1, var3, 4, 4, var8, 4, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
				this.fillWithRandomizedBlocks(var1, var3, 7, 4, var8, 7, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
				this.fillWithRandomizedBlocks(var1, var3, 9, 4, var8, 9, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
			}

			this.fillWithRandomizedBlocks(var1, var3, 5, 6, 0, 6, 6, 0, false, var2, junglePyramidsRandomScatteredStones);

			for(var8 = 0; var8 <= 11; var8 += 11) {
				for(int var9 = 2; var9 <= 12; var9 += 2) {
					this.fillWithRandomizedBlocks(var1, var3, var8, 4, var9, var8, 5, var9, false, var2, junglePyramidsRandomScatteredStones);
				}

				this.fillWithRandomizedBlocks(var1, var3, var8, 6, 5, var8, 6, 5, false, var2, junglePyramidsRandomScatteredStones);
				this.fillWithRandomizedBlocks(var1, var3, var8, 6, 9, var8, 6, 9, false, var2, junglePyramidsRandomScatteredStones);
			}

			this.fillWithRandomizedBlocks(var1, var3, 2, 7, 2, 2, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 9, 7, 2, 9, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 2, 7, 12, 2, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 9, 7, 12, 9, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 4, 9, 4, 4, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 7, 9, 4, 7, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 4, 9, 10, 4, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 7, 9, 10, 7, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 5, 9, 7, 6, 9, 7, false, var2, junglePyramidsRandomScatteredStones);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 5, 9, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 6, 9, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var5, 5, 9, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var5, 6, 9, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 4, 0, 0, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 5, 0, 0, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 6, 0, 0, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 7, 0, 0, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 4, 1, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 4, 2, 9, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 4, 3, 10, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 7, 1, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 7, 2, 9, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var4, 7, 3, 10, var3);
			this.fillWithRandomizedBlocks(var1, var3, 4, 1, 9, 4, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 7, 1, 9, 7, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 4, 1, 10, 7, 2, 10, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 5, 4, 5, 6, 4, 5, false, var2, junglePyramidsRandomScatteredStones);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var6, 4, 4, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var7, 7, 4, 5, var3);

			for(var8 = 0; var8 < 4; ++var8) {
				this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var5, 5, 0 - var8, 6 + var8, var3);
				this.placeBlockAtCurrentPosition(var1, Block.stairsCobblestone.blockID, var5, 6, 0 - var8, 6 + var8, var3);
				this.fillWithAir(var1, var3, 5, 0 - var8, 7 + var8, 6, 0 - var8, 9 + var8);
			}

			this.fillWithAir(var1, var3, 1, -3, 12, 10, -1, 13);
			this.fillWithAir(var1, var3, 1, -3, 1, 3, -1, 13);
			this.fillWithAir(var1, var3, 1, -3, 1, 9, -1, 5);

			for(var8 = 1; var8 <= 13; var8 += 2) {
				this.fillWithRandomizedBlocks(var1, var3, 1, -3, var8, 1, -2, var8, false, var2, junglePyramidsRandomScatteredStones);
			}

			for(var8 = 2; var8 <= 12; var8 += 2) {
				this.fillWithRandomizedBlocks(var1, var3, 1, -1, var8, 3, -1, var8, false, var2, junglePyramidsRandomScatteredStones);
			}

			this.fillWithRandomizedBlocks(var1, var3, 2, -2, 1, 5, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 7, -2, 1, 9, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 6, -3, 1, 6, -3, 1, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 6, -1, 1, 6, -1, 1, false, var2, junglePyramidsRandomScatteredStones);
			this.placeBlockAtCurrentPosition(var1, Block.tripWireSource.blockID, this.getMetadataWithOffset(Block.tripWireSource.blockID, 3) | 4, 1, -3, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWireSource.blockID, this.getMetadataWithOffset(Block.tripWireSource.blockID, 1) | 4, 4, -3, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWire.blockID, 4, 2, -3, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWire.blockID, 4, 3, -3, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 7, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 4, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 3, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 2, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 5, -3, 1, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 4, -3, 1, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 3, -3, 1, var3);
			if(!this.field_74945_j) {
				this.field_74945_j = this.generateStructureDispenserContents(var1, var3, var2, 3, -2, 1, 2, junglePyramidsDispenserContents, 2);
			}

			this.placeBlockAtCurrentPosition(var1, Block.vine.blockID, 15, 3, -2, 2, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWireSource.blockID, this.getMetadataWithOffset(Block.tripWireSource.blockID, 2) | 4, 7, -3, 1, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWireSource.blockID, this.getMetadataWithOffset(Block.tripWireSource.blockID, 0) | 4, 7, -3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWire.blockID, 4, 7, -3, 2, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWire.blockID, 4, 7, -3, 3, var3);
			this.placeBlockAtCurrentPosition(var1, Block.tripWire.blockID, 4, 7, -3, 4, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 8, -3, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 9, -3, 6, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 9, -3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 9, -3, 4, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 9, -2, 4, var3);
			if(!this.field_74946_k) {
				this.field_74946_k = this.generateStructureDispenserContents(var1, var3, var2, 9, -2, 3, 4, junglePyramidsDispenserContents, 2);
			}

			this.placeBlockAtCurrentPosition(var1, Block.vine.blockID, 15, 8, -1, 3, var3);
			this.placeBlockAtCurrentPosition(var1, Block.vine.blockID, 15, 8, -2, 3, var3);
			if(!this.field_74947_h) {
				this.field_74947_h = this.generateStructureChestContents(var1, var3, var2, 8, -3, 3, WeightedRandomChestContent.func_92080_a(junglePyramidsChestContents, new WeightedRandomChestContent[]{Item.enchantedBook.func_92114_b(var2)}), 2 + var2.nextInt(5));
			}

			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 9, -3, 2, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 8, -3, 1, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 4, -3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 5, -2, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 5, -1, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 6, -3, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 7, -2, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 7, -1, 5, var3);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 8, -3, 5, var3);
			this.fillWithRandomizedBlocks(var1, var3, 9, -1, 1, 9, -1, 5, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithAir(var1, var3, 8, -3, 8, 10, -1, 10);
			this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 8, -2, 11, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 9, -2, 11, var3);
			this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 10, -2, 11, var3);
			this.placeBlockAtCurrentPosition(var1, Block.lever.blockID, BlockLever.invertMetadata(this.getMetadataWithOffset(Block.lever.blockID, 2)), 8, -2, 12, var3);
			this.placeBlockAtCurrentPosition(var1, Block.lever.blockID, BlockLever.invertMetadata(this.getMetadataWithOffset(Block.lever.blockID, 2)), 9, -2, 12, var3);
			this.placeBlockAtCurrentPosition(var1, Block.lever.blockID, BlockLever.invertMetadata(this.getMetadataWithOffset(Block.lever.blockID, 2)), 10, -2, 12, var3);
			this.fillWithRandomizedBlocks(var1, var3, 8, -3, 8, 8, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
			this.fillWithRandomizedBlocks(var1, var3, 10, -3, 8, 10, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
			this.placeBlockAtCurrentPosition(var1, Block.cobblestoneMossy.blockID, 0, 10, -2, 9, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 8, -2, 9, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 8, -2, 10, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneWire.blockID, 0, 10, -1, 9, var3);
			this.placeBlockAtCurrentPosition(var1, Block.pistonStickyBase.blockID, 1, 9, -2, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.pistonStickyBase.blockID, this.getMetadataWithOffset(Block.pistonStickyBase.blockID, 4), 10, -2, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.pistonStickyBase.blockID, this.getMetadataWithOffset(Block.pistonStickyBase.blockID, 4), 10, -1, 8, var3);
			this.placeBlockAtCurrentPosition(var1, Block.redstoneRepeaterIdle.blockID, this.getMetadataWithOffset(Block.redstoneRepeaterIdle.blockID, 2), 10, -2, 10, var3);
			if(!this.field_74948_i) {
				this.field_74948_i = this.generateStructureChestContents(var1, var3, var2, 9, -3, 10, WeightedRandomChestContent.func_92080_a(junglePyramidsChestContents, new WeightedRandomChestContent[]{Item.enchantedBook.func_92114_b(var2)}), 2 + var2.nextInt(5));
			}

			return true;
		}
	}
}
