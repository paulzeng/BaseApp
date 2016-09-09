package com.zwt.base.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":"
						+ unitFormat(second);
			}
		}
		return timeStr;
	}

	public static Date StringToTime(String str) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			date = formatter.parse(str);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StringToTimeByT(String str) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'hh:mm:ss");
			date = formatter.parse(str);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}



	public static String getBetweenTimeByT(String str) {
		long interval;
		long day;
		long hour;
		long minute;
		long second;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		Date currentTime = new Date();
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);
			// 默认为毫秒，除以1000是为了转换成秒
			interval = (currentTime.getTime() - beginTime.getTime()) / 1000;// 秒
			day = interval / (24 * 3600);// 天
			hour = interval % (24 * 3600) / 3600;// 小时
			minute = interval % 3600 / 60;// 分钟
			second = interval % 60;// 秒
			if (day > 1) {
				return day + "天前";
			} else if (day == 1) {
				return "昨天";
			} else if (day == 0) {
				if (hour > 1) {
					return hour + "小时前";
				} else if (hour == 0) {
					return "60分钟前";
				} else {
					return minute + "分钟前";
				}
			} else {
				return "";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取两个日期之间的间隔天数
	 * 
	 * @return
	 */
	public static int getGapCount(Date startDate, Date endDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime()
				.getTime()) / (1000 * 60 * 60 * 24));
	}

	@SuppressLint("SimpleDateFormat")
	public static String getBetweenTimeBy(String str) {
		long interval;
		long day;
		long hour;
		long minute;
		long second;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		try {
			beginTime = sdf.parse(str);
			DateFormat df1 = DateFormat.getDateInstance();// 日期格式，精确到日
			// 默认为毫秒，除以1000是为了转换成秒

			interval = (currentTime.getTime() - beginTime.getTime()) / 1000;// 秒
			// day = interval / (24 * 3600);// 天
			day = getGapCount(beginTime, currentTime);// 天
			hour = interval % (24 * 3600) / 3600;// 小时
			minute = interval % 3600 / 60;// 分钟
			second = interval % 60;// 秒
			if (day > 1 && day < 30) {
				return day + "天前";
			} else if (day == 1) {
				return "昨天";
			} else if (day >= 30) {
				return df1.format(beginTime) + "";
			} else if (day == 0) {
				if (hour > 1||hour==1) {
					return hour + "小时前";
				} else if (hour == 0 && minute != 0) {
					return minute + "分钟前";
				} else if (second > 10 && second < 60) {
					return second + "秒前";
				} else if (second < 10) {
					return "刚刚";
				}
			} else {
				return "";
			}

			/*
			 * Long timeLong = (currentTime.getTime() - beginTime.getTime()) /
			 * 1000;// 秒 if (timeLong < 60 * 1000) { return timeLong / 1000 +
			 * "秒前"; } else if (timeLong < 60 * 60 * 1000) { timeLong = timeLong
			 * / 1000 / 60; return timeLong + "分钟前"; } else if (timeLong < 60 *
			 * 60 * 24 * 1000) { timeLong = timeLong / 1000 / 60 / 60; return
			 * timeLong + "小时前"; } else if (timeLong < 60 * 60 * 24 * 1000 * 7)
			 * { timeLong = timeLong / 1000 / 60 / 60 / 24; return timeLong +
			 * "天前"; } else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
			 * timeLong = timeLong / 1000 / 60 / 60 / 24 / 7; return timeLong +
			 * "周前"; } else { return df1.format(beginTime) + ""; }
			 */
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String getDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);
			return sdf.format(beginTime) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getDateByT(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:mm");
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);
			DateFormat df1 = DateFormat.getDateInstance();// 日期格式，精确到日
			return df1.format(beginTime) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getExamDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);
			DateFormat df1 = DateFormat.getDateInstance();// 日期格式，精确到日
			return df1.format(beginTime) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getExamDateByT(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:mm");
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);
			DateFormat df1 = DateFormat.getDateInstance();// 日期格式，精确到日
			return df1.format(beginTime) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 将秒数改为分数
	public static String getTimeByInt(int n) {
		long minute;
		long second;
		// 默认为毫秒，除以1000是为了转换成秒
		minute = n % 3600 / 60;// 分钟
		second = n % 60;// 秒

		if (minute == 0) {
			if (second < 10) {
				return "00:0" + second;
			} else {
				return "00:" + second;
			}
		} else {
			if (minute < 10) {
				if (second < 10) {
					return "0" + minute + ":0" + second;
				} else {
					return "0" + minute + ":" + second;
				}
			} else {
				if (second < 10) {
					return minute + ":0" + second;
				} else {
					return minute + ":" + second;
				}
			}
		}
	}

	// 将秒数改为分数
	public static String getTimeByInt2(int n) {
		long minute;
		long second;
		// 默认为毫秒，除以1000是为了转换成秒
		minute = n % 3600 / 60;// 分钟
		second = n % 60;// 秒

		if (minute == 0) {
			if (second < 10) {
				return second+"秒";
			} else {
				return second+"秒";
			}
		} else {
			if (minute < 10) {
				if (second < 10) {
					return "0" + minute + "分0" + second+"秒";
				} else {
					return "0" + minute + "分" + second+"秒";
				}
			} else {
				if (second < 10) {
					return minute + "分0" + second+"秒";
				} else {
					return minute + "分" + second+"秒";
				}
			}
		}
	}

	public static String getBetweenTime(String str) {
		long interval;
		long day;
		long hour;
		long minute;
		long second;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:mm");

		Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);
			// 默认为毫秒，除以1000是为了转换成秒
			interval = (currentTime.getTime() - beginTime.getTime()) / 1000;// 秒
			day = interval / (24 * 3600);// 天
			hour = interval % (24 * 3600) / 3600;// 小时
			minute = interval % 3600 / 60;// 分钟
			second = interval % 60;// 秒

			if (day > 29) {
				return getExamDateByT(str);
			} else if (day > 1 && day < 30) {
				return day + "天";
			} else if (day == 1) {
				return "昨天";
			} else if (day == 0) {
				if (hour > 1) {
					return hour + "小时前";
				} else {
					if (minute > 0 && minute < 60) {
						return minute + "分钟前";
					} else {
						return "刚刚";
					}

				}
			} else {
				return "";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";

	}

	// 1 minute = 60 seconds
	// 1 hour = 60 x 60 = 3600
	// 1 day = 3600 x 24 = 86400
	public void printDifference(Date startDate, Date endDate) {

		// milliseconds
		long different = endDate.getTime() - startDate.getTime();

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : " + endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf("%d days, %d hours, %d minutes, %d seconds%n",
				elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);

	}

	public static String getBetweenTimes(String str) {
		long interval;
		long day;
		long hour;
		long minute;
		long second;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
		Date currentTime = new Date();
		// 将截取到的时间字符串转化为时间格式的字符串
		Date beginTime;
		// beginTime = sdf.parse("2016-01-02 12:53:30");
		try {
			beginTime = sdf.parse(str);

			// 默认为毫秒，除以1000是为了转换成秒
			interval = (currentTime.getTime() - beginTime.getTime()) / 1000;// 秒
			day = interval / (24 * 3600);// 天
			hour = interval % (24 * 3600) / 3600;// 小时
			minute = (interval % 3600) / 60;// 分钟
			second = interval % 60;// 秒

			if (day > 29) {
				return getExamDate(str);
			} else if (day > 1 && day < 30) {
				return day + "天";
			} else if (day == 1) {
				return "昨天";
			} else if (day == 0) {
				if (hour > 1) {
					return hour + "小时前";
				} else {
					if (minute > 0 && minute < 60) {
						return minute + "分钟前";
					} else {
						return "刚刚";
					}

				}
			} else {
				return "";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 网络是否可用
	 */
	public synchronized static boolean isNetworkAvailable(Context context) {
		boolean result = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = null;
		if (null != connectivityManager) {
			networkInfo = connectivityManager.getActiveNetworkInfo();
			if (null != networkInfo && networkInfo.isAvailable()
					&& networkInfo.isConnected()) {
				result = true;
			}
		}
		return result;
	}

}
