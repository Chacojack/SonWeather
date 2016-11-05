package jack.me.sonweather.utils;

import android.util.Log;

import jack.me.sonweather.BuildConfig;

/**
 * Created by zjchai on 2016/11/5.
 */
public class LogUtils {

    public static void dFormat(String tag, String format, Object... params) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, String.format(format, params));
        }
    }

    public static void eFormat(String tag, String format, Object... params) {
        Log.e(tag, String.format(format, params));
    }

    public static void d(String tag, String message){
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message){
        Log.e(tag, message);
    }

}
