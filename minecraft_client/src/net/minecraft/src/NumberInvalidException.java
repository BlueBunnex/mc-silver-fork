package net.minecraft.src;

import net.minecraft.src.command.CommandException;

public class NumberInvalidException extends CommandException {
	public NumberInvalidException() {
		this("commands.generic.num.invalid", new Object[0]);
	}

	public NumberInvalidException(String var1, Object... var2) {
		super(var1, var2);
	}
}
