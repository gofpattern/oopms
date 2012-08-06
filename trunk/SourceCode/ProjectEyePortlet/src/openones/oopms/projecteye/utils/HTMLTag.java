package openones.oopms.projecteye.utils;

public class HTMLTag {
	public static String replaceHTMLTag(String input) {
		if(input==null) {
			return null;
		}
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll("<", "&lt;");
		return input;
	}
}
