package jack.me.sonweather.ui.fregment;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import jack.me.sonweather.model.WeatherIndex;
import jack.me.sonweather.model.WeatherIndexs;
import jack.me.sonweather.module.WeatherViewModule;
import jack.me.sonweather.utils.TimeUtils;
import jack.me.sonweather.utils.WeatherUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements WeatherContract.IView {


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
    @BindView(R.id.txt_sun_rise_time)
    TextView txtSunRiseTime;
    @BindView(R.id.txt_sun_set_time)
    TextView txtSunSetTime;
    @BindView(R.id.weather_index_list)
    RecyclerView weatherIndexList;

    private CommonAdapter<Weather> hourWeatherAdapter;
    private CommonAdapter<Weather> weekWeatherAdapter;
    private CommonAdapter<WeatherIndex> weatherIndexAdapter;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initExtra(savedInstanceState);
        inject();
        afterInject();
        ButterKnife.bind(this, view);
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
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#00000000"));
//        presenter.loadCurrentLocationWeather();
        showHourWeather(null);
        showWeekWeather(null);
        showWeatherIndex(null);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            presenter.checkPermissions(getActivity());
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
        hourWeatherList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hourWeatherList.setAdapter(hourWeatherAdapter = new CommonAdapter<Weather>(getContext(), R.layout.vh_hour_weather) {
            @Override
            public void bind(CommonViewHolder holder, Weather weather, int position) {
                View view = holder.getView(R.id.rl_root);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (position == 0) {
                    layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.dimen_16);
                    layoutParams.rightMargin = 0;
                } else if (position == hourWeatherAdapter.getItemCount() - 1) {
                    layoutParams.leftMargin = 0;
                    layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.dimen_16);
                } else {
                    layoutParams.leftMargin = 0;
                    layoutParams.rightMargin = 0;
                }
                holder.setText(R.id.txt_time, TimeUtils.getTwelveHour(weather.getDate()));
                holder.setText(R.id.txt_temperature, WeatherUtils.getTemperatureString(Integer.parseInt(weather.getDayTemperature())));
                String iconOfWeather = WeatherUtils.getIconOfWeather(weather.getDayWeather(), weather.getDate());
                holder.setText(R.id.icon_state, iconOfWeather);
                holder.setTextColor(R.id.icon_state, WeatherUtils.getWeatherIconColor(iconOfWeather));
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
        weekWeatherList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        weekWeatherList.setAdapter(weekWeatherAdapter = new CommonAdapter<Weather>(getContext(), R.layout.vh_week_weather) {
            @Override
            public void bind(CommonViewHolder holder, Weather weather, int position) {
                holder.setText(R.id.txt_week_name, TimeUtils.getWeekDayName(weather.getDate()));
                holder.setText(R.id.txt_low_temperature, weather.getNightTemperature());
                holder.setText(R.id.txt_high_temperature, weather.getDayTemperature());
                String iconOfWeather = WeatherUtils.getIconOfWeather(weather.getDayWeather(), weather.getDate());
                holder.setText(R.id.icon_state, iconOfWeather);
                holder.setTextColor(R.id.icon_state, WeatherUtils.getWeatherIconColor(iconOfWeather));
            }
        });

        List<Weather> list = new ArrayList<>();
        list.add(Weather.builder().date("2016-03-09").dayWeather("多云").dayTemperature("6").nightTemperature("-2").build());
        list.add(Weather.builder().date("2016-03-10").dayWeather("晴").dayTemperature("24").nightTemperature("-12").build());
        list.add(Weather.builder().date("2016-03-11").dayWeather("雨").dayTemperature("30").nightTemperature("23").build());
        list.add(Weather.builder().date("2016-03-12").dayWeather("阴").dayTemperature("35").nightTemperature("-12").build());
        list.add(Weather.builder().date("2016-03-13").dayWeather("雪").dayTemperature("22").nightTemperature("2").build());
        list.add(Weather.builder().date("2016-03-14").dayWeather("雾").dayTemperature("10").nightTemperature("-5").build());
        list.add(Weather.builder().date("2016-03-15").dayWeather("雷阵雨").dayTemperature("20").nightTemperature("-9").build());

        weekWeatherAdapter.addDatas(list);
        weekWeatherAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSun(Sun sun) {
        // TODO: 2016/11/12 to show sun

    }


    @Override
    public void showAir(Air air) {
        // TODO: 2016/11/12 to show air info
    }


    @Override
    public void showAlarm(Alarm alarm) {
        // TODO: 2016/11/12 to show alarm info
    }

    @Override
    public void showWeatherIndex(WeatherIndexs weatherIndexs) {
        // TODO: 2016/11/13 show weather Index
        weatherIndexList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        weatherIndexList.setAdapter(weatherIndexAdapter = new CommonAdapter<WeatherIndex>(getContext(), R.layout.vh_weather_index) {
            @Override
            public void bind(CommonViewHolder holder, WeatherIndex weatherIndex, int position) {
                holder.setText(R.id.txt_weather_index_name, weatherIndex.getType());
                holder.setText(R.id.txt_weather_index_info, weatherIndex.getShortDesc());
            }
        });

        List<WeatherIndex> list = new ArrayList<>();
        list.add(WeatherIndex.builder().type("体感温度：").shortDesc("偏冷").build());
        list.add(WeatherIndex.builder().type("感冒：").shortDesc("很容易发生").build());
        list.add(WeatherIndex.builder().type("穿衣：").shortDesc("毛衣类衣物").build());
        list.add(WeatherIndex.builder().type("运动：").shortDesc("不适宜").build());
        list.add(WeatherIndex.builder().type("紫外线：").shortDesc("最弱").build());
        list.add(WeatherIndex.builder().type("空调：").shortDesc("适合制热").build());

        weatherIndexAdapter.addDatas(list);
        weatherIndexAdapter.notifyDataSetChanged();

    }


}
