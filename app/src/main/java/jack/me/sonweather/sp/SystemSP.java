package jack.me.sonweather.sp;

import android.content.Context;
import android.content.SharedPreferences;

import jack.me.sonweather.sp.framework.BooleanPrefField;
import jack.me.sonweather.sp.framework.EditorHelper;
import jack.me.sonweather.sp.framework.SharedPreferencesHelper;

/**
 * Created by zjchai on 2016/11/5.
 */
public class SystemSP extends SharedPreferencesHelper {

    private static final String TAG = SystemSP.class.getSimpleName();

    public SystemSP(Context context) {
        super(context.getSharedPreferences("SystemSP", 0));
    }

    public SystemSPEditor edit() {
        return new SystemSPEditor(getSharedPreferences());
    }

    public BooleanPrefField loadedCities(){
        return booleanField("loadedCities",false);
    }

    public final static class SystemSPEditor
            extends EditorHelper<SystemSPEditor> {

        SystemSPEditor(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

    }

}