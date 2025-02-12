package net.minecraft.src;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Request {
	protected HttpURLConnection field_96367_a;
	private boolean field_96366_c;
	protected String field_96365_b;

	public Request(String var1, int var2, int var3) {
		try {
			this.field_96365_b = var1;
			this.field_96367_a = (HttpURLConnection)(new URL(var1)).openConnection();
			this.field_96367_a.setConnectTimeout(var2);
			this.field_96367_a.setReadTimeout(var3);
		} catch (Exception var5) {
			throw new ExceptionMcoHttp("Failed URL: " + var1, var5);
		}
	}

	public void func_100006_a(String var1, String var2) {
		String var3 = this.field_96367_a.getRequestProperty("Cookie");
		if(var3 == null) {
			this.field_96367_a.setRequestProperty("Cookie", var1 + "=" + var2);
		} else {
			this.field_96367_a.setRequestProperty("Cookie", var3 + ";" + var1 + "=" + var2);
		}

	}

	public int func_96362_a() {
		try {
			this.func_96354_d();
			return this.field_96367_a.getResponseCode();
		} catch (Exception var2) {
			throw new ExceptionMcoHttp("Failed URL: " + this.field_96365_b, var2);
		}
	}

	public McoOption func_98175_b() {
		String var1 = this.field_96367_a.getHeaderField("Set-Cookie");
		if(var1 != null) {
			String var2 = var1.substring(0, var1.indexOf("="));
			String var3 = var1.substring(var1.indexOf("=") + 1, var1.indexOf(";"));
			return McoOption.func_98153_a(McoPair.func_98157_a(var2, var3));
		} else {
			return McoOption.func_98154_b();
		}
	}

	public String func_96364_c() {
		try {
			this.func_96354_d();
			String var1 = this.func_96362_a() >= 400 ? this.func_96352_a(this.field_96367_a.getErrorStream()) : this.func_96352_a(this.field_96367_a.getInputStream());
			this.func_96360_f();
			return var1;
		} catch (IOException var2) {
			throw new ExceptionMcoHttp("Failed URL: " + this.field_96365_b, var2);
		}
	}

	private String func_96352_a(InputStream var1) throws IOException {
		if(var1 == null) {
			throw new IllegalArgumentException("input stream cannot be null");
		} else {
			StringBuilder var2 = new StringBuilder();

			for(int var3 = var1.read(); var3 != -1; var3 = var1.read()) {
				var2.append((char)var3);
			}

			return var2.toString();
		}
	}

	private void func_96360_f() {
		byte[] var1 = new byte[1024];

		InputStream var3;
		try {
			boolean var2 = false;
			var3 = this.field_96367_a.getInputStream();

			while(true) {
				int var7 = var3.read(var1);
				if(var7 <= 0) {
					var3.close();
					break;
				}
			}
		} catch (Exception var6) {
			try {
				var3 = this.field_96367_a.getErrorStream();
				boolean var4 = false;

				while(true) {
					int var8 = var3.read(var1);
					if(var8 <= 0) {
						var3.close();
						break;
					}
				}
			} catch (IOException var5) {
			}
		}

	}

	protected Request func_96354_d() {
		if(!this.field_96366_c) {
			Request var1 = this.func_96359_e();
			this.field_96366_c = true;
			return var1;
		} else {
			return this;
		}
	}

	protected abstract Request func_96359_e();

	public static Request func_96358_a(String var0) {
		return new RequestGet(var0, 5000, 10000);
	}

	public static Request func_96361_b(String var0, String var1) {
		return new RequestPost(var0, var1.getBytes(), 5000, 10000);
	}

	public static Request func_104064_a(String var0, String var1, int var2, int var3) {
		return new RequestPost(var0, var1.getBytes(), var2, var3);
	}

	public static Request func_96355_b(String var0) {
		return new RequestDelete(var0, 5000, 10000);
	}

	public static Request func_96363_c(String var0, String var1) {
		return new RequestPut(var0, var1.getBytes(), 5000, 10000);
	}

	public static Request func_96353_a(String var0, String var1, int var2, int var3) {
		return new RequestPut(var0, var1.getBytes(), var2, var3);
	}
}
