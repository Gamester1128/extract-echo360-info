package autolecture.modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import autolecture.database.Lecture;
import autolecture.database.Subject;
import autolecture.util.Util;

public class CS141 implements ModuleInterface {

	public static String NAME = "CS141";

	@Override
	public Subject getAllLecturesAndExist(WebDriver driver) {
		driver.get("https://warwick.ac.uk/fac/sci/dcs/teaching/material/cs141/");
		Document doc = Jsoup.parse(driver.getPageSource());

		Elements links = doc.select("table[style = max-width: 100%;]").get(1).select("tr");
		links.remove(0);

		// add all elements with child nodes size 5 to a new elements
		Elements keyElements = new Elements();
		for (Element element : links) {
			if (element.childrenSize() == 5) keyElements.add(element);
		}

		Subject exists = new Subject(NAME);

		for (Element element : keyElements) {
			if (element.child(2).childrenSize() == 0) continue;
			String date = Util.getDateInRightFormat(element.child(0).text(), "d MMMM yyyy");
			String name = element.child(1).text();

			exists.addLecture(new Lecture(name, date, Util.getDateNow()));
		}

		return exists;
	}

}
