package net.minecraft.src;

public class GuiSleepMP extends GuiChat {
	public void initGui() {
		super.initGui();
		StringTranslate var1 = StringTranslate.getInstance();
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 40, var1.translateKey("multiplayer.stopSleeping")));
	}

	protected void keyTyped(char var1, int var2) {
		if(var2 == 1) {
			this.wakeEntity();
		} else if(var2 == 28) {
			String var3 = this.inputField.getText().trim();
			if(var3.length() > 0) {
				this.mc.thePlayer.sendChatMessage(var3);
			}

			this.inputField.setText("");
			this.mc.ingameGUI.getChatGUI().resetScroll();
		} else {
			super.keyTyped(var1, var2);
		}

	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == 1) {
			this.wakeEntity();
		} else {
			super.actionPerformed(var1);
		}

	}

	private void wakeEntity() {
		NetClientHandler var1 = this.mc.thePlayer.sendQueue;
		var1.addToSendQueue(new Packet19EntityAction(this.mc.thePlayer, 3));
	}
}
