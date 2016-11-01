package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zjchai on 2016/11/1.
 */

public interface ISonNetService {

    @GET(HttpURLs.CITY_LIST)
    Observable<City> getCityList();

}
