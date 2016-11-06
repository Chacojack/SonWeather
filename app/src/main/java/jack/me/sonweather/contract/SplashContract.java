package jack.me.sonweather.contract;

import java.util.List;

import jack.me.sonweather.model.City;

/**
 * Created by zjchai on 2016/11/5.
 */
public class SplashContract {

    public interface IView {
        void loadCitiesOver(List<City> cities);
    }

    public interface IPresenter {
        void loadCityCode();
    }
}