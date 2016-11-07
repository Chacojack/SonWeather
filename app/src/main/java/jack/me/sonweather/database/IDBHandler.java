package jack.me.sonweather.database;

import jack.me.sonweather.model.City;

/**
 * Created by zjchai on 2016/11/1.
 */
public interface IDBHandler {
    int addCity(City city);

    City getCityByName(String cityName);
}
