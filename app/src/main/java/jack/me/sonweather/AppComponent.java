package jack.me.sonweather;

import javax.inject.Singleton;

import dagger.Component;
import jack.me.sonweather.database.IDBHandler;
import jack.me.sonweather.net.INetHandler;
import jack.me.sonweather.presenter.SplashPresenter;
import jack.me.sonweather.presenter.WeatherPresenter;

/**
 * Created by zjchai on 2016/11/1.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(SonApplication application);

    void inject(WeatherPresenter presenter);

    void inject(SplashPresenter splashPresenter);
}
