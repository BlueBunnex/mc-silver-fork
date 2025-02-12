package net.minecraft.src;

import java.io.IOException;

import net.minecraft.src.gui.GuiScreenResetWorld;

public class TaskResetWorld extends TaskLongRunning {
	
	private final long field_96591_c;
	private final String field_104066_d;
	final GuiScreenResetWorld field_96592_a;

	public TaskResetWorld(GuiScreenResetWorld var1, long var2, String var4) {
		this.field_96592_a = var1;
		this.field_96591_c = var2;
		this.field_104066_d = var4;
	}

	public void run() {
		McoClient var1 = new McoClient(this.func_96578_b().session);
		String var2 = StringTranslate.getInstance().translateKey("mco.reset.world.resetting.screen.title");
		this.func_96576_b(var2);

		try {
			var1.func_96376_d(this.field_96591_c, this.field_104066_d);
			GuiScreenResetWorld.func_96147_b(this.field_96592_a).displayGuiScreen(GuiScreenResetWorld.func_96148_a(this.field_96592_a));
		
		} catch (ExceptionMcoService e) {
			this.func_96575_a(e.field_96391_b);
			
		} catch (IOException e) {}
	}
}
