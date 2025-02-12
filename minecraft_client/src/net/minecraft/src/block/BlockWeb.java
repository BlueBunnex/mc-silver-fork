package net.minecraft.src.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.item.Item;
import net.minecraft.src.worldgen.World;

public class BlockWeb extends Block {
	public BlockWeb(int var1) {
		super(var1, Material.web);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
		var5.setInWeb();
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		return null;
	}

	public int getRenderType() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int idDropped(int var1, Random var2, int var3) {
		return Item.silk.itemID;
	}

	protected boolean canSilkHarvest() {
		return true;
	}
}
