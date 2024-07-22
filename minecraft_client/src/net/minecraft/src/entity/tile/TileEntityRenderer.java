package net.minecraft.src.entity.tile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.lwjgl.opengl.GL11;

import net.minecraft.src.CrashReport;
import net.minecraft.src.CrashReportCategory;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.RenderEnchantmentTable;
import net.minecraft.src.RenderEndPortal;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.ReportedException;
import net.minecraft.src.entity.EntityLiving;
import net.minecraft.src.worldgen.World;

public class TileEntityRenderer {
	private Map specialRendererMap = new HashMap();
	public static TileEntityRenderer instance = new TileEntityRenderer();
	private FontRenderer fontRenderer;
	public static double staticPlayerX;
	public static double staticPlayerY;
	public static double staticPlayerZ;
	public RenderEngine renderEngine;
	public World worldObj;
	public EntityLiving entityLivingPlayer;
	public float playerYaw;
	public float playerPitch;
	public double playerX;
	public double playerY;
	public double playerZ;

	private TileEntityRenderer() {
		this.specialRendererMap.put(TileEntitySign.class, new TileEntitySignRenderer());
		this.specialRendererMap.put(TileEntityMobSpawner.class, new TileEntityMobSpawnerRenderer());
		this.specialRendererMap.put(TileEntityPiston.class, new TileEntityRendererPiston());
		this.specialRendererMap.put(TileEntityChest.class, new TileEntityChestRenderer());
		this.specialRendererMap.put(TileEntityEnderChest.class, new TileEntityEnderChestRenderer());
		this.specialRendererMap.put(TileEntityEnchantmentTable.class, new RenderEnchantmentTable());
		this.specialRendererMap.put(TileEntityEndPortal.class, new RenderEndPortal());
		this.specialRendererMap.put(TileEntityBeacon.class, new TileEntityBeaconRenderer());
		this.specialRendererMap.put(TileEntitySkull.class, new TileEntitySkullRenderer());
		Iterator var1 = this.specialRendererMap.values().iterator();

		while(var1.hasNext()) {
			TileEntitySpecialRenderer var2 = (TileEntitySpecialRenderer)var1.next();
			var2.setTileEntityRenderer(this);
		}

	}

	public TileEntitySpecialRenderer getSpecialRendererForClass(Class var1) {
		TileEntitySpecialRenderer var2 = (TileEntitySpecialRenderer)this.specialRendererMap.get(var1);
		if(var2 == null && var1 != TileEntity.class) {
			var2 = this.getSpecialRendererForClass(var1.getSuperclass());
			this.specialRendererMap.put(var1, var2);
		}

		return var2;
	}

	public boolean hasSpecialRenderer(TileEntity var1) {
		return this.getSpecialRendererForEntity(var1) != null;
	}

	public TileEntitySpecialRenderer getSpecialRendererForEntity(TileEntity var1) {
		return var1 == null ? null : this.getSpecialRendererForClass(var1.getClass());
	}

	public void cacheActiveRenderInfo(World var1, RenderEngine var2, FontRenderer var3, EntityLiving var4, float var5) {
		if(this.worldObj != var1) {
			this.setWorld(var1);
		}

		this.renderEngine = var2;
		this.entityLivingPlayer = var4;
		this.fontRenderer = var3;
		this.playerYaw = var4.prevRotationYaw + (var4.rotationYaw - var4.prevRotationYaw) * var5;
		this.playerPitch = var4.prevRotationPitch + (var4.rotationPitch - var4.prevRotationPitch) * var5;
		this.playerX = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * (double)var5;
		this.playerY = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * (double)var5;
		this.playerZ = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * (double)var5;
	}

	public void renderTileEntity(TileEntity var1, float var2) {
		if(var1.getDistanceFrom(this.playerX, this.playerY, this.playerZ) < var1.getMaxRenderDistanceSquared()) {
			int var3 = this.worldObj.getLightBrightnessForSkyBlocks(var1.xCoord, var1.yCoord, var1.zCoord, 0);
			int var4 = var3 % 65536;
			int var5 = var3 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var4 / 1.0F, (float)var5 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.renderTileEntityAt(var1, (double)var1.xCoord - staticPlayerX, (double)var1.yCoord - staticPlayerY, (double)var1.zCoord - staticPlayerZ, var2);
		}

	}

	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		TileEntitySpecialRenderer var9 = this.getSpecialRendererForEntity(var1);
		if(var9 != null) {
			try {
				var9.renderTileEntityAt(var1, var2, var4, var6, var8);
			} catch (Throwable var13) {
				CrashReport var11 = CrashReport.makeCrashReport(var13, "Rendering Tile Entity");
				CrashReportCategory var12 = var11.makeCategory("Tile Entity Details");
				var1.func_85027_a(var12);
				throw new ReportedException(var11);
			}
		}

	}

	public void setWorld(World var1) {
		this.worldObj = var1;
		Iterator var2 = this.specialRendererMap.values().iterator();

		while(var2.hasNext()) {
			TileEntitySpecialRenderer var3 = (TileEntitySpecialRenderer)var2.next();
			if(var3 != null) {
				var3.onWorldChange(var1);
			}
		}

	}

	public FontRenderer getFontRenderer() {
		return this.fontRenderer;
	}
}
