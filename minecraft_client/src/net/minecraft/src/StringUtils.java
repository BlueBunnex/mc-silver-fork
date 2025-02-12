package net.minecraft.src;

import java.util.regex.Pattern;

public class StringUtils {
	private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

	public static String ticksToElapsedTime(int var0) {
		int var1 = var0 / 20;
		int var2 = var1 / 60;
		var1 %= 60;
		return var1 < 10 ? var2 + ":0" + var1 : var2 + ":" + var1;
	}

	public static String stripControlCodes(String var0) {
		return patternControlCode.matcher(var0).replaceAll("");
	}
}
