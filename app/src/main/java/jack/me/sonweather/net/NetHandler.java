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

    public NetHandler() {
        init();
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
        return sonNetService.getCityList();
    }


}
