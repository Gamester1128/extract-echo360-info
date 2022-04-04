package autolecture.commands;

import com.cliinterface.commands.Command;
import com.cliinterface.commands.CommandSet;

import autolecture.InputProcess;

public class CHelp extends Command {

	public CHelp(String[] keys) {
		super(keys);
	}

	public void doCommand(String[] inputs, Object... args) {
		if (inputs.length != 1) {
			System.out.println("Too many arguments - Max 1");
			return;
		}
		CommandSet set = InputProcess.get().getAsker().getCurrentCommandSet();

		System.out.println("_____________________________\n");
		for (Command command : set) {
			command.printHelp();
		}

		System.out.println("_____________________________\n");
	}

	public void printHelp() {
		System.out.println("___help___");
		System.out.println("help - you already know how to use it... if you typed it for this...");
	}

}
