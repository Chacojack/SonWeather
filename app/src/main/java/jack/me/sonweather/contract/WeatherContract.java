package jack.me.sonweather.contract;

import android.app.Activity;

import jack.me.sonweather.ui.activity.WeatherActivity;

/**
 * Created by zjchai on 2016/11/2.
 */

public class WeatherContract {

    public interface IView{

    }

    public interface IPresenter{

        void loadCurrentLocationWeather();

        void checkPermissions(Activity activity);

        void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt);

    }

}
