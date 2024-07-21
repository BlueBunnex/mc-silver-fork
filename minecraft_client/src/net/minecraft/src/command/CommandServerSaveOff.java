package net.minecraft.src.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.WorldServer;

public class CommandServerSaveOff extends CommandBase {
	public String getCommandName() {
		return "save-off";
	}

	public int getRequiredPermissionLevel() {
		return 4;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		MinecraftServer var3 = MinecraftServer.getServer();

		for(int var4 = 0; var4 < var3.worldServers.length; ++var4) {
			if(var3.worldServers[var4] != null) {
				WorldServer var5 = var3.worldServers[var4];
				var5.canNotSave = true;
			}
		}

		notifyAdmins(var1, "commands.save.disabled", new Object[0]);
	}
}
