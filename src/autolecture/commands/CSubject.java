package autolecture.commands;

import com.cliinterface.commands.Command;

import autolecture.InputProcess;
import autolecture.database.Subject;

public class CSubject extends Command {

	public CSubject(String[] keys) {
		super(keys);
	}

	public void doCommand(String[] inputs, Object... args) {
		if (inputs.length != 2) {
			System.out.println("write some arguments, you won't win it then");
		}

		if (inputs[1].equals("names")) {
			System.out.println(CLecture.startingMessage + "\n");
			for (Subject subject : InputProcess.get().getAllSubjects()) {
				System.out.println("Subject : \"" + subject.getName() + "\"");
			}
			System.out.println(CLecture.endingMessage);
		}

		System.out.println("Write arguments pls");
	}

	public void printHelp() {
		System.out.println("___subject___");
		System.out.println("subject names - prints all subject names");
	}

}
