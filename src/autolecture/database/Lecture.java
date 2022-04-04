package autolecture.database;

import org.json.simple.JSONObject;

import autolecture.util.Util;

public class Lecture {

	private String dateReleased, title, dateCompleted, dateAdded;

	public Lecture(String title, String dateToBeReleased, String dateAdded) {
		this(title, dateToBeReleased, dateAdded, null);
	}

	public Lecture(String title, String dateToBeReleased, String dateAdded, String dateCompleted) {
		this.title = title;
		this.dateReleased = dateToBeReleased;
		this.dateAdded = dateAdded;
		this.dateCompleted = dateCompleted;
	}
	
	
	/**
	 * the key of JSONObject returned is lecture title, the value stores a
	 * JSONObject that contains key-value pairs of all other information
	 * 
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject lectureInfoToJSON() {
		JSONObject json = new JSONObject();
		JSONObject lectureJson = new JSONObject();

		lectureJson.put(Subject.DATERELEASED_K, getDateReleased());
		lectureJson.put(Subject.DATEADDED_K, getDateAdded());
		lectureJson.put(Subject.DATECOMPLETED_K, getDateCompleted());

		json.put(title, lectureJson);
		return json;
	}

	public void completeLectureToday() {
		dateCompleted = Util.getDateNow();
	}
	
	// GETTERS AND SETTERS
	
	public String getDateReleased() {
		return dateReleased;
	}

	public void setDateReleased(String dateReleased) {
		this.dateReleased = dateReleased;
	}

	public String getTitle() {
		return title;
	}

	public void setName(String title) {
		this.title = title;
	}

	public String getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

}
