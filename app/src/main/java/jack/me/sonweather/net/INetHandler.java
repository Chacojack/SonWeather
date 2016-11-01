package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import rx.Observable;

/**
 * Created by zjchai on 2016/11/1.
 */
public interface INetHandler {
    Observable<City> getCityList();
}
