package net.minecraft.src.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.WorldServer;

public class CommandServerSaveOn extends CommandBase {
	public String getCommandName() {
		return "save-on";
	}

	public int getRequiredPermissionLevel() {
		return 4;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		MinecraftServer var3 = MinecraftServer.getServer();

		for(int var4 = 0; var4 < var3.worldServers.length; ++var4) {
			if(var3.worldServers[var4] != null) {
				WorldServer var5 = var3.worldServers[var4];
				var5.canNotSave = false;
			}
		}

		notifyAdmins(var1, "commands.save.enabled", new Object[0]);
	}
}
