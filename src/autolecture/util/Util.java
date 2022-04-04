package autolecture.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Util {

	public static String[] readFileAsArray(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> list = new ArrayList<String>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
			return (String[]) list.toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readFileAsString(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String result = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				result += line + "\n";
			}
			reader.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void save(String path, boolean append, String[] strings) {
		try {
			FileWriter writer = new FileWriter(path, append);
			for (String i : strings) {
				writer.write(i);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(String path, boolean append, String string) {
		try {
			FileWriter writer = new FileWriter(path, append);
			writer.write(string);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveJSON(String path, JSONObject json) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(json.toJSONString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// returns jsonobject from jsonfile
	public static JSONObject getJSONfromFile(String path) {
		try {
			return (JSONObject) new JSONParser().parse(new FileReader(path));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

	public static String getDateInRightFormat(String date, String dateParserFormat) {
		LocalDate localdate = LocalDate.parse(date, DateTimeFormatter.ofPattern(dateParserFormat, Locale.UK));
		return localdate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
	}

	public static String getDateNow() {
		return LocalDate.now(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("dd/MM/yy"));
	}

}
