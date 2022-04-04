package autolecture.commands;

import com.cliinterface.commands.Command;

import autolecture.InputProcess;

public class CQuit extends Command {

	public CQuit(String[] keys) {
		super(keys);
	}

	public void doCommand(String[] inputs, Object... args) {
		if (inputs.length != 1) {
			System.out.println("No Options or aguments");
			return;
		}

		InputProcess.get().getAsker().setShouldAsk(false);
		System.out.println("Go and do work! >:D");
	}

	public void printHelp() {
		System.out.println("___quit___");
		System.out.println("quit - delete system32 of this program");
	}

}
