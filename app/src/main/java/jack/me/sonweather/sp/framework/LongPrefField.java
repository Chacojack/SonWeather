//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;

import jack.me.sonweather.sp.framework.AbstractPrefField;

public class LongPrefField extends AbstractPrefField {
    private final long defaultValue;

    LongPrefField(SharedPreferences sharedPreferences, String key, long defaultValue) {
        super(sharedPreferences, key);
        this.defaultValue = defaultValue;
    }

    public long get() {
        return this.getOr(this.defaultValue);
    }

    public long getOr(long defaultValue) {
        try {
            return this.sharedPreferences.getLong(this.key, defaultValue);
        } catch (ClassCastException var6) {
            try {
                String e2 = this.sharedPreferences.getString(this.key, "" + defaultValue);
                return Long.parseLong(e2);
            } catch (Exception var5) {
                throw var6;
            }
        }
    }

    public void put(long value) {
        this.apply(this.edit().putLong(this.key, value));
    }
}
