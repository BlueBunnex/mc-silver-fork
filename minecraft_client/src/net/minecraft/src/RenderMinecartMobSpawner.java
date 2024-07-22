package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityMinecart;
import net.minecraft.src.entity.EntityMinecartMobSpawner;
import net.minecraft.src.entity.tile.TileEntityMobSpawnerRenderer;

public class RenderMinecartMobSpawner extends RenderMinecart {
	protected void func_98192_a(EntityMinecartMobSpawner var1, float var2, Block var3, int var4) {
		super.renderBlockInMinecart(var1, var2, var3, var4);
		if(var3 == Block.mobSpawner) {
			TileEntityMobSpawnerRenderer.func_98144_a(var1.func_98039_d(), var1.posX, var1.posY, var1.posZ, var2);
		}

	}

	protected void renderBlockInMinecart(EntityMinecart var1, float var2, Block var3, int var4) {
		this.func_98192_a((EntityMinecartMobSpawner)var1, var2, var3, var4);
	}
}
