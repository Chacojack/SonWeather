package jack.me.sonweather.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amap.api.location.AMapLocation;

import javax.inject.Inject;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.database.IDBHandler;
import jack.me.sonweather.location.ILocationHandler;
import jack.me.sonweather.model.City;
import jack.me.sonweather.net.INetHandler;
import jack.me.sonweather.net.entity.YYWeatherActualResult;
import jack.me.sonweather.net.entity.YYWeatherAirResult;
import jack.me.sonweather.net.entity.YYWeatherAlarmResult;
import jack.me.sonweather.net.entity.YYWeatherHourResult;
import jack.me.sonweather.net.entity.YYWeatherSunResult;
import jack.me.sonweather.sp.ISPHandler;
import jack.me.sonweather.utils.LogUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zjchai on 2016/11/2.
 */

public class WeatherPresenter implements WeatherContract.IPresenter {

    private static final String TAG = WeatherPresenter.class.getSimpleName();

    private WeatherContract.IView view;

    @Inject
    INetHandler netHandler;
    @Inject
    ILocationHandler locationHandler;
    @Inject
    ISPHandler spHandler;
    @Inject
    IDBHandler dbHandler;

    public WeatherPresenter(WeatherContract.IView view) {
        this.view = view;
        SonApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    public void loadCurrentLocationWeather() {
        locationHandler.getLocation(
                aMapLocation -> Schedulers.io().createWorker().schedule(
                        ()-> handleLocationResult(aMapLocation)
                )
        );
    }

    private void handleLocationResult(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                String cityName = aMapLocation.getCity();
                Log.d(TAG, "loadCurrentLocationWeather: city:" + cityName);
                if (cityName != null) {
                    cityName = trimTheCitySuffix(cityName);
                    spHandler.setLocation(cityName);
                    City city = dbHandler.getCityByName(cityName);
                    if (city != null) {
                        LogUtils.dFormat(TAG, "loadCurrentLocationWeather : cityId :%s ", city.getId());
                        getWeather7D(city);
                        getActualWeather(city);
                        getHoursWeather(city);
                        getAirWeather(city);
                        getSunWeather(city);
                        getAlarmWeather(city);
                    }
                }
            }
        }
    }

    private void getAlarmWeather(City city) {
        netHandler.getAlarmWeatherByCity(city.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    LogUtils.dFormat(TAG, "getAlarmWeather : result:%s ", result);
                });
    }

    private void getSunWeather(City city) {
        netHandler.getSunWeatherByCity(city.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    LogUtils.dFormat(TAG, "getSunWeather : result:%s ", result);
                });
    }

    private void getAirWeather(City city) {
        netHandler.getAirWeatherByCity(city.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    LogUtils.dFormat(TAG, "getAirWeather : result:%s ", result);
                });
    }

    private void getHoursWeather(City city) {
        netHandler.getHoursWeatherByCity(city.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    LogUtils.dFormat(TAG, "getHoursWeather : result:%s ", result);
                });
    }

    private void getActualWeather(City city) {
        netHandler.getActualWeatherByCity(city.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    LogUtils.dFormat(TAG, "getActualWeather : result:%s ", result);
                });
    }

    private void getWeather7D(City city) {
        netHandler.get7DWeatherByCity(city.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    LogUtils.dFormat(TAG, "getWeather7D : result:%s ", result);
                });
    }

    @NonNull
    private String trimTheCitySuffix(String cityName) {
        if (cityName.endsWith("市")) {
            cityName = cityName.substring(0, cityName.lastIndexOf("市"));
        }
        return cityName;
    }

    @Override
    public void checkPermissions(Activity activity) {
        locationHandler.checkPermissions(activity);
    }


    @Override
    public void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        locationHandler.handleRequestPermissionsResult(requestCode, permissions, paramArrayOfInt
                , (permission, result) -> LogUtils.dFormat(TAG, "onPermissionRequestFail permission:%s, result:%d ", permission, result));
    }
}
