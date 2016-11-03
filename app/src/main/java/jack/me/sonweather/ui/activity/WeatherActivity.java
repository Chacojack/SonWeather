package jack.me.sonweather.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.ButterKnife;
import jack.me.sonweather.R;
import jack.me.sonweather.component.DaggerWeatherViewComponent;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.module.WeatherViewModule;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.IView, AMapLocationListener {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    @Inject
    WeatherContract.IPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initExtra(savedInstanceState);
        inject();
        afterInject();
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        afterViews();
    }

    /**
     * init Extra from intent
     *
     * @param savedInstanceState
     */
    private void initExtra(Bundle savedInstanceState) {

    }

    private void inject() {
        DaggerWeatherViewComponent.builder().weatherViewModule(new WeatherViewModule(this)).build().inject(this);
    }

    private void afterInject() {
        presenter.printWeather();
    }

    private void afterViews() {
        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#00000000"));
        presenter.loadCurrentLocationWeather();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                String city = amapLocation.getCity();//城市信息
                Log.d(TAG, "onLocationChanged: city:"+city+" time:"+System.currentTimeMillis());
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

}
