package net.minecraft.src.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.WrongUsageException;

public class CommandServerEmote extends CommandBase {
	public String getCommandName() {
		return "me";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender var1) {
		return var1.translateString("commands.me.usage", new Object[0]);
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length > 0) {
			String var3 = func_82361_a(var1, var2, 0, var1.canCommandSenderUseCommand(1, "me"));
			MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(new Packet3Chat("* " + var1.getCommandSenderName() + " " + var3));
		} else {
			throw new WrongUsageException("commands.me.usage", new Object[0]);
		}
	}

	public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
		return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
	}
}
