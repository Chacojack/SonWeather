package jack.me.sonweather.contract;

import android.app.Activity;

import jack.me.sonweather.model.Air;
import jack.me.sonweather.model.Alarm;
import jack.me.sonweather.model.HourWeather;
import jack.me.sonweather.model.SevenDayWeather;
import jack.me.sonweather.model.Sun;
import jack.me.sonweather.model.WeatherIndexs;
import jack.me.sonweather.ui.activity.WeatherActivity;

/**
 * Created by zjchai on 2016/11/2.
 */

public class WeatherContract {

    public interface IView{

        void showHourWeather(HourWeather hourWeather);

        void showWeekWeather(SevenDayWeather weather);

        void showSun(Sun sun);

        void showAir(Air air);

        void showAlarm(Alarm alarm);

        void showWeatherIndex(WeatherIndexs weatherIndexs);
    }

    public interface IPresenter{

        void loadCurrentLocationWeather();

        void checkPermissions(Activity activity);

        void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt);

    }

}
