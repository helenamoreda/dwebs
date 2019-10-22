package utils;


public abstract class Utilities {
	public static final String CONTENT_TYPE = "text/html";
	public static final String DOCTYPE = "<!DOCTYPE HTML>";

	public static String headWithTitle(String title) {
		return (DOCTYPE + "\n" + "<html>\n" + "<head><title>" + title + "</title><meta charset=\"utf-8\"/></head>\n");
	}
}