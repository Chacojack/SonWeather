package jack.me.sonweather.location;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import jack.me.sonweather.SonApplication;

/**
 * Created by zjchai on 2016/11/3.
 */

public class LocationHandler implements ILocationHandler{

    private SonApplication sonApplication;

    private AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mLocationClient;



    public LocationHandler(SonApplication sonApplication) {
        this.sonApplication = sonApplication;
    }

    @Override
    public void getLocation(AMapLocationListener listener){
        mLocationClient = new AMapLocationClient(sonApplication);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationListener(listener);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(3000);
        mLocationOption.setOnceLocationLatest(true);
        mLocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mLocationClient.startLocation();
    }



}
