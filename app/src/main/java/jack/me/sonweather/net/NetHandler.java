package jack.me.sonweather.net;

import jack.me.sonweather.model.City;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zjchai on 2016/11/1.
 */

public class NetHandler implements INetHandler {

    public static final String TAG = NetHandler.class.getSimpleName();

    Retrofit retrofit;
    ISonNetService sonNetService;

    private static INetHandler instance;

    private NetHandler() {
        init();
    }

    public static INetHandler getInstance() {
        if (instance == null) {
            synchronized (NetHandler.class) {
                if (instance == null) {
                    instance = new NetHandler();
                }
            }
        }
        return instance;
    }


    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.yytianqi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        sonNetService = retrofit.create(ISonNetService.class);
    }

    @Override
    public Observable<City> getCityList() {
        return sonNetService.getCityList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
