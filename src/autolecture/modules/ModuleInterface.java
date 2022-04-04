package autolecture.modules;

import org.openqa.selenium.WebDriver;

import autolecture.database.Subject;

//implement for every new module
public interface ModuleInterface {

	// returns all the lectures of that module
	Subject getAllLecturesAndExist(WebDriver driver);

}
