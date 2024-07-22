package net.minecraft.src.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;

public class CommandShowSeed extends CommandBase {
	public boolean canCommandSenderUseCommand(ICommandSender var1) {
		return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(var1);
	}

	public String getCommandName() {
		return "seed";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public void processCommand(ICommandSender var1, String[] var2) {
		Object var3 = var1 instanceof EntityPlayer ? ((EntityPlayer)var1).worldObj : MinecraftServer.getServer().worldServerForDimension(0);
		var1.sendChatToPlayer("Seed: " + ((World)var3).getSeed());
	}
}
