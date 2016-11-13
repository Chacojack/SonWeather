package jack.me.sonweather.ui.activity;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jack.me.sonweather.R;
import jack.me.sonweather.ui.fregment.WeatherFragment;
import jack.me.sonweather.ui.view.AwesomeFontView;
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
            if (position==0) {
                imageView.setImageResource(R.drawable.bg_location_radio_in_weather);
            } else {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageResource(R.drawable.bg_radio_in_weather);
            }
            return imageView;
        });
        initViewPager();
    }

    private void initViewPager() {
        viewPagerWeather.setAdapter(pagerAdapter = new WeatherFragmentPagerAdapter(getSupportFragmentManager()));

        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());
        pagerAdapter.addFragment(new WeatherFragment());

        pagerAdapter.notifyDataSetChanged();

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

































