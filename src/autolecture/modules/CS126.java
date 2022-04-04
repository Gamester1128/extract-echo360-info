package autolecture.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import autolecture.database.Lecture;
import autolecture.database.Subject;
import autolecture.util.Util;

public class CS126 implements ModuleInterface {

	public static String NAME = "CS126";

	@Override
	public Subject getAllLecturesAndExist(WebDriver driver) {
		driver.get("https://moodle.warwick.ac.uk/mod/page/view.php?id=1188016");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Document doc = Jsoup.parse(driver.getPageSource());

		Elements elements = doc.select("div[role=main]").select("div[class=no-overflow]");
		elements = elements.select("ol");

		// initialise subject
		Subject subject = new Subject(NAME);

		for (int i = 0; i < elements.size(); i++) {
			String name = null;

			// gets weeks first day
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy", Locale.UK);
			LocalDate localDate = LocalDate.parse("11/01/21", formatter).plusDays(i * 7);

			// get each list in i of elements, since each list is a lecture
			Elements lectureElements = elements.get(i).select("li");
			for (Element element : lectureElements) {
				name = elements.get(i).parent().child(elements.get(i).elementSiblingIndex() - 1).text() + " ";// week
				name += element.text(); // lecture title

				// add lecture
				subject.addLecture(new Lecture(name, localDate.format(formatter), Util.getDateNow()));
			}

		}

		return subject;
	}

}
