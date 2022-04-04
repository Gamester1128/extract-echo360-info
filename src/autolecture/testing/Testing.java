package autolecture.testing;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import autolecture.InputProcess;
import autolecture.database.Subject;
import autolecture.modules.ModuleInterface;

public class Testing {

	private ModuleInterface cs;

	public Testing(ModuleInterface cs) {
		this.cs = cs;
	}

	public void start() {
		WebDriver driver = null;
		driver = InputProcess.startChrome();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Subject subject = cs.getAllLecturesAndExist(driver);
			System.out.println(subject);
			scanner.nextLine();
		}
	}

}
