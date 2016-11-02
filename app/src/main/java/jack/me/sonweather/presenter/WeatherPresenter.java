package jack.me.sonweather.presenter;

import android.util.Log;

import javax.inject.Inject;

import jack.me.sonweather.contract.WeatherContract;

/**
 * Created by zjchai on 2016/11/2.
 */

public class WeatherPresenter implements WeatherContract.IPresenter {

    public static final String TAG = WeatherPresenter.class.getSimpleName();

    @Inject
    WeatherContract.IView view;

    @Inject
    public WeatherPresenter(WeatherContract.IView view) {
        this.view = view;
    }

    @Override
    public void printWeather(){
        Log.d(TAG, "printWeather: #######");
    }

}
