package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntitySnowman;
import net.minecraft.src.entity.ModelSnowMan;
import net.minecraft.src.item.ItemStack;

public class RenderSnowMan extends RenderLiving {
	private ModelSnowMan snowmanModel = (ModelSnowMan)this.mainModel;

	public RenderSnowMan() {
		super(new ModelSnowMan(), 0.5F);
		this.setRenderPassModel(this.snowmanModel);
	}

	protected void renderSnowmanPumpkin(EntitySnowman var1, float var2) {
		super.renderEquippedItems(var1, var2);
		ItemStack var3 = new ItemStack(Block.pumpkin, 1);
		if(var3 != null && var3.getItem().itemID < 256) {
			GL11.glPushMatrix();
			this.snowmanModel.head.postRender(1.0F / 16.0F);
			if(RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())) {
				float var4 = 10.0F / 16.0F;
				GL11.glTranslatef(0.0F, -(11.0F / 32.0F), 0.0F);
				GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var4, -var4, var4);
			}

			this.renderManager.itemRenderer.renderItem(var1, var3, 0);
			GL11.glPopMatrix();
		}

	}

	protected void renderEquippedItems(EntityLiving var1, float var2) {
		this.renderSnowmanPumpkin((EntitySnowman)var1, var2);
	}
}
