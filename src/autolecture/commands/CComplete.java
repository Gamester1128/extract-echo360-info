package autolecture.commands;

import java.util.List;

import com.cliinterface.commands.Command;

import autolecture.InputProcess;
import autolecture.database.Lecture;
import autolecture.database.Subject;

public class CComplete extends Command {

	public CComplete(String[] keys) {
		super(keys);
	}

	public void doCommand(String[] inputs, Object... args) {
		if (inputs.length != 3) {
			System.out.println("Too little arguments!");
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

		if (subjectToPrint == null) {
			System.out.println("Subject does not exist :P bye");
			return;
		}

		String message = "";
		List<Lecture> lectures = subjectToPrint.getLectures();
		lectures.sort(CLecture.orderLectureOnReleased);
		// if printing indexes
		if (inputs[2].equals("order")) {
			System.out.println(CLecture.startingMessage + "\n");
			int i = 0;
			for (Lecture lecture : lectures) {
				message += i++ + "  \"" + lecture.getTitle() + "\"\n";
			}
			System.out.print(message);
			System.out.println(CLecture.endingMessage);
			return;
		}

		// if setting complete to an index
		try {
			int index = Integer.parseInt(inputs[2]);
			if (index < 0 || index > lectures.size()) {
				System.out.println("Why lecture that no exist, you give me a lot more brain damage");
				return;
			}
			if (inputs[0].equals("complete")) {
				lectures.get(index).completeLectureToday();
				System.out.println("Good job, you finished: \"" + lectures.get(index).getTitle() + "\"");
			} else {
				lectures.get(index).setDateCompleted(null);
				System.out.println(
						"Good job, you finished: \"" + lectures.get(index).getTitle() + "\", but just backwards");

			}
			InputProcess.get().getDatabase().updateSubjectsIntoStorage();
		} catch (

		NumberFormatException e) {
			System.out.println("why no number or \"all\", you give me brain damage");
		}
	}

	public void printHelp() {
		System.out.println("___complete/uncomplete___");
		System.out.println("complete/uncomplete <Subject Name> order - prints index of each lecture of subject");
		System.out.println("complete <Subject Name> <number> - sets lecture at index <number> as completed");
		System.out.println("uncomplete <Subject Name> <number> - sets lecture at index <number> as uncompleted");
	}

}
