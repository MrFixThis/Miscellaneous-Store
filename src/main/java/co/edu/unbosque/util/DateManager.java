package co.edu.unbosque.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bryan Baron
 */
public class DateManager {
	/**
	 * Transforms a given string-like date into an array with its parts
	 *
	 * @param date string-like date to transform
	 * @return an array with the string-like date's information mapped
	 */
	public static String[] transformStringDate(String date) {
		String[] dateParts = new String[3];
		Pattern regex = Pattern.compile("([0-9]+)");
		Matcher matches = regex.matcher(date);

		for(int i = 0; i < dateParts.length; i++) {
			matches.find();
			dateParts[i] = matches.group();
		}

		return dateParts;
	}
}
