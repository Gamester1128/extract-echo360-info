package autolecture.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.sql.rowset.spi.SyncFactoryException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import autolecture.database.Lecture;
import autolecture.database.Subject;
import autolecture.util.Util;

public class CS131 implements ModuleInterface {

	public static String NAME = "CS131";

	@Override
	public Subject getAllLecturesAndExist(WebDriver driver) {
		Document doc = new Document("");
		for (int i = 0; i < 4; i++) {
			driver.get("https://warwick.ac.uk/fac/sci/dcs/teaching/material/cs131/part" + (i + 1));
			doc.append(Jsoup.parse(driver.getPageSource()).toString());
		}

		Elements elements = doc.select("div[class=boxstyle_ box1]").select("li");
		Elements newElements = new Elements();

		for (Element element : elements) {
			if (element.childrenSize() == 0) break;
			newElements.add(element);
		}

		Elements childrens = new Elements();
		for (Element element : newElements) {
			for (Element child : element.children()) {
				if (child.text().contains("Lect")
						&& (child.text().contains("(video)") || child.text().contains("(Video)")))
					childrens.add(child);
			}
		}
		Subject subject = new Subject(NAME);

		for (Element element : childrens) {
			// get name
			String name = element.parent().text().substring(0, element.parent().text().indexOf("Lecture")); // title

			String elementText = element.text().toLowerCase();
			name += element.text().substring(0, elementText.indexOf("(video)")); // lecture number

			// get week number
			StringBuilder builder = new StringBuilder(elementText);
			builder.replace(0, builder.indexOf("lecture") + 7, "");
			builder.replace(builder.indexOf("("), builder.lastIndexOf("(video)") + 7, "");

			int num = (int) ((Double.parseDouble(builder.toString().replaceAll("\\s+", "")) - 1) / 3);

			// gets weeks first day
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy", Locale.UK);
			LocalDate localDate = LocalDate.parse("11/01/21", formatter).plusDays(num * 7);

			subject.addLecture(new Lecture(name, localDate.format(formatter), Util.getDateNow()));
		}

		return subject;
	}

}
