package jack.me.sonweather.net;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.constant.SonConstant;
import jack.me.sonweather.model.City;
import jack.me.sonweather.net.entity.YYWeather7DResult;
import jack.me.sonweather.net.entity.YYWeatherActualResult;
import jack.me.sonweather.net.entity.YYWeatherAirResult;
import jack.me.sonweather.net.entity.YYWeatherHourResult;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by zjchai on 2016/11/1.
 */

public class NetHandler implements INetHandler {

    public static final String TAG = NetHandler.class.getSimpleName();

    Retrofit retrofit;
    ISonNetService sonNetService;

    public NetHandler() {
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.yytianqi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        sonNetService = retrofit.create(ISonNetService.class);
    }

    @Override
    public Observable<City> getCityList() {
        return sonNetService.getCityList();
    }

    @Override
    public Observable<YYWeather7DResult> get7DWeatherByCity(String cityId) {
        return sonNetService.getWeatherForSeven(cityId, SonConstant.YYWeatherAppKey);
    }

    @Override
    public Observable<YYWeatherActualResult> getActualWeatherByCity(String city) {
        return sonNetService.getWeatherForActual(city, SonConstant.YYWeatherAppKey);
    }

    @Override
    public Observable<YYWeatherHourResult> getHoursWeatherByCity(String city) {
        return sonNetService.getWeatherForHours(city, SonConstant.YYWeatherAppKey);
    }

    @Override
    public Observable<YYWeatherAirResult> getAirWeatherByCity(String city) {
        return sonNetService.getWeatherForAir(city, SonConstant.YYWeatherAppKey);
    }


}
