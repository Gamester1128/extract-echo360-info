package autolecture.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

import autolecture.util.Util;

public class Database {

	private static final String path = "res/database/lectures.json";
	public List<Subject> subjects;

	public Database() {
		subjects = new ArrayList<Subject>();
	}

	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public void setSubjects(Subject[] subjects) {
		for (Subject subject : subjects)
			this.subjects.add(subject);
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	@SuppressWarnings("unchecked")
	public void loadSubjects() {
		subjects = new ArrayList<Subject>();

		JSONObject json = Util.getJSONfromFile(path);

		for (String key : (Set<String>) json.keySet()) {

			Subject subject = new Subject(key);

			JSONObject lectureJson = (JSONObject) json.get(key);
			for (String lectureKey : (Set<String>) lectureJson.keySet()) {
				JSONObject lectureInfoJson = (JSONObject) lectureJson.get(lectureKey);
				subject.addLecture(new Lecture(lectureKey, (String) lectureInfoJson.get(Subject.DATERELEASED_K),
						(String) lectureInfoJson.get(Subject.DATEADDED_K),
						(String) lectureInfoJson.get(Subject.DATECOMPLETED_K)));
			}
			subjects.add(subject);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateSubjectsIntoStorage() {

		// old subjects data
		JSONObject json = Util.getJSONfromFile(path);

		for (Subject subject : subjects) {
			// add new subject if not existed
			if (!json.containsKey(subject.getName())) {
				json.put(subject.getName(), subject.toJson().get(subject.getName()));
				continue;
			}

			// gets all lectures of subject
			List<Lecture> lectures = subject.getLectures();
			JSONObject subjectJsonOld = (JSONObject) json.get(subject.getName());

			for (Lecture lecture : lectures) {

				// if lecture already exists, update date completed
				if (subjectJsonOld.containsKey(lecture.getTitle())) {
					JSONObject lectureInfoJson = (JSONObject) subjectJsonOld.get(lecture.getTitle());
					lectureInfoJson.put(Subject.DATECOMPLETED_K, lecture.getDateCompleted());
					subjectJsonOld.put(lecture.getTitle(), lectureInfoJson);
				} else { // else is a new lecture, add it to json
					JSONObject lectureJson = lecture.lectureInfoToJSON();
					subjectJsonOld.put(lecture.getTitle(), lectureJson.get(lecture.getTitle()));
				}
			}
			// update subject's value
			json.put(subject.getName(), subjectJsonOld);
		}

		// save new json
		Util.saveJSON(path, json);
	}

}
