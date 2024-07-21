package net.minecraft.src.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ICommandSender;

public class CommandServerList extends CommandBase {
	public String getCommandName() {
		return "list";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		var1.sendChatToPlayer(var1.translateString("commands.players.list", new Object[]{Integer.valueOf(MinecraftServer.getServer().getCurrentPlayerCount()), Integer.valueOf(MinecraftServer.getServer().getMaxPlayers())}));
		var1.sendChatToPlayer(MinecraftServer.getServer().getConfigurationManager().getPlayerListAsString());
	}
}
