package autolecture.commands;

import java.util.List;

import com.cliinterface.commands.Command;

import autolecture.InputProcess;
import autolecture.database.Lecture;
import autolecture.database.Subject;

public class CQuantity extends Command {

	private static final int FINISHED = 0, TOTAL = 1;

	public CQuantity(String[] keys) {
		super(keys);
	}

	public CQuantity(String key) {
		super(key);
	}

	public void doCommand(String[] inputs, Object... args) {
		if (inputs.length == 2) {
			// for all
			if (inputs[1].equals("all")) {
				List<Subject> subjects = InputProcess.get().getAllSubjects();

				// get finished with total
				int[] nums = getFinished(subjects);

				CLecture.printWithWrapper("\n" + nums[FINISHED] + "/" + nums[TOTAL]);
				return;
			}
			// if choosing specific subject case
			List<Subject> subjects = InputProcess.get().getAllSubjects();
			Subject subjectToPrint = null;
			inputs[1] = inputs[1].toUpperCase();

			for (Subject subject : subjects) {
				if (subject.getName().equals(inputs[1])) {
					subjectToPrint = subject;
					break;
				}
			}

			if (subjectToPrint == null) System.out.println("Subject not found!");
			else {
				int[] nums = getFinished(List.of(subjectToPrint));
				CLecture.printWithWrapper("\n" + nums[FINISHED] + "/" + nums[TOTAL]);
			}

			return;
		}

		System.out.println("Cool!");

	}

	private int[] getFinished(List<Subject> subjects) {
		// get numbers
		int finished = 0, total = 0;
		for (Subject subject : subjects) {
			for (Lecture lecture : subject.getLectures()) {
				if (lecture.getDateCompleted() != null) finished++;
				total++;
			}
		}

		return new int[] { finished, total };
	}

	public void printHelp() {
		System.out.println("___quantity___");
		System.out.println("quantity all - prints finished/total lectures for all subjects");
		System.out.println("quantity <Subject Name> - prints finished/total lectures for <Subject Name>");
	}

}
