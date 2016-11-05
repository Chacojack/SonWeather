package jack.me.sonweather.sp;

import android.content.Context;
import android.content.SharedPreferences;

import jack.me.sonweather.sp.framework.EditorHelper;
import jack.me.sonweather.sp.framework.SharedPreferencesHelper;
import jack.me.sonweather.sp.framework.StringPrefEditorField;
import jack.me.sonweather.sp.framework.StringPrefField;

/**
 * Created by zjchai on 2016/11/4.
 */

public class LocationSP extends SharedPreferencesHelper {

    public LocationSP(Context context) {
        super(context.getSharedPreferences("LocationSP", 0));
    }

    public LocationSPEditor edit() {
        return new LocationSPEditor(getSharedPreferences());
    }

    public StringPrefField location(){
        return stringField("location","");
    }


    public final static class LocationSPEditor
            extends EditorHelper<LocationSPEditor> {

        LocationSPEditor(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

        public StringPrefEditorField<LocationSPEditor> location(){
            return stringField("location");
        }

    }

}
