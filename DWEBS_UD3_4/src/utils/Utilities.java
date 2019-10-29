package utils;

public abstract class Utilities {
	public static final String CONTENT_TYPE = "text/html";
	public static final String DOCTYPE = "<!DOCTYPE HTML>";

	public static String headWithTitle(String title) {
		return (DOCTYPE + "\n" + "<html>\n" + "<head>" + css() + "<title>" + title
				+ "</title><meta charset=\"utf-8\"/></head>\n<body>");
	}

	public static String css() {
		String css = "<style>*{margin:0 auto;text-align:center}table{border-collapse: collapse;}";
		css += "table, th, td {border: 1px solid black}thead{background-color:grey}</style>";
		return css;
	}
}