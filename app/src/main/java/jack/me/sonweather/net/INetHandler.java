package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import jack.me.sonweather.net.entity.YYWeather7DResult;
import jack.me.sonweather.net.entity.YYWeatherActualResult;
import jack.me.sonweather.net.entity.YYWeatherAirResult;
import jack.me.sonweather.net.entity.YYWeatherHourResult;
import rx.Observable;

/**
 * Created by zjchai on 2016/11/1.
 */
public interface INetHandler {
    Observable<City> getCityList();

    Observable<YYWeather7DResult> get7DWeatherByCity(String cityId);

    Observable<YYWeatherActualResult> getActualWeatherByCity(String city);

    Observable<YYWeatherHourResult> getHoursWeatherByCity(String city);

    Observable<YYWeatherAirResult> getAirWeatherByCity(String city);
}
