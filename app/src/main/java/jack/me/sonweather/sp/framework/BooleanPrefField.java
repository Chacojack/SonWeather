//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;

public class BooleanPrefField extends AbstractPrefField {
    private final boolean defaultValue;

    BooleanPrefField(SharedPreferences sharedPreferences, String key, boolean defaultValue) {
        super(sharedPreferences, key);
        this.defaultValue = defaultValue;
    }

    public boolean get() {
        return this.getOr(this.defaultValue);
    }

    public boolean getOr(boolean defaultValue) {
        return this.sharedPreferences.getBoolean(this.key, defaultValue);
    }

    public void put(boolean value) {
        this.apply(this.edit().putBoolean(this.key, value));
    }
}
