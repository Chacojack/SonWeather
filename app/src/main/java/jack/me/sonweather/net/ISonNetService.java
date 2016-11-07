package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import jack.me.sonweather.model.Weather7D;
import jack.me.sonweather.net.entity.YYWeather7DResult;
import jack.me.sonweather.net.entity.YYWeatherBaseResult;
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

}
