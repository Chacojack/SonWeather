package jack.me.sonweather.component;

import dagger.Component;
import jack.me.sonweather.module.SplashModule;
import jack.me.sonweather.ui.activity.SplashActivity;

/**
 * Created by zjchai on 2016/11/5.
 */
@Component(modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity splashActivity);



}
