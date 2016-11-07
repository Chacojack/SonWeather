package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import jack.me.sonweather.net.entity.YYWeather7DResult;
import rx.Observable;

/**
 * Created by zjchai on 2016/11/1.
 */
public interface INetHandler {
    Observable<City> getCityList();

    Observable<YYWeather7DResult> getWeatherByCity(String cityId);
}
