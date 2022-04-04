package com.cliinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cliinterface.commands.Command;
import com.cliinterface.commands.CommandSet;

public class Asker {

	private List<CommandSet> commandSets;
	private boolean shouldAsk = true;
	private int currentSet = 0;
	private String askMessage;
	private String failMessage;
	private Scanner scanner;

	public Asker(String askMessage, String failMessage) {
		scanner = new Scanner(System.in);
		this.askMessage = askMessage;
		this.failMessage = failMessage;
		this.commandSets = new ArrayList<CommandSet>();
	}

	public void addCommandSet(CommandSet commandSet) {
		for (CommandSet cmdSet : commandSets)
			if (cmdSet.getName().equals(commandSet.getName())) System.err
					.println("Warning, adding two command sets with different name!! \"" + commandSet.getName() + "\"");

		this.commandSets.add(commandSet);
	}

	/**
	 * 
	 * @return String[] inputs
	 */
	public String[] ask() {
		System.out.print(askMessage);
		String[] inputs = scanner.nextLine().trim().split("\\s+");
		if (inputs == null) {
			System.out.println(failMessage + " | quotation marks!");
			return null;
		}
		if (inputs[0].length() == 0) return null;
		inputs[0] = inputs[0].toLowerCase();
		boolean successful = process(inputs);
		if (!successful) System.out.println(failMessage);
		return inputs;
	}

	public String split(String input) {
		input = input.replaceAll("\\s+", " ").trim();

		@SuppressWarnings("unused")
		int numOfQuotes = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '"') {
				if (i != 0) {
					if (input.charAt(i - 1) != '/') numOfQuotes++;
				}
			}
		}

		boolean shouldFindEnd = false;
		String special = "&^/@!!-_";
		StringBuilder builder = new StringBuilder(input);
		int count = 0;

		for (int i = 0; i < input.length(); i++) {
			if (shouldFindEnd) {
				if (input.charAt(i) == ' ') {
					System.out.println("Gell");
					builder.replace(i + (special.length() * count), i + 1 + (special.length() * count), special);
					count++;
				} else if (input.charAt(i) == '"') shouldFindEnd = false;
			} else if (i != 0) {
				if (input.charAt(i - 1) != '/' && input.charAt(i) == '"') {
					shouldFindEnd = true;
				}
			}
		}
		System.out.println(builder);
		return input;
	}

	public boolean process(String[] inputs) {
		for (Command command : commandSets.get(currentSet)) {
			if (command.matchesKey(inputs[0])) {
				command.doCommand(inputs);
				return true;
			}
		}
		return false;
	}

	public CommandSet getCurrentCommandSet() {
		return commandSets.get(currentSet);
	}

	public CommandSet getCommandSet(String name) {
		for (CommandSet commandSet : commandSets) {
			if (commandSet.getName().equals(name)) return commandSet;
		}

		return null;
	}

	// GETTERS AND SETTERS

	public void setCommandSet(String name) {
		currentSet = commandSets.indexOf(getCommandSet(name));
	}

	public void setCommandSet(CommandSet commandSet) {
		currentSet = commandSets.indexOf(commandSet);
	}

	public String getAskMessage() {
		return askMessage;
	}

	public void setAskMessage(String askMessage) {
		this.askMessage = askMessage;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public boolean shouldAsk() {
		return shouldAsk;
	}

	public void setShouldAsk(boolean shouldAsk) {
		this.shouldAsk = shouldAsk;
	}

}
