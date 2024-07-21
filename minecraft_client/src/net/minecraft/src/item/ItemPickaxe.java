package net.minecraft.src.item;

import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Material;
import net.minecraft.src.block.Block;

public class ItemPickaxe extends ItemTool {
	private static Block[] blocksEffectiveAgainst = new Block[]{Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, Block.railActivator};

	protected ItemPickaxe(int var1, EnumToolMaterial var2) {
		super(var1, 2, var2, blocksEffectiveAgainst);
	}

	public boolean canHarvestBlock(Block var1) {
		return var1 == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (var1 != Block.blockDiamond && var1 != Block.oreDiamond ? (var1 != Block.oreEmerald && var1 != Block.blockEmerald ? (var1 != Block.blockGold && var1 != Block.oreGold ? (var1 != Block.blockIron && var1 != Block.oreIron ? (var1 != Block.blockLapis && var1 != Block.oreLapis ? (var1 != Block.oreRedstone && var1 != Block.oreRedstoneGlowing ? (var1.blockMaterial == Material.rock ? true : (var1.blockMaterial == Material.iron ? true : var1.blockMaterial == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
	}

	public float getStrVsBlock(ItemStack var1, Block var2) {
		return var2 == null || var2.blockMaterial != Material.iron && var2.blockMaterial != Material.anvil && var2.blockMaterial != Material.rock ? super.getStrVsBlock(var1, var2) : this.efficiencyOnProperMaterial;
	}
}
