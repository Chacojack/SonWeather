package jack.me.sonweather;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by zjchai on 2016/10/31.
 */
public class SonApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
