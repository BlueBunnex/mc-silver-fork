package net.minecraft.src.gui;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.src.BossStatus;
import net.minecraft.src.Chunk;
import net.minecraft.src.Direction;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.FoodStats;
import net.minecraft.src.Icon;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Potion;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Score;
import net.minecraft.src.ScoreObjective;
import net.minecraft.src.ScorePlayerTeam;
import net.minecraft.src.Scoreboard;
import net.minecraft.src.StatCollector;
import net.minecraft.src.StringUtils;
import net.minecraft.src.Tessellator;
import net.minecraft.src.block.Block;
import net.minecraft.src.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiIngame extends Gui {
	private static final RenderItem itemRenderer = new RenderItem();
	private final Random rand = new Random();
	private final Minecraft mc;
	private final GuiNewChat persistantChatGUI;
	private int updateCounter = 0;
	private String recordPlaying = "";
	private int recordPlayingUpFor = 0;
	private boolean recordIsPlaying = false;
	public float prevVignetteBrightness = 1.0F;
	private int remainingHighlightTicks;
	private ItemStack highlightingItemStack;

	public GuiIngame(Minecraft var1) {
		this.mc = var1;
		this.persistantChatGUI = new GuiNewChat(var1);
	}

	public void renderGameOverlay(float var1, boolean var2, int var3, int var4) {
		ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		FontRenderer var8 = this.mc.fontRenderer;
		this.mc.entityRenderer.setupOverlayRendering();
		GL11.glEnable(GL11.GL_BLEND);
		if(Minecraft.isFancyGraphicsEnabled()) {
			this.renderVignette(this.mc.thePlayer.getBrightness(var1), var6, var7);
		} else {
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

		ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);
		if(this.mc.gameSettings.thirdPersonView == 0 && var9 != null && var9.itemID == Block.pumpkin.blockID) {
			this.renderPumpkinBlur(var6, var7);
		}

		if(!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
			float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * var1;
			if(var10 > 0.0F) {
				this.renderPortalOverlay(var10, var6, var7);
			}
		}

		boolean var11;
		int var12;
		int var13;
		int var16;
		int var17;
		int var18;
		int var20;
		int var22;
		int var23;
		int var24;
		int var26;
		byte var27;
		int var47;
		int var52;
		if(!this.mc.playerController.enableEverythingIsScrewedUpMode()) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture("/gui/gui.png");
			InventoryPlayer var31 = this.mc.thePlayer.inventory;
			this.zLevel = -90.0F;
			this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
			this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var31.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
			this.mc.renderEngine.bindTexture("/gui/icons.png");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
			this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			var11 = this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1;
			if(this.mc.thePlayer.hurtResistantTime < 10) {
				var11 = false;
			}

			var12 = this.mc.thePlayer.getHealth();
			var13 = this.mc.thePlayer.prevHealth;
			this.rand.setSeed((long)(this.updateCounter * 312871));
			boolean var14 = false;
			FoodStats var15 = this.mc.thePlayer.getFoodStats();
			var16 = var15.getFoodLevel();
			var17 = var15.getPrevFoodLevel();
			this.mc.mcProfiler.startSection("bossHealth");
			this.renderBossHealth();
			this.mc.mcProfiler.endSection();
			int var19;
			if(this.mc.playerController.shouldDrawHUD()) {
				var18 = var6 / 2 - 91;
				var19 = var6 / 2 + 91;
				this.mc.mcProfiler.startSection("expBar");
				var20 = this.mc.thePlayer.xpBarCap();
				if(var20 > 0) {
					short var21 = 182;
					var22 = (int)(this.mc.thePlayer.experience * (float)(var21 + 1));
					var23 = var7 - 32 + 3;
					this.drawTexturedModalRect(var18, var23, 0, 64, var21, 5);
					if(var22 > 0) {
						this.drawTexturedModalRect(var18, var23, 0, 69, var22, 5);
					}
				}

				var47 = var7 - 39;
				var22 = var47 - 10;
				var23 = this.mc.thePlayer.getTotalArmorValue();
				var24 = -1;
				if(this.mc.thePlayer.isPotionActive(Potion.regeneration)) {
					var24 = this.updateCounter % 25;
				}

				this.mc.mcProfiler.endStartSection("healthArmor");

				int var25;
				int var28;
				int var29;
				for(var25 = 0; var25 < 10; ++var25) {
					if(var23 > 0) {
						var26 = var18 + var25 * 8;
						if(var25 * 2 + 1 < var23) {
							this.drawTexturedModalRect(var26, var22, 34, 9, 9, 9);
						}

						if(var25 * 2 + 1 == var23) {
							this.drawTexturedModalRect(var26, var22, 25, 9, 9, 9);
						}

						if(var25 * 2 + 1 > var23) {
							this.drawTexturedModalRect(var26, var22, 16, 9, 9, 9);
						}
					}

					var26 = 16;
					if(this.mc.thePlayer.isPotionActive(Potion.poison)) {
						var26 += 36;
					} else if(this.mc.thePlayer.isPotionActive(Potion.wither)) {
						var26 += 72;
					}

					var27 = 0;
					if(var11) {
						var27 = 1;
					}

					var28 = var18 + var25 * 8;
					var29 = var47;
					if(var12 <= 4) {
						var29 = var47 + this.rand.nextInt(2);
					}

					if(var25 == var24) {
						var29 -= 2;
					}

					byte var30 = 0;
					if(this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
						var30 = 5;
					}

					this.drawTexturedModalRect(var28, var29, 16 + var27 * 9, 9 * var30, 9, 9);
					if(var11) {
						if(var25 * 2 + 1 < var13) {
							this.drawTexturedModalRect(var28, var29, var26 + 54, 9 * var30, 9, 9);
						}

						if(var25 * 2 + 1 == var13) {
							this.drawTexturedModalRect(var28, var29, var26 + 63, 9 * var30, 9, 9);
						}
					}

					if(var25 * 2 + 1 < var12) {
						this.drawTexturedModalRect(var28, var29, var26 + 36, 9 * var30, 9, 9);
					}

					if(var25 * 2 + 1 == var12) {
						this.drawTexturedModalRect(var28, var29, var26 + 45, 9 * var30, 9, 9);
					}
				}

				this.mc.mcProfiler.endStartSection("food");

				for(var25 = 0; var25 < 10; ++var25) {
					var26 = var47;
					var52 = 16;
					byte var53 = 0;
					if(this.mc.thePlayer.isPotionActive(Potion.hunger)) {
						var52 += 36;
						var53 = 13;
					}

					if(this.mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (var16 * 3 + 1) == 0) {
						var26 = var47 + (this.rand.nextInt(3) - 1);
					}

					if(var14) {
						var53 = 1;
					}

					var29 = var19 - var25 * 8 - 9;
					this.drawTexturedModalRect(var29, var26, 16 + var53 * 9, 27, 9, 9);
					if(var14) {
						if(var25 * 2 + 1 < var17) {
							this.drawTexturedModalRect(var29, var26, var52 + 54, 27, 9, 9);
						}

						if(var25 * 2 + 1 == var17) {
							this.drawTexturedModalRect(var29, var26, var52 + 63, 27, 9, 9);
						}
					}

					if(var25 * 2 + 1 < var16) {
						this.drawTexturedModalRect(var29, var26, var52 + 36, 27, 9, 9);
					}

					if(var25 * 2 + 1 == var16) {
						this.drawTexturedModalRect(var29, var26, var52 + 45, 27, 9, 9);
					}
				}

				this.mc.mcProfiler.endStartSection("air");
				if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
					var25 = this.mc.thePlayer.getAir();
					var26 = MathHelper.ceiling_double_int((double)(var25 - 2) * 10.0D / 300.0D);
					var52 = MathHelper.ceiling_double_int((double)var25 * 10.0D / 300.0D) - var26;

					for(var28 = 0; var28 < var26 + var52; ++var28) {
						if(var28 < var26) {
							this.drawTexturedModalRect(var19 - var28 * 8 - 9, var22, 16, 18, 9, 9);
						} else {
							this.drawTexturedModalRect(var19 - var28 * 8 - 9, var22, 25, 18, 9, 9);
						}
					}
				}

				this.mc.mcProfiler.endSection();
			}

			GL11.glDisable(GL11.GL_BLEND);
			this.mc.mcProfiler.startSection("actionBar");
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.enableGUIStandardItemLighting();

			for(var18 = 0; var18 < 9; ++var18) {
				var19 = var6 / 2 - 90 + var18 * 20 + 2;
				var20 = var7 - 16 - 3;
				this.renderInventorySlot(var18, var19, var20, var1);
			}

			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			this.mc.mcProfiler.endSection();
		}

		float var33;
		if(this.mc.thePlayer.getSleepTimer() > 0) {
			this.mc.mcProfiler.startSection("sleep");
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			int var32 = this.mc.thePlayer.getSleepTimer();
			var33 = (float)var32 / 100.0F;
			if(var33 > 1.0F) {
				var33 = 1.0F - (float)(var32 - 100) / 10.0F;
			}

			var12 = (int)(220.0F * var33) << 24 | 1052704;
			drawRect(0, 0, var6, var7, var12);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			this.mc.mcProfiler.endSection();
		}

		int var36;
		int var40;
		if(this.mc.playerController.func_78763_f() && this.mc.thePlayer.experienceLevel > 0) {
			this.mc.mcProfiler.startSection("expLevel");
			var11 = false;
			var12 = var11 ? 16777215 : 8453920;
			String var34 = "" + this.mc.thePlayer.experienceLevel;
			var36 = (var6 - var8.getStringWidth(var34)) / 2;
			var40 = var7 - 31 - 4;
			var8.drawString(var34, var36 + 1, var40, 0);
			var8.drawString(var34, var36 - 1, var40, 0);
			var8.drawString(var34, var36, var40 + 1, 0);
			var8.drawString(var34, var36, var40 - 1, 0);
			var8.drawString(var34, var36, var40, var12);
			this.mc.mcProfiler.endSection();
		}

		String var35;
		if(this.mc.gameSettings.heldItemTooltips) {
			this.mc.mcProfiler.startSection("toolHighlight");
			if(this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
				var35 = this.highlightingItemStack.getDisplayName();
				var12 = (var6 - var8.getStringWidth(var35)) / 2;
				var13 = var7 - 59;
				if(!this.mc.playerController.shouldDrawHUD()) {
					var13 += 14;
				}

				var36 = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);
				if(var36 > 255) {
					var36 = 255;
				}

				if(var36 > 0) {
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					var8.drawStringWithShadow(var35, var12, var13, 16777215 + (var36 << 24));
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
				}
			}

			this.mc.mcProfiler.endSection();
		}

		if(this.mc.gameSettings.showDebugInfo) {
			this.mc.mcProfiler.startSection("debug");
			GL11.glPushMatrix();
			var8.drawStringWithShadow("Minecraft 1.5.2 (" + this.mc.debug + ")", 2, 2, 16777215);
			var8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
			var8.drawStringWithShadow(this.mc.getEntityDebug(), 2, 22, 16777215);
			var8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
			var8.drawStringWithShadow(this.mc.getWorldProviderName(), 2, 42, 16777215);
			long var39 = Runtime.getRuntime().maxMemory();
			long var37 = Runtime.getRuntime().totalMemory();
			long var41 = Runtime.getRuntime().freeMemory();
			long var44 = var37 - var41;
			String var45 = "Used memory: " + var44 * 100L / var39 + "% (" + var44 / 1024L / 1024L + "MB) of " + var39 / 1024L / 1024L + "MB";
			this.drawString(var8, var45, var6 - var8.getStringWidth(var45) - 2, 2, 14737632);
			var45 = "Allocated memory: " + var37 * 100L / var39 + "% (" + var37 / 1024L / 1024L + "MB)";
			this.drawString(var8, var45, var6 - var8.getStringWidth(var45) - 2, 12, 14737632);
			var47 = MathHelper.floor_double(this.mc.thePlayer.posX);
			var22 = MathHelper.floor_double(this.mc.thePlayer.posY);
			var23 = MathHelper.floor_double(this.mc.thePlayer.posZ);
			this.drawString(var8, String.format("x: %.5f (%d) // c: %d (%d)", new Object[]{Double.valueOf(this.mc.thePlayer.posX), Integer.valueOf(var47), Integer.valueOf(var47 >> 4), Integer.valueOf(var47 & 15)}), 2, 64, 14737632);
			this.drawString(var8, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[]{Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 72, 14737632);
			this.drawString(var8, String.format("z: %.5f (%d) // c: %d (%d)", new Object[]{Double.valueOf(this.mc.thePlayer.posZ), Integer.valueOf(var23), Integer.valueOf(var23 >> 4), Integer.valueOf(var23 & 15)}), 2, 80, 14737632);
			var24 = MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			this.drawString(var8, "f: " + var24 + " (" + Direction.directions[var24] + ") / " + MathHelper.wrapAngleTo180_float(this.mc.thePlayer.rotationYaw), 2, 88, 14737632);
			if(this.mc.theWorld != null && this.mc.theWorld.blockExists(var47, var22, var23)) {
				Chunk var50 = this.mc.theWorld.getChunkFromBlockCoords(var47, var23);
				this.drawString(var8, "lc: " + (var50.getTopFilledSegment() + 15) + " b: " + var50.getBiomeGenForWorldCoords(var47 & 15, var23 & 15, this.mc.theWorld.getWorldChunkManager()).biomeName + " bl: " + var50.getSavedLightValue(EnumSkyBlock.Block, var47 & 15, var22, var23 & 15) + " sl: " + var50.getSavedLightValue(EnumSkyBlock.Sky, var47 & 15, var22, var23 & 15) + " rl: " + var50.getBlockLightValue(var47 & 15, var22, var23 & 15, 0), 2, 96, 14737632);
			}

			this.drawString(var8, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[]{Float.valueOf(this.mc.thePlayer.capabilities.getWalkSpeed()), Float.valueOf(this.mc.thePlayer.capabilities.getFlySpeed()), Boolean.valueOf(this.mc.thePlayer.onGround), Integer.valueOf(this.mc.theWorld.getHeightValue(var47, var23))}), 2, 104, 14737632);
			GL11.glPopMatrix();
			this.mc.mcProfiler.endSection();
		}

		if(this.recordPlayingUpFor > 0) {
			this.mc.mcProfiler.startSection("overlayMessage");
			var33 = (float)this.recordPlayingUpFor - var1;
			var12 = (int)(var33 * 256.0F / 20.0F);
			if(var12 > 255) {
				var12 = 255;
			}

			if(var12 > 0) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 48), 0.0F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				var13 = 16777215;
				if(this.recordIsPlaying) {
					var13 = Color.HSBtoRGB(var33 / 50.0F, 0.7F, 0.6F) & 16777215;
				}

				var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var13 + (var12 << 24));
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}

			this.mc.mcProfiler.endSection();
		}

		ScoreObjective var42 = this.mc.theWorld.getScoreboard().func_96539_a(1);
		if(var42 != null) {
			this.func_96136_a(var42, var7, var6, var8);
		}

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);
		this.mc.mcProfiler.startSection("chat");
		this.persistantChatGUI.drawChat(this.updateCounter);
		this.mc.mcProfiler.endSection();
		GL11.glPopMatrix();
		var42 = this.mc.theWorld.getScoreboard().func_96539_a(0);
		if(this.mc.gameSettings.keyBindPlayerList.pressed && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1 || var42 != null)) {
			this.mc.mcProfiler.startSection("playerList");
			NetClientHandler var38 = this.mc.thePlayer.sendQueue;
			List var43 = var38.playerInfoList;
			var36 = var38.currentServerMaxPlayers;
			var40 = var36;

			for(var16 = 1; var40 > 20; var40 = (var36 + var16 - 1) / var16) {
				++var16;
			}

			var17 = 300 / var16;
			if(var17 > 150) {
				var17 = 150;
			}

			var18 = (var6 - var16 * var17) / 2;
			byte var46 = 10;
			drawRect(var18 - 1, var46 - 1, var18 + var17 * var16, var46 + 9 * var40, Integer.MIN_VALUE);

			for(var20 = 0; var20 < var36; ++var20) {
				var47 = var18 + var20 % var16 * var17;
				var22 = var46 + var20 / var16 * 9;
				drawRect(var47, var22, var47 + var17 - 1, var22 + 8, 553648127);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				if(var20 < var43.size()) {
					GuiPlayerInfo var48 = (GuiPlayerInfo)var43.get(var20);
					ScorePlayerTeam var49 = this.mc.theWorld.getScoreboard().getPlayersTeam(var48.name);
					String var51 = ScorePlayerTeam.func_96667_a(var49, var48.name);
					var8.drawStringWithShadow(var51, var47, var22, 16777215);
					if(var42 != null) {
						var26 = var47 + var8.getStringWidth(var51) + 5;
						var52 = var47 + var17 - 12 - 5;
						if(var52 - var26 > 5) {
							Score var55 = var42.getScoreboard().func_96529_a(var48.name, var42);
							String var57 = EnumChatFormatting.YELLOW + "" + var55.func_96652_c();
							var8.drawStringWithShadow(var57, var52 - var8.getStringWidth(var57), var22, 16777215);
						}
					}

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					this.mc.renderEngine.bindTexture("/gui/icons.png");
					byte var54 = 0;
					boolean var56 = false;
					if(var48.responseTime < 0) {
						var27 = 5;
					} else if(var48.responseTime < 150) {
						var27 = 0;
					} else if(var48.responseTime < 300) {
						var27 = 1;
					} else if(var48.responseTime < 600) {
						var27 = 2;
					} else if(var48.responseTime < 1000) {
						var27 = 3;
					} else {
						var27 = 4;
					}

					this.zLevel += 100.0F;
					this.drawTexturedModalRect(var47 + var17 - 12, var22, 0 + var54 * 10, 176 + var27 * 8, 10, 8);
					this.zLevel -= 100.0F;
				}
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	private void func_96136_a(ScoreObjective var1, int var2, int var3, FontRenderer var4) {
		Scoreboard var5 = var1.getScoreboard();
		Collection var6 = var5.func_96534_i(var1);
		if(var6.size() <= 15) {
			int var7 = var4.getStringWidth(var1.getDisplayName());

			String var11;
			for(Iterator var8 = var6.iterator(); var8.hasNext(); var7 = Math.max(var7, var4.getStringWidth(var11))) {
				Score var9 = (Score)var8.next();
				ScorePlayerTeam var10 = var5.getPlayersTeam(var9.func_96653_e());
				var11 = ScorePlayerTeam.func_96667_a(var10, var9.func_96653_e()) + ": " + EnumChatFormatting.RED + var9.func_96652_c();
			}

			int var22 = var6.size() * var4.FONT_HEIGHT;
			int var23 = var2 / 2 + var22 / 3;
			byte var24 = 3;
			int var25 = var3 - var7 - var24;
			int var12 = 0;
			Iterator var13 = var6.iterator();

			while(var13.hasNext()) {
				Score var14 = (Score)var13.next();
				++var12;
				ScorePlayerTeam var15 = var5.getPlayersTeam(var14.func_96653_e());
				String var16 = ScorePlayerTeam.func_96667_a(var15, var14.func_96653_e());
				String var17 = EnumChatFormatting.RED + "" + var14.func_96652_c();
				int var19 = var23 - var12 * var4.FONT_HEIGHT;
				int var20 = var3 - var24 + 2;
				drawRect(var25 - 2, var19, var20, var19 + var4.FONT_HEIGHT, 1342177280);
				var4.drawString(var16, var25, var19, 553648127);
				var4.drawString(var17, var20 - var4.getStringWidth(var17), var19, 553648127);
				if(var12 == var6.size()) {
					String var21 = var1.getDisplayName();
					drawRect(var25 - 2, var19 - var4.FONT_HEIGHT - 1, var20, var19 - 1, 1610612736);
					drawRect(var25 - 2, var19 - 1, var20, var19, 1342177280);
					var4.drawString(var21, var25 + var7 / 2 - var4.getStringWidth(var21) / 2, var19 - var4.FONT_HEIGHT, 553648127);
				}
			}

		}
	}

	private void renderBossHealth() {
		if(BossStatus.bossName != null && BossStatus.statusBarLength > 0) {
			--BossStatus.statusBarLength;
			FontRenderer var1 = this.mc.fontRenderer;
			ScaledResolution var2 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int var3 = var2.getScaledWidth();
			short var4 = 182;
			int var5 = var3 / 2 - var4 / 2;
			int var6 = (int)(BossStatus.healthScale * (float)(var4 + 1));
			byte var7 = 12;
			this.drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
			this.drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
			if(var6 > 0) {
				this.drawTexturedModalRect(var5, var7, 0, 79, var6, 5);
			}

			String var8 = BossStatus.bossName;
			var1.drawStringWithShadow(var8, var3 / 2 - var1.getStringWidth(var8) / 2, var7 - 10, 16777215);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture("/gui/icons.png");
		}
	}

	private void renderPumpkinBlur(int var1, int var2) {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		this.mc.renderEngine.bindTexture("%blur%/misc/pumpkinblur.png");
		Tessellator var3 = Tessellator.instance;
		var3.startDrawingQuads();
		var3.addVertexWithUV(0.0D, (double)var2, -90.0D, 0.0D, 1.0D);
		var3.addVertexWithUV((double)var1, (double)var2, -90.0D, 1.0D, 1.0D);
		var3.addVertexWithUV((double)var1, 0.0D, -90.0D, 1.0D, 0.0D);
		var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		var3.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderVignette(float var1, int var2, int var3) {
		var1 = 1.0F - var1;
		if(var1 < 0.0F) {
			var1 = 0.0F;
		}

		if(var1 > 1.0F) {
			var1 = 1.0F;
		}

		this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(var1 - this.prevVignetteBrightness) * 0.01D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
		this.mc.renderEngine.bindTexture("%blur%/misc/vignette.png");
		Tessellator var4 = Tessellator.instance;
		var4.startDrawingQuads();
		var4.addVertexWithUV(0.0D, (double)var3, -90.0D, 0.0D, 1.0D);
		var4.addVertexWithUV((double)var2, (double)var3, -90.0D, 1.0D, 1.0D);
		var4.addVertexWithUV((double)var2, 0.0D, -90.0D, 1.0D, 0.0D);
		var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		var4.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	private void renderPortalOverlay(float var1, int var2, int var3) {
		if(var1 < 1.0F) {
			var1 *= var1;
			var1 *= var1;
			var1 = var1 * 0.8F + 0.2F;
		}

		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, var1);
		this.mc.renderEngine.bindTexture("/terrain.png");
		Icon var4 = Block.portal.getBlockTextureFromSide(1);
		float var5 = var4.getMinU();
		float var6 = var4.getMinV();
		float var7 = var4.getMaxU();
		float var8 = var4.getMaxV();
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV(0.0D, (double)var3, -90.0D, (double)var5, (double)var8);
		var9.addVertexWithUV((double)var2, (double)var3, -90.0D, (double)var7, (double)var8);
		var9.addVertexWithUV((double)var2, 0.0D, -90.0D, (double)var7, (double)var6);
		var9.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var5, (double)var6);
		var9.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderInventorySlot(int var1, int var2, int var3, float var4) {
		ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[var1];
		if(var5 != null) {
			float var6 = (float)var5.animationsToGo - var4;
			if(var6 > 0.0F) {
				GL11.glPushMatrix();
				float var7 = 1.0F + var6 / 5.0F;
				GL11.glTranslatef((float)(var2 + 8), (float)(var3 + 12), 0.0F);
				GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
				GL11.glTranslatef((float)(-(var2 + 8)), (float)(-(var3 + 12)), 0.0F);
			}

			itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, var2, var3);
			if(var6 > 0.0F) {
				GL11.glPopMatrix();
			}

			itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, var2, var3);
		}
	}

	public void updateTick() {
		if(this.recordPlayingUpFor > 0) {
			--this.recordPlayingUpFor;
		}

		++this.updateCounter;
		if(this.mc.thePlayer != null) {
			ItemStack var1 = this.mc.thePlayer.inventory.getCurrentItem();
			if(var1 == null) {
				this.remainingHighlightTicks = 0;
			} else if(this.highlightingItemStack != null && var1.itemID == this.highlightingItemStack.itemID && ItemStack.areItemStackTagsEqual(var1, this.highlightingItemStack) && (var1.isItemStackDamageable() || var1.getItemDamage() == this.highlightingItemStack.getItemDamage())) {
				if(this.remainingHighlightTicks > 0) {
					--this.remainingHighlightTicks;
				}
			} else {
				this.remainingHighlightTicks = 40;
			}

			this.highlightingItemStack = var1;
		}

	}

	public void setRecordPlayingMessage(String var1) {
		this.recordPlaying = "Now playing: " + var1;
		this.recordPlayingUpFor = 60;
		this.recordIsPlaying = true;
	}

	public GuiNewChat getChatGUI() {
		return this.persistantChatGUI;
	}

	public int getUpdateCounter() {
		return this.updateCounter;
	}
}
