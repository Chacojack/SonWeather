package jack.me.sonweather;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jack.me.sonweather.database.DBHandler;
import jack.me.sonweather.database.IDBHandler;
import jack.me.sonweather.net.INetHandler;
import jack.me.sonweather.net.NetHandler;

/**
 * Created by zjchai on 2016/11/1.
 */
@Module
public class AppModule {

    SonApplication application;

    public AppModule(SonApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public SonApplication provideSonApplication(){
        return application;
    }

    @Singleton
    @Provides
    public INetHandler provideNetHandler(){
        return NetHandler.getInstance();
    }

    @Singleton
    @Provides
    public IDBHandler provideDBHandler(){
        return DBHandler.getInstance(application);
    }

}
