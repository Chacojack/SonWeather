//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;

public final class FloatPrefField extends AbstractPrefField {
    private final float defaultValue;

    FloatPrefField(SharedPreferences sharedPreferences, String key, float defaultValue) {
        super(sharedPreferences, key);
        this.defaultValue = defaultValue;
    }

    public float get() {
        return this.getOr(this.defaultValue);
    }

    public float getOr(float defaultValue) {
        try {
            return this.sharedPreferences.getFloat(this.key, defaultValue);
        } catch (ClassCastException var5) {
            try {
                String e2 = this.sharedPreferences.getString(this.key, "" + defaultValue);
                return Float.parseFloat(e2);
            } catch (Exception var4) {
                throw var5;
            }
        }
    }

    public void put(float value) {
        this.apply(this.edit().putFloat(this.key, value));
    }
}
