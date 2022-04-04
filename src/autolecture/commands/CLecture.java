package autolecture.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.cliinterface.commands.Command;

import autolecture.InputProcess;
import autolecture.database.Lecture;
import autolecture.database.Subject;

public class CLecture extends Command {

	public static String startingMessage = "_____________________________",
			endingMessage = "_____________________________\n";
	public static Comparator<Lecture> orderLectureOnReleased = new Comparator<Lecture>() {

		public int compare(Lecture o1, Lecture o2) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

			Date o1date = null;
			Date o2date = null;
			try {
				o1date = dateFormat.parse(o1.getDateReleased());
				o2date = dateFormat.parse(o2.getDateReleased());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (o1date.getTime() == o2date.getTime()) return 0;
			if (o1date.getTime() > o2date.getTime()) return 1;
			return -1;
		}

	};

	public CLecture(String[] keys) {
		super(keys);
	}

	public CLecture(String keys) {
		super(keys);
	}

	@Override
	public void doCommand(String[] inputs, Object... arg1) {
		if (inputs.length == 2) {
			if (inputs[1].equals("update")) {
				InputProcess.get().updateDatabaseFromSite();
				return;
			}
		}
		if (inputs.length == 3) {
			inputs[1] = inputs[1].toUpperCase();
			Subject subject = InputProcess.get().getSubject(inputs[1]);

			if (subject == null) {
				System.out.println("Subject name not found");
				return;
			}
			System.out.println("\n______Subject Title: " + subject.getName() + "______");

			if (inputs[2].equals("all")) {
				subject.getLectures().sort(orderLectureOnReleased);
				printLectures(subject.getLectures(), true, true, true, true);
				return;
			}

			if (inputs[2].equals("finished")) {
				List<Lecture> lectures = subject.getLectures();
				List<Lecture> lectureToDisplay = new ArrayList<Lecture>();

				for (Lecture lecture : lectures) {
					if (lecture.getDateCompleted() != null) lectureToDisplay.add(lecture);
				}
				lectureToDisplay.sort(orderLectureOnReleased);

				printLectures(lectureToDisplay, true, true, true, true);
				System.out.println("______" + lectureToDisplay.size() + "/" + lectures.size() + "______\n");
				return;
			}

			if (inputs[2].equals("procrastinating")) {
				List<Lecture> lectures = subject.getLectures();
				List<Lecture> lectureToDisplay = new ArrayList<Lecture>();

				for (Lecture lecture : lectures) {
					if (lecture.getDateCompleted() == null) lectureToDisplay.add(lecture);
				}
				lectureToDisplay.sort(orderLectureOnReleased);
				printLectures(lectureToDisplay, true, true, true, true);
				System.out.println();
				return;
			}
		}
		System.out.println("Wrong options for command");
	}

	public static void printLectures(List<Lecture> lectures, boolean dateAdded, boolean dateReleased, boolean title,
			boolean dateCompleted) {
		int i = 1;
		for (Lecture lecture : lectures) {
			if (title) System.out.println(i++ + "                        Title: ---" + lecture.getTitle());
			if (dateAdded) System.out.println("        Date Added Into System: " + lecture.getDateAdded());
			if (dateReleased) System.out.println("                 Date Released: " + lecture.getDateReleased());
			if (dateCompleted) System.out.println("                Date Completed: " + lecture.getDateCompleted());
			System.out.println();
		}
		System.out.println("____________________________________");
	}

	public static void printWithWrapper(String message) {
		System.out.println(startingMessage);
		System.out.println(message);
		System.out.println(endingMessage);
	}

	@Override
	public void printHelp() {
		System.out.println("___lecture___");
		System.out.println("lecture update - update lectures from internet");
		System.out.println("lecture <Subject Name> all - prints all info for <Subject Name> lectures");
		System.out.println(
				"lecture <Subject Name> procrastinating - prints all info for <Subject Name> lectures that are not finished");
		System.out.println(
				"lecture <Subject Name> finished - prints all info for <Subject Name> lectures that are finished");

	}

}
