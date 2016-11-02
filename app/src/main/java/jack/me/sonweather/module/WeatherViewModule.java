package jack.me.sonweather.module;

import dagger.Module;
import dagger.Provides;
import jack.me.sonweather.contract.WeatherContract;
import jack.me.sonweather.presenter.WeatherPresenter;

/**
 * Created by zjchai on 2016/11/2.
 */
@Module
public class WeatherViewModule {

    private WeatherContract.IView view;

    public WeatherViewModule(WeatherContract.IView view) {
        this.view = view;
    }

    @Provides
    WeatherContract.IPresenter provideWeatherPresenter(){
        return new WeatherPresenter(view);
    }

}
