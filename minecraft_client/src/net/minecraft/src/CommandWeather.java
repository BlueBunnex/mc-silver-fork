package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class CommandWeather extends CommandBase {
	public String getCommandName() {
		return "weather";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length < 1) {
			throw new WrongUsageException("commands.weather.usage", new Object[0]);
		} else {
			int var3 = (300 + (new Random()).nextInt(600)) * 20;
			if(var2.length >= 2) {
				var3 = parseIntBounded(var1, var2[1], 1, 1000000) * 20;
			}

			WorldServer var4 = MinecraftServer.getServer().worldServers[0];
			WorldInfo var5 = var4.getWorldInfo();
			var5.setRainTime(var3);
			var5.setThunderTime(var3);
			if("clear".equalsIgnoreCase(var2[0])) {
				var5.setRaining(false);
				var5.setThundering(false);
				notifyAdmins(var1, "commands.weather.clear", new Object[0]);
			} else if("rain".equalsIgnoreCase(var2[0])) {
				var5.setRaining(true);
				var5.setThundering(false);
				notifyAdmins(var1, "commands.weather.rain", new Object[0]);
			} else if("thunder".equalsIgnoreCase(var2[0])) {
				var5.setRaining(true);
				var5.setThundering(true);
				notifyAdmins(var1, "commands.weather.thunder", new Object[0]);
			}

		}
	}

	public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
		return var2.length == 1 ? getListOfStringsMatchingLastWord(var2, new String[]{"clear", "rain", "thunder"}) : null;
	}
}
