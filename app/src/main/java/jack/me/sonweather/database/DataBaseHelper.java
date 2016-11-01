package jack.me.sonweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import jack.me.sonweather.model.City;

/**
 * Created by zjchai on 2016/10/31.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "son_weather.db";

    private static DataBaseHelper instance;

    private DataBaseHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, VERSION);
    }

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null) {
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, City.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
