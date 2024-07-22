package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class ServerList {
	private final Minecraft mc;
	private final List servers = new ArrayList();

	public ServerList(Minecraft var1) {
		this.mc = var1;
		this.loadServerList();
	}

	public void loadServerList() {
		try {
			NBTTagCompound var1 = CompressedStreamTools.read(new File(this.mc.mcDataDir, "servers.dat"));
			NBTTagList var2 = var1.getTagList("servers");
			this.servers.clear();

			for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
				this.servers.add(ServerData.getServerDataFromNBTCompound((NBTTagCompound)var2.tagAt(var3)));
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

	}

	public void saveServerList() {
		try {
			NBTTagList var1 = new NBTTagList();
			Iterator var2 = this.servers.iterator();

			while(var2.hasNext()) {
				ServerData var3 = (ServerData)var2.next();
				var1.appendTag(var3.getNBTCompound());
			}

			NBTTagCompound var5 = new NBTTagCompound();
			var5.setTag("servers", var1);
			CompressedStreamTools.safeWrite(var5, new File(this.mc.mcDataDir, "servers.dat"));
		} catch (Exception var4) {
			var4.printStackTrace();
		}

	}

	public ServerData getServerData(int var1) {
		return (ServerData)this.servers.get(var1);
	}

	public void removeServerData(int var1) {
		this.servers.remove(var1);
	}

	public void addServerData(ServerData var1) {
		this.servers.add(var1);
	}

	public int countServers() {
		return this.servers.size();
	}

	public void swapServers(int var1, int var2) {
		ServerData var3 = this.getServerData(var1);
		this.servers.set(var1, this.getServerData(var2));
		this.servers.set(var2, var3);
		this.saveServerList();
	}

	public void setServer(int var1, ServerData var2) {
		this.servers.set(var1, var2);
	}

	public static void func_78852_b(ServerData var0) {
		ServerList var1 = new ServerList(Minecraft.getMinecraft());
		var1.loadServerList();

		for(int var2 = 0; var2 < var1.countServers(); ++var2) {
			ServerData var3 = var1.getServerData(var2);
			if(var3.serverName.equals(var0.serverName) && var3.serverIP.equals(var0.serverIP)) {
				var1.setServer(var2, var0);
				break;
			}
		}

		var1.saveServerList();
	}
}
