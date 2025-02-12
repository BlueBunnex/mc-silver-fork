package net.minecraft.src.callable;

import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public class CallableServerMemoryStats implements Callable {
	final MinecraftServer mcServer;

	public CallableServerMemoryStats(MinecraftServer var1) {
		this.mcServer = var1;
	}

	public String callServerMemoryStats() {
		return MinecraftServer.getServerConfigurationManager(this.mcServer).getCurrentPlayerCount() + " / " + MinecraftServer.getServerConfigurationManager(this.mcServer).getMaxPlayers() + "; " + MinecraftServer.getServerConfigurationManager(this.mcServer).playerEntityList;
	}

	public Object call() {
		return this.callServerMemoryStats();
	}
}
