package jack.me.sonweather.presenter;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;

import javax.inject.Inject;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.location.ILocationHandler;
import jack.me.sonweather.net.INetHandler;

/**
 * Created by zjchai on 2016/11/2.
 */

public class WeatherPresenter implements WeatherContract.IPresenter {

    public static final String TAG = WeatherPresenter.class.getSimpleName();

    WeatherContract.IView view;

    @Inject
    INetHandler netHandler;
    @Inject
    ILocationHandler locationHandler;

    public WeatherPresenter(WeatherContract.IView view) {
        this.view = view;
        SonApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    public void printWeather() {
        Log.d(TAG, "printWeather: #######");
    }


    @Override
    public void loadCurrentLocationWeather() {
        locationHandler.getLocation(aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    String city = aMapLocation.getCity();
                    Log.d(TAG, "loadCurrentLocationWeather: city:"+city);
                }
            }
        });
    }
}
