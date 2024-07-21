package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableParticlePositionInfo implements Callable {
	final double field_85101_a;
	final double field_85099_b;
	final double field_85100_c;
	final RenderGlobal globalRenderer;

	CallableParticlePositionInfo(RenderGlobal var1, double var2, double var4, double var6) {
		this.globalRenderer = var1;
		this.field_85101_a = var2;
		this.field_85099_b = var4;
		this.field_85100_c = var6;
	}

	public String callParticlePositionInfo() {
		return CrashReportCategory.func_85074_a(this.field_85101_a, this.field_85099_b, this.field_85100_c);
	}

	public Object call() {
		return this.callParticlePositionInfo();
	}
}
