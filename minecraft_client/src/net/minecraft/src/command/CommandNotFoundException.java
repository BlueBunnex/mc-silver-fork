package net.minecraft.src.command;

public class CommandNotFoundException extends CommandException {
	public CommandNotFoundException() {
		this("commands.generic.notFound", new Object[0]);
	}

	public CommandNotFoundException(String var1, Object... var2) {
		super(var1, var2);
	}
}
