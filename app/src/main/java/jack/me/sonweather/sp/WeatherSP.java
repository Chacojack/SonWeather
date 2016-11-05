package jack.me.sonweather.sp;

import android.content.Context;
import android.content.SharedPreferences;

import jack.me.sonweather.sp.framework.EditorHelper;
import jack.me.sonweather.sp.framework.SharedPreferencesHelper;

/**
 * Created by zjchai on 2016/11/5.
 */
public class WeatherSP extends SharedPreferencesHelper {

    private static final String TAG = WeatherSP.class.getSimpleName();

    public WeatherSP(Context context) {
        super(context.getSharedPreferences("WeatherSP", 0));
    }

    public WeatherSPEditor edit() {
        return new WeatherSPEditor(getSharedPreferences());
    }


    public final static class WeatherSPEditor
            extends EditorHelper<WeatherSPEditor> {

        WeatherSPEditor(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

    }

}