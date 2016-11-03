package jack.me.sonweather.location;

import com.amap.api.location.AMapLocationListener;

/**
 * Created by zjchai on 2016/11/3.
 */

public interface ILocationHandler {
    void getLocation(AMapLocationListener listener);
}
