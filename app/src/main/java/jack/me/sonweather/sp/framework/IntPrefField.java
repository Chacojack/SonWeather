//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;

public class IntPrefField extends AbstractPrefField {
    private final int defaultValue;

    IntPrefField(SharedPreferences sharedPreferences, String key, int defaultValue) {
        super(sharedPreferences, key);
        this.defaultValue = defaultValue;
    }

    public int get() {
        return this.getOr(this.defaultValue);
    }

    public int getOr(int defaultValue) {
        try {
            return this.sharedPreferences.getInt(this.key, defaultValue);
        } catch (ClassCastException var5) {
            try {
                String e2 = this.sharedPreferences.getString(this.key, "" + defaultValue);
                return Integer.parseInt(e2);
            } catch (Exception var4) {
                throw var5;
            }
        }
    }

    public void put(int value) {
        this.apply(this.edit().putInt(this.key, value));
    }
}
