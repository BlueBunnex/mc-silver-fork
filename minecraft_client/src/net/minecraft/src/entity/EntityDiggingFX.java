package net.minecraft.src.entity;

import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;
import net.minecraft.src.block.Block;
import net.minecraft.src.worldgen.World;

public class EntityDiggingFX extends EntityFX {
	private Block blockInstance;

	public EntityDiggingFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, Block var14, int var15, int var16, RenderEngine var17) {
		super(var1, var2, var4, var6, var8, var10, var12);
		this.blockInstance = var14;
		this.setParticleIcon(var17, var14.getIcon(0, var16));
		this.particleGravity = var14.blockParticleGravity;
		this.particleRed = this.particleGreen = this.particleBlue = 0.6F;
		this.particleScale /= 2.0F;
	}

	public EntityDiggingFX func_70596_a(int var1, int var2, int var3) {
		if(this.blockInstance == Block.grass) {
			return this;
		} else {
			int var4 = this.blockInstance.colorMultiplier(this.worldObj, var1, var2, var3);
			this.particleRed *= (float)(var4 >> 16 & 255) / 255.0F;
			this.particleGreen *= (float)(var4 >> 8 & 255) / 255.0F;
			this.particleBlue *= (float)(var4 & 255) / 255.0F;
			return this;
		}
	}

	public EntityDiggingFX applyRenderColor(int var1) {
		if(this.blockInstance == Block.grass) {
			return this;
		} else {
			int var2 = this.blockInstance.getRenderColor(var1);
			this.particleRed *= (float)(var2 >> 16 & 255) / 255.0F;
			this.particleGreen *= (float)(var2 >> 8 & 255) / 255.0F;
			this.particleBlue *= (float)(var2 & 255) / 255.0F;
			return this;
		}
	}

	public int getFXLayer() {
		return 1;
	}

	public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		float var8 = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
		float var9 = var8 + 0.999F / 64.0F;
		float var10 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
		float var11 = var10 + 0.999F / 64.0F;
		float var12 = 0.1F * this.particleScale;
		if(this.particleIcon != null) {
			var8 = this.particleIcon.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
			var9 = this.particleIcon.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
			var10 = this.particleIcon.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
			var11 = this.particleIcon.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
		}

		float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)var2 - interpPosX);
		float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)var2 - interpPosY);
		float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)var2 - interpPosZ);
		float var16 = 1.0F;
		var1.setColorOpaque_F(var16 * this.particleRed, var16 * this.particleGreen, var16 * this.particleBlue);
		var1.addVertexWithUV((double)(var13 - var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 - var5 * var12 - var7 * var12), (double)var8, (double)var11);
		var1.addVertexWithUV((double)(var13 - var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 - var5 * var12 + var7 * var12), (double)var8, (double)var10);
		var1.addVertexWithUV((double)(var13 + var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 + var5 * var12 + var7 * var12), (double)var9, (double)var10);
		var1.addVertexWithUV((double)(var13 + var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 + var5 * var12 - var7 * var12), (double)var9, (double)var11);
	}
}
