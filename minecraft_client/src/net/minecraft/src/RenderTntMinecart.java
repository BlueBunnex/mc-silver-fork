package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.block.Block;

public class RenderTntMinecart extends RenderMinecart {
	protected void func_94146_a(EntityMinecartTNT var1, float var2, Block var3, int var4) {
		int var5 = var1.func_94104_d();
		if(var5 > -1 && (float)var5 - var2 + 1.0F < 10.0F) {
			float var6 = 1.0F - ((float)var5 - var2 + 1.0F) / 10.0F;
			if(var6 < 0.0F) {
				var6 = 0.0F;
			}

			if(var6 > 1.0F) {
				var6 = 1.0F;
			}

			var6 *= var6;
			var6 *= var6;
			float var7 = 1.0F + var6 * 0.3F;
			GL11.glScalef(var7, var7, var7);
		}

		super.renderBlockInMinecart(var1, var2, var3, var4);
		if(var5 > -1 && var5 / 5 % 2 == 0) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, (1.0F - ((float)var5 - var2 + 1.0F) / 100.0F) * 0.8F);
			GL11.glPushMatrix();
			this.field_94145_f.renderBlockAsItem(Block.tnt, 0, 1.0F);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

	}

	protected void renderBlockInMinecart(EntityMinecart var1, float var2, Block var3, int var4) {
		this.func_94146_a((EntityMinecartTNT)var1, var2, var3, var4);
	}
}
