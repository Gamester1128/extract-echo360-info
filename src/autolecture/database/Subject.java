package autolecture.database;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Subject {

	private List<Lecture> lectures;
	private String name, importantInfo;
	public static final String DATERELEASED_K = "dateReleased", DATEADDED_K = "dateAdded",
			DATECOMPLETED_K = "dateCompleted";

	public Subject(String name, List<Lecture> lectures) {
		this.name = name;
		this.lectures = lectures;
		if (lectures == null) this.lectures = new ArrayList<Lecture>();
	}

	public Subject(String name) {
		this.name = name;
		this.lectures = new ArrayList<Lecture>();
	}

	public Lecture getLectureOf(String title) {
		for (Lecture lecture : lectures) {
			if (lecture.getTitle().equals(title)) return lecture;
		}
		return null;
	}

	/**
	 * JSONObject Key = Subject Name JSONObject Value = JSONObject of all lectures
	 * lectureInfoToJSON() stores
	 * 
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJson() {

		JSONObject subjectJson = new JSONObject();

		for (Lecture lecture : lectures) {
			JSONObject lectureJson = lecture.lectureInfoToJSON();
			String key = (String) lectureJson.keySet().toArray()[0];
			subjectJson.put(key, lectureJson.get(key));
		}
		JSONObject json = new JSONObject();
		json.put(getName(), subjectJson);
		System.out.println(json.toString());
		return json;
	}

	public void addLecture(Lecture lecture) {
		lectures.add(lecture);
	}

	public String getName() {
		return name;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public String getImportantInfo() {
		return importantInfo;
	}

	public void setImportantInfo(String importantInfo) {
		this.importantInfo = importantInfo;
	}

	public String toString() {
		String string = "Subject Name: " + name + "\n";
		for (int i = 0; i < lectures.size(); i++) {
			string += "  Lecture          Name: " + lectures.get(i).getTitle() + "\n";
			string += "  Lecture          Date: " + lectures.get(i).getDateReleased() + "\n";
			string += "  Lecture     DateAdded: " + lectures.get(i).getDateAdded() + "\n";
			string += "  Lecture DateCompleted: " + lectures.get(i).getDateCompleted() + "\n\n";
		}
		return string;
	}

}
