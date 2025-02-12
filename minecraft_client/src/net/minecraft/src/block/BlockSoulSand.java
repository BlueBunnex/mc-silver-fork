package net.minecraft.src.block;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.entity.Entity;
import net.minecraft.src.worldgen.World;

public class BlockSoulSand extends Block {
	public BlockSoulSand(int var1) {
		super(var1, Material.sand);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		float var5 = 2.0F / 16.0F;
		return AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)(var2 + 1), (double)((float)(var3 + 1) - var5), (double)(var4 + 1));
	}

	public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
		var5.motionX *= 0.4D;
		var5.motionZ *= 0.4D;
	}
}
