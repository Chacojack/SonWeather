package jack.me.sonweather.presenter;

import android.app.Activity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;

import javax.inject.Inject;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.location.ILocationHandler;
import jack.me.sonweather.net.INetHandler;
import jack.me.sonweather.sp.ISPHandler;
import jack.me.sonweather.utils.LogUtils;

import static jack.me.sonweather.location.LocationHandler.PERMISSON_REQUESTCODE;

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

    public WeatherPresenter(WeatherContract.IView view) {
        this.view = view;
        SonApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    public void loadCurrentLocationWeather() {
        locationHandler.getLocation(aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    String city = aMapLocation.getCity();
                    Log.d(TAG, "loadCurrentLocationWeather: city:"+city);
                    if (city != null) {
                        spHandler.setLocation(city);
                        // TODO: 2016/11/5 do load location info
                    }
                }
            }
        });
    }

    @Override
    public void checkPermissions(Activity activity) {
        locationHandler.checkPermissions(activity);
    }


    @Override
    public void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        locationHandler.handleRequestPermissionsResult(requestCode, permissions, paramArrayOfInt
                , (permission, result) -> LogUtils.dFormat(TAG,"onPermissionRequestFail permission:%s, result:%d ",permission,result));
    }
}
