package net.minecraft.src.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.entity.EntityPlayerMP;

public class CommandClearInventory extends CommandBase {
	public String getCommandName() {
		return "clear";
	}

	public String getCommandUsage(ICommandSender var1) {
		return var1.translateString("commands.clear.usage", new Object[0]);
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayerMP var3 = var2.length == 0 ? getCommandSenderAsPlayer(var1) : func_82359_c(var1, var2[0]);
		int var4 = var2.length >= 2 ? parseIntWithMin(var1, var2[1], 1) : -1;
		int var5 = var2.length >= 3 ? parseIntWithMin(var1, var2[2], 0) : -1;
		int var6 = var3.inventory.clearInventory(var4, var5);
		var3.inventoryContainer.detectAndSendChanges();
		if(var6 == 0) {
			throw new CommandException("commands.clear.failure", new Object[]{var3.getEntityName()});
		} else {
			notifyAdmins(var1, "commands.clear.success", new Object[]{var3.getEntityName(), Integer.valueOf(var6)});
		}
	}

	public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
		return var2.length == 1 ? getListOfStringsMatchingLastWord(var2, this.getAllOnlineUsernames()) : null;
	}

	protected String[] getAllOnlineUsernames() {
		return MinecraftServer.getServer().getAllUsernames();
	}

	public boolean isUsernameIndex(String[] var1, int var2) {
		return var2 == 0;
	}
}
