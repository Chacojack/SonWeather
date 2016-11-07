package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import jack.me.sonweather.net.entity.YYWeather7DResult;
import jack.me.sonweather.net.entity.YYWeatherActualResult;
import jack.me.sonweather.net.entity.YYWeatherAirResult;
import jack.me.sonweather.net.entity.YYWeatherHourResult;
import jack.me.sonweather.net.entity.YYWeatherSunResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zjchai on 2016/11/1.
 */

public interface ISonNetService {

    @GET(HttpURLs.CITY_LIST)
    Observable<City> getCityList();

    @GET(HttpURLs.GET_WEATHER_SEVEN)
    Observable<YYWeather7DResult> getWeatherForSeven(@Query("city") String cityId, @Query("key") String key);

    @GET(HttpURLs.GET_WEATHER_ACTUAL)
    Observable<YYWeatherActualResult> getWeatherForActual(@Query("city") String city, @Query("key") String key);

    @GET(HttpURLs.GET_WEATHER_HOUR)
    Observable<YYWeatherHourResult> getWeatherForHours(@Query("city") String city, @Query("key") String key);

    @GET(HttpURLs.GET_WEATHER_AIR)
    Observable<YYWeatherAirResult> getWeatherForAir(@Query("city") String city, @Query("key") String key);

    @GET(HttpURLs.GET_WEATHER_SUN)
    Observable<YYWeatherSunResult> getWeatherForSun(@Query("city") String city, @Query("key") String key);
}
