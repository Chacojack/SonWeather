package jack.me.sonweather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import jack.me.sonweather.R;
import jack.me.sonweather.component.DaggerWeatherViewComponent;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.module.WeatherViewModule;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.IView{

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

    }

}
