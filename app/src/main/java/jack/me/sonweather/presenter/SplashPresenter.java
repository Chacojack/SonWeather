package jack.me.sonweather.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.contract.SplashContract;
import jack.me.sonweather.database.IDBHandler;
import jack.me.sonweather.model.City;
import jack.me.sonweather.net.INetHandler;
import jack.me.sonweather.sp.ISPHandler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zjchai on 2016/11/5.
 */
public class SplashPresenter implements SplashContract.IPresenter {

    private static final String TAG = SplashPresenter.class.getSimpleName();

    @Inject
    INetHandler netHandler;
    @Inject
    IDBHandler dbHandler;
    @Inject
    ISPHandler spHandler;

    SplashContract.IView view;

    public SplashPresenter(SplashContract.IView view) {
        this.view = view;
        SonApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    public void loadCityCode() {
        if (spHandler.isLoadedCities()) {
            view.loadCitiesOver(null);
        } else {
            netHandler.getCityList()
                    .subscribeOn(Schedulers.io())
                    .map(this::getCities)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.io())
                    .forEach(cities -> {
                        storeCities(cities);
                        AndroidSchedulers.mainThread().createWorker().schedule(() -> view.loadCitiesOver(cities));
                    });

        }
    }

    private void storeCities(List<City> cities) {
        for (City city : cities) {
            dbHandler.addCity(city);
        }
        spHandler.setLoadedCities(true);
    }

    @NonNull
    private List<City> getCities(City city) {
        List<City> result = new ArrayList<>();
        Stack<City> cityStack = new Stack<>();
        cityStack.push(city);
        while (!cityStack.isEmpty()) {
            // 处理单个City
            City temp = cityStack.pop();
            temp.transformChildren();
            result.add(temp);
            // 处理City下的子City
            List<City> cityList = temp.getCities();
            if (cityList != null) {
                for (City c : cityList) {
                    cityStack.push(c);
                }
            }
        }
        return result;
    }
}


































