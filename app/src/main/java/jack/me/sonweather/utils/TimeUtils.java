package jack.me.sonweather.utils;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

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

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

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

    public static boolean isDay(@NonNull String time) {
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


    public static Calendar getDateFormString(@NonNull String time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        Date date = null;
        try {
            date = format.parse(time.trim());
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            LogUtils.eFormat(TAG, "isDay : have ParseException time:%s ", time);
            return null;
        }
    }

}
