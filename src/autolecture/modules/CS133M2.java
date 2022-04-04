package autolecture.modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import autolecture.database.Lecture;
import autolecture.database.Subject;
import autolecture.util.Util;

public class CS133M2 implements ModuleInterface {
	
	public static String NAME = "CS133M2";
	
	@Override
	public Subject getAllLecturesAndExist(WebDriver driver) {
		driver.get("https://moodle.warwick.ac.uk/course/view.php?id=39392#section-16");
		Document doc = Jsoup.parse(driver.getPageSource());

		Elements elements = doc.select("h3[class=sectionname]");

		Elements afterTerm2 = new Elements();
		boolean hasFoundTerm2 = false;

		for (Element element : elements) {
			if (element.text().contains("Term 2")) {
				hasFoundTerm2 = true;
				continue;
			}
			if (hasFoundTerm2 && element.text().startsWith("C")) {
				afterTerm2.add(element);
			}
		}

		Subject subject = new Subject(NAME);

		for (Element element : afterTerm2) {
			String name = element.text();
			subject.addLecture(new Lecture(name, null, Util.getDateNow()));
		}

		return subject;
	}

}
