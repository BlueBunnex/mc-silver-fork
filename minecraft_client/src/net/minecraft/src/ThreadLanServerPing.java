package net.minecraft.src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import net.minecraft.client.Minecraft;

public class ThreadLanServerPing extends Thread {
	private final String motd;
	private final DatagramSocket socket;
	private boolean isStopping = true;
	private final String address;

	public ThreadLanServerPing(String var1, String var2) throws IOException {
		super("LanServerPinger");
		this.motd = var1;
		this.address = var2;
		this.setDaemon(true);
		this.socket = new DatagramSocket();
	}

	public void run() {
		String var1 = getPingResponse(this.motd, this.address);
		byte[] var2 = var1.getBytes();

		while(!this.isInterrupted() && this.isStopping) {
			try {
				InetAddress var3 = InetAddress.getByName("224.0.2.60");
				DatagramPacket var4 = new DatagramPacket(var2, var2.length, var3, 4445);
				this.socket.send(var4);
			} catch (IOException var6) {
				Minecraft.getMinecraft().getLogAgent().logWarning("LanServerPinger: " + var6.getMessage());
				break;
			}

			try {
				sleep(1500L);
			} catch (InterruptedException var5) {
			}
		}

	}

	public void interrupt() {
		super.interrupt();
		this.isStopping = false;
	}

	public static String getPingResponse(String var0, String var1) {
		return "[MOTD]" + var0 + "[/MOTD][AD]" + var1 + "[/AD]";
	}

	public static String getMotdFromPingResponse(String var0) {
		int var1 = var0.indexOf("[MOTD]");
		if(var1 < 0) {
			return "missing no";
		} else {
			int var2 = var0.indexOf("[/MOTD]", var1 + "[MOTD]".length());
			return var2 < var1 ? "missing no" : var0.substring(var1 + "[MOTD]".length(), var2);
		}
	}

	public static String getAdFromPingResponse(String var0) {
		int var1 = var0.indexOf("[/MOTD]");
		if(var1 < 0) {
			return null;
		} else {
			int var2 = var0.indexOf("[/MOTD]", var1 + "[/MOTD]".length());
			if(var2 >= 0) {
				return null;
			} else {
				int var3 = var0.indexOf("[AD]", var1 + "[/MOTD]".length());
				if(var3 < 0) {
					return null;
				} else {
					int var4 = var0.indexOf("[/AD]", var3 + "[AD]".length());
					return var4 < var3 ? null : var0.substring(var3 + "[AD]".length(), var4);
				}
			}
		}
	}
}
