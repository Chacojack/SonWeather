package jack.me.sonweather.component;

import dagger.Component;
import jack.me.sonweather.module.WeatherViewModule;
import jack.me.sonweather.ui.activity.WeatherActivity;

/**
 * Created by zjchai on 2016/11/2.
 */
@Component(modules = WeatherViewModule.class)
public interface WeatherViewComponent {

    void inject(WeatherActivity weatherActivity);

}
