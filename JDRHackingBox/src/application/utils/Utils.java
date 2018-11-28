package application.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Utils class
 * 
 * @author adamc
 *
 */
public class Utils {

	public static final List<Character> letters = new ArrayList<>();

	static {
		for (int i = 97; i < 123; i++) {
			letters.add(Character.valueOf((char) i));
		}
	}

	/**
	 * Insert characters between each element in list
	 * 
	 * @param list
	 *            to each
	 * @param toInsert
	 *            to insert
	 * @return sentence
	 */
	public static String insertBetween(List<?> list, char toInsert) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) builder.append(toInsert);
			builder.append(list.get(i));
		}
		return builder.toString();
	}
}
