package jack.me.sonweather.module;

import dagger.Module;
import dagger.Provides;
import jack.me.sonweather.contract.SplashContract;
import jack.me.sonweather.presenter.SplashPresenter;
import jack.me.sonweather.ui.activity.SplashActivity;

/**
 * Created by zjchai on 2016/11/5.
 */
@Module
public class SplashModule {

    private static final String TAG = SplashModule.class.getSimpleName();

    SplashActivity activity;

    public SplashModule(SplashActivity activity) {
        this.activity = activity;
    }

    @Provides
    public SplashContract.IPresenter providePersenter(){
        return new SplashPresenter(activity);
    }

}
