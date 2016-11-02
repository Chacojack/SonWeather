package jack.me.sonweather.database;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import jack.me.sonweather.SonApplication;
import jack.me.sonweather.model.City;

/**
 * Created by zjchai on 2016/11/1.
 */

public class DBHandler implements IDBHandler {

    private DataBaseHelper dataBaseHelper;
    private Dao<City, Integer> cityBao;

    private static IDBHandler instance;

    private DBHandler(SonApplication a) {
        init(a);
    }

    public static IDBHandler getInstance(SonApplication a) {
        if (instance == null) {
            synchronized (DBHandler.class) {
                if (instance == null) {
                    instance = new DBHandler(a);
                }
            }
        }
        return instance;
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
    public int addCity(City city){
        try {
            return cityBao.create(city);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }



}
