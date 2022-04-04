package autolecture;

public class CookieUtil {

	/* public static void loadCookies(WebDriver driver) { try {
	 * 
	 * File file = new File("res/Cookies.data"); FileReader fileReader = new
	 * FileReader(file); BufferedReader Buffreader = new BufferedReader(fileReader);
	 * String strline; while ((strline = Buffreader.readLine()) != null) {
	 * StringTokenizer token = new StringTokenizer(strline, ";"); while
	 * (token.hasMoreTokens()) { String name = token.nextToken(); String value =
	 * token.nextToken(); String domain = token.nextToken(); // if (domain.charAt(0)
	 * == '.') domain = domain.substring(1); String path = token.nextToken(); Date
	 * expiry = null;
	 * 
	 * String val; if (!(val = token.nextToken()).equals("null")) { expiry = new
	 * Date(val); } Boolean isSecure = new
	 * Boolean(token.nextToken()).booleanValue(); Cookie ck = new Cookie(name,
	 * value, domain, path, expiry, isSecure); System.out.println(ck);
	 * driver.manage().addCookie(ck); // This will add the stored cookie to your
	 * current session } } } catch (Exception ex) { ex.printStackTrace(); } }
	 * 
	 * public static void saveCookies(WebDriver driver) { File file = new
	 * File("res/Cookies.data"); try { // Delete old file if exists file.delete();
	 * file.createNewFile(); FileWriter fileWrite = new FileWriter(file);
	 * BufferedWriter Bwrite = new BufferedWriter(fileWrite); // loop for getting
	 * the cookie information
	 * 
	 * // loop for getting the cookie information for (Cookie ck :
	 * driver.manage().getCookies()) { Bwrite.write((ck.getName() + ";" +
	 * ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";" +
	 * ck.getExpiry() + ";" + ck.isSecure())); Bwrite.newLine(); } Bwrite.close();
	 * fileWrite.close();
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } }
	 * 
	 * public static void loadCookiesv2(WebDriver driver) { try { FileInputStream
	 * fis = new FileInputStream("res/Cookies.ser"); ObjectInputStream ois = new
	 * ObjectInputStream(fis); Object obj;
	 * 
	 * while ((obj = ois.readObject()) != null) { driver.manage().addCookie((Cookie)
	 * obj); }
	 * 
	 * ois.close(); fis.close(); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); } catch (ClassNotFoundException | IOException e) {
	 * e.printStackTrace();
	 * 
	 * } }
	 * 
	 * public static void saveCookiesv2(WebDriver driver) {
	 * 
	 * try { FileOutputStream fos = new FileOutputStream("res/Cookies.ser");
	 * ObjectOutputStream oos = new ObjectOutputStream(fos);
	 * 
	 * for (Cookie cookie : driver.manage().getCookies()) { oos.writeObject(cookie);
	 * } oos.close(); fos.close(); } catch (IOException e) { e.printStackTrace(); }
	 * }
	 * 
	 * @SuppressWarnings("unchecked") public static void saveCookies(String path,
	 * Set<Cookie> cookies) { // getcookies JSONObject json = getJSONfromFile(path);
	 * 
	 * for (Cookie cookie : cookies) { if (json.containsKey(cookie.getDomain())) {
	 * JSONObject existingCookie = (JSONObject) json.get(cookie.getDomain());
	 * addCookieToJson(existingCookie, cookie); json.put(cookie.getDomain(),
	 * existingCookie); } else { JSONObject newCookie = new JSONObject();
	 * addCookieToJson(newCookie, cookie); json.put(cookie.getDomain(), newCookie);
	 * } } saveJSON(path, json); }
	 * 
	 * @SuppressWarnings("unchecked") public static JSONObject
	 * addCookieToJson(JSONObject json, Cookie cookie) { json.put("domain",
	 * JSONObject.escape(cookie.getDomain())); Date date = cookie.getExpiry();
	 * 
	 * if (date != null) json.put("expiry",
	 * JSONObject.escape(cookie.getExpiry().toString())); json.put("name",
	 * JSONObject.escape(cookie.getName())); json.put("path",
	 * JSONObject.escape(cookie.getPath())); json.put("value",
	 * JSONObject.escape(cookie.getValue())); json.put("httpOnly",
	 * cookie.isHttpOnly()); json.put("secure", cookie.isSecure());
	 * 
	 * return json; }
	 * 
	 * @SuppressWarnings("unchecked") public static void loadCookies(WebDriver
	 * driver, String path) { // getcookies JSONObject json = getJSONfromFile(path);
	 * System.out.println(json);
	 * 
	 * for (String key : (Set<String>) json.keySet()) { JSONObject cookieJson =
	 * (JSONObject) json.get(key);
	 * 
	 * @SuppressWarnings("deprecation") Date expiry =
	 * (!cookieJson.containsKey("expiry")) ? null : new Date((String)
	 * cookieJson.get("expiry")); Cookie cookie = new Cookie((String)
	 * cookieJson.get("name"), (String) cookieJson.get("value"), (String)
	 * cookieJson.get("domain"), (String) cookieJson.get("path"), expiry, (boolean)
	 * cookieJson.get("secure"), (boolean) cookieJson.get("httpOnly"));
	 * driver.manage().addCookie(cookie); } }
	 * 
	 * private static void saveJSON(String path, JSONObject json) { try { FileWriter
	 * writer = new FileWriter(path); writer.write(json.toJSONString());
	 * writer.close(); } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * // returns jsonobject from jsonfile private static JSONObject
	 * getJSONfromFile(String path) { try { return (JSONObject) new
	 * JSONParser().parse(new FileReader(path)); } catch (IOException |
	 * ParseException e) { e.printStackTrace(); return new JSONObject(); } } */

}
