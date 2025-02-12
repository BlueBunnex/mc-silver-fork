package net.minecraft.src.gui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.ISaveFormat;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RunnableTitleScreen;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.Tessellator;
import net.minecraft.src.ThreadTitleScreen;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GuiMainMenu extends GuiScreen {
	
	private static final Random rand = new Random();
	private float updateCounter = 0.0F;
	private String splashText = "missingno";
	private int panoramaTimer = 0;
	private int viewportTexture;
	private boolean field_96141_q = true;
	private static boolean field_96140_r = false;
	private static boolean field_96139_s = false;
	private final Object field_104025_t = new Object();
	private String field_92025_p;
	private String field_104024_v;
	private static final String[] titlePanoramaPaths = new String[]{"/title/bg/panorama0.png", "/title/bg/panorama1.png", "/title/bg/panorama2.png", "/title/bg/panorama3.png", "/title/bg/panorama4.png", "/title/bg/panorama5.png"};
	public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
	private int field_92024_r;
	private int field_92023_s;
	private int field_92022_t;
	private int field_92021_u;
	private int field_92020_v;
	private int field_92019_w;

	public GuiMainMenu() {
		
		// read in splashes
		BufferedReader read = null;
		
		try {
			ArrayList<String> allSplashes = new ArrayList<String>();
			read = new BufferedReader(new InputStreamReader(GuiMainMenu.class.getResourceAsStream("/title/splashes.txt"), Charset.forName("UTF-8")));

			String line;
			
			while (true) {
				
				line = read.readLine();
				
				if(line == null) {
					
					this.splashText = allSplashes.get(rand.nextInt(allSplashes.size()));
					break;
				}

				line = line.trim();
				
				if(line.length() > 0)
					allSplashes.add(line);
			}
			
		} catch (IOException e) {
			
		} finally {
			
			if(read != null) {
				try {
					read.close();
				} catch (IOException e) {}
			}
		}

		this.updateCounter = rand.nextFloat();
		this.field_92025_p = "";
		String architecture = System.getProperty("os_architecture");
		String version      = System.getProperty("java_version");
		
		if("ppc".equalsIgnoreCase(architecture)) {
			this.field_92025_p = "" + EnumChatFormatting.BOLD + "Notice!" + EnumChatFormatting.RESET + " PowerPC compatibility will be dropped in Minecraft 1.6";
			this.field_104024_v = "http://tinyurl.com/javappc";
			
		} else if (version != null && version.startsWith("1.5")) {
			this.field_92025_p = "" + EnumChatFormatting.BOLD + "Notice!" + EnumChatFormatting.RESET + " Java 1.5 compatibility will be dropped in Minecraft 1.6";
			this.field_104024_v = "http://tinyurl.com/javappc";
		}

		if(this.field_92025_p.length() == 0) {
			(new Thread(new RunnableTitleScreen(this), "1.6 Update Check Thread")).start();
		}

	}

	public void updateScreen() {
		++this.panoramaTimer;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	protected void keyTyped(char var1, int var2) {
	}

	public void initGui() {
		this.viewportTexture = this.mc.renderEngine.allocateAndSetupTexture(new BufferedImage(256, 256, 2));

		StringTranslate var2 = StringTranslate.getInstance();
		int var4 = this.height / 4 + 48;
		
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, var4, var2.translateKey("menu.singleplayer")));
		
		GuiButton mult = new GuiButton(2, this.width / 2 - 100, var4 + 24 * 1, var2.translateKey("menu.multiplayer"));
		mult.enabled = false;
		this.buttonList.add(mult);
		
		this.func_96137_a(var2, var4, 24);
		if(this.mc.hideQuitButton) {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var4 + 72, var2.translateKey("menu.options")));
		} else {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var4 + 72 + 12, 98, 20, var2.translateKey("menu.options")));
			this.buttonList.add(new GuiButton(4, this.width / 2 + 2, var4 + 72 + 12, 98, 20, var2.translateKey("menu.quit")));
		}

		this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, var4 + 72 + 12));
		Object var5 = this.field_104025_t;
		synchronized(var5) {
			this.field_92023_s = this.fontRenderer.getStringWidth(this.field_92025_p);
			this.field_92024_r = this.fontRenderer.getStringWidth(field_96138_a);
			int var6 = Math.max(this.field_92023_s, this.field_92024_r);
			this.field_92022_t = (this.width - var6) / 2;
			this.field_92021_u = ((GuiButton)this.buttonList.get(0)).yPosition - 24;
			this.field_92020_v = this.field_92022_t + var6;
			this.field_92019_w = this.field_92021_u + 24;
		}
	}

	private void func_96137_a(StringTranslate var1, int var2, int var3) {
		if(this.field_96141_q) {
			if(!field_96140_r) {
				field_96140_r = true;
				(new ThreadTitleScreen(this, var1, var2, var3)).start();
			} else if(field_96139_s) {
				this.func_98060_b(var1, var2, var3);
			}
		}

	}

	private void func_98060_b(StringTranslate var1, int var2, int var3) {
		this.buttonList.add(new GuiButton(3, this.width / 2 - 100, var2 + var3 * 2, var1.translateKey("menu.online")));
	}

	protected void actionPerformed(GuiButton button) {
		
		switch (button.id) {
		
			case 0:
				this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
				break;
				
			case 1:
				this.mc.displayGuiScreen(new GuiSelectWorld(this));
				break;
				
			case 2:
				// multiplayer is currently disabled entirely
				this.mc.displayGuiScreen(new GuiMultiplayer(this));
				break;
				
			case 3:
				this.mc.displayGuiScreen(new GuiScreenOnlineServers(this));
				break;
				
			case 4:
				this.mc.shutdown();
				break;
			
			case 5:
				this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings));
				break;
		}

	}

	public void confirmClicked(boolean var1, int var2) {
		if(var1 && var2 == 12) {
			ISaveFormat var6 = this.mc.getSaveLoader();
			var6.flushCache();
			var6.deleteWorldDirectory("Demo_World");
			this.mc.displayGuiScreen(this);
		} else if(var2 == 13) {
			if(var1) {
				try {
					Class var3 = Class.forName("java.awt.Desktop");
					Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
					var3.getMethod("browse", new Class[]{URI.class}).invoke(var4, new Object[]{new URI(this.field_104024_v)});
				} catch (Throwable var5) {
					var5.printStackTrace();
				}
			}

			this.mc.displayGuiScreen(this);
		}

	}

	private void drawPanorama(int var1, int var2, float var3) {
		Tessellator var4 = Tessellator.instance;
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GLU.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		byte var5 = 8;

		for(int var6 = 0; var6 < var5 * var5; ++var6) {
			GL11.glPushMatrix();
			float var7 = ((float)(var6 % var5) / (float)var5 - 0.5F) / 64.0F;
			float var8 = ((float)(var6 / var5) / (float)var5 - 0.5F) / 64.0F;
			float var9 = 0.0F;
			GL11.glTranslatef(var7, var8, var9);
			GL11.glRotatef(MathHelper.sin(((float)this.panoramaTimer + var3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-((float)this.panoramaTimer + var3) * 0.1F, 0.0F, 1.0F, 0.0F);

			for(int var10 = 0; var10 < 6; ++var10) {
				GL11.glPushMatrix();
				if(var10 == 1) {
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				}

				if(var10 == 2) {
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				}

				if(var10 == 3) {
					GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
				}

				if(var10 == 4) {
					GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				}

				if(var10 == 5) {
					GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				}

				this.mc.renderEngine.bindTexture(titlePanoramaPaths[var10]);
				var4.startDrawingQuads();
				var4.setColorRGBA_I(16777215, 255 / (var6 + 1));
				float var11 = 0.0F;
				var4.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + var11), (double)(0.0F + var11));
				var4.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - var11), (double)(0.0F + var11));
				var4.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - var11), (double)(1.0F - var11));
				var4.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + var11), (double)(1.0F - var11));
				var4.draw();
				GL11.glPopMatrix();
			}

			GL11.glPopMatrix();
			GL11.glColorMask(true, true, true, false);
		}

		var4.setTranslation(0.0D, 0.0D, 0.0D);
		GL11.glColorMask(true, true, true, true);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	private void rotateAndBlurSkybox(float var1) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.viewportTexture);
		this.mc.renderEngine.resetBoundTexture();
		GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColorMask(true, true, true, false);
		Tessellator var2 = Tessellator.instance;
		var2.startDrawingQuads();
		byte var3 = 3;

		for(int var4 = 0; var4 < var3; ++var4) {
			var2.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float)(var4 + 1));
			int var5 = this.width;
			int var6 = this.height;
			float var7 = (float)(var4 - var3 / 2) / 256.0F;
			var2.addVertexWithUV((double)var5, (double)var6, (double)this.zLevel, (double)(0.0F + var7), 0.0D);
			var2.addVertexWithUV((double)var5, 0.0D, (double)this.zLevel, (double)(1.0F + var7), 0.0D);
			var2.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(1.0F + var7), 1.0D);
			var2.addVertexWithUV(0.0D, (double)var6, (double)this.zLevel, (double)(0.0F + var7), 1.0D);
		}

		var2.draw();
		GL11.glColorMask(true, true, true, true);
		this.mc.renderEngine.resetBoundTexture();
	}

	private void renderSkybox(int var1, int var2, float var3) {
		GL11.glViewport(0, 0, 256, 256);
		this.drawPanorama(var1, var2, var3);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		this.rotateAndBlurSkybox(var3);
		GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		Tessellator var4 = Tessellator.instance;
		var4.startDrawingQuads();
		float var5 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
		float var6 = (float)this.height * var5 / 256.0F;
		float var7 = (float)this.width * var5 / 256.0F;
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		var4.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
		int var8 = this.width;
		int var9 = this.height;
		var4.addVertexWithUV(0.0D, (double)var9, (double)this.zLevel, (double)(0.5F - var6), (double)(0.5F + var7));
		var4.addVertexWithUV((double)var8, (double)var9, (double)this.zLevel, (double)(0.5F - var6), (double)(0.5F - var7));
		var4.addVertexWithUV((double)var8, 0.0D, (double)this.zLevel, (double)(0.5F + var6), (double)(0.5F - var7));
		var4.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(0.5F + var6), (double)(0.5F + var7));
		var4.draw();
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.renderSkybox(var1, var2, var3);
		Tessellator var4 = Tessellator.instance;
		short var5 = 274;
		int var6 = this.width / 2 - var5 / 2;
		byte var7 = 30;
		this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
		this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
		this.mc.renderEngine.bindTexture("/title/mclogo.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if((double)this.updateCounter < 1.0E-4D) {
			this.drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 99, 44);
			this.drawTexturedModalRect(var6 + 99, var7 + 0, 129, 0, 27, 44);
			this.drawTexturedModalRect(var6 + 99 + 26, var7 + 0, 126, 0, 3, 44);
			this.drawTexturedModalRect(var6 + 99 + 26 + 3, var7 + 0, 99, 0, 26, 44);
			this.drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
		} else {
			this.drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 155, 44);
			this.drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
		}

		var4.setColorOpaque_I(16777215);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
		var8 = var8 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.splashText) + 32);
		GL11.glScalef(var8, var8, var8);
		this.drawCenteredString(this.fontRenderer, this.splashText, 0, -8, 16776960);
		GL11.glPopMatrix();

		//this.drawString(this.fontRenderer, "Minecraft 1.5.2", 2, this.height - 10, 16777215);
		String var10 = "Developed by the Minecraft Silver team. Distribute if you want!";
		this.drawString(this.fontRenderer, var10, this.width - this.fontRenderer.getStringWidth(var10) - 2, this.height - 10, 16777215);
		if(this.field_92025_p != null && this.field_92025_p.length() > 0) {
			drawRect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1428160512);
			this.drawString(this.fontRenderer, this.field_92025_p, this.field_92022_t, this.field_92021_u, 16777215);
			this.drawString(this.fontRenderer, field_96138_a, (this.width - this.field_92024_r) / 2, ((GuiButton)this.buttonList.get(0)).yPosition - 12, 16777215);
		}

		super.drawScreen(var1, var2, var3);
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		Object var4 = this.field_104025_t;
		synchronized(var4) {
			if(this.field_92025_p.length() > 0 && var1 >= this.field_92022_t && var1 <= this.field_92020_v && var2 >= this.field_92021_u && var2 <= this.field_92019_w) {
				GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
				var5.func_92026_h();
				this.mc.displayGuiScreen(var5);
			}

		}
	}

	public static Object func_104004_a(GuiMainMenu var0) {
		return var0.field_104025_t;
	}

	public static String func_104005_a(GuiMainMenu var0, String var1) {
		return var0.field_92025_p = var1;
	}

	public static String func_104013_b(GuiMainMenu var0, String var1) {
		return var0.field_104024_v = var1;
	}

	public static int func_104006_a(GuiMainMenu var0, int var1) {
		return var0.field_92023_s = var1;
	}

	public static String func_104023_b(GuiMainMenu var0) {
		return var0.field_92025_p;
	}

	public static FontRenderer func_104022_c(GuiMainMenu var0) {
		return var0.fontRenderer;
	}

	public static int func_104014_b(GuiMainMenu var0, int var1) {
		return var0.field_92024_r = var1;
	}

	public static FontRenderer func_104007_d(GuiMainMenu var0) {
		return var0.fontRenderer;
	}

	public static int func_104016_e(GuiMainMenu var0) {
		return var0.field_92023_s;
	}

	public static int func_104015_f(GuiMainMenu var0) {
		return var0.field_92024_r;
	}

	public static int func_104008_c(GuiMainMenu var0, int var1) {
		return var0.field_92022_t = var1;
	}

	public static int func_104009_d(GuiMainMenu var0, int var1) {
		return var0.field_92021_u = var1;
	}

	public static List func_104019_g(GuiMainMenu var0) {
		return var0.buttonList;
	}

	public static int func_104011_e(GuiMainMenu var0, int var1) {
		return var0.field_92020_v = var1;
	}

	public static int func_104018_h(GuiMainMenu var0) {
		return var0.field_92022_t;
	}

	public static int func_104012_f(GuiMainMenu var0, int var1) {
		return var0.field_92019_w = var1;
	}

	public static int func_104020_i(GuiMainMenu var0) {
		return var0.field_92021_u;
	}

	public static Minecraft func_98058_a(GuiMainMenu var0) {
		return var0.mc;
	}

	public static void func_98061_a(GuiMainMenu var0, StringTranslate var1, int var2, int var3) {
		var0.func_98060_b(var1, var2, var3);
	}

	public static boolean func_98059_a(boolean var0) {
		field_96139_s = var0;
		return var0;
	}
}
