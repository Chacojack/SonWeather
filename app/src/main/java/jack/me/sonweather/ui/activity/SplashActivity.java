package jack.me.sonweather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import jack.me.sonweather.R;
import jack.me.sonweather.component.DaggerSplashComponent;
import jack.me.sonweather.contract.SplashContract;
import jack.me.sonweather.model.City;
import jack.me.sonweather.module.SplashModule;
import jack.me.sonweather.utils.LogUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity implements SplashContract.IView {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Inject
    SplashContract.IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DaggerSplashComponent.builder().splashModule(new SplashModule(this)).build().inject(this);

        afterViews();
    }

    private void afterViews() {
        presenter.loadCityCode();
    }

    @Override
    public void loadCitiesOver(List<City> cities) {
        Toast.makeText(this, "loadCitiesOver : cities size:" + cities.size(), Toast.LENGTH_SHORT).show();
        WeatherActivity.start(this);
        finish();
    }

}


























