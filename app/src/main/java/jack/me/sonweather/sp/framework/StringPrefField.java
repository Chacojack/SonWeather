//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;

import jack.me.sonweather.sp.framework.AbstractPrefField;

public class StringPrefField extends AbstractPrefField {
    private final String defaultValue;

    StringPrefField(SharedPreferences sharedPreferences, String key, String defaultValue) {
        super(sharedPreferences, key);
        this.defaultValue = defaultValue;
    }

    public String get() {
        return this.getOr(this.defaultValue);
    }

    public String getOr(String defaultValue) {
        return this.sharedPreferences.getString(this.key, defaultValue);
    }

    public void put(String value) {
        this.apply(this.edit().putString(this.key, value));
    }
}
