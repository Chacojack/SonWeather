package jack.me.sonweather.utils;

import android.support.annotation.NonNull;

import jack.me.sonweather.R;
import jack.me.sonweather.SonApplication;

/**
 * Created by zjchai on 2016/11/12.
 */

public class WeatherUtils {

    // 晴 多云 阴 阵雨 雷阵雨 雷阵雨伴有冰雹 雨夹雪 小雨 中雨 大雨 暴雨 大暴雨 特大暴雨 阵雪 小雪 中雪 大雪 暴雪 雾 冻雨 沙尘暴
    // 小到中雨 中到大雨 大到暴雨 大暴雨到特大暴雨 小到中雪 中到大雪 大到暴雪 浮尘 扬沙 强沙尘暴 浓雾 强浓雾 霾
    // 中度霾 重度霾 严重霾 大雾 特强浓雾 无 刮风

    private static final String TAG = WeatherUtils.class.getSimpleName();

    private static final String FINE_DAY = "晴";
    private static final String RAIN_DAY = "雨";
    private static final String CLOUD_DAY = "云";
    private static final String OVERCAST = "阴";
    private static final String LIGHTNING = "雷";
    private static final String FOG = "雾";
    private static final String HAZE = "霾";
    private static final String SNOW = "雪";


    public static String getIconOfWeather(@NonNull String name, @NonNull String time) {
        if (isFineDay(name)) {
            if (TimeUtils.isDay(time)) {
                return SonApplication.INSTANCE.getString(R.string.font_awesome_sun);
            } else {
                return SonApplication.INSTANCE.getString(R.string.font_awesome_moon);
            }
        } else if (isLightningDay(name)) {
            return SonApplication.INSTANCE.getString(R.string.font_awesome_lightning);
        } else if (isRainDay(name)) {
            return SonApplication.INSTANCE.getString(R.string.font_awesome_rain);
        } else if (isCloudDay(name)) {
            return SonApplication.INSTANCE.getString(R.string.font_awesome_cloud);
        } else if (isFogDay(name)) {
            return SonApplication.INSTANCE.getString(R.string.font_awesome_fog);
        } else if (isSnowDay(name)) {
            return SonApplication.INSTANCE.getString(R.string.font_awesome_snow);
        } else {
            if (TimeUtils.isDay(time)) {
                return SonApplication.INSTANCE.getString(R.string.font_awesome_sun);
            } else {
                return SonApplication.INSTANCE.getString(R.string.font_awesome_moon);
            }
        }
    }


    private static boolean isSnowDay(@NonNull String name) {
        return name.contains(SNOW);
    }

    private static boolean isFogDay(@NonNull String name) {
        return name.contains(FOG) || name.contains(HAZE);
    }

    private static boolean isLightningDay(@NonNull String name) {
        return name.contains(LIGHTNING);
    }

    private static boolean isCloudDay(@NonNull String name) {
        return name.contains(CLOUD_DAY) || name.contains(OVERCAST);
    }

    private static boolean isCloudDayOnly(@NonNull String name) {
        return name.contains(CLOUD_DAY);
    }

    private static boolean isOvercastOnly(@NonNull String name) {
        return name.contains(OVERCAST);
    }

    private static boolean isRainDay(@NonNull String name) {
        return name.contains(RAIN_DAY);
    }

    private static boolean isFineDay(@NonNull String name) {
        return name.contains(FINE_DAY);
    }

    public static String getTemperatureString(int temperature) {
        return String.format(SonApplication.INSTANCE.getString(R.string.temperature), temperature);
    }

    public static int getWeatherIconColor(@NonNull String icon) {
        if (SonApplication.INSTANCE.getString(R.string.font_awesome_sun).equals(icon)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.sun_yellow);
        } else if (SonApplication.INSTANCE.getResources().getString(R.string.font_awesome_moon).equals(icon)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.moon_yellow);
        } else if (SonApplication.INSTANCE.getResources().getString(R.string.font_awesome_lightning).equals(icon)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.lightning_yellow);
        } else if (SonApplication.INSTANCE.getResources().getString(R.string.font_awesome_rain).equals(icon)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.rain_blue);
        } else {
            return SonApplication.INSTANCE.getResources().getColor(R.color.white);
        }
    }

    public static int getWeatherBackgroundColor(@NonNull String name) {
        if (isFineDay(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.fine_day);
        }
        if (isLightningDay(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.lightning);
        }
        if (isRainDay(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.rain_day);
        }
        if (isCloudDayOnly(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.cloud_day);
        }
        if (isOvercastOnly(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.overcast);
        }
        if (isFogDayOnly(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.fog);
        }
        if (isHazeDayOnly(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.haze);
        }
        if (isSnowDay(name)) {
            return SonApplication.INSTANCE.getResources().getColor(R.color.snow);
        }
        return SonApplication.INSTANCE.getResources().getColor(R.color.fine_day);
    }

    private static boolean isHazeDayOnly(String name) {
        return name.contains(HAZE);
    }

    private static boolean isFogDayOnly(@NonNull String name) {
        return name.contains(FOG);
    }


}



















