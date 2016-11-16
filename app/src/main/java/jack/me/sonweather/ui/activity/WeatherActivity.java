package jack.me.sonweather.ui.activity;

import android.app.Application;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jack.me.fixdrawsizerelativelayoutlibrary.FixDrawSizeRelativeLayout;
import jack.me.nativetransitionlayoutlibrary.NativeTransitionLayout;
import jack.me.sonweather.R;
import jack.me.sonweather.model.ActualWeather;
import jack.me.sonweather.ui.BackgroundDrawable;
import jack.me.sonweather.ui.fregment.WeatherFragment;
import jack.me.sonweather.ui.view.AwesomeFontView;
import jack.me.sonweather.ui.view.CityListView;
import jack.me.sonweather.utils.LogUtils;
import jack.me.sonweather.utils.TimeUtils;
import jack.me.sonweather.utils.WeatherUtils;
import jack.me.viewpagerindicatorlibrary.ViewPagerIndicator;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = WeatherActivity.class.getSimpleName();


    WeatherFragmentPagerAdapter pagerAdapter;
    @BindView(R.id.view_pager_indicator)
    ViewPagerIndicator viewPagerIndicator;
    @BindView(R.id.icon_city_list)
    AwesomeFontView iconCityList;
    @BindView(R.id.navigation_bar_container)
    RelativeLayout navigationBarContainer;
    @BindView(R.id.view_pager_weather)
    ViewPager viewPagerWeather;
    @BindView(R.id.weather_view_pager_container)
    FixDrawSizeRelativeLayout weatherViewPagerContainer;

    @BindArray(R.array.bg_weather_colors)
    int[] color;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.img_background)
    ImageView imgBackground;
    @BindView(R.id.city_list_view)
    CityListView cityListView;

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

    }

    private void afterInject() {

    }

    private void afterViews() {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#00000000"));

        viewPagerIndicator.setViewPager(viewPagerWeather);
        viewPagerIndicator.setTabInjector((viewPagerIndicator, position) -> {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(viewPagerIndicator.getDefaultLayoutParams());
            if (position == 0) {
                imageView.setImageResource(R.drawable.bg_location_radio_in_weather);
            } else {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageResource(R.drawable.bg_radio_in_weather);
            }
            return imageView;
        });
        initViewPager();
        initBackGround();
        initCityListView();

        weatherViewPagerContainer.setBackgroundColor(color[0]);
    }

    private void initCityListView() {
        cityListView
                .init(R.layout.vh_city, viewPagerWeather, weatherViewPagerContainer)
                .setOnBindDataListener((view, actualWeather, position) -> {
                    TextView name = (TextView) view.findViewById(R.id.txt_city_name);
                    name.setText(actualWeather.getCityName());
                    TextView temp = (TextView) view.findViewById(R.id.txt_temperature);
                    temp.setText(WeatherUtils.getTemperatureString(Integer.parseInt(actualWeather.getDayTemperature())));
                    TextView time = (TextView) view.findViewById(R.id.txt_time);
                    time.setText(TimeUtils.getTwelveHour(actualWeather.getLastUpdate()));
                    view.setBackgroundColor(WeatherUtils.getWeatherBackgroundColor(actualWeather.getDayWeather()));
                    if (position == 0) {
                        view.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.font_size_100);
                    }
                });

        List<ActualWeather> list = new ArrayList<>();
        list.add(ActualWeather.mBuilder().cityName("北京市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("南京市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("上海市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("保定市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("深圳市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("天津市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("长沙市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("昆明市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("株洲市").lastUpdate("2016-03-09 17:10:00").build());
        list.add(ActualWeather.mBuilder().cityName("武汉市").lastUpdate("2016-03-09 17:10:00").build());

        String[] weather = new String[]{"多云","晴","霾","多云","阴","雪","雨","大雨","雷阵雨","雨"};
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDayTemperature(String.valueOf(i+10));
            list.get(i).setDayWeather(weather[i]);
        }

        cityListView.addAllData(list);
        cityListView.notifyRefreshAllList();

    }

    private void initBackGround() {
        Drawable drawable = WallpaperManager.getInstance(this).getFastDrawable();
        imgBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgBackground.setImageDrawable(new BackgroundDrawable(drawable, 0xb2000000));
    }

    private void initViewPager() {
        viewPagerWeather.setAdapter(pagerAdapter = new WeatherFragmentPagerAdapter(getSupportFragmentManager()));
        viewPagerWeather.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                weatherViewPagerContainer.setBackgroundColor(color[position]);
            }
        });

        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());

        pagerAdapter.notifyDataSetChanged();
        viewPagerWeather.setCurrentItem(0, false);

    }

    @OnClick(R.id.icon_city_list)
    public void onCityListIconClick() {
        int currentItem = viewPagerWeather.getCurrentItem();
        cityListView.startShrinkTransition(currentItem);
    }


    class WeatherFragmentPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<>();

        public WeatherFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment) {
            if (fragments.contains(fragment)) {
                return;
            }
            fragments.add(fragment);
        }

        public void removeFragment(Fragment fragment) {
            if (fragments.contains(fragment)) {
                fragments.remove(fragment);
            }
        }

    }


}

































