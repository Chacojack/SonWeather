package jack.me.sonweather.ui.activity;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import jack.me.sonweather.R;
import jack.me.sonweather.component.DaggerWeatherViewComponent;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.module.WeatherViewModule;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.IView {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    @Inject
    WeatherContract.IPresenter presenter;

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
}
