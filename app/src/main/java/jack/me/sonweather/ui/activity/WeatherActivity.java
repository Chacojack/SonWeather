package jack.me.sonweather.ui.activity;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jack.me.sonweather.R;
import jack.me.sonweather.component.DaggerWeatherViewComponent;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.model.Air;
import jack.me.sonweather.model.Alarm;
import jack.me.sonweather.model.HourWeather;
import jack.me.sonweather.model.SevenDayWeather;
import jack.me.sonweather.model.Sun;
import jack.me.sonweather.module.WeatherViewModule;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.IView {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    @Inject
    WeatherContract.IPresenter presenter;
    @BindView(R.id.actual_weather_city_name)
    TextView actualWeatherCityName;
    @BindView(R.id.actual_weather_name)
    TextView actualWeatherName;
    @BindView(R.id.header_city_and_state)
    RelativeLayout headerCityAndState;
    @BindView(R.id.actual_weather_temperature)
    TextView actualWeatherTemperature;
    @BindView(R.id.hour_weather_list)
    RecyclerView hourWeatherList;
    @BindView(R.id.txt_weekday)
    TextView txtWeekday;
    @BindView(R.id.txt_today)
    TextView txtToday;
    @BindView(R.id.txt_low_temperature)
    TextView txtLowTemperature;
    @BindView(R.id.txt_high_temperature)
    TextView txtHighTemperature;
    @BindView(R.id.header_bottom_bar)
    RelativeLayout headerBottomBar;
    @BindView(R.id.week_weather_list)
    RecyclerView weekWeatherList;
    @BindView(R.id.txt_suggest)
    TextView txtSuggest;
    @BindView(R.id.detail_weather_list)
    RecyclerView detailWeatherList;

    public static void start(Context context) {
        Intent intent = new Intent(context, WeatherActivity.class);
        if (context instanceof Application || context instanceof Service) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

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

    }

    private void afterViews() {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#00000000"));
        presenter.loadCurrentLocationWeather();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            presenter.checkPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        presenter.handleRequestPermissionsResult(requestCode, permissions, paramArrayOfInt);

    }

    @Override
    public void showHourWeather(HourWeather hourWeather) {
        // TODO: 2016/11/12 to show hour weather list

    }


    @Override
    public void showWeekWeather(SevenDayWeather weather) {
        // TODO: 2016/11/12 to show weekWeather list
    }

    @Override
    public void showSun(Sun sun) {
        // TODO: 2016/11/12 to show sun info
    }


    @Override
    public void showAir(Air air) {
        // TODO: 2016/11/12 to show air info
    }


    @Override
    public void showAlarm(Alarm alarm){
        // TODO: 2016/11/12 to show alarm info
    }






}

































