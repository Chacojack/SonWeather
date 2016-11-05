package jack.me.sonweather.sp.framework;

/**
 * Created by zjchai on 2016/11/4.
 */

import android.content.SharedPreferences;

import java.util.Set;

public abstract class SharedPreferencesHelper {
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public final void clear() {
        SharedPreferencesCompat.apply(this.sharedPreferences.edit().clear());
    }

    protected IntPrefField intField(String key, int defaultValue) {
        return new IntPrefField(this.sharedPreferences, key, defaultValue);
    }

    protected StringPrefField stringField(String key, String defaultValue) {
        return new StringPrefField(this.sharedPreferences, key, defaultValue);
    }

    protected StringSetPrefField stringSetField(String key, Set<String> defaultValue) {
        return new StringSetPrefField(this.sharedPreferences, key, defaultValue);
    }

    protected BooleanPrefField booleanField(String key, boolean defaultValue) {
        return new BooleanPrefField(this.sharedPreferences, key, defaultValue);
    }

    protected FloatPrefField floatField(String key, float defaultValue) {
        return new FloatPrefField(this.sharedPreferences, key, defaultValue);
    }

    protected LongPrefField longField(String key, long defaultValue) {
        return new LongPrefField(this.sharedPreferences, key, defaultValue);
    }
}
