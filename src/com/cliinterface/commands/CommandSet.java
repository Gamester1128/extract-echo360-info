package com.cliinterface.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandSet implements Iterable<Command> {

	private List<Command> commands;
	private String name;

	public CommandSet(String commandSetName) {
		this.commands = new ArrayList<Command>();
		this.name = commandSetName;
	}

	public void add(Command command) {
		commands.add(command);
	}

	public String getName() {
		return name;
	}

	@Override
	public Iterator<Command> iterator() {
		Iterator<Command> iterator = new Iterator<Command>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < commands.size();
			}

			@Override
			public Command next() {
				return commands.get(index++);
			}

			@Override
			public void remove() {

			}
		};

		return iterator;
	}

}
