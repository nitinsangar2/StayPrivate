package com.example.androidlauncher2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	/**
	 * 
	 * @return "HH:mm:ss"
	 */
	public static String getCurTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	public static String getCurDayTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 
	 * @return "HH:mm"
	 */
	public static String getTime() {
		String timeString = getCurTime();
		String[] timeArr = timeString.split(":");
		String dateString = timeArr[0] + ":" + timeArr[1];
		return dateString;
	}

}
