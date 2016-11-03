package jack.me.sonweather;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import jack.me.sonweather.database.IDBHandler;

/**
 * Created by zjchai on 2016/10/31.
 */
public class SonApplication extends Application {

    public static final String TAG = SonApplication.class.getSimpleName();
    public static SonApplication INSTANCE;
    AppComponent appComponent;

    @Inject
    IDBHandler dbHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        INSTANCE = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
