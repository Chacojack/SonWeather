package jack.me.sonweather.ui.activity;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jack.me.commonadapter.CommonAdapter;
import jack.me.commonadapter.CommonViewHolder;
import jack.me.sonweather.R;
import jack.me.sonweather.component.DaggerWeatherViewComponent;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.model.Air;
import jack.me.sonweather.model.Alarm;
import jack.me.sonweather.model.HourWeather;
import jack.me.sonweather.model.SevenDayWeather;
import jack.me.sonweather.model.Sun;
import jack.me.sonweather.model.Weather;
import jack.me.sonweather.module.WeatherViewModule;
import jack.me.sonweather.utils.TimeUtils;
import jack.me.sonweather.utils.WeatherUtils;

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

    private CommonAdapter<Weather> hourWeatherAdapter;

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
        showHourWeather(null);
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
        hourWeatherList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hourWeatherList.setAdapter(hourWeatherAdapter = new CommonAdapter<Weather>(this, R.layout.vh_hour_weather) {
            @Override
            public void bind(CommonViewHolder holder, Weather weather, int position) {
                if (position == 0) {
                    View view = holder.getView(R.id.rl_root);
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.dimen_16);
                } else if (position == hourWeatherAdapter.getItemCount() - 1) {
                    View view = holder.getView(R.id.rl_root);
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.dimen_16);
                }
                holder.setText(R.id.txt_time, TimeUtils.getTwelveHour(weather.getDate()));
                holder.setText(R.id.txt_temperature, weather.getDayTemperature() + "˚");
                holder.setText(R.id.icon_state, WeatherUtils.getIconOfWeather(weather.getDayWeather(), weather.getDate()));
            }
        });

        List<Weather> temp = new ArrayList<>();  // TODO: 2016/11/13 remove

        temp.add(Weather.builder().date("2016-03-09 18:00:00").dayWeather("多云").dayTemperature("4").build());
        temp.add(Weather.builder().date("2016-03-09 19:00:00").dayWeather("晴").dayTemperature("14").build());
        temp.add(Weather.builder().date("2016-03-09 20:00:00").dayWeather("雨").dayTemperature("6").build());
        temp.add(Weather.builder().date("2016-03-09 21:00:00").dayWeather("大雨").dayTemperature("24").build());
        temp.add(Weather.builder().date("2016-03-09 22:00:00").dayWeather("雷阵雨").dayTemperature("-4").build());
        temp.add(Weather.builder().date("2016-03-09 23:00:00").dayWeather("雾").dayTemperature("14").build());
        temp.add(Weather.builder().date("2016-03-09 24:00:00").dayWeather("阴").dayTemperature("34").build());
        temp.add(Weather.builder().date("2016-03-09 01:00:00").dayWeather("大雨").dayTemperature("2").build());
        temp.add(Weather.builder().date("2016-03-09 02:00:00").dayWeather("雪").dayTemperature("7").build());
        temp.add(Weather.builder().date("2016-03-09 03:00:00").dayWeather("雪").dayTemperature("8").build());
        temp.add(Weather.builder().date("2016-03-09 04:00:00").dayWeather("雪").dayTemperature("0").build());
        temp.add(Weather.builder().date("2016-03-09 05:00:00").dayWeather("晴").dayTemperature("-1").build());
        temp.add(Weather.builder().date("2016-03-09 06:00:00").dayWeather("雪").dayTemperature("-6").build());
        temp.add(Weather.builder().date("2016-03-09 07:00:00").dayWeather("雪").dayTemperature("10").build());
        temp.add(Weather.builder().date("2016-03-09 08:00:00").dayWeather("晴").dayTemperature("18").build());

        hourWeatherAdapter.addDatas(temp);
        hourWeatherAdapter.notifyDataSetChanged();
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
    public void showAlarm(Alarm alarm) {
        // TODO: 2016/11/12 to show alarm info
    }


}

































