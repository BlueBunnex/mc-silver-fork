package net.minecraft.src;

public class RequestDelete extends Request {
	public RequestDelete(String var1, int var2, int var3) {
		super(var1, var2, var3);
	}

	public RequestDelete func_96370_f() {
		try {
			this.field_96367_a.setDoOutput(true);
			this.field_96367_a.setRequestMethod("DELETE");
			this.field_96367_a.connect();
			return this;
		} catch (Exception var2) {
			throw new ExceptionMcoHttp("Failed URL: " + this.field_96365_b, var2);
		}
	}

	public Request func_96359_e() {
		return this.func_96370_f();
	}
}
