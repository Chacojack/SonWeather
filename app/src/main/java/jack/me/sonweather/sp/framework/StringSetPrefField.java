//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

import jack.me.sonweather.sp.framework.AbstractPrefField;
import jack.me.sonweather.sp.framework.SharedPreferencesCompat;

public class StringSetPrefField extends AbstractPrefField {
    private final Set<String> defaultValue;

    StringSetPrefField(SharedPreferences sharedPreferences, String key, Set<String> defaultValue) {
        super(sharedPreferences, key);
        this.defaultValue = defaultValue;
    }

    public Set<String> get() {
        return this.getOr(this.defaultValue);
    }

    public Set<String> getOr(Set<String> defaultValue) {
        return SharedPreferencesCompat.getStringSet(this.sharedPreferences, this.key, defaultValue);
    }

    public void put(Set<String> value) {
        Editor editor = this.sharedPreferences.edit();
        SharedPreferencesCompat.putStringSet(editor, this.key, value);
        this.apply(editor);
    }
}
