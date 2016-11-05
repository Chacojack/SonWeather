//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import java.util.Set;

import jack.me.sonweather.sp.framework.AbstractPrefEditorField;
import jack.me.sonweather.sp.framework.EditorHelper;
import jack.me.sonweather.sp.framework.SharedPreferencesCompat;

public final class StringSetPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    StringSetPrefEditorField(T editorHelper, String key) {
        super(editorHelper, key);
    }

    public T put(Set<String> value) {
        SharedPreferencesCompat.putStringSet(this.editorHelper.getEditor(), this.key, value);
        return this.editorHelper;
    }
}
