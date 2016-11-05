//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class EditorHelper<T extends EditorHelper<T>> {
    private final Editor editor;

    public EditorHelper(SharedPreferences sharedPreferences) {
        this.editor = sharedPreferences.edit();
    }

    protected Editor getEditor() {
        return this.editor;
    }

    public final T clear() {
        this.editor.clear();
        return this.cast();
    }

    public final void apply() {
        SharedPreferencesCompat.apply(this.editor);
    }

    protected IntPrefEditorField<T> intField(String key) {
        return new IntPrefEditorField(this.cast(), key);
    }

    protected StringPrefEditorField<T> stringField(String key) {
        return new StringPrefEditorField(this.cast(), key);
    }

    protected StringSetPrefEditorField<T> stringSetField(String key) {
        return new StringSetPrefEditorField(this.cast(), key);
    }

    protected BooleanPrefEditorField<T> booleanField(String key) {
        return new BooleanPrefEditorField(this.cast(), key);
    }

    protected FloatPrefEditorField<T> floatField(String key) {
        return new FloatPrefEditorField(this.cast(), key);
    }

    protected LongPrefEditorField<T> longField(String key) {
        return new LongPrefEditorField(this.cast(), key);
    }

    private T cast() {
        return (T) this;
    }
}
