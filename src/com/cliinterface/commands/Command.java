package com.cliinterface.commands;

public abstract class Command {

	protected String[] keys;

	public Command(String[] keys) {
		this.keys = keys;
	}

	public Command(String key) {
		this(new String[] { key });
	}

	public abstract void doCommand(String[] inputs, Object... args);

	public abstract void printHelp();

	protected String[] stringOfKeys() {
		return keys;
	}

	public boolean matchesKey(String key) {
		for (String i : keys)
			if (i.equals(key)) return true;
		return false;
	}

}
