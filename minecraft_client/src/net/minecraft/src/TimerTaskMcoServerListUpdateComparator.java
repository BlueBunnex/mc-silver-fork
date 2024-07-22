package net.minecraft.src;

import java.util.Comparator;

class TimerTaskMcoServerListUpdateComparator implements Comparator {
	private final String field_102024_b;
	final TimerTaskMcoServerListUpdate field_102025_a;

	private TimerTaskMcoServerListUpdateComparator(TimerTaskMcoServerListUpdate var1, String var2) {
		this.field_102025_a = var1;
		this.field_102024_b = var2;
	}

	public int func_102023_a(McoServer var1, McoServer var2) {
		if(var1.field_96405_e.equals(var2.field_96405_e)) {
			return var1.field_96408_a < var2.field_96408_a ? 1 : (var1.field_96408_a > var2.field_96408_a ? -1 : 0);
		} else if(var1.field_96405_e.equals(this.field_102024_b)) {
			return -1;
		} else if(var2.field_96405_e.equals(this.field_102024_b)) {
			return 1;
		} else {
			if(var1.field_96404_d.equals("CLOSED") || var2.field_96404_d.equals("CLOSED")) {
				if(var1.field_96404_d.equals("CLOSED")) {
					return 1;
				}

				if(var2.field_96404_d.equals("CLOSED")) {
					return 0;
				}
			}

			return var1.field_96408_a < var2.field_96408_a ? 1 : (var1.field_96408_a > var2.field_96408_a ? -1 : 0);
		}
	}

	public int compare(Object var1, Object var2) {
		return this.func_102023_a((McoServer)var1, (McoServer)var2);
	}

	TimerTaskMcoServerListUpdateComparator(TimerTaskMcoServerListUpdate var1, String var2, McoServerListINNER1 var3) {
		this(var1, var2);
	}
}
