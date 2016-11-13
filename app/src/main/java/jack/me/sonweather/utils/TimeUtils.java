package jack.me.sonweather.utils;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jack.me.sonweather.R;
import jack.me.sonweather.SonApplication;

import static java.util.Calendar.HOUR_OF_DAY;

/**
 * Created by zjchai on 2016/11/13.
 */

public class TimeUtils {

    private static final String TAG = TimeUtils.class.getSimpleName();

    private static final String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DAY_PATTERN = "yyyy-MM-dd";
    private static final String HOUR_PATTERN = "HH:mm:ss";
    public static final int DAY_PATTERN_LENGTH = 10;
    public static final int HOUR_PATTERN_LENGTH = 8;
    public static final int FULL_PATTERN_LENGTH = 19;


    public static String getTwelveHour(@NonNull String time) {
        Calendar calendar = getDateFormString(time);
        if (calendar != null) {
            int hour = calendar.get(HOUR_OF_DAY);
            if (isBeforeNoon(hour)) {
                return String.format(SonApplication.INSTANCE.getString(R.string.before_noon_time), hour);
            } else {
                return String.format(SonApplication.INSTANCE.getString(R.string.after_noon_time), hour - 12);
            }
        } else {
            return "";
        }
    }

    static boolean isDay(@NonNull String time) {
        Calendar calendar = getDateFormString(time);
        if (calendar != null) {
            int hour = calendar.get(HOUR_OF_DAY);
            return isDay(hour);
        } else {
            return true;
        }
    }

    private static boolean isBeforeNoon(@IntRange(from = 0, to = 24) int hour) {
        return 0 <= hour && hour <= 12;
    }

    private static boolean isDay(@IntRange(from = 0, to = 24) int hour) {
        return 6 <= hour && hour <= 18;
    }


    private static Calendar getDateFormString(@NonNull String time) {
        time = time.trim();
        DateFormat format = getDateFormatFormTime(time);
        if (format == null) {
            return null;
        }
        try {
            Date date = format.parse(time);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            LogUtils.eFormat(TAG, "isDay : have ParseException time:%s ", time);
            return null;
        }
    }

    private static DateFormat getDateFormatFormTime(String time) {
        time = time.trim();
        switch (time.length()) {
            case DAY_PATTERN_LENGTH:
                return new SimpleDateFormat(DAY_PATTERN);
            case HOUR_PATTERN_LENGTH:
                return new SimpleDateFormat(HOUR_PATTERN);
            case FULL_PATTERN_LENGTH:
                return new SimpleDateFormat(FULL_PATTERN);
            default:
                return null;
        }
    }

    public static String getWeekDayName(String time) {
        Calendar calendar = getDateFormString(time);
        String[] weekArray = SonApplication.INSTANCE.getResources().getStringArray(R.array.week_name);
        if (calendar != null) {
            int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (week >= 0 && weekArray.length > week) {
                return weekArray[week];
            } else {
                return weekArray[0];
            }
        } else {
            return weekArray[0];
        }

    }


}
