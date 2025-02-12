package net.minecraft.src;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.callable.CallableServerType;
import net.minecraft.src.callable.CallableType;
import net.minecraft.src.entity.EntityPlayer;
import net.minecraft.src.worldgen.World;
import net.minecraft.src.worldgen.WorldSettings;
import net.minecraft.src.worldgen.WorldType;

public class DedicatedServer extends MinecraftServer implements IServer {
	
	private final List pendingCommandList = Collections.synchronizedList(new ArrayList());
	private final ILogAgent field_98131_l;
	private RConThreadQuery theRConThreadQuery;
	private RConThreadMain theRConThreadMain;
	private PropertyManager settings;
	private boolean canSpawnStructures;
	private EnumGameType gameType;
	private NetworkListenThread networkThread;
	private boolean guiIsEnabled = false;

	public DedicatedServer(File var1) {
		super(var1);
		this.field_98131_l = new LogAgent("Minecraft-Server", (String)null, (new File(var1, "server.log")).getAbsolutePath());
		new DedicatedServerSleepThread(this);
	}

	protected boolean startServer() throws IOException {
		DedicatedServerCommandThread var1 = new DedicatedServerCommandThread(this);
		var1.setDaemon(true);
		var1.start();
		this.getLogAgent().logInfo("Starting minecraft server version 1.5.2");
		if(Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
			this.getLogAgent().logWarning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
		}

		this.getLogAgent().logInfo("Loading properties");
		this.settings = new PropertyManager(new File("server.properties"), this.getLogAgent());
		if(this.isSinglePlayer()) {
			this.setHostname("127.0.0.1");
		} else {
			this.setOnlineMode(this.settings.getBooleanProperty("online-mode", true));
			this.setHostname(this.settings.getProperty("server-ip", ""));
		}

		this.setCanSpawnAnimals(this.settings.getBooleanProperty("spawn-animals", true));
		this.setCanSpawnNPCs(this.settings.getBooleanProperty("spawn-npcs", true));
		this.setAllowPvp(this.settings.getBooleanProperty("pvp", true));
		this.setAllowFlight(this.settings.getBooleanProperty("allow-flight", false));
		this.setTexturePack(this.settings.getProperty("texture-pack", ""));
		this.setMOTD(this.settings.getProperty("motd", "A Minecraft Server"));
		this.func_104055_i(this.settings.getBooleanProperty("force-gamemode", false));
		if(this.settings.getIntProperty("difficulty", 1) < 0) {
			this.settings.setProperty("difficulty", Integer.valueOf(0));
		} else if(this.settings.getIntProperty("difficulty", 1) > 3) {
			this.settings.setProperty("difficulty", Integer.valueOf(3));
		}

		this.canSpawnStructures = this.settings.getBooleanProperty("generate-structures", true);
		int var2 = this.settings.getIntProperty("gamemode", EnumGameType.SURVIVAL.getID());
		this.gameType = WorldSettings.getGameTypeById(var2);
		this.getLogAgent().logInfo("Default game type: " + this.gameType);
		InetAddress var3 = null;
		if(this.getServerHostname().length() > 0) {
			var3 = InetAddress.getByName(this.getServerHostname());
		}

		if(this.getServerPort() < 0) {
			this.setServerPort(this.settings.getIntProperty("server-port", 25565));
		}

		this.getLogAgent().logInfo("Generating keypair");
		this.setKeyPair(CryptManager.createNewKeyPair());
		this.getLogAgent().logInfo("Starting Minecraft server on " + (this.getServerHostname().length() == 0 ? "*" : this.getServerHostname()) + ":" + this.getServerPort());

		try {
			this.networkThread = new DedicatedServerListenThread(this, var3, this.getServerPort());
		} catch (IOException var16) {
			this.getLogAgent().logWarning("**** FAILED TO BIND TO PORT!");
			this.getLogAgent().logWarningFormatted("The exception was: {0}", new Object[]{var16.toString()});
			this.getLogAgent().logWarning("Perhaps a server is already running on that port?");
			return false;
		}

		if(!this.isServerInOnlineMode()) {
			this.getLogAgent().logWarning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
			this.getLogAgent().logWarning("The server will make no attempt to authenticate usernames. Beware.");
			this.getLogAgent().logWarning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
			this.getLogAgent().logWarning("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
		}

		this.setConfigurationManager(new DedicatedPlayerList(this));
		long var4 = System.nanoTime();
		if(this.getFolderName() == null) {
			this.setFolderName(this.settings.getProperty("level-name", "world"));
		}

		String var6 = this.settings.getProperty("level-seed", "");
		String var7 = this.settings.getProperty("level-type", "DEFAULT");
		String var8 = this.settings.getProperty("generator-settings", "");
		long var9 = (new Random()).nextLong();
		if(var6.length() > 0) {
			try {
				long var11 = Long.parseLong(var6);
				if(var11 != 0L) {
					var9 = var11;
				}
			} catch (NumberFormatException var15) {
				var9 = (long)var6.hashCode();
			}
		}

		WorldType var17 = WorldType.parseWorldType(var7);
		if(var17 == null) {
			var17 = WorldType.DEFAULT;
		}

		this.setBuildLimit(this.settings.getIntProperty("max-build-height", 256));
		this.setBuildLimit((this.getBuildLimit() + 8) / 16 * 16);
		this.setBuildLimit(MathHelper.clamp_int(this.getBuildLimit(), 64, 256));
		this.settings.setProperty("max-build-height", Integer.valueOf(this.getBuildLimit()));
		this.getLogAgent().logInfo("Preparing level \"" + this.getFolderName() + "\"");
		this.loadAllWorlds(this.getFolderName(), this.getFolderName(), var9, var17, var8);
		long var12 = System.nanoTime() - var4;
		String var14 = String.format("%.3fs", new Object[]{Double.valueOf((double)var12 / 1.0E9D)});
		this.getLogAgent().logInfo("Done (" + var14 + ")! For help, type \"help\" or \"?\"");
		if(this.settings.getBooleanProperty("enable-query", false)) {
			this.getLogAgent().logInfo("Starting GS4 status listener");
			this.theRConThreadQuery = new RConThreadQuery(this);
			this.theRConThreadQuery.startThread();
		}

		if(this.settings.getBooleanProperty("enable-rcon", false)) {
			this.getLogAgent().logInfo("Starting remote control listener");
			this.theRConThreadMain = new RConThreadMain(this);
			this.theRConThreadMain.startThread();
		}

		return true;
	}

	public boolean canStructuresSpawn() {
		return this.canSpawnStructures;
	}

	public EnumGameType getGameType() {
		return this.gameType;
	}

	public int getDifficulty() {
		return this.settings.getIntProperty("difficulty", 1);
	}

	public boolean isHardcore() {
		return this.settings.getBooleanProperty("hardcore", false);
	}

	protected void finalTick(CrashReport var1) {
		while(this.isServerRunning()) {
			this.executePendingCommands();

			try {
				Thread.sleep(10L);
			} catch (InterruptedException var3) {
				var3.printStackTrace();
			}
		}

	}

	public CrashReport addServerInfoToCrashReport(CrashReport var1) {
		var1 = super.addServerInfoToCrashReport(var1);
		var1.func_85056_g().addCrashSectionCallable("Is Modded", new CallableType(this));
		var1.func_85056_g().addCrashSectionCallable("Type", new CallableServerType(this));
		return var1;
	}

	protected void systemExitNow() {
		System.exit(0);
	}

	protected void updateTimeLightAndEntitiesDedicated() {
		super.updateTimeLightAndEntities();
		this.executePendingCommands();
	}

	public boolean getAllowNether() {
		return this.settings.getBooleanProperty("allow-nether", true);
	}

	public boolean allowSpawnMonsters() {
		return this.settings.getBooleanProperty("spawn-monsters", true);
	}

	public void addServerStatsToSnooper(PlayerUsageSnooper var1) {
		var1.addData("whitelist_enabled", Boolean.valueOf(this.getDedicatedPlayerList().isWhiteListEnabled()));
		var1.addData("whitelist_count", Integer.valueOf(this.getDedicatedPlayerList().getWhiteListedPlayers().size()));
		super.addServerStatsToSnooper(var1);
	}

	public boolean isSnooperEnabled() {
		return this.settings.getBooleanProperty("snooper-enabled", true);
	}

	public void addPendingCommand(String var1, ICommandSender var2) {
		this.pendingCommandList.add(new ServerCommand(var1, var2));
	}

	public void executePendingCommands() {
		while(!this.pendingCommandList.isEmpty()) {
			ServerCommand var1 = (ServerCommand)this.pendingCommandList.remove(0);
			this.getCommandManager().executeCommand(var1.sender, var1.command);
		}

	}

	public boolean isDedicatedServer() {
		return true;
	}

	public DedicatedPlayerList getDedicatedPlayerList() {
		return (DedicatedPlayerList)super.getConfigurationManager();
	}

	public NetworkListenThread getNetworkThread() {
		return this.networkThread;
	}

	public int getIntProperty(String var1, int var2) {
		return this.settings.getIntProperty(var1, var2);
	}

	public String getStringProperty(String var1, String var2) {
		return this.settings.getProperty(var1, var2);
	}

	public boolean getBooleanProperty(String var1, boolean var2) {
		return this.settings.getBooleanProperty(var1, var2);
	}

	public void setProperty(String var1, Object var2) {
		this.settings.setProperty(var1, var2);
	}

	public void saveProperties() {
		this.settings.saveProperties();
	}

	public String getSettingsFilename() {
		File var1 = this.settings.getPropertiesFile();
		return var1 != null ? var1.getAbsolutePath() : "No settings file";
	}

	public boolean getGuiEnabled() {
		return this.guiIsEnabled;
	}

	public String shareToLAN(EnumGameType var1, boolean var2) {
		return "";
	}

	public boolean isCommandBlockEnabled() {
		return this.settings.getBooleanProperty("enable-command-block", false);
	}

	public int getSpawnProtectionSize() {
		return this.settings.getIntProperty("spawn-protection", super.getSpawnProtectionSize());
	}

	public boolean func_96290_a(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		if(var1.provider.dimensionId != 0) {
			return false;
		} else if(this.getDedicatedPlayerList().getOps().isEmpty()) {
			return false;
		} else if(this.getDedicatedPlayerList().areCommandsAllowed(var5.username)) {
			return false;
		} else if(this.getSpawnProtectionSize() <= 0) {
			return false;
		} else {
			ChunkCoordinates var6 = var1.getSpawnPoint();
			int var7 = MathHelper.abs_int(var2 - var6.posX);
			int var8 = MathHelper.abs_int(var4 - var6.posZ);
			int var9 = Math.max(var7, var8);
			return var9 <= this.getSpawnProtectionSize();
		}
	}

	public ILogAgent getLogAgent() {
		return this.field_98131_l;
	}

	public ServerConfigurationManager getConfigurationManager() {
		return this.getDedicatedPlayerList();
	}
}
