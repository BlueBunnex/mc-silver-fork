package net.minecraft.src.item;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Icon;
import net.minecraft.src.MapData;
import net.minecraft.src.MapItemRenderer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderManager;
import net.minecraft.src.RenderPlayer;
import net.minecraft.src.Tessellator;
import net.minecraft.src.block.Block;
import net.minecraft.src.entity.EntityClientPlayerMP;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.entity.EntityPlayerSP;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ItemRenderer {
	private Minecraft mc;
	private ItemStack itemToRender = null;
	private float equippedProgress = 0.0F;
	private float prevEquippedProgress = 0.0F;
	private RenderBlocks renderBlocksInstance = new RenderBlocks();
	public final MapItemRenderer mapItemRenderer;
	private int equippedItemSlot = -1;

	public ItemRenderer(Minecraft var1) {
		this.mc = var1;
		this.mapItemRenderer = new MapItemRenderer(var1.fontRenderer, var1.gameSettings, var1.renderEngine);
	}

	public void renderItem(EntityLiving var1, ItemStack var2, int var3) {
		GL11.glPushMatrix();
		if(var2.getItemSpriteNumber() == 0 && Block.blocksList[var2.itemID] != null && RenderBlocks.renderItemIn3d(Block.blocksList[var2.itemID].getRenderType())) {
			this.mc.renderEngine.bindTexture("/terrain.png");
			this.renderBlocksInstance.renderBlockAsItem(Block.blocksList[var2.itemID], var2.getItemDamage(), 1.0F);
		} else {
			Icon var4 = var1.getItemIcon(var2, var3);
			if(var4 == null) {
				GL11.glPopMatrix();
				return;
			}

			if(var2.getItemSpriteNumber() == 0) {
				this.mc.renderEngine.bindTexture("/terrain.png");
			} else {
				this.mc.renderEngine.bindTexture("/gui/items.png");
			}

			Tessellator var5 = Tessellator.instance;
			float var6 = var4.getMinU();
			float var7 = var4.getMaxU();
			float var8 = var4.getMinV();
			float var9 = var4.getMaxV();
			float var10 = 0.0F;
			float var11 = 0.3F;
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef(-var10, -var11, 0.0F);
			float var12 = 1.5F;
			GL11.glScalef(var12, var12, var12);
			GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-(15.0F / 16.0F), -(1.0F / 16.0F), 0.0F);
			renderItemIn2D(var5, var7, var8, var6, var9, var4.getSheetWidth(), var4.getSheetHeight(), 1.0F / 16.0F);
			if(var2 != null && var2.hasEffect() && var3 == 0) {
				GL11.glDepthFunc(GL11.GL_EQUAL);
				GL11.glDisable(GL11.GL_LIGHTING);
				this.mc.renderEngine.bindTexture("%blur%/misc/glint.png");
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
				float var13 = 0.76F;
				GL11.glColor4f(0.5F * var13, 0.25F * var13, 0.8F * var13, 1.0F);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glPushMatrix();
				float var14 = 2.0F / 16.0F;
				GL11.glScalef(var14, var14, var14);
				float var15 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
				GL11.glTranslatef(var15, 0.0F, 0.0F);
				GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
				renderItemIn2D(var5, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 1.0F / 16.0F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(var14, var14, var14);
				var15 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
				GL11.glTranslatef(-var15, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
				renderItemIn2D(var5, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 1.0F / 16.0F);
				GL11.glPopMatrix();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDepthFunc(GL11.GL_LEQUAL);
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}

		GL11.glPopMatrix();
	}

	public static void renderItemIn2D(Tessellator var0, float var1, float var2, float var3, float var4, int var5, int var6, float var7) {
		var0.startDrawingQuads();
		var0.setNormal(0.0F, 0.0F, 1.0F);
		var0.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var1, (double)var4);
		var0.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)var3, (double)var4);
		var0.addVertexWithUV(1.0D, 1.0D, 0.0D, (double)var3, (double)var2);
		var0.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)var1, (double)var2);
		var0.draw();
		var0.startDrawingQuads();
		var0.setNormal(0.0F, 0.0F, -1.0F);
		var0.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - var7), (double)var1, (double)var2);
		var0.addVertexWithUV(1.0D, 1.0D, (double)(0.0F - var7), (double)var3, (double)var2);
		var0.addVertexWithUV(1.0D, 0.0D, (double)(0.0F - var7), (double)var3, (double)var4);
		var0.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - var7), (double)var1, (double)var4);
		var0.draw();
		float var8 = (float)var5 * (var1 - var3);
		float var9 = (float)var6 * (var4 - var2);
		var0.startDrawingQuads();
		var0.setNormal(-1.0F, 0.0F, 0.0F);

		int var10;
		float var11;
		float var12;
		for(var10 = 0; (float)var10 < var8; ++var10) {
			var11 = (float)var10 / var8;
			var12 = var1 + (var3 - var1) * var11 - 0.5F / (float)var5;
			var0.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - var7), (double)var12, (double)var4);
			var0.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var12, (double)var4);
			var0.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var12, (double)var2);
			var0.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - var7), (double)var12, (double)var2);
		}

		var0.draw();
		var0.startDrawingQuads();
		var0.setNormal(1.0F, 0.0F, 0.0F);

		float var13;
		for(var10 = 0; (float)var10 < var8; ++var10) {
			var11 = (float)var10 / var8;
			var12 = var1 + (var3 - var1) * var11 - 0.5F / (float)var5;
			var13 = var11 + 1.0F / var8;
			var0.addVertexWithUV((double)var13, 1.0D, (double)(0.0F - var7), (double)var12, (double)var2);
			var0.addVertexWithUV((double)var13, 1.0D, 0.0D, (double)var12, (double)var2);
			var0.addVertexWithUV((double)var13, 0.0D, 0.0D, (double)var12, (double)var4);
			var0.addVertexWithUV((double)var13, 0.0D, (double)(0.0F - var7), (double)var12, (double)var4);
		}

		var0.draw();
		var0.startDrawingQuads();
		var0.setNormal(0.0F, 1.0F, 0.0F);

		for(var10 = 0; (float)var10 < var9; ++var10) {
			var11 = (float)var10 / var9;
			var12 = var4 + (var2 - var4) * var11 - 0.5F / (float)var6;
			var13 = var11 + 1.0F / var9;
			var0.addVertexWithUV(0.0D, (double)var13, 0.0D, (double)var1, (double)var12);
			var0.addVertexWithUV(1.0D, (double)var13, 0.0D, (double)var3, (double)var12);
			var0.addVertexWithUV(1.0D, (double)var13, (double)(0.0F - var7), (double)var3, (double)var12);
			var0.addVertexWithUV(0.0D, (double)var13, (double)(0.0F - var7), (double)var1, (double)var12);
		}

		var0.draw();
		var0.startDrawingQuads();
		var0.setNormal(0.0F, -1.0F, 0.0F);

		for(var10 = 0; (float)var10 < var9; ++var10) {
			var11 = (float)var10 / var9;
			var12 = var4 + (var2 - var4) * var11 - 0.5F / (float)var6;
			var0.addVertexWithUV(1.0D, (double)var11, 0.0D, (double)var3, (double)var12);
			var0.addVertexWithUV(0.0D, (double)var11, 0.0D, (double)var1, (double)var12);
			var0.addVertexWithUV(0.0D, (double)var11, (double)(0.0F - var7), (double)var1, (double)var12);
			var0.addVertexWithUV(1.0D, (double)var11, (double)(0.0F - var7), (double)var3, (double)var12);
		}

		var0.draw();
	}

	public void renderItemInFirstPerson(float var1) {
		float var2 = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * var1;
		EntityClientPlayerMP var3 = this.mc.thePlayer;
		float var4 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var1;
		GL11.glPushMatrix();
		GL11.glRotatef(var4, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var1, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		float var6;
		float var7;
		if(var3 instanceof EntityPlayerSP) {
			EntityPlayerSP var5 = (EntityPlayerSP)var3;
			var6 = var5.prevRenderArmPitch + (var5.renderArmPitch - var5.prevRenderArmPitch) * var1;
			var7 = var5.prevRenderArmYaw + (var5.renderArmYaw - var5.prevRenderArmYaw) * var1;
			GL11.glRotatef((var3.rotationPitch - var6) * 0.1F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef((var3.rotationYaw - var7) * 0.1F, 0.0F, 1.0F, 0.0F);
		}

		ItemStack var17 = this.itemToRender;
		var6 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ));
		var6 = 1.0F;
		int var18 = this.mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ), 0);
		int var8 = var18 % 65536;
		int var9 = var18 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var8 / 1.0F, (float)var9 / 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var10;
		float var19;
		float var21;
		if(var17 != null) {
			var18 = Item.itemsList[var17.itemID].getColorFromItemStack(var17, 0);
			var19 = (float)(var18 >> 16 & 255) / 255.0F;
			var21 = (float)(var18 >> 8 & 255) / 255.0F;
			var10 = (float)(var18 & 255) / 255.0F;
			GL11.glColor4f(var6 * var19, var6 * var21, var6 * var10, 1.0F);
		} else {
			GL11.glColor4f(var6, var6, var6, 1.0F);
		}

		float var11;
		float var12;
		float var13;
		Render var24;
		RenderPlayer var26;
		if(var17 != null && var17.itemID == Item.map.itemID) {
			GL11.glPushMatrix();
			var7 = 0.8F;
			var19 = var3.getSwingProgress(var1);
			var21 = MathHelper.sin(var19 * (float)Math.PI);
			var10 = MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI);
			GL11.glTranslatef(-var10 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI * 2.0F) * 0.2F, -var21 * 0.2F);
			var19 = 1.0F - var4 / 45.0F + 0.1F;
			if(var19 < 0.0F) {
				var19 = 0.0F;
			}

			if(var19 > 1.0F) {
				var19 = 1.0F;
			}

			var19 = -MathHelper.cos(var19 * (float)Math.PI) * 0.5F + 0.5F;
			GL11.glTranslatef(0.0F, 0.0F * var7 - (1.0F - var2) * 1.2F - var19 * 0.5F + 0.04F, -0.9F * var7);
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(var19 * -85.0F, 0.0F, 0.0F, 1.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTextureForDownloadableImage(this.mc.thePlayer.skinUrl, this.mc.thePlayer.getTexture()));
			this.mc.renderEngine.resetBoundTexture();

			for(var9 = 0; var9 < 2; ++var9) {
				int var22 = var9 * 2 - 1;
				GL11.glPushMatrix();
				GL11.glTranslatef(-0.0F, -0.6F, 1.1F * (float)var22);
				GL11.glRotatef((float)(-45 * var22), 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef((float)(-65 * var22), 0.0F, 1.0F, 0.0F);
				var24 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
				var26 = (RenderPlayer)var24;
				var13 = 1.0F;
				GL11.glScalef(var13, var13, var13);
				var26.renderFirstPersonArm(this.mc.thePlayer);
				GL11.glPopMatrix();
			}

			var21 = var3.getSwingProgress(var1);
			var10 = MathHelper.sin(var21 * var21 * (float)Math.PI);
			var11 = MathHelper.sin(MathHelper.sqrt_float(var21) * (float)Math.PI);
			GL11.glRotatef(-var10 * 20.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var11 * 20.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-var11 * 80.0F, 1.0F, 0.0F, 0.0F);
			var12 = 0.38F;
			GL11.glScalef(var12, var12, var12);
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
			var13 = 0.015625F;
			GL11.glScalef(var13, var13, var13);
			this.mc.renderEngine.bindTexture("/misc/mapbg.png");
			Tessellator var27 = Tessellator.instance;
			GL11.glNormal3f(0.0F, 0.0F, -1.0F);
			var27.startDrawingQuads();
			byte var28 = 7;
			var27.addVertexWithUV((double)(0 - var28), (double)(128 + var28), 0.0D, 0.0D, 1.0D);
			var27.addVertexWithUV((double)(128 + var28), (double)(128 + var28), 0.0D, 1.0D, 1.0D);
			var27.addVertexWithUV((double)(128 + var28), (double)(0 - var28), 0.0D, 1.0D, 0.0D);
			var27.addVertexWithUV((double)(0 - var28), (double)(0 - var28), 0.0D, 0.0D, 0.0D);
			var27.draw();
			MapData var16 = Item.map.getMapData(var17, this.mc.theWorld);
			if(var16 != null) {
				this.mapItemRenderer.renderMap(this.mc.thePlayer, this.mc.renderEngine, var16);
			}

			GL11.glPopMatrix();
		} else if(var17 != null) {
			GL11.glPushMatrix();
			var7 = 0.8F;
			if(var3.getItemInUseCount() > 0) {
				EnumAction var20 = var17.getItemUseAction();
				if(var20 == EnumAction.eat || var20 == EnumAction.drink) {
					var21 = (float)var3.getItemInUseCount() - var1 + 1.0F;
					var10 = 1.0F - var21 / (float)var17.getMaxItemUseDuration();
					var11 = 1.0F - var10;
					var11 = var11 * var11 * var11;
					var11 = var11 * var11 * var11;
					var11 = var11 * var11 * var11;
					var12 = 1.0F - var11;
					GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var21 / 4.0F * (float)Math.PI) * 0.1F) * (float)((double)var10 > 0.2D ? 1 : 0), 0.0F);
					GL11.glTranslatef(var12 * 0.6F, -var12 * 0.5F, 0.0F);
					GL11.glRotatef(var12 * 90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(var12 * 10.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(var12 * 30.0F, 0.0F, 0.0F, 1.0F);
				}
			} else {
				var19 = var3.getSwingProgress(var1);
				var21 = MathHelper.sin(var19 * (float)Math.PI);
				var10 = MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI);
				GL11.glTranslatef(-var10 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI * 2.0F) * 0.2F, -var21 * 0.2F);
			}

			GL11.glTranslatef(0.7F * var7, -0.65F * var7 - (1.0F - var2) * 0.6F, -0.9F * var7);
			GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			var19 = var3.getSwingProgress(var1);
			var21 = MathHelper.sin(var19 * var19 * (float)Math.PI);
			var10 = MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI);
			GL11.glRotatef(-var21 * 20.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var10 * 20.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-var10 * 80.0F, 1.0F, 0.0F, 0.0F);
			var11 = 0.4F;
			GL11.glScalef(var11, var11, var11);
			float var14;
			float var15;
			if(var3.getItemInUseCount() > 0) {
				EnumAction var23 = var17.getItemUseAction();
				if(var23 == EnumAction.block) {
					GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
					GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
				} else if(var23 == EnumAction.bow) {
					GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
					var13 = (float)var17.getMaxItemUseDuration() - ((float)var3.getItemInUseCount() - var1 + 1.0F);
					var14 = var13 / 20.0F;
					var14 = (var14 * var14 + var14 * 2.0F) / 3.0F;
					if(var14 > 1.0F) {
						var14 = 1.0F;
					}

					if(var14 > 0.1F) {
						GL11.glTranslatef(0.0F, MathHelper.sin((var13 - 0.1F) * 1.3F) * 0.01F * (var14 - 0.1F), 0.0F);
					}

					GL11.glTranslatef(0.0F, 0.0F, var14 * 0.1F);
					GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glTranslatef(0.0F, 0.5F, 0.0F);
					var15 = 1.0F + var14 * 0.2F;
					GL11.glScalef(1.0F, 1.0F, var15);
					GL11.glTranslatef(0.0F, -0.5F, 0.0F);
					GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
				}
			}

			if(var17.getItem().shouldRotateAroundWhenRendering()) {
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			}

			if(var17.getItem().requiresMultipleRenderPasses()) {
				this.renderItem(var3, var17, 0);
				int var25 = Item.itemsList[var17.itemID].getColorFromItemStack(var17, 1);
				var13 = (float)(var25 >> 16 & 255) / 255.0F;
				var14 = (float)(var25 >> 8 & 255) / 255.0F;
				var15 = (float)(var25 & 255) / 255.0F;
				GL11.glColor4f(var6 * var13, var6 * var14, var6 * var15, 1.0F);
				this.renderItem(var3, var17, 1);
			} else {
				this.renderItem(var3, var17, 0);
			}

			GL11.glPopMatrix();
		} else if(!var3.isInvisible()) {
			GL11.glPushMatrix();
			var7 = 0.8F;
			var19 = var3.getSwingProgress(var1);
			var21 = MathHelper.sin(var19 * (float)Math.PI);
			var10 = MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI);
			GL11.glTranslatef(-var10 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI * 2.0F) * 0.4F, -var21 * 0.4F);
			GL11.glTranslatef(0.8F * var7, -(12.0F / 16.0F) * var7 - (1.0F - var2) * 0.6F, -0.9F * var7);
			GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			var19 = var3.getSwingProgress(var1);
			var21 = MathHelper.sin(var19 * var19 * (float)Math.PI);
			var10 = MathHelper.sin(MathHelper.sqrt_float(var19) * (float)Math.PI);
			GL11.glRotatef(var10 * 70.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var21 * 20.0F, 0.0F, 0.0F, 1.0F);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTextureForDownloadableImage(this.mc.thePlayer.skinUrl, this.mc.thePlayer.getTexture()));
			this.mc.renderEngine.resetBoundTexture();
			GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
			GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			GL11.glTranslatef(5.6F, 0.0F, 0.0F);
			var24 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
			var26 = (RenderPlayer)var24;
			var13 = 1.0F;
			GL11.glScalef(var13, var13, var13);
			var26.renderFirstPersonArm(this.mc.thePlayer);
			GL11.glPopMatrix();
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
	}

	public void renderOverlays(float var1) {
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		if(this.mc.thePlayer.isBurning()) {
			this.mc.renderEngine.bindTexture("/terrain.png");
			this.renderFireInFirstPerson(var1);
		}

		if(this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
			int var2 = MathHelper.floor_double(this.mc.thePlayer.posX);
			int var3 = MathHelper.floor_double(this.mc.thePlayer.posY);
			int var4 = MathHelper.floor_double(this.mc.thePlayer.posZ);
			this.mc.renderEngine.bindTexture("/terrain.png");
			int var5 = this.mc.theWorld.getBlockId(var2, var3, var4);
			if(this.mc.theWorld.isBlockNormalCube(var2, var3, var4)) {
				this.renderInsideOfBlock(var1, Block.blocksList[var5].getBlockTextureFromSide(2));
			} else {
				for(int var6 = 0; var6 < 8; ++var6) {
					float var7 = ((float)((var6 >> 0) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
					float var8 = ((float)((var6 >> 1) % 2) - 0.5F) * this.mc.thePlayer.height * 0.2F;
					float var9 = ((float)((var6 >> 2) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
					int var10 = MathHelper.floor_float((float)var2 + var7);
					int var11 = MathHelper.floor_float((float)var3 + var8);
					int var12 = MathHelper.floor_float((float)var4 + var9);
					if(this.mc.theWorld.isBlockNormalCube(var10, var11, var12)) {
						var5 = this.mc.theWorld.getBlockId(var10, var11, var12);
					}
				}
			}

			if(Block.blocksList[var5] != null) {
				this.renderInsideOfBlock(var1, Block.blocksList[var5].getBlockTextureFromSide(2));
			}
		}

		if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
			this.mc.renderEngine.bindTexture("/misc/water.png");
			this.renderWarpedTextureOverlay(var1);
		}

		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	private void renderInsideOfBlock(float var1, Icon var2) {
		Tessellator var3 = Tessellator.instance;
		float var4 = 0.1F;
		GL11.glColor4f(var4, var4, var4, 0.5F);
		GL11.glPushMatrix();
		float var5 = -1.0F;
		float var6 = 1.0F;
		float var7 = -1.0F;
		float var8 = 1.0F;
		float var9 = -0.5F;
		float var10 = var2.getMinU();
		float var11 = var2.getMaxU();
		float var12 = var2.getMinV();
		float var13 = var2.getMaxV();
		var3.startDrawingQuads();
		var3.addVertexWithUV((double)var5, (double)var7, (double)var9, (double)var11, (double)var13);
		var3.addVertexWithUV((double)var6, (double)var7, (double)var9, (double)var10, (double)var13);
		var3.addVertexWithUV((double)var6, (double)var8, (double)var9, (double)var10, (double)var12);
		var3.addVertexWithUV((double)var5, (double)var8, (double)var9, (double)var11, (double)var12);
		var3.draw();
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderWarpedTextureOverlay(float var1) {
		Tessellator var2 = Tessellator.instance;
		float var3 = this.mc.thePlayer.getBrightness(var1);
		GL11.glColor4f(var3, var3, var3, 0.5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glPushMatrix();
		float var4 = 4.0F;
		float var5 = -1.0F;
		float var6 = 1.0F;
		float var7 = -1.0F;
		float var8 = 1.0F;
		float var9 = -0.5F;
		float var10 = -this.mc.thePlayer.rotationYaw / 64.0F;
		float var11 = this.mc.thePlayer.rotationPitch / 64.0F;
		var2.startDrawingQuads();
		var2.addVertexWithUV((double)var5, (double)var7, (double)var9, (double)(var4 + var10), (double)(var4 + var11));
		var2.addVertexWithUV((double)var6, (double)var7, (double)var9, (double)(0.0F + var10), (double)(var4 + var11));
		var2.addVertexWithUV((double)var6, (double)var8, (double)var9, (double)(0.0F + var10), (double)(0.0F + var11));
		var2.addVertexWithUV((double)var5, (double)var8, (double)var9, (double)(var4 + var10), (double)(0.0F + var11));
		var2.draw();
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void renderFireInFirstPerson(float var1) {
		Tessellator var2 = Tessellator.instance;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float var3 = 1.0F;

		for(int var4 = 0; var4 < 2; ++var4) {
			GL11.glPushMatrix();
			Icon var5 = Block.fire.func_94438_c(1);
			float var6 = var5.getMinU();
			float var7 = var5.getMaxU();
			float var8 = var5.getMinV();
			float var9 = var5.getMaxV();
			float var10 = (0.0F - var3) / 2.0F;
			float var11 = var10 + var3;
			float var12 = 0.0F - var3 / 2.0F;
			float var13 = var12 + var3;
			float var14 = -0.5F;
			GL11.glTranslatef((float)(-(var4 * 2 - 1)) * 0.24F, -0.3F, 0.0F);
			GL11.glRotatef((float)(var4 * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
			var2.startDrawingQuads();
			var2.addVertexWithUV((double)var10, (double)var12, (double)var14, (double)var7, (double)var9);
			var2.addVertexWithUV((double)var11, (double)var12, (double)var14, (double)var6, (double)var9);
			var2.addVertexWithUV((double)var11, (double)var13, (double)var14, (double)var6, (double)var8);
			var2.addVertexWithUV((double)var10, (double)var13, (double)var14, (double)var7, (double)var8);
			var2.draw();
			GL11.glPopMatrix();
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void updateEquippedItem() {
		this.prevEquippedProgress = this.equippedProgress;
		EntityClientPlayerMP var1 = this.mc.thePlayer;
		ItemStack var2 = var1.inventory.getCurrentItem();
		boolean var3 = this.equippedItemSlot == var1.inventory.currentItem && var2 == this.itemToRender;
		if(this.itemToRender == null && var2 == null) {
			var3 = true;
		}

		if(var2 != null && this.itemToRender != null && var2 != this.itemToRender && var2.itemID == this.itemToRender.itemID && var2.getItemDamage() == this.itemToRender.getItemDamage()) {
			this.itemToRender = var2;
			var3 = true;
		}

		float var4 = 0.4F;
		float var5 = var3 ? 1.0F : 0.0F;
		float var6 = var5 - this.equippedProgress;
		if(var6 < -var4) {
			var6 = -var4;
		}

		if(var6 > var4) {
			var6 = var4;
		}

		this.equippedProgress += var6;
		if(this.equippedProgress < 0.1F) {
			this.itemToRender = var2;
			this.equippedItemSlot = var1.inventory.currentItem;
		}

	}

	public void resetEquippedProgress() {
		this.equippedProgress = 0.0F;
	}

	public void resetEquippedProgress2() {
		this.equippedProgress = 0.0F;
	}
}
