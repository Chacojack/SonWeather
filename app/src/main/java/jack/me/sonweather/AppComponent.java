package jack.me.sonweather;

import javax.inject.Singleton;

import dagger.Component;
import jack.me.sonweather.database.IDBHandler;
import jack.me.sonweather.net.INetHandler;

/**
 * Created by zjchai on 2016/11/1.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    INetHandler getNetHandler();

    IDBHandler getDBHandler();

}
