package net.minecraft.src;

class TcpMonitorThread extends Thread {
	final TcpConnection theTcpConnection;

	TcpMonitorThread(TcpConnection var1) {
		this.theTcpConnection = var1;
	}

	public void run() {
		try {
			Thread.sleep(2000L);
			if(TcpConnection.isRunning(this.theTcpConnection)) {
				TcpConnection.getWriteThread(this.theTcpConnection).interrupt();
				this.theTcpConnection.networkShutdown("disconnect.closed", new Object[0]);
			}
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}
}
