package com.duleendra.budgetmanager.util;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateUtil {

	public static final String DATE_FORMAT_YYMMDD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static SimpleDateFormat getSimpleDateFormatObject(String dateFormat) {
		SimpleDateFormat fmt = null;
		
		if (dateFormat != null) {
			fmt = new SimpleDateFormat(dateFormat);
		} else {
			fmt = new SimpleDateFormat(DATE_FORMAT_YYMMDD);
		}
		
		return fmt;
	}
	

}
