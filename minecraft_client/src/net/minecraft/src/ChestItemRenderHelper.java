package net.minecraft.src;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.tile.TileEntityChest;
import net.minecraft.src.entity.tile.TileEntityEnderChest;
import net.minecraft.src.entity.tile.TileEntityRenderer;

public class ChestItemRenderHelper {
	public static ChestItemRenderHelper instance = new ChestItemRenderHelper();
	private TileEntityChest theChest = new TileEntityChest();
	private TileEntityEnderChest theEnderChest = new TileEntityEnderChest();

	public void renderChest(Block var1, int var2, float var3) {
		if(var1.blockID == Block.enderChest.blockID) {
			TileEntityRenderer.instance.renderTileEntityAt(this.theEnderChest, 0.0D, 0.0D, 0.0D, 0.0F);
		} else {
			TileEntityRenderer.instance.renderTileEntityAt(this.theChest, 0.0D, 0.0D, 0.0D, 0.0F);
		}

	}
}
