package autolecture;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cliinterface.Asker;
import com.cliinterface.commands.CommandSet;

import autolecture.commands.CComplete;
import autolecture.commands.CHelp;
import autolecture.commands.CLecture;
import autolecture.commands.CQuantity;
import autolecture.commands.CQuit;
import autolecture.commands.CSubject;
import autolecture.database.Database;
import autolecture.database.Lecture;
import autolecture.database.Subject;
import autolecture.modules.CS126;
import autolecture.modules.CS131;
import autolecture.modules.CS133M2;
import autolecture.modules.CS141;

public class InputProcess implements Runnable {

	private static InputProcess inputProcess;

	private Thread thread;
	private Asker asker;
	private CommandSet cmdSet;
	private Database database;

	public static InputProcess get() {
		if (inputProcess != null) return inputProcess;
		return (inputProcess = new InputProcess());
	}

	private InputProcess() {
		// commands
		asker = new Asker("<coollinuxcomplicatedcommandtext>:// ", "Sorry you wrong");
		cmdSet = new CommandSet("base");
		cmdSet.add(new CLecture(new String[] { "lecture" }));
		cmdSet.add(new CQuit(new String[] { "quit" }));
		cmdSet.add(new CHelp(new String[] { "help" }));
		cmdSet.add(new CQuantity(new String[] { "quantity" }));
		cmdSet.add(new CComplete(new String[] { "complete", "uncomplete" }));
		cmdSet.add(new CSubject(new String[] { "subject" }));
		asker.addCommandSet(cmdSet);
		asker.setCommandSet(cmdSet);

		// database
		database = new Database();
		database.loadSubjects();

	}

	public synchronized void start() {
		thread = new Thread(this, "Input Processor");
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println(
				"Welcome to life hacks for remembering lectures for people with debuffed attention span | bad memory | lazy | gamers");
		System.out.println("BUILD " + Main.BUILD);
		asker.setShouldAsk(true);
		while (asker.shouldAsk()) {
			asker.ask();
		}
	}

	public static WebDriver startChrome() {
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver", "/home/rahul/dev/chromedriver_linux64/chromedriver");

		PrintStream syserr = System.err;
		System.setErr(new PrintStream(new NullOutputStream()));

		driver = new ChromeDriver();
		driver.get(
				"https://websignon.warwick.ac.uk/origin/slogin?shire=https%3A%2F%2Fwarwick.ac.uk%2Fsitebuilder2%2Fshire-read&providerId=urn%3Awarwick.ac.uk%3Asitebuilder2%3Aread%3Aservice&target=https%3A%2F%2Fwarwick.ac.uk%2F");

		System.setErr(syserr);

		Scanner scanner = new Scanner(System.in);
		System.out.print(
				"\nPlease type something and enter after you have logged in successfully because my brain is not prepared if you don't listen: ");
		scanner.next();
		System.out.println("Ummm... so hows your day so far? (pls wait, it takes some time)");

		return driver;
	}

	public void updateDatabaseFromSite() {
		WebDriver driver = startChrome();

		Subject cs126 = new CS126().getAllLecturesAndExist(driver);
		Subject cs131 = new CS131().getAllLecturesAndExist(driver);
		Subject cs133 = new CS133M2().getAllLecturesAndExist(driver);
		//Subject cs141 = new CS141().getAllLecturesAndExist(driver);

		List<Subject> newSubjects = List.of(cs126, cs131, cs133);
		List<Subject> oldSubjects = database.getSubjects();

		// put a please wait here
		boolean foundNewLecture = false;

		for (Subject newSubject : newSubjects) {
			boolean shouldPrintSubjectTitle = true;

			// get same subject from oldSubject
			Subject oldSubject = null;
			for (Subject oldSubjectCheck : oldSubjects) {
				if (oldSubjectCheck.getName().equals(newSubject.getName())) oldSubject = oldSubjectCheck;
			}
			// when new subject detected
			if (oldSubject == null) {
				System.out.println("NEW SUBJECT ADDED!!");
				break;
			}

			for (Lecture newLecture : newSubject.getLectures()) {
				if (oldSubject.getLectureOf(newLecture.getTitle()) == null) {
					foundNewLecture = true;
					// print subject title once
					if (shouldPrintSubjectTitle) {
						System.out.println("____" + newSubject.getName() + "____");
					}
					shouldPrintSubjectTitle = false;
					System.out.println("New lecture title: " + newLecture.getTitle());
					// add new lecture to old lecture
					oldSubject.addLecture(newLecture);
				}
			}
		}

		if (!foundNewLecture) {
			CLecture.printWithWrapper("Good news, not more becoming behind for you");
		}

		database.updateSubjectsIntoStorage();

		driver.close();
	}

	/**
	 * Returns Subject if contained Returns null if Subject name does not exist
	 * 
	 * @param String name of subject
	 * @return Subject that matches name
	 */
	public Subject getSubject(String name) {
		if (name == null) return null;

		for (Subject subject : database.getSubjects())
			if (name.equals(subject.getName())) return subject;

		return null;
	}

	public List<Subject> getAllSubjects() {
		return database.getSubjects();
	}

	public Asker getAsker() {
		return asker;
	}

	public Database getDatabase() {
		return database;
	}

	private static class NullOutputStream extends OutputStream {
		@Override
		public void write(int b) {
			return;
		}

		@Override
		public void write(byte[] b) {
			return;
		}

		@Override
		public void write(byte[] b, int off, int len) {
			return;
		}

		public NullOutputStream() {
		}
	}

}
