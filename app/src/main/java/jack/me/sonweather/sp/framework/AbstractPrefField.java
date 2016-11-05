//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class AbstractPrefField {
    protected final SharedPreferences sharedPreferences;
    protected final String key;

    public AbstractPrefField(SharedPreferences sharedPreferences, String key) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
    }

    public final boolean exists() {
        return this.sharedPreferences.contains(this.key);
    }

    public String key() {
        return this.key;
    }

    public final void remove() {
        this.apply(this.edit().remove(this.key));
    }

    protected Editor edit() {
        return this.sharedPreferences.edit();
    }

    protected final void apply(Editor editor) {
        SharedPreferencesCompat.apply(editor);
    }
}
