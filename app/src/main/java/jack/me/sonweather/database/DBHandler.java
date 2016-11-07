package jack.me.sonweather.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.model.City;

/**
 * Created by zjchai on 2016/11/1.
 */

public class DBHandler implements IDBHandler {

    private DataBaseHelper dataBaseHelper;
    private Dao<City, Integer> cityBao;

    public DBHandler(SonApplication a) {
        init(a);
    }

    private void init(SonApplication application) {
        dataBaseHelper = DataBaseHelper.getInstance(application);
        try {
            cityBao = dataBaseHelper.getDao(City.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addCity(City city) {
        try {
            return cityBao.create(city);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public City getCityByName(String cityName) {
        try {
            List<City> cities = cityBao.queryForEq("cName", cityName);
            if (cities != null && cities.size() > 0) {
                return cities.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
}

























