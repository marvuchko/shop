package com.marko.shop.infrastructure.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	private DateUtil() {
		super();
	}
	
	public static Date addDays(int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
}
