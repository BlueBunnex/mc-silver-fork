package net.minecraft.src.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.ICommand;
import net.minecraft.src.ICommandManager;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.PlayerSelector;
import net.minecraft.src.WrongUsageException;

public class CommandHandler implements ICommandManager {
	private final Map commandMap = new HashMap();
	private final Set commandSet = new HashSet();

	public int executeCommand(ICommandSender var1, String var2) {
		var2 = var2.trim();
		if(var2.startsWith("/")) {
			var2 = var2.substring(1);
		}

		String[] var3 = var2.split(" ");
		String var4 = var3[0];
		var3 = dropFirstString(var3);
		ICommand var5 = (ICommand)this.commandMap.get(var4);
		int var6 = this.getUsernameIndex(var5, var3);
		int var7 = 0;

		try {
			if(var5 == null) {
				throw new CommandNotFoundException();
			}

			if(var5.canCommandSenderUseCommand(var1)) {
				if(var6 > -1) {
					EntityPlayerMP[] var8 = PlayerSelector.matchPlayers(var1, var3[var6]);
					String var9 = var3[var6];
					EntityPlayerMP[] var10 = var8;
					int var11 = var8.length;

					for(int var12 = 0; var12 < var11; ++var12) {
						EntityPlayerMP var13 = var10[var12];
						var3[var6] = var13.getEntityName();

						try {
							var5.processCommand(var1, var3);
							++var7;
						} catch (CommandException var15) {
							var1.sendChatToPlayer(EnumChatFormatting.RED + var1.translateString(var15.getMessage(), var15.getErrorOjbects()));
						}
					}

					var3[var6] = var9;
				} else {
					var5.processCommand(var1, var3);
					++var7;
				}
			} else {
				var1.sendChatToPlayer("" + EnumChatFormatting.RED + "You do not have permission to use this command.");
			}
		} catch (WrongUsageException var16) {
			var1.sendChatToPlayer(EnumChatFormatting.RED + var1.translateString("commands.generic.usage", new Object[]{var1.translateString(var16.getMessage(), var16.getErrorOjbects())}));
		} catch (CommandException var17) {
			var1.sendChatToPlayer(EnumChatFormatting.RED + var1.translateString(var17.getMessage(), var17.getErrorOjbects()));
		} catch (Throwable var18) {
			var1.sendChatToPlayer(EnumChatFormatting.RED + var1.translateString("commands.generic.exception", new Object[0]));
			var18.printStackTrace();
		}

		return var7;
	}

	public ICommand registerCommand(ICommand var1) {
		List var2 = var1.getCommandAliases();
		this.commandMap.put(var1.getCommandName(), var1);
		this.commandSet.add(var1);
		if(var2 != null) {
			Iterator var3 = var2.iterator();

			while(true) {
				String var4;
				ICommand var5;
				do {
					if(!var3.hasNext()) {
						return var1;
					}

					var4 = (String)var3.next();
					var5 = (ICommand)this.commandMap.get(var4);
				} while(var5 != null && var5.getCommandName().equals(var4));

				this.commandMap.put(var4, var1);
			}
		} else {
			return var1;
		}
	}

	private static String[] dropFirstString(String[] var0) {
		String[] var1 = new String[var0.length - 1];

		for(int var2 = 1; var2 < var0.length; ++var2) {
			var1[var2 - 1] = var0[var2];
		}

		return var1;
	}

	public List getPossibleCommands(ICommandSender var1, String var2) {
		String[] var3 = var2.split(" ", -1);
		String var4 = var3[0];
		if(var3.length == 1) {
			ArrayList var8 = new ArrayList();
			Iterator var6 = this.commandMap.entrySet().iterator();

			while(var6.hasNext()) {
				Entry var7 = (Entry)var6.next();
				if(CommandBase.doesStringStartWith(var4, (String)var7.getKey()) && ((ICommand)var7.getValue()).canCommandSenderUseCommand(var1)) {
					var8.add(var7.getKey());
				}
			}

			return var8;
		} else {
			if(var3.length > 1) {
				ICommand var5 = (ICommand)this.commandMap.get(var4);
				if(var5 != null) {
					return var5.addTabCompletionOptions(var1, dropFirstString(var3));
				}
			}

			return null;
		}
	}

	public List getPossibleCommands(ICommandSender var1) {
		ArrayList var2 = new ArrayList();
		Iterator var3 = this.commandSet.iterator();

		while(var3.hasNext()) {
			ICommand var4 = (ICommand)var3.next();
			if(var4.canCommandSenderUseCommand(var1)) {
				var2.add(var4);
			}
		}

		return var2;
	}

	public Map getCommands() {
		return this.commandMap;
	}

	private int getUsernameIndex(ICommand var1, String[] var2) {
		if(var1 == null) {
			return -1;
		} else {
			for(int var3 = 0; var3 < var2.length; ++var3) {
				if(var1.isUsernameIndex(var2, var3) && PlayerSelector.matchesMultiplePlayers(var2[var3])) {
					return var3;
				}
			}

			return -1;
		}
	}
}
