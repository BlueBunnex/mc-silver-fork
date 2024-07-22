package net.minecraft.src.item;

import java.util.List;

import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagIntArray;
import net.minecraft.src.StatCollector;
import net.minecraft.src.entity.EntityPlayer;

public class ItemFireworkCharge extends Item {
	private Icon theIcon;

	public ItemFireworkCharge(int var1) {
		super(var1);
	}

	public Icon getIconFromDamageForRenderPass(int var1, int var2) {
		return var2 > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(var1, var2);
	}

	public int getColorFromItemStack(ItemStack var1, int var2) {
		if(var2 != 1) {
			return super.getColorFromItemStack(var1, var2);
		} else {
			NBTBase var3 = func_92108_a(var1, "Colors");
			if(var3 == null) {
				return 9079434;
			} else {
				NBTTagIntArray var4 = (NBTTagIntArray)var3;
				if(var4.intArray.length == 1) {
					return var4.intArray[0];
				} else {
					int var5 = 0;
					int var6 = 0;
					int var7 = 0;
					int[] var8 = var4.intArray;
					int var9 = var8.length;

					for(int var10 = 0; var10 < var9; ++var10) {
						int var11 = var8[var10];
						var5 += (var11 & 16711680) >> 16;
						var6 += (var11 & '\uff00') >> 8;
						var7 += (var11 & 255) >> 0;
					}

					var5 /= var4.intArray.length;
					var6 /= var4.intArray.length;
					var7 /= var4.intArray.length;
					return var5 << 16 | var6 << 8 | var7;
				}
			}
		}
	}

	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	public static NBTBase func_92108_a(ItemStack var0, String var1) {
		if(var0.hasTagCompound()) {
			NBTTagCompound var2 = var0.getTagCompound().getCompoundTag("Explosion");
			if(var2 != null) {
				return var2.getTag(var1);
			}
		}

		return null;
	}

	public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
		if(var1.hasTagCompound()) {
			NBTTagCompound var5 = var1.getTagCompound().getCompoundTag("Explosion");
			if(var5 != null) {
				func_92107_a(var5, var3);
			}
		}

	}

	public static void func_92107_a(NBTTagCompound var0, List var1) {
		byte var2 = var0.getByte("Type");
		if(var2 >= 0 && var2 <= 4) {
			var1.add(StatCollector.translateToLocal("item.fireworksCharge.type." + var2).trim());
		} else {
			var1.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
		}

		int[] var3 = var0.getIntArray("Colors");
		int var8;
		int var9;
		if(var3.length > 0) {
			boolean var4 = true;
			String var5 = "";
			int[] var6 = var3;
			int var7 = var3.length;

			for(var8 = 0; var8 < var7; ++var8) {
				var9 = var6[var8];
				if(!var4) {
					var5 = var5 + ", ";
				}

				var4 = false;
				boolean var10 = false;

				for(int var11 = 0; var11 < 16; ++var11) {
					if(var9 == ItemDye.dyeColors[var11]) {
						var10 = true;
						var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.dyeColorNames[var11]);
						break;
					}
				}

				if(!var10) {
					var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
				}
			}

			var1.add(var5);
		}

		int[] var13 = var0.getIntArray("FadeColors");
		boolean var15;
		if(var13.length > 0) {
			var15 = true;
			String var14 = StatCollector.translateToLocal("item.fireworksCharge.fadeTo") + " ";
			int[] var16 = var13;
			var8 = var13.length;

			for(var9 = 0; var9 < var8; ++var9) {
				int var18 = var16[var9];
				if(!var15) {
					var14 = var14 + ", ";
				}

				var15 = false;
				boolean var19 = false;

				for(int var12 = 0; var12 < 16; ++var12) {
					if(var18 == ItemDye.dyeColors[var12]) {
						var19 = true;
						var14 = var14 + StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.dyeColorNames[var12]);
						break;
					}
				}

				if(!var19) {
					var14 = var14 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
				}
			}

			var1.add(var14);
		}

		var15 = var0.getBoolean("Trail");
		if(var15) {
			var1.add(StatCollector.translateToLocal("item.fireworksCharge.trail"));
		}

		boolean var17 = var0.getBoolean("Flicker");
		if(var17) {
			var1.add(StatCollector.translateToLocal("item.fireworksCharge.flicker"));
		}

	}

	public void registerIcons(IconRegister var1) {
		super.registerIcons(var1);
		this.theIcon = var1.registerIcon("fireworksCharge_overlay");
	}
}
