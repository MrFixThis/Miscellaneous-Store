package co.edu.unbosque.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.unbosque.bean.BirthDate;
import co.edu.unbosque.bean.HireDate;

/**
 * @author Bryan Baron
 */
public class DateManager {
	/**
	 * Retrieves a BirthDate bean instance build from a string-like date
	 *
	 * @param date string-like date to transform
	 * @return a BirthDate bean instance with the string-like's information mapped
	 */
	public static BirthDate transformStringDate(String date, BirthDate bd) {
		String[] dateParts = new String[3];
		Pattern regex = Pattern.compile("([0-9]+)");
		Matcher matches = regex.matcher(date);

		for(int i = 0; i < dateParts.length; i++) {
			matches.find();
			dateParts[i] = matches.group();
		}
		bd.setBYear(dateParts[0]);
		bd.setBMonth(dateParts[1]);
		bd.setBDay(dateParts[2]);

		return bd;
	}

	/**
	 * Retrieves a HireDate bean instance build from a string-like date
	 *
	 * @param date string-like date to transform
	 * @return a HireDate bean instance with the string-like's information mapped
	 */
	public static HireDate transformStringDate(String date, HireDate hd) {
		String[] dateParts = new String[3];
		Pattern regex = Pattern.compile("([0-9]+)");
		Matcher matches = regex.matcher(date);

		for(int i = 0; i < dateParts.length; i++) {
			matches.find();
			dateParts[i] = matches.group();
		}
		hd.setHYear(dateParts[0]);
		hd.setHMonth(dateParts[1]);
		hd.setHDay(dateParts[2]);

		return hd;
	}

	/**
	 * Creates a SQL date instance from a BirthDate's bean instance
	 *
	 * @param birthDate source BirthDate's bean instance
	 * @return an SQL date instance built from a BirthDate's bean instance
	 */
	public static Date createSQLDate(BirthDate birthDate) {
		Date date = null;
		date = Date.valueOf(String.format("%s-%s-%s",
					birthDate.getBYear(),
					birthDate.getBMonth(),
					birthDate.getBDay())
				);
		return date;
	}

	/**
	 * Creates a SQL date instance from a HireDate's bean instance
	 *
	 * @param hireDate source HireDate's bean instance
	 * @return an SQL date instance built from a HireDate's bean instance
	 */
	public static Date createSQLDate(HireDate hireDate) {
		Date date = null;
		date = Date.valueOf(String.format("%s-%s-%s",
					hireDate.getHYear(),
					hireDate.getHMonth(),
					hireDate.getHDay())
				);
		return date;
	}
}
